package ir;

/**
 */
public class IrCtrlFlowWhile extends IrCtrlFlow {
    public IrCtrlFlowWhile(IrExpr condExpr, IrCodeBlock stmtBody) {
        super(condExpr, stmtBody);
    }
}
