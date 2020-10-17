package ir.VARBlockDecl;

import ir.Literal.IrLiteral;

public class IrValueSimple extends IrValue {
    private final IrLiteral value;
    public IrValueSimple( IrLiteral value) {
        super(value.getLineNumber(), value.getColNumber());
        this.value = value;
    }
}
