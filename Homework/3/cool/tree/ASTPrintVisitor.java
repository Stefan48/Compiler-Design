package cool.tree;

public class ASTPrintVisitor implements ASTVisitor<String> {
    private int indent = 0;

    String addIndent(String text) {
        String newText = text;
        for (int i = 0; i < indent; ++i) {
            newText = "  " + newText;
        }
        return newText;
    }

    @Override
    public String visit(NotNode notNode) {
        String output = addIndent("not\n");
        indent++;
        output += notNode.expr.accept(this);
        indent--;
        return output;
    }

    @Override
    public String visit(AssignNode assignNode) {
        String output = addIndent("<-\n");
        indent++;
        output += addIndent(assignNode.token.getText() + "\n");
        output += assignNode.expr.accept(this);
        indent--;
        return output;
    }

    @Override
    public String visit(RelNode relNode) {
        String output = addIndent(relNode.token.getText() + "\n");
        indent++;
        output += relNode.leftExpr.accept(this);
        output += relNode.rightExpr.accept(this);
        indent--;
        return output;
    }

    @Override
    public String visit(ArithmeticNode arithmeticNode) {
        String output = addIndent(arithmeticNode.token.getText() + "\n");
        indent++;
        output += arithmeticNode.leftExpr.accept(this);
        output += arithmeticNode.rightExpr.accept(this);
        indent--;
        return output;
    }

    @Override
    public String visit(BoolNode boolNode) {
        String output = addIndent(boolNode.token.getText() + "\n");
        return output;
    }

    @Override
    public String visit(IntNode intNode) {
        String output = addIndent(intNode.token.getText() + "\n");
        return output;
    }

    @Override
    public String visit(StringNode stringNode) {
        String output = addIndent(stringNode.token.getText() + "\n");
        return output;
    }

    @Override
    public String visit(IdNode idNode) {
        String output = addIndent(idNode.token.getText() + "\n");
        return output;
    }

    @Override
    public String visit(NegNode negNode) {
        String output = addIndent( "~\n");
        indent++;
        output += negNode.expr.accept(this);
        indent--;
        return output;
    }

    @Override
    public String visit(BlockNode blockNode) {
        String output = addIndent("block\n");
        indent++;
        for (ExpressionNode expr : blockNode.expressions) {
            output += expr.accept(this);
        }
        indent--;
        return output;
    }

    @Override
    public String visit(NewNode newNode) {
        String output = addIndent("new\n");
        indent++;
        output += addIndent(newNode.token.getText() + "\n");
        indent--;
        return output;
    }

    @Override
    public String visit(IsVoidNode isVoidNode) {
        String output = addIndent("isvoid\n");
        indent++;
        output += isVoidNode.expr.accept(this);
        indent--;
        return output;
    }

    @Override
    public String visit(CaseNode caseNode) {
        String output = addIndent("case\n");
        indent++;
        output += caseNode.expr.accept(this);
        for (CaseBranchNode branch : caseNode.branches) {
            output += visit(branch);
        }
        indent--;
        return output;
    }

    @Override
    public String visit(CaseBranchNode caseBranchNode) {
        String output = addIndent("case branch\n");
        indent++;
        output += addIndent(caseBranchNode.token.getText() + "\n");
        output += addIndent(caseBranchNode.type.getText() + "\n");
        output += caseBranchNode.res.accept(this);
        indent--;
        return output;
    }

    @Override
    public String visit(LetNode letNode) {
        String output = addIndent("let\n");
        indent++;
        for (LocalVarDefNode localVarDef : letNode.localVarDefs) {
            output += localVarDef.accept(this);
        }
        output += letNode.body.accept(this);
        indent--;
        return output;
    }

    @Override
    public String visit(WhileNode whileNode) {
        String output = addIndent("while\n");
        indent++;
        output += whileNode.cond.accept(this);
        output += whileNode.body.accept(this);
        indent--;
        return output;
    }

    @Override
    public String visit(IfNode ifNode) {
        String output = addIndent("if\n");
        indent++;
        output += ifNode.cond.accept(this);
        output += ifNode.thenBranch.accept(this);
        output += ifNode.elseBranch.accept(this);
        indent--;
        return output;
    }

    @Override
    public String visit(CallNode callNode) {
        String output;
        if (callNode.caller != null) {
            output = addIndent(".\n");
            indent++;
            output += callNode.caller.accept(this);
            if (callNode.type != null) {
                output += addIndent(callNode.type.getText() + "\n");
            }
        }
        else {
            output = addIndent("implicit dispatch\n");
            indent++;
        }
        output += addIndent(callNode.func.getText() + "\n");
        for (ExpressionNode param : callNode.params) {
            output += param.accept(this);
        }
        indent--;
        return output;
    }

    @Override
    public String visit(FormalNode formalNode) {
        String output = addIndent("formal\n");
        indent++;
        output += addIndent(formalNode.name.getText() + "\n");
        output += addIndent(formalNode.type.getText() + "\n");
        indent--;
        return output;
    }

    @Override
    public String visit(LocalVarDefNode localVarDefNode) {
        String output = addIndent("local\n");
        indent++;
        output += addIndent(localVarDefNode.name.getText() + "\n");
        output += addIndent(localVarDefNode.type.getText() + "\n");
        if (localVarDefNode.expr != null) {
            output += localVarDefNode.expr.accept(this);
        }
        indent--;
        return output;
    }

    @Override
    public String visit(VarDefNode varDefNode) {
        String output = addIndent("attribute\n");
        indent++;
        output += addIndent(varDefNode.name.getText() + "\n");
        output += addIndent(varDefNode.type.getText() + "\n");
        if (varDefNode.expr != null) {
            output += varDefNode.expr.accept(this);
        }
        indent--;
        return output;
    }

    @Override
    public String visit(FuncDefNode funcDefNode) {
        String output = addIndent("method\n");
        indent++;
        output += addIndent(funcDefNode.name.getText() + "\n");
        for (FormalNode param : funcDefNode.params) {
            output += param.accept(this);
        }
        output += addIndent(funcDefNode.type.getText() + "\n");
        output += funcDefNode.body.accept(this);
        indent--;
        return output;
    }

    @Override
    public String visit(ClassDefNode classDefNode) {
        String output = addIndent("class\n");
        indent++;
        output += addIndent(classDefNode.name.getText() + "\n");
        if (classDefNode.parentName != null) {
            output += addIndent(classDefNode.parentName.getText() + "\n");
        }
        for (DefinitionNode member : classDefNode.members) {
            output += member.accept(this);
        }
        indent--;
        return output;
    }

    @Override
    public String visit(ProgramNode programNode) {
        String output = addIndent("program\n");
        indent++;
        for (ClassDefNode classDef : programNode.classDefs) {
            output += classDef.accept(this);
        }
        indent--;
        return output;
    }
}
