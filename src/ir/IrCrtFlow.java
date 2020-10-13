package ir;

public class IrCrtFlow extends IrStat {
    final IrExpr condExpr;
    final IrCodeBlock stmtBody;

    public IrExpr getCondExpr() {
        return condExpr;
    }
    public IrCodeBlock getStmtBody() {
        return stmtBody;
    }


    public IrCrtFlow(IrExpr condExpr, IrCodeBlock stmtBody) {
        super(condExpr.getLineNumber(), condExpr.getColNumber());
        this.condExpr = condExpr;
        this.stmtBody = stmtBody;
    }
}
