package cool.tree;

import org.antlr.v4.runtime.Token;
import java.util.List;


public abstract class ASTNode {
    public <T> T accept(ASTVisitor<T> visitor) {
        return null;
    }
}

abstract class ExpressionNode extends ASTNode {
    Token token;

    ExpressionNode(Token token) {
        this.token = token;
    }
}

class NotNode extends ExpressionNode {
    ExpressionNode expr;

    NotNode(Token token, ExpressionNode expr) {
        super(token);
        this.expr = expr;
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

class AssignNode extends ExpressionNode {
    ExpressionNode expr;

    public AssignNode(Token name, ExpressionNode expr) {
        super(name);
        this.expr = expr;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

class RelNode extends ExpressionNode {
    ExpressionNode leftExpr;
    ExpressionNode rightExpr;

    public RelNode(Token op, ExpressionNode leftExpr, ExpressionNode rightExpr) {
        super(op);
        this.leftExpr = leftExpr;
        this.rightExpr = rightExpr;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

class ArithmeticNode extends ExpressionNode {
    ExpressionNode leftExpr;
    ExpressionNode rightExpr;

    public ArithmeticNode(Token op, ExpressionNode leftExpr, ExpressionNode rightExpr) {
        super(op);
        this.leftExpr = leftExpr;
        this.rightExpr = rightExpr;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

class BoolNode extends ExpressionNode {
    public BoolNode(Token token) {
        super(token);
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

class IntNode extends ExpressionNode {
    public IntNode(Token token) {
        super(token);
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

class StringNode extends ExpressionNode {
    public StringNode(Token token) {
        super(token);
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

class IdNode extends ExpressionNode {
    IdNode(Token token) {
        super(token);
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

class NegNode extends ExpressionNode {
    ExpressionNode expr;

    NegNode(Token token, ExpressionNode expr) {
        super(token);
        this.expr = expr;
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

class BlockNode extends ExpressionNode {
    List<ExpressionNode> expressions;

    public BlockNode(Token token, List<ExpressionNode> expressions) {
        super(token);
        this.expressions = expressions;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

class NewNode extends ExpressionNode {
    public NewNode(Token type) {
        super(type);
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

class IsVoidNode extends ExpressionNode {
    ExpressionNode expr;

    IsVoidNode(Token token, ExpressionNode expr) {
        super(token);
        this.expr = expr;
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

class CaseNode extends ExpressionNode {
    ExpressionNode expr;
    List<CaseBranchNode> branches;

    CaseNode(Token start, ExpressionNode expr, List<CaseBranchNode> branches) {
        super(start);
        this.expr = expr;
        this.branches = branches;
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

class CaseBranchNode extends ExpressionNode {
    Token type;
    ExpressionNode res;

    CaseBranchNode(Token name, Token type, ExpressionNode res) {
        super(name);
        this.type = type;
        this.res = res;
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

class LetNode extends ExpressionNode {
    List<LocalVarDefNode> localVarDefs;
    ExpressionNode body;

    LetNode(Token token, List<LocalVarDefNode> localVarDefs, ExpressionNode body) {
        super(token);
        this.localVarDefs = localVarDefs;
        this.body = body;
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

class WhileNode extends ExpressionNode {
    ExpressionNode cond;
    ExpressionNode body;

    WhileNode(Token start, ExpressionNode cond, ExpressionNode body) {
        super(start);
        this.cond = cond;
        this.body = body;
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

class IfNode extends ExpressionNode {
    ExpressionNode cond;
    ExpressionNode thenBranch;
    ExpressionNode elseBranch;

    IfNode(Token start, ExpressionNode cond, ExpressionNode thenBranch, ExpressionNode elseBranch) {
        super(start);
        this.cond = cond;
        this.thenBranch = thenBranch;
        this.elseBranch = elseBranch;
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

class CallNode extends ExpressionNode {
    List<ExpressionNode> params;

    CallNode(Token func, List<ExpressionNode> params) {
        super(func);
        this.params = params;
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

class DispatchNode extends ExpressionNode {
    ExpressionNode caller;
    Token type;
    Token func;
    List<ExpressionNode> params;

    DispatchNode(Token start, ExpressionNode caller, Token type, Token func, List<ExpressionNode> params) {
        super(start);
        this.caller = caller;
        this.type= type;
        this.func = func;
        this.params = params;
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

class DefinitionNode extends ASTNode {
    Token name;
    Token type;

    public DefinitionNode(Token name, Token type) {
        this.name = name;
        this.type = type;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

class LocalVarDefNode extends DefinitionNode {
    ExpressionNode expr;

    LocalVarDefNode(Token name, Token type, ExpressionNode expr) {
        super(name, type);
        this.expr = expr;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

class VarDefNode extends DefinitionNode {
    ExpressionNode expr;

    VarDefNode(Token name, Token type, ExpressionNode expr) {
        super(name, type);
        this.expr = expr;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

class FuncDefNode extends DefinitionNode {
    List<DefinitionNode> params;
    ExpressionNode body;

    FuncDefNode(Token name, Token type, List<DefinitionNode> params, ExpressionNode body) {
        super(name, type);
        this.params = params;
        this.body = body;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

class ClassDefNode extends ASTNode {
    Token name;
    Token parentName;
    List<DefinitionNode> members;

    ClassDefNode(Token name, Token parentName, List<DefinitionNode> members) {
        this.name = name;
        this.parentName = parentName;
        this.members = members;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

class ProgramNode extends ASTNode {
    List<ClassDefNode> classDefs;

    ProgramNode(List<ClassDefNode> classDefs) {
        this.classDefs = classDefs;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}