package ir;

public class IrVarArray extends IrVar {
    private final int arraySize;
    public IrVarArray(int lineNumber, int colNumber, IrType type, IrIdent name, int arraySize) {
        super(lineNumber, colNumber, type, name);
        this.arraySize = arraySize;
    }
}
