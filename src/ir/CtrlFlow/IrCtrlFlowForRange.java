package ir.CtrlFlow;

import SymbolTable.SymTable;
import ir.BaseVisitor;
import ir.Ir;
import ir.IrExpr;

public class IrCtrlFlowForRange extends Ir {
    private final IrExpr low;
    private final IrExpr high;
    private final IrExpr step;
    public Integer stepNum;

    // TODO : 这里应该是被我复杂化了，一般情况下IrCtrlFlowForRange 只能是两个数值


    public IrCtrlFlowForRange( IrExpr low, IrExpr high, IrExpr step) {

        super( low.getLineNumber(), low.getColNumber());
        this.low = low;
        this.high = high;
        this.step = step;
    }

    @Override
    public String semanticCheck(SymTable symTable) {
        return null;
    }

    @Override
    public String prettyPrint(String indentSpace) {
        return null;
    }

    @Override
    public void accept(BaseVisitor<Void> visitor) {
        visitor.visitIrCtrlFlowForRange(this);
    }

    public IrExpr getLow() {
        return low;
    }

    public IrExpr getHigh() {
        return high;
    }

    public IrExpr getStep() {
        return step;
    }
}
