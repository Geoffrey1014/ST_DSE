package ir.Literal;

import ir.IrType;
import ir.IrTypeInt;

public class IrIntLiteral extends IrLiteral {
    private final int value;

    public IrIntLiteral(int value, int lineNumber, int colNumber) {
        super(lineNumber, colNumber);
        this.value = value;
    }

    @Override
    public IrType getExpressionType() {
        return  new IrTypeInt(this.getLineNumber(), this.getColNumber());
    }
}