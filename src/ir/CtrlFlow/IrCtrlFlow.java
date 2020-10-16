package ir.CtrlFlow;

import ir.IrCodeBlock;
import ir.IrExpr;
import ir.IrStmt;

public class IrCtrlFlow extends IrStmt {
    final IrExpr condExpr;
    final IrCodeBlock stmtBody;

    public IrExpr getCondExpr() {
        return condExpr;
    }
    public IrCodeBlock getStmtBody() {
        return stmtBody;
    }


    public IrCtrlFlow(IrExpr condExpr, IrCodeBlock stmtBody) {
        super(condExpr.getLineNumber(), condExpr.getColNumber());
        this.condExpr = condExpr;
        this.stmtBody = stmtBody;
    }
}
