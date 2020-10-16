package ir.CtrlFlow;

import ir.IrCodeBlock;
import ir.IrExpr;

public class IrCtrlFlowElsif extends IrCtrlFlowIf {
    public IrCtrlFlowElsif(IrExpr condExpr, IrCodeBlock stmtBody) {
        super(condExpr, stmtBody);

    }


}
