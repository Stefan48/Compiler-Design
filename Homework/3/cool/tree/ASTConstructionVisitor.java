package cool.tree;

import cool.parser.CoolParser;
import cool.parser.CoolParserBaseVisitor;

import java.util.List;
import java.util.stream.Collectors;

public class ASTConstructionVisitor extends CoolParserBaseVisitor<ASTNode> {
    @Override public ASTNode visitNot(CoolParser.NotContext ctx) {
        return new NotNode(ctx, ctx.start, (ExpressionNode)visit(ctx.e));
    }

    @Override public ASTNode visitAssign(CoolParser.AssignContext ctx) {
        return new AssignNode(ctx, ctx.name, (ExpressionNode)visit(ctx.e));
    }

    @Override public ASTNode visitRel(CoolParser.RelContext ctx) {
        return new RelNode(ctx, ctx.op, (ExpressionNode)visit(ctx.left), (ExpressionNode)visit(ctx.right));
    }

    @Override public ASTNode visitPlusMinus(CoolParser.PlusMinusContext ctx) {
        return new ArithmeticNode(ctx, ctx.op, (ExpressionNode)visit(ctx.left), (ExpressionNode)visit(ctx.right));
    }

    @Override public ASTNode visitMultDiv(CoolParser.MultDivContext ctx) {
        return new ArithmeticNode(ctx, ctx.op, (ExpressionNode)visit(ctx.left), (ExpressionNode)visit(ctx.right));
    }

    @Override public ASTNode visitBool(CoolParser.BoolContext ctx) {
        return new BoolNode(ctx, ctx.start);
    }

    @Override public ASTNode visitInt(CoolParser.IntContext ctx) {
        return new IntNode(ctx, ctx.start);
    }

    @Override public ASTNode visitString(CoolParser.StringContext ctx) {
        return new StringNode(ctx, ctx.start);
    }

    @Override public ASTNode visitId(CoolParser.IdContext ctx) {
        return new IdNode(ctx, ctx.start);
    }

    @Override public ASTNode visitNeg(CoolParser.NegContext ctx) {
        return new NegNode(ctx, ctx.start, (ExpressionNode)visit(ctx.e));
    }

    @Override public ASTNode visitBlock(CoolParser.BlockContext ctx) {
        List<ExpressionNode> expressions = ctx.expressions
                .stream()
                .map(expr -> (ExpressionNode)visit(expr))
                .collect(Collectors.toList());
        return new BlockNode(ctx, ctx.start, expressions);
    }

    @Override public ASTNode visitParen(CoolParser.ParenContext ctx) {
        return ctx.e.accept(this);
    }

    @Override public ASTNode visitNew(CoolParser.NewContext ctx) {
        return new NewNode(ctx, ctx.type);
    }

    @Override public ASTNode visitIsVoid(CoolParser.IsVoidContext ctx) {
        return new IsVoidNode(ctx, ctx.start, (ExpressionNode)visit(ctx.e));
    }

    @Override public ASTNode visitCaseBranch(CoolParser.CaseBranchContext ctx) {
        return new CaseBranchNode(ctx, ctx.name, ctx.type, (ExpressionNode)visit(ctx.res));
    }

    @Override public ASTNode visitCase(CoolParser.CaseContext ctx) {
        List<CaseBranchNode> branches = ctx.branches
                .stream()
                .map(branch -> new CaseBranchNode(ctx, branch.name, branch.type, (ExpressionNode)visit(branch.res)))
                .collect(Collectors.toList());
        return new CaseNode(ctx, ctx.start, (ExpressionNode)visit(ctx.e), branches);
    }

    @Override public ASTNode visitLet(CoolParser.LetContext ctx) {
        List<LocalVarDefNode> localVarDefs = ctx.localVarDefs
                .stream()
                .map(localVarDef -> new LocalVarDefNode(
                        ctx,
                        localVarDef.name,
                        localVarDef.type,
                        localVarDef.e != null ? (ExpressionNode)visit(localVarDef.e) : null))
                .collect(Collectors.toList());
        return new LetNode(ctx, ctx.start, localVarDefs, (ExpressionNode)visit(ctx.body));
    }

    @Override public ASTNode visitWhile(CoolParser.WhileContext ctx) {
        return new WhileNode(ctx, ctx.start, (ExpressionNode)visit(ctx.cond), (ExpressionNode)visit(ctx.body));
    }

    @Override public ASTNode visitIf(CoolParser.IfContext ctx) {
        return new IfNode(ctx, ctx.start, (ExpressionNode)visit(ctx.cond), (ExpressionNode)visit(ctx.thenBranch), (ExpressionNode)visit(ctx.elseBranch));
    }

    @Override public ASTNode visitCall(CoolParser.CallContext ctx) {
        List<ExpressionNode> params = ctx.params
                .stream()
                .map(param -> (ExpressionNode)visit(param))
                .collect(Collectors.toList());
        return new CallNode(ctx, ctx.start, null, null, ctx.func, params);
    }

    @Override public ASTNode visitDispatch(CoolParser.DispatchContext ctx) {
        List<ExpressionNode> params = ctx.params
                .stream()
                .map(param -> (ExpressionNode)visit(param))
                .collect(Collectors.toList());
        return new CallNode(ctx, ctx.start, (ExpressionNode)visit(ctx.caller), ctx.type, ctx.func, params);
    }

    @Override public ASTNode visitFormal(CoolParser.FormalContext ctx) {
        return new FormalNode(ctx, ctx.name, ctx.type);
    }

    @Override public ASTNode visitLocalVarDef(CoolParser.LocalVarDefContext ctx) {
        return new LocalVarDefNode(ctx, ctx.name, ctx.type, ctx.e != null ? (ExpressionNode)visit(ctx.e) : null);
    }

    @Override public ASTNode visitVarDef(CoolParser.VarDefContext ctx) {
        return new VarDefNode(ctx, ctx.name, ctx.type, ctx.e != null ? (ExpressionNode)visit(ctx.e) : null);
    }

    @Override public ASTNode visitFuncDef(CoolParser.FuncDefContext ctx) {
        List<FormalNode> params = ctx.params
                .stream()
                .map(param -> new FormalNode(ctx, param.name, param.type))
                .collect(Collectors.toList());
        return new FuncDefNode(ctx, ctx.name, ctx.type, params, (ExpressionNode)visit(ctx.body));
    }

    @Override public ASTNode visitClassDef(CoolParser.ClassDefContext ctx) {
        List<DefinitionNode> members = ctx.members
                .stream()
                .map(member -> (DefinitionNode)visit(member))
                .collect(Collectors.toList());
        return new ClassDefNode(ctx, ctx.name, ctx.parentName, members);
    }

    @Override public ASTNode visitProgram(CoolParser.ProgramContext ctx) {
        List<ClassDefNode> classDefs = ctx.classDefs
                .stream()
                .map(classDef -> (ClassDefNode)visit(classDef))
                .collect(Collectors.toList());
        return new ProgramNode(ctx, classDefs);
    }
}
