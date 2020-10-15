package ir;

import java.util.ArrayList;

public class IrCtrlFlowElsif extends IrCtrlFlowIf {
    public IrCtrlFlowElsif(IrExpr condExpr, IrCodeBlock stmtBody) {
        super(condExpr, stmtBody);

    }


}
