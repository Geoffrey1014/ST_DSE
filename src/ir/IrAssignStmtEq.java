package ir;

public class IrAssignStmtEq extends IrAssignStmt {
    private final IrExpr expr;

    public IrAssignStmtEq(IrLocation storeLocation, IrExpr newValue, int lineNumber, int colNumber) {
        super(storeLocation, lineNumber, colNumber);
        this.expr = newValue;
    }
}
