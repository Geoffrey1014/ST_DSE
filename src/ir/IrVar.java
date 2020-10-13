package ir;

public class IrVar extends Ir {
    private final IrType type;
    private final IrIdent name;
    public IrVar(int lineNumber, int colNumber, IrType type, IrIdent name) {
        super(lineNumber, colNumber);
        this.type = type;
        this.name = name;
    }
}
