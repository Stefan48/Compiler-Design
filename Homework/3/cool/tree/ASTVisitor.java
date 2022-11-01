package cool.tree;

public interface ASTVisitor<T> {
    T visit(NotNode notNode);
    T visit(AssignNode assignNode);
    T visit(RelNode relNode);
    T visit(ArithmeticNode arithmeticNode);
    T visit(BoolNode boolNode);
    T visit(IntNode intNode);
    T visit(StringNode stringNode);
    T visit(IdNode idNode);
    T visit(NegNode negNode);
    T visit(BlockNode blockNode);
    T visit(NewNode newNode);
    T visit(IsVoidNode isVoidNode);
    T visit(CaseNode caseNode);
    T visit(CaseBranchNode caseBranchNode);
    T visit(LetNode letNode);
    T visit(WhileNode whileNode);
    T visit(IfNode ifNode);
    T visit(CallNode callNode);
    T visit(FormalNode formalNode);
    T visit(LocalVarDefNode localVarDefNode);
    T visit(VarDefNode varDefNode);
    T visit(FuncDefNode funcDefNode);
    T visit(ClassDefNode classDefNode);
    T visit(ProgramNode programNode);
}
