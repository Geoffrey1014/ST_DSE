package ir;

/**
 */
public class IrCtrlFlowIfElse extends IrCtrlFlow {
    private final IrCodeBlock elseBlock;

    public IrCtrlFlowIfElse( IrExpr condExpr, IrCodeBlock stmtBody, IrCodeBlock elseBlock) {
        super(condExpr, stmtBody);
        this.elseBlock = elseBlock;
    }



    public IrCodeBlock getElseBlock() {
        return elseBlock;
    }
}
