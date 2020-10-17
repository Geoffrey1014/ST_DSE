package ir.CtrlFlow;

import ir.Ir;
import ir.Where;
import ir.IrCodeBlock;
import ir.Location.IrLocationVar;

public class IrCtrlFlowFor extends Ir {
    private final IrLocationVar counter;
    private final IrCtrlFlowForRange range;
    private final IrCodeBlock codeBlock;


    public IrCtrlFlowFor(IrLocationVar counter, IrCtrlFlowForRange range, IrCodeBlock codeBlock) {

        super( counter.getLineNumber(), counter.getColNumber());
        this.counter = counter;
        this.range = range;
        this.codeBlock = codeBlock;
    }

}
