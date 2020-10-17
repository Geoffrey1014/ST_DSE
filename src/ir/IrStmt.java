package ir;

public abstract class IrStmt implements Where {
    private final int lineNumber;
    private final int colNumber;

    public IrStmt(int lineNumber, int colNumber) {
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
