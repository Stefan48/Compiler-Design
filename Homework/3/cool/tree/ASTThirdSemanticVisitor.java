package cool.tree;

import cool.structures.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

public class ASTThirdSemanticVisitor implements ASTVisitor<Type> {
    Scope currentScope;

    @Override
    public Type visit(ProgramNode programNode) {
        for (ClassDefNode classDefNode : programNode.classDefs) {
            classDefNode.accept(this);
        }
        return null;
    }

    @Override
    public Type visit(ClassDefNode classDefNode) {
        if (classDefNode.type == null) {
            return null;
        }
        currentScope = classDefNode.type;
        for (DefinitionNode member : classDefNode.members) {
            member.accept(this);
        }
        currentScope = currentScope.getParent();
        return null;
    }

    @Override
    public Type visit(FuncDefNode funcDefNode) {
        FunctionSymbol functionSymbol = funcDefNode.functionSymbol;
        if (functionSymbol == null) {
            return null;
        }
        String functionName = functionSymbol.getName();
        Type returnType = functionSymbol.getReturnType();
        String returnTypeName = returnType.getName();
        String className = ((Type)currentScope).getName();
        FunctionSymbol parentFunction = currentScope.getParent().lookupFunction(functionName);
        if (parentFunction != null) {
            Type parentFunctionType = parentFunction.getReturnType();
            if (returnType != parentFunctionType) {
                SymbolTable.error(funcDefNode.context, funcDefNode.type, "Class " + className + " overrides method " + functionName +
                        " but changes return type from " + parentFunctionType.getName() + " to " + returnTypeName);
                return null;
            }
            LinkedHashMap<String, VariableSymbol> functionParams = functionSymbol.getVariableSymbols();
            LinkedHashMap<String, VariableSymbol> parentFunctionParams = parentFunction.getVariableSymbols();
            if (functionParams.size() != parentFunctionParams.size()) {
                SymbolTable.error(funcDefNode.context, funcDefNode.name, "Class " + className + " overrides method " + functionName +
                        " with different number of formal parameters");
                return null;
            }
            List<VariableSymbol> functionParamsList = new ArrayList<>(functionParams.values());
            List<VariableSymbol> parentFunctionParamsList = new ArrayList<>(parentFunctionParams.values());
            for (int i = 0; i < functionParamsList.size(); ++i) {
                Type paramType = functionParamsList.get(i).getType();
                Type parentParamType = parentFunctionParamsList.get(i).getType();
                if (paramType != null && parentParamType != null) {
                    if (!paramType.isDescendantOf(parentParamType)) {
                        SymbolTable.error(funcDefNode.context, funcDefNode.params.get(i).type, "Class " + className +
                                " overrides method " + functionName + " but changes type of formal parameter " + functionParamsList.get(i).getName() +
                                " from " + parentParamType.getName() + " to " + paramType.getName());
                        return null;
                    }
                }
            }
        }
        currentScope = functionSymbol;
        Type bodyType = funcDefNode.body.accept(this);
        if (bodyType != null && bodyType != returnType) {
            bodyType = bodyType.dynamicType(currentScope);
            if (!bodyType.isDescendantOf(returnType)) {
                SymbolTable.error(funcDefNode.context, funcDefNode.body.context.start, "Type " + bodyType.getName() + " of the body of method " +
                        functionName + " is incompatible with declared return type " + returnTypeName);
            }
        }
        currentScope = currentScope.getParent();
        return null;
    }

    @Override
    public Type visit(VarDefNode varDefNode) {
        VariableSymbol variableSymbol = varDefNode.variableSymbol;
        if (variableSymbol == null) {
            return null;
        }
        Type dynamicType = variableSymbol.getType().dynamicType(currentScope);
        if (varDefNode.expr != null) {
            Type initType = varDefNode.expr.accept(this);
            Type initDynamicType = initType.dynamicType(currentScope);
            if (!initDynamicType.isDescendantOf(dynamicType)) {
                SymbolTable.error(varDefNode.context, varDefNode.expr.context.start, "Type " + initType.getName() +
                        " of initialization expression of attribute " + variableSymbol.getName() +
                        " is incompatible with declared type " + dynamicType.getName());
            }
        }
        return dynamicType;
    }

    @Override
    public Type visit(LocalVarDefNode localVarDefNode) {
        VariableSymbol variableSymbol = localVarDefNode.variableSymbol;
        if (variableSymbol == null) {
            return null;
        }
        String variableName = variableSymbol.getName();
        String typeName = localVarDefNode.type.getText();
        Type type = SymbolTable.types.get(typeName);
        if (type == null) {
            localVarDefNode.variableSymbol = null;
            SymbolTable.error(localVarDefNode.context, localVarDefNode.type, "Let variable " + variableName + " has undefined type " + typeName);
            return null;
        }
        if (localVarDefNode.expr != null) {
            Type dynamicType = type.dynamicType(currentScope);
            Type initType = localVarDefNode.expr.accept(this);
            if (initType != null && !initType.isDescendantOf(dynamicType)) {
                SymbolTable.error(localVarDefNode.context, localVarDefNode.expr.context.start, "Type " + initType.getName() +
                        " of initialization expression of identifier " + variableName + " is incompatible with declared type " + dynamicType.getName());
                return null;
            }
        }
        localVarDefNode.variableSymbol.setType(type);
        return null;
    }

