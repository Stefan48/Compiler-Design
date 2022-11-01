package cool.tree;

import cool.structures.FunctionSymbol;
import cool.structures.Scope;
import cool.structures.Type;
import cool.structures.VariableSymbol;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import java.util.List;


public abstract class ASTNode {
    ParserRuleContext context;

    public ASTNode(ParserRuleContext context) {
        this.context = context;
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return null;
    }
}

abstract class ExpressionNode extends ASTNode {
    Token token;

    ExpressionNode(ParserRuleContext context, Token token) {
        super(context);
        this.token = token;
    }
}

class NotNode extends ExpressionNode {
    ExpressionNode expr;

    NotNode(ParserRuleContext context, Token token, ExpressionNode expr) {
        super(context, token);
        this.expr = expr;
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

class AssignNode extends ExpressionNode {
    ExpressionNode expr;

    public AssignNode(ParserRuleContext context, Token name, ExpressionNode expr) {
        super(context, name);
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

    public RelNode(ParserRuleContext context, Token op, ExpressionNode leftExpr, ExpressionNode rightExpr) {
        super(context, op);
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

    public ArithmeticNode(ParserRuleContext context, Token op, ExpressionNode leftExpr, ExpressionNode rightExpr) {
        super(context, op);
        this.leftExpr = leftExpr;
        this.rightExpr = rightExpr;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

class BoolNode extends ExpressionNode {
    public BoolNode(ParserRuleContext context, Token token) {
        super(context, token);
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

class IntNode extends ExpressionNode {
    public IntNode(ParserRuleContext context, Token token) {
        super(context, token);
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

class StringNode extends ExpressionNode {
    public StringNode(ParserRuleContext context, Token token) {
        super(context, token);
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

class IdNode extends ExpressionNode {
    IdNode(ParserRuleContext context, Token token) {
        super(context, token);
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

class NegNode extends ExpressionNode {
    ExpressionNode expr;

    NegNode(ParserRuleContext context, Token token, ExpressionNode expr) {
        super(context, token);
        this.expr = expr;
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

class BlockNode extends ExpressionNode {
    List<ExpressionNode> expressions;

    public BlockNode(ParserRuleContext context, Token token, List<ExpressionNode> expressions) {
        super(context, token);
        this.expressions = expressions;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

class NewNode extends ExpressionNode {
    public NewNode(ParserRuleContext context, Token type) {
        super(context, type);
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

class IsVoidNode extends ExpressionNode {
    ExpressionNode expr;

    IsVoidNode(ParserRuleContext context, Token token, ExpressionNode expr) {
        super(context, token);
        this.expr = expr;
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

class CaseNode extends ExpressionNode {
    ExpressionNode expr;
    List<CaseBranchNode> branches;

    CaseNode(ParserRuleContext context, Token start, ExpressionNode expr, List<CaseBranchNode> branches) {
        super(context, start);
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

    CaseBranchNode(ParserRuleContext context, Token name, Token type, ExpressionNode res) {
        super(context, name);
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
    Scope scope;

    LetNode(ParserRuleContext context, Token token, List<LocalVarDefNode> localVarDefs, ExpressionNode body) {
        super(context, token);
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

    WhileNode(ParserRuleContext context, Token start, ExpressionNode cond, ExpressionNode body) {
        super(context, start);
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

    IfNode(ParserRuleContext context, Token start, ExpressionNode cond, ExpressionNode thenBranch, ExpressionNode elseBranch) {
        super(context, start);
        this.cond = cond;
        this.thenBranch = thenBranch;
        this.elseBranch = elseBranch;
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

class CallNode extends ExpressionNode {
    ExpressionNode caller;
    Token type;
    Token func;
    List<ExpressionNode> params;

    CallNode(ParserRuleContext context, Token start, ExpressionNode caller, Token type, Token func, List<ExpressionNode> params) {
        super(context, start);
        this.caller = caller;
        this.type= type;
        this.func = func;
        this.params = params;
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

abstract class DefinitionNode extends ASTNode {
    Token name;
    Token type;

    public DefinitionNode(ParserRuleContext context, Token name, Token type) {
        super(context);
        this.name = name;
        this.type = type;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return null;
    }
}

class FormalNode extends DefinitionNode {
    VariableSymbol variableSymbol;

    public FormalNode(ParserRuleContext context, Token name, Token type) {
        super(context, name, type);
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

class LocalVarDefNode extends DefinitionNode {
    ExpressionNode expr;
    VariableSymbol variableSymbol;

    LocalVarDefNode(ParserRuleContext context, Token name, Token type, ExpressionNode expr) {
        super(context, name, type);
        this.expr = expr;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

class VarDefNode extends DefinitionNode {
    ExpressionNode expr;
    VariableSymbol variableSymbol;

    VarDefNode(ParserRuleContext context, Token name, Token type, ExpressionNode expr) {
        super(context, name, type);
        this.expr = expr;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

class FuncDefNode extends DefinitionNode {
    List<FormalNode> params;
    ExpressionNode body;
    FunctionSymbol functionSymbol;

    FuncDefNode(ParserRuleContext context, Token name, Token type, List<FormalNode> params, ExpressionNode body) {
        super(context, name, type);
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
    Type type;

    ClassDefNode(ParserRuleContext context, Token name, Token parentName, List<DefinitionNode> members) {
        super(context);
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

    ProgramNode(ParserRuleContext context, List<ClassDefNode> classDefs) {
        super(context);
        this.classDefs = classDefs;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}