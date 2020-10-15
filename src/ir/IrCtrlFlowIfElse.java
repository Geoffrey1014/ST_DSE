package ir;

/**
 */
public class IrCtrlFlowIfElse extends IrCtrlFlow {
    private final IrCodeBlock elseBlock;

    public IrCtrlFlowIfElse(IrCodeBlock elseBlock, IrExpr condExpr,
                            IrCodeBlock stmtBody) {
        super(condExpr, stmtBody);
        this.elseBlock = elseBlock;
    }

    public IrCodeBlock getElseBlock() {
        return elseBlock;
    }
}
