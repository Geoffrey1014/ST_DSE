package ir;

/**
 */
public class IrCtrlFlowIfElse extends IrCtrFlow{
    private final IrCodeBlock elseBlock;

    public IrCtrlFlowIfElse(IrCodeBlock elseBlock, IrExpr condExpr,
                            IrCodeBlock stmtBody, int lineNumber, int colNumber) {
        super(condExpr, stmtBody);
        this.elseBlock = elseBlock;
    }
}
