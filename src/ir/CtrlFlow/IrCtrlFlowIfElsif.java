package ir.CtrlFlow;

import ir.BaseVisitor;
import ir.IrCodeBlock;
import ir.IrExpr;

import java.util.ArrayList;

public class IrCtrlFlowIfElsif extends  IrCtrlFlowIf{
    private  final ArrayList<IrCtrlFlowElsif> elsifArrayList;
    public IrCtrlFlowIfElsif(IrExpr condExpr, IrCodeBlock stmtBody, ArrayList<IrCtrlFlowElsif> elsifArrayList) {
        super(condExpr, stmtBody);
        this.elsifArrayList = elsifArrayList;
    }

    public ArrayList<IrCtrlFlowElsif> getElsifArrayList() {
        return elsifArrayList;
    }

    @Override
    public void visit(BaseVisitor<Void> visitor) {
        visitor.visitIrCtrlFlowIfElsif(this);
    }
}
