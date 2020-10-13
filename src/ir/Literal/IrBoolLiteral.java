package ir.Literal;

import ir.IrType;
import ir.IrTypeBool;
import ir.IrTypeInt;

public class IrBoolLiteral extends IrLiteral {
    private final boolean value;

    public IrBoolLiteral(boolean value, int lineNumber, int colNumber) {
        super(lineNumber, colNumber);
        this.value = value;
    }

    @Override
    public IrType getExpressionType() {
        new IrTypeBool(this.getLineNumber(), this.getColNumber())        ;

    }
}