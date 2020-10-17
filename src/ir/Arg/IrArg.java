package ir.Arg;

import ir.Ir;

public abstract class IrArg implements Ir{
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