    @Override
    public Type visit(FormalNode formalNode) {
        return null;
    }

    @Override
    public Type visit(CallNode callNode) {
        Type callerType, callerDynamicType;
        if (callNode.caller != null) {
            callerType = callNode.caller.accept(this);
            callerDynamicType = callerType.dynamicType(currentScope);
        }
        else {
            callerType = Type.SELF_TYPE.dynamicType(currentScope);
            callerDynamicType = callerType;
        }
        if (callNode.type != null) {
            String typeName = callNode.type.getText();
            if (typeName.equals("SELF_TYPE")) {
                SymbolTable.error(callNode.context, callNode.type, "Type of static dispatch cannot be SELF_TYPE");
                return Type.OBJECT;
            }
            Type type = SymbolTable.types.get(typeName);
            if (type == null) {
                SymbolTable.error(callNode.context, callNode.type, "Type " + typeName + " of static dispatch is undefined");
                return Type.OBJECT;
            }
            if (!callerDynamicType.isDescendantOf(type)) {
                SymbolTable.error(callNode.context, callNode.type, "Type " + typeName + " of static dispatch is not a superclass of type " +
                        callerDynamicType.getName());
                return Type.OBJECT;
            }
            callerDynamicType = type;
        }
        String className = callerDynamicType.getName();
        String functionName = callNode.func.getText();
        FunctionSymbol functionSymbol = callerDynamicType.lookupFunction(functionName);
        if (functionSymbol == null) {
            SymbolTable.error(callNode.context, callNode.func, "Undefined method " + functionName + " in class " + className);
            return Type.OBJECT;
        }
        if (callNode.params.size() != functionSymbol.getVariableSymbols().size()) {
            SymbolTable.error(callNode.context, callNode.func, "Method " + functionName + " of class " + className +
                    " is applied to wrong number of arguments");
            return Type.OBJECT;
        }
        List<Type> paramsTypes = callNode.params.stream().map(param -> param.accept(this)).collect(Collectors.toList());
        List<VariableSymbol> paramsList = new ArrayList<>(functionSymbol.getVariableSymbols().values());
        for (int i = 0; i < paramsTypes.size(); ++i) {
            if (!paramsTypes.get(i).isDescendantOf(paramsList.get(i).getType())) {
                SymbolTable.error(callNode.context, callNode.params.get(i).context.start, "In call to method " + functionName +
                        " of class " + className + ", actual type " + paramsTypes.get(i).getName() + " of formal parameter " +
                        paramsList.get(i).getName() + " is incompatible with declared type " + paramsList.get(i).getType().getName());
            }
        }
        return (functionSymbol.getReturnType() == Type.SELF_TYPE) ? callerType : functionSymbol.getReturnType();
    }

    @Override
    public Type visit(IfNode ifNode) {
        Type condType = ifNode.cond.accept(this);
        if (condType != Type.BOOL) {
            SymbolTable.error(ifNode.context, ifNode.cond.context.start, "If condition has type " + condType.getName() + " instead of Bool");
        }
        return Type.lowestCommonAncestor(ifNode.thenBranch.accept(this), ifNode.elseBranch.accept(this));
    }

    @Override
    public Type visit(WhileNode whileNode) {
        Type condType = whileNode.cond.accept(this);
        if (condType != Type.BOOL) {
            SymbolTable.error(whileNode.context, whileNode.cond.context.start, "While condition has type " + condType.getName() + " instead of Bool");
        }
        return Type.OBJECT;
    }

    @Override
    public Type visit(LetNode letNode) {
        currentScope = letNode.scope;
        for (LocalVarDefNode localVarDef : letNode.localVarDefs) {
            localVarDef.accept(this);
            if (localVarDef.variableSymbol != null) {
                currentScope.addVariable(localVarDef.variableSymbol);
            }
        }
        Type returnType = letNode.body.accept(this);
        currentScope = currentScope.getParent();
        return returnType;
    }

    @Override
    public Type visit(CaseBranchNode caseBranchNode) {
        String typeName = caseBranchNode.type.getText();
        if (typeName.equals("SELF_TYPE")) {
            return null;
        }
        String variableName = caseBranchNode.token.getText();
        Type type = SymbolTable.types.get(typeName);
        if (type == null) {
            SymbolTable.error(caseBranchNode.context, caseBranchNode.type, "Case variable " + variableName + " has undefined type " + typeName);
            return null;
        }
        Scope scope = new Scope(currentScope);
        VariableSymbol variableSymbol = new VariableSymbol(variableName, type);
        scope.addVariable(variableSymbol);
        currentScope = scope;
        Type returnType = caseBranchNode.res.accept(this);
        currentScope = currentScope.getParent();
        return returnType;
    }

