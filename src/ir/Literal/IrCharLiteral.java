package ir.Literal;

import ir.IrType;

/**
 这个应该是用不上的
 */
public class IrCharLiteral extends IrLiteral {
    private final char value;

    public IrCharLiteral(char value, int lineNumber, int colNumber) {
        super(lineNumber, colNumber);
        this.value = value;
    }

    @Override
    public IrType getExpressionType() {
        return null;
    }
}