package cool.tree;

import cool.structures.*;

public class ASTFirstSemanticVisitor implements ASTVisitor<Void> {
    Scope currentScope;

    @Override
    public Void visit(ProgramNode programNode) {
        for (ClassDefNode classDefNode : programNode.classDefs) {
            classDefNode.accept(this);
        }
        return null;
    }

    @Override
    public Void visit(ClassDefNode classDefNode) {
        String className = classDefNode.name.getText();
        if (className.equals("SELF_TYPE")) {
            SymbolTable.error(classDefNode.context, classDefNode.name, "Class has illegal name SELF_TYPE");
            return null;
        }
        String parentName = (classDefNode.parentName == null) ? "Object" : classDefNode.parentName.getText();
        Type type = new Type(className, parentName);
        if (!SymbolTable.addType(type)) {
            SymbolTable.error(classDefNode.context, classDefNode.name, "Class " + className + " is redefined");
            return null;
        }
        if (parentName.equals("Bool") || parentName.equals("Int") || parentName.equals("String") || parentName.equals("SELF_TYPE")) {
            SymbolTable.error(classDefNode.context, classDefNode.parentName, "Class " + className + " has illegal parent " + parentName);
            return null;
        }
        classDefNode.type = type;
        currentScope = type;
        for (DefinitionNode member : classDefNode.members) {
            member.accept(this);
        }
        currentScope = currentScope.getParent();
        return null;
    }

    @Override
    public Void visit(FuncDefNode funcDefNode) {
        String functionName = funcDefNode.name.getText();
        FunctionSymbol functionSymbol = new FunctionSymbol(functionName, currentScope);
        if (!currentScope.addFunction(functionSymbol)) {
            SymbolTable.error(funcDefNode.context, funcDefNode.name, "Class " + ((Type)currentScope).getName() + " redefines method " + functionName);
            return null;
        }
        funcDefNode.functionSymbol = functionSymbol;
        currentScope = functionSymbol;
        for (FormalNode param : funcDefNode.params) {
            param.accept(this);
        }
        funcDefNode.body.accept(this);
        currentScope = currentScope.getParent();
        return null;
    }

    @Override
    public Void visit(VarDefNode varDefNode) {
        String variableName = varDefNode.name.getText();
        if (variableName.equals("self")) {
            SymbolTable.error(varDefNode.context, varDefNode.name, "Class " + ((Type)currentScope).getName() + " has attribute with illegal name self");
            return null;
        }
        VariableSymbol variableSymbol = new VariableSymbol(variableName);
        if (!currentScope.addVariable(variableSymbol)) {
            SymbolTable.error(varDefNode.context, varDefNode.name, "Class " + ((Type)currentScope).getName() + " redefines attribute " + variableName);
            return null;
        }
        varDefNode.variableSymbol = variableSymbol;
        if (varDefNode.expr != null) {
            varDefNode.expr.accept(this);
        }
        return null;
    }

    @Override
    public Void visit(LocalVarDefNode localVarDefNode) {
        String variableName = localVarDefNode.name.getText();
        if (variableName.equals("self")) {
            SymbolTable.error(localVarDefNode.context, localVarDefNode.name, "Let variable has illegal name self");
            return null;
        }
        localVarDefNode.variableSymbol = new VariableSymbol(variableName);
        if (localVarDefNode.expr != null) {
            localVarDefNode.expr.accept(this);
        }
        return null;
    }

    @Override
    public Void visit(FormalNode formalNode) {
        String variableName = formalNode.name.getText();
        String functionName = ((FunctionSymbol)currentScope).getName();
        String className = ((Type)currentScope.getParent()).getName();
        if (variableName.equals("self")) {
            SymbolTable.error(formalNode.context, formalNode.name, "Method " + functionName + " of class " + className +
                    " has formal parameter with illegal name self");
            return null;
        }
        if (formalNode.type.getText().equals("SELF_TYPE")) {
            SymbolTable.error(formalNode.context, formalNode.type, "Method " + functionName + " of class " + className +
                    " has formal parameter " + variableName + " with illegal type SELF_TYPE");
            return null;
        }
        VariableSymbol variableSymbol = new VariableSymbol(variableName);
        if (!currentScope.addVariable(variableSymbol)) {
            SymbolTable.error(formalNode.context, formalNode.name, "Method " + functionName + " of class " + className +
                    " redefines formal parameter " + variableName);
            return null;
        }
        formalNode.variableSymbol = variableSymbol;
        return null;
    }

    @Override
    public Void visit(CallNode callNode) {
        return null;
    }

    @Override
    public Void visit(IfNode ifNode) {
        ifNode.cond.accept(this);
        ifNode.thenBranch.accept(this);
        ifNode.elseBranch.accept(this);
        return null;
    }

    @Override
    public Void visit(WhileNode whileNode) {
        whileNode.cond.accept(this);
        whileNode.body.accept(this);
        return null;
    }

    @Override
    public Void visit(LetNode letNode) {
        letNode.scope = new Scope(currentScope);
        currentScope = letNode.scope;
        for (LocalVarDefNode localVarDef : letNode.localVarDefs) {
            localVarDef.accept(this);
        }
        letNode.body.accept(this);
        currentScope = currentScope.getParent();
        return null;
    }

    @Override
    public Void visit(CaseBranchNode caseBranchNode) {
        String variableName = caseBranchNode.token.getText();
        if (variableName.equals("self")) {
            SymbolTable.error(caseBranchNode.context, caseBranchNode.token, "Case variable has illegal name self");
            return null;
        }
        if (caseBranchNode.type.getText().equals("SELF_TYPE")) {
            SymbolTable.error(caseBranchNode.context, caseBranchNode.type, "Case variable " + variableName + " has illegal type SELF_TYPE");
            return null;
        }
        caseBranchNode.res.accept(this);
        return null;
    }

    @Override
    public Void visit(CaseNode caseNode) {
        caseNode.expr.accept(this);
        for (CaseBranchNode branch : caseNode.branches) {
            branch.accept(this);
        }
        return null;
    }

    @Override
    public Void visit(IsVoidNode isVoidNode) {
        isVoidNode.expr.accept(this);
        return null;
    }

    @Override
    public Void visit(NewNode newNode) {
        return null;
    }

    @Override
    public Void visit(BlockNode blockNode) {
        for (ExpressionNode expression : blockNode.expressions) {
            expression.accept(this);
        }
        return null;
    }

    @Override
    public Void visit(NegNode negNode) {
        negNode.expr.accept(this);
        return null;
    }

    @Override
    public Void visit(IdNode idNode) {
        return null;
    }

    @Override
    public Void visit(BoolNode boolNode) {
        return null;
    }

    @Override
    public Void visit(IntNode intNode) {
        return null;
    }

    @Override
    public Void visit(StringNode stringNode) {
        return null;
    }

    @Override
    public Void visit(ArithmeticNode arithmeticNode) {
        arithmeticNode.leftExpr.accept(this);
        arithmeticNode.rightExpr.accept(this);
        return null;
    }

    @Override
    public Void visit(RelNode relNode) {
        relNode.leftExpr.accept(this);
        relNode.rightExpr.accept(this);
        return null;
    }

    @Override
    public Void visit(AssignNode assignNode) {
        assignNode.expr.accept(this);
        return null;
    }

    @Override
    public Void visit(NotNode notNode) {
        notNode.expr.accept(this);
        return null;
    }
}