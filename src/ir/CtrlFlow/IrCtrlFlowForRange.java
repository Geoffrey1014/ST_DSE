package ir.CtrlFlow;

import ir.Where;
import ir.IrExpr;

public class IrCtrlFlowForRange implements Where {
    private final IrExpr low;
    private final IrExpr high;
    private final Integer step;
    private final int lineNumber;
    private final int colNumber;
    // TODO : 这里应该是被我复杂化了，一般情况下IrCtrlFlowForRange 只能是两个数值


    public IrCtrlFlowForRange( IrExpr low, IrExpr high, Integer step) {

        this.lineNumber = low.getLineNumber();
        this.colNumber = low.getColNumber();
        this.low = low;
        this.high = high;
        this.step = step;
    }
    @Override
    public int getLineNumber() {
        return lineNumber;
    }

    @Override
    public int getColNumber() {
        return colNumber;
    }
}