    @Override
    public Type visit(CaseNode caseNode) {
        List<Type> branchesTypes = new ArrayList<>();
        for (int i = 0; i < caseNode.branches.size(); ++i) {
            Type type = caseNode.branches.get(i).accept(this);
            if (type != null) {
                branchesTypes.add(type);
            }
        }
        if (branchesTypes.isEmpty()) {
            return null;
        }
        Type returnType = branchesTypes.get(0);
        for (int i = 1; i < branchesTypes.size(); ++i) {
            returnType = Type.lowestCommonAncestor(returnType, branchesTypes.get(i));
        }
        return returnType;
    }

    @Override
    public Type visit(IsVoidNode isVoidNode) {
        return Type.BOOL;
    }

    @Override
    public Type visit(NewNode newNode) {
        String typeName = newNode.token.getText();
        Type type = SymbolTable.types.get(typeName);
        if (type == null) {
            SymbolTable.error(newNode.context, newNode.token, "new is used with undefined type " + typeName);
            return null;
        }
        return type;
    }

    @Override
    public Type visit(BlockNode blockNode) {
        Type returnType = null;
        for (int i = 0; i < blockNode.expressions.size(); ++i) {
            returnType = blockNode.expressions.get(i).accept(this);
        }
        return returnType;
    }

    @Override
    public Type visit(NegNode negNode) {
        Type type = negNode.expr.accept(this);
        if (type != Type.INT) {
            SymbolTable.error(negNode.context, negNode.expr.context.start, "Operand of ~ has type " + type.getName() + " instead of Int");
        }
        return Type.INT;
    }

    @Override
    public Type visit(IdNode idNode) {
        String variableName = idNode.token.getText();
        VariableSymbol variableSymbol = currentScope.lookupVariable(variableName);
        if (variableSymbol == null) {
            SymbolTable.error(idNode.context, idNode.context.start, "Undefined identifier " + variableName);
            return null;
        }
        return variableSymbol.getType();
    }

    @Override
    public Type visit(BoolNode boolNode) {
        return Type.BOOL;
    }

    @Override
    public Type visit(IntNode intNode) {
        return Type.INT;
    }

    @Override
    public Type visit(StringNode stringNode) {
        return Type.STRING;
    }

    @Override
    public Type visit(ArithmeticNode arithmeticNode) {
        Type type = arithmeticNode.leftExpr.accept(this);
        if (type != null && type != Type.INT) {
            SymbolTable.error(arithmeticNode.context, arithmeticNode.leftExpr.context.start, "Operand of " + arithmeticNode.token.getText() +
                    " has type " + type.getName() + " instead of Int");
        }
        type = arithmeticNode.rightExpr.accept(this);
        if (type != null && type != Type.INT) {
            SymbolTable.error(arithmeticNode.context, arithmeticNode.rightExpr.context.start, "Operand of " + arithmeticNode.token.getText() +
                    " has type " + type.getName() + " instead of Int");
        }
        return Type.INT;
    }

    @Override
    public Type visit(RelNode relNode) {
        Type leftType = relNode.leftExpr.accept(this);
        Type rightType = relNode.rightExpr.accept(this);
        if (leftType == null || rightType == null) {
            return null;
        }
        String operator = relNode.token.getText();
        if (operator.equals("=")) {
            if (((leftType == Type.BOOL || leftType == Type.INT || leftType == Type.STRING) ||
                    (rightType == Type.BOOL || rightType == Type.INT || rightType == Type.STRING)) && leftType != rightType) {
                SymbolTable.error(relNode.context, relNode.token, "Cannot compare " + leftType.getName() + " with " + rightType.getName());
            }
        }
        else {
            if (leftType != Type.INT) {
                SymbolTable.error(relNode.context, relNode.leftExpr.context.start, "Operand of " + operator + " has type " +
                        leftType.getName() + " instead of Int");
            }
            if (rightType != Type.INT) {
                SymbolTable.error(relNode.context, relNode.rightExpr.context.start, "Operand of " + operator + " has type " +
                        rightType.getName() + " instead of Int");
            }
        }
        return Type.BOOL;
    }

    @Override
    public Type visit(AssignNode assignNode) {
        String variableName = assignNode.token.getText();
        if (variableName.equals("self")) {
            SymbolTable.error(assignNode.context, assignNode.token, "Cannot assign to self");
            return null;
        }
        VariableSymbol variableSymbol = currentScope.lookupVariable(variableName);
        Type type = variableSymbol.getType();
        Type initType = assignNode.expr.accept(this);
        if (type == null || initType == null) {
            return null;
        }
        Type dynamicType = type.dynamicType(currentScope);
        Type initDynamicType = initType.dynamicType(currentScope);
        if (!initDynamicType.isDescendantOf(dynamicType)) {
            SymbolTable.error(assignNode.context, assignNode.expr.context.start, "Type " + initType.getName() +
                    " of assigned expression is incompatible with declared type " + type.getName() + " of identifier " + variableName);
        }
        return initType;
    }

    @Override
    public Type visit(NotNode notNode) {
        Type type = notNode.expr.accept(this);
        if (type != Type.BOOL) {
            SymbolTable.error(notNode.context, notNode.expr.context.start, "Operand of not has type " + type.getName() + " instead of Bool");
        }
        return Type.BOOL;
    }
}