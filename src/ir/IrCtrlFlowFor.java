package ir;

public class IrCtrlFlowFor extends Ir {
    private final IrLocationVar counter;
    private final IrExpr low;
    private final IrExpr high;
    private final IrExpr step;
    private final IrCodeBlock codeBlock;


    public IrCtrlFlowFor(IrLocationVar counter, IrExpr low, IrExpr high, IrExpr step, IrCodeBlock codeBlock, int lineNumber, int colNumber) {
        super(lineNumber, colNumber);
        this.counter = counter;
        this.low = low;
        this.high = high;
        this.step = step;
        this.codeBlock = codeBlock;
    }
}
