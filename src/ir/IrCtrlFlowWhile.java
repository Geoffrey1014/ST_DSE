package ir;

/**
 */
public class IrCtrlFlowWhile extends IrCtrFlow{
    public IrCtrlFlowWhile(IrExpr condExpr, IrCodeBlock stmtBody, int lineNumber, int colNumber) {
        super(condExpr, stmtBody);
    }
}
