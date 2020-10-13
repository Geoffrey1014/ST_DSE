package ir;

public class IrCtrFlow extends IrStmt {
    final IrExpr condExpr;
    final IrCodeBlock stmtBody;

    public IrExpr getCondExpr() {
        return condExpr;
    }
    public IrCodeBlock getStmtBody() {
        return stmtBody;
    }


    public IrCtrFlow(IrExpr condExpr, IrCodeBlock stmtBody) {
        super(condExpr.getLineNumber(), condExpr.getColNumber());
        this.condExpr = condExpr;
        this.stmtBody = stmtBody;
    }
}
