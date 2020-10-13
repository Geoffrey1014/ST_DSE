package ir.Literal;


import ir.Ir;
import ir.IrExpr;

public abstract class IrLiteral extends IrExpr {
    public IrLiteral(int lineNumber, int colNumber) {
        super(lineNumber, colNumber);
    }
}