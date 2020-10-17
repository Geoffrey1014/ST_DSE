package ir.CtrlFlow;

import ir.Ir;
import ir.IrCodeBlock;
import ir.Location.IrLocationVar;

public class IrCtrlFlowFor implements Ir {
    private final IrLocationVar counter;
    private final IrCtrlFlowForRange range;
    private final IrCodeBlock codeBlock;

    private final int lineNumber;
    private final int colNumber;


    public IrCtrlFlowFor(IrLocationVar counter, IrCtrlFlowForRange range, IrCodeBlock codeBlock) {
        this.lineNumber = counter.getLineNumber();
        this.colNumber = counter.getColNumber();
        this.counter = counter;
        this.range = range;
        this.codeBlock = codeBlock;
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
