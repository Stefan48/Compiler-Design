package cool.tree;

import cool.structures.*;

public class ASTSecondSemanticVisitor implements ASTVisitor<Void> {
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
        Type type = classDefNode.type;
        if (type == null) {
            return null;
        }
        String className = type.getName();
        while (type != Type.OBJECT) {
            String parentName = type.getParentName();
            if (parentName.equals(className)) {
                SymbolTable.error(classDefNode.context, classDefNode.name, "Inheritance cycle for class " + className);
                return null;
            }
            Type parent = SymbolTable.types.get(parentName);
            if (parent == null) {
                SymbolTable.error(classDefNode.context, classDefNode.parentName, "Class " + className + " has undefined parent " + parentName);
                return null;
            }
            type.setParent(parent);
            type = parent;
        }
        currentScope = classDefNode.type;
        for (DefinitionNode member : classDefNode.members) {
            member.accept(this);
        }
        currentScope = currentScope.getParent();
        return null;
    }

    @Override
    public Void visit(FuncDefNode funcDefNode) {
        FunctionSymbol functionSymbol = funcDefNode.functionSymbol;
        if (functionSymbol == null) {
            return null;
        }
        String functionName = functionSymbol.getName();
        String returnTypeName = funcDefNode.type.getText();
        Type returnType = SymbolTable.types.get(returnTypeName);
        if (returnType == null) {
            SymbolTable.error(funcDefNode.context, funcDefNode.type, "Class " + ((Type) functionSymbol.getParent()).getName() +
                    " has method " + functionName + " with undefined return type " + returnTypeName);
            return null;
        }
        functionSymbol.setReturnType(returnType);
        currentScope = functionSymbol;
        for (FormalNode param : funcDefNode.params) {
            param.accept(this);
        }
        currentScope = currentScope.getParent();
        return null;
    }

    @Override
    public Void visit(VarDefNode varDefNode) {
        if (varDefNode.variableSymbol == null) {
            return null;
        }
        String variableName = varDefNode.variableSymbol.getName();
        String className = ((Type)currentScope).getName();
        if (currentScope.getParent().lookupVariable(variableName) != null) {
            varDefNode.variableSymbol = null;
            SymbolTable.error(varDefNode.context, varDefNode.name, "Class " + className + " redefines inherited attribute " + variableName);
            return null;
        }
        String typeName = varDefNode.type.getText();
        Type type = SymbolTable.types.get(typeName);
        if (type == null) {
            varDefNode.variableSymbol = null;
            SymbolTable.error(varDefNode.context, varDefNode.type, "Class " + className + " has attribute " + variableName +
                    " with undefined type " + typeName);
            return null;
        }
        varDefNode.variableSymbol.setType(type);
        return null;
    }

    @Override
    public Void visit(LocalVarDefNode localVarDefNode) {
        return null;
    }

    @Override
    public Void visit(FormalNode formalNode) {
        VariableSymbol variableSymbol = formalNode.variableSymbol;
        if (variableSymbol == null) {
            return null;
        }
        String typeName = formalNode.type.getText();
        Type type = SymbolTable.types.get(typeName);
        if (type == null) {
            formalNode.variableSymbol = null;
            SymbolTable.error(formalNode.context, formalNode.type, "Method " + ((FunctionSymbol) currentScope).getName() +
                    " of class " + ((Type) currentScope.getParent()).getName() + " has formal parameter " + variableSymbol.getName() +
                    " with undefined type " + typeName);
            return null;
        }
        variableSymbol.setType(type);
        return null;
    }

    @Override
    public Void visit(CallNode callNode) {
        return null;
    }

    @Override
    public Void visit(IfNode ifNode) {
        return null;
    }

    @Override
    public Void visit(WhileNode whileNode) {
        return null;
    }

    @Override
    public Void visit(LetNode letNode) {
        return null;
    }

    @Override
    public Void visit(CaseBranchNode caseBranchNode) {
        return null;
    }

    @Override
    public Void visit(CaseNode caseNode) {
        return null;
    }

    @Override
    public Void visit(IsVoidNode isVoidNode) {
        return null;
    }

    @Override
    public Void visit(NewNode newNode) {
        return null;
    }

    @Override
    public Void visit(BlockNode blockNode) {
        return null;
    }

    @Override
    public Void visit(NegNode negNode) {
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
        return null;
    }

    @Override
    public Void visit(RelNode relNode) {
        return null;
    }

    @Override
    public Void visit(AssignNode assignNode) {
        return null;
    }

    @Override
    public Void visit(NotNode notNode) {
        return null;
    }
}