package ir.CtrlFlow;

import ir.IrCodeBlock;
import ir.IrExpr;

/**
 */
public class IrCtrlFlowIfElse extends IrCtrlFlow {
    private final IrCodeBlock elseBlock;

    public IrCtrlFlowIfElse(IrExpr condExpr, IrCodeBlock stmtBody, IrCodeBlock elseBlock) {
        super(condExpr, stmtBody);
        this.elseBlock = elseBlock;
    }



    public IrCodeBlock getElseBlock() {
        return elseBlock;
    }
}
