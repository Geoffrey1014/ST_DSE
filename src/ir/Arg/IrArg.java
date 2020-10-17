package ir.Arg;

import ir.Where;

public abstract class IrArg implements Where {
    private final int lineNumber;
    private final int colNumber;

    public IrArg(int lineNumber, int colNumber) {
        this.lineNumber = lineNumber;
        this.colNumber = colNumber;
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
