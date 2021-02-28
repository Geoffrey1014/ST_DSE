package simulation;

import ll.LlEmptyStmt;
import ll.LlMethodCallStmt;
import ll.LlReturn;
import ll.assignStmt.LlAssignStmtBinaryOp;
import ll.assignStmt.LlAssignStmtRegular;
import ll.assignStmt.LlAssignStmtUnaryOp;
import ll.jump.LlJumpConditional;
import ll.jump.LlJumpUnconditional;


public interface LlStatementVisitor {
//    public void visitor(LlAssignStmtBinaryOp llAssignStmtBinaryOp, ConMemory memory);
//    public void visitor(LlAssignStmtUnaryOp llAssignStmtUnaryOp, ConMemory memory);
//    public void visitor(LlAssignStmtRegular llAssignStmtRegular, ConMemory memory);
//    public void visitor(LlJumpConditional llJumpConditional, ConMemory memory);
//    public void visitor(LlJumpUnconditional llJumpUnconditional, ConMemory memory);
//    public void visitor(LlEmptyStmt llEmptyStmt, ConMemory memory);
//    public void visitor(LlMethodCallStmt llMethodCallStmt, ConMemory memory);
//    public void visitor(LlReturn llReturn, ConMemory memory);
//
//    public void visitor(LlAssignStmtBinaryOp llAssignStmtBinaryOp, Context ctx, SymMemory memory);
//    public void visitor(LlAssignStmtUnaryOp llAssignStmtUnaryOp, Context ctx,SymMemory memory);
//    public void visitor(LlAssignStmtRegular llAssignStmtRegular, Context ctx,SymMemory memory);
//    public void visitor(LlJumpConditional llJumpConditional, Context ctx,SymMemory memory);
//    public void visitor(LlJumpUnconditional llJumpUnconditional,Context ctx, SymMemory memory);
//    public void visitor(LlEmptyStmt llEmptyStmt, Context ctx,SymMemory memory);
//    public void visitor(LlMethodCallStmt llMethodCallStmt,Context ctx, SymMemory memory);
//    public void visitor(LlReturn llReturn,Context ctx, SymMemory memory);

    public void visitor(LlAssignStmtBinaryOp llAssignStmtBinaryOp, Memory memory);
    public void visitor(LlAssignStmtUnaryOp llAssignStmtUnaryOp, Memory memory);
    public void visitor(LlAssignStmtRegular llAssignStmtRegular, Memory memory);
    public void visitor(LlJumpConditional llJumpConditional, Memory memory);
    public void visitor(LlJumpUnconditional llJumpUnconditional, Memory memory);
    public void visitor(LlEmptyStmt llEmptyStmt, Memory memory);
    public void visitor(LlMethodCallStmt llMethodCallStmt, Memory memory);
    public void visitor(LlReturn llReturn, Memory memory);
}
