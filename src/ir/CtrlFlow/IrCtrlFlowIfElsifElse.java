package ir.CtrlFlow;

import ir.BaseVisitor;
import ir.IrCodeBlock;
import ir.IrExpr;

import java.util.ArrayList;

public class IrCtrlFlowIfElsifElse extends IrCtrlFlowIf {
    private  final ArrayList<IrCtrlFlowElsif> elsifArrayList;
    private  final IrCodeBlock elseBlock;
    public IrCtrlFlowIfElsifElse(IrExpr condExpr, IrCodeBlock stmtBody,
                                 ArrayList<IrCtrlFlowElsif> elsifArrayList, IrCodeBlock elseBlock) {
        super(condExpr, stmtBody);
        this.elsifArrayList = elsifArrayList;
        this.elseBlock = elseBlock;
    }

    public IrCodeBlock getElseBlock() {
        return elseBlock;
    }

    public ArrayList<IrCtrlFlowElsif> getElsifArrayList() {
        return elsifArrayList;
    }

    @Override
    public void visit(BaseVisitor<Void> visitor) {
        visitor.visitIrCtrlFlowIfElsifElse(this);
    }
}
