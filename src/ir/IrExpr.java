package ir;

public abstract class IrExpr extends Ir {
    public IrExpr(int lineNumber, int colNumber) {
        super(lineNumber, colNumber);
    }

    protected static IrExpr canonicalizeExpr(IrExpr Operand) {
        //TODO:  规范化
        return Operand;
    }
    public abstract IrType getExpressionType();

    public String toString() {
        return "IrExpr";
    }

}
