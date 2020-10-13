package ir;

public class IrCtrFlowIf extends IrCrtFlow {
    public IrCtrFlowIf(IrExpr condExpr, IrCodeBlock stmtBody) {
        super(condExpr, stmtBody);
    }
    public IrExpr getIfCondition() {
        return this.condExpr;
    }

    public IrCodeBlock getIfBodyBlock() {
        return this.stmtBody;
    }

}
