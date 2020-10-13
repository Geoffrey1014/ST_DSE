package ir.Literal;

public class IrIntLiteral extends IrLiteral {
    private final int value;

    public IrIntLiteral(int value, int lineNumber, int colNumber) {
        super(lineNumber, colNumber);
        this.value = value;
    }
}