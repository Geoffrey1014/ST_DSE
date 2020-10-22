package ir.CtrlFlow;

import ir.BaseVisitor;
import ir.IrCodeBlock;
import ir.IrExpr;

public class IrCtrlFlowElsif extends IrCtrlFlowIf {
    public IrCtrlFlowElsif(IrExpr condExpr, IrCodeBlock stmtBody) {
        super(condExpr, stmtBody);

    }
    @Override
    public void visit(BaseVisitor<Void> visitor) {
        visitor.visitIrCtrlFlowElsif(this);
    }

}
