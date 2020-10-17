package ir;

public abstract class IrExpr implements Where {
    private final int lineNumber;
    private final int colNumber;

    public IrExpr(int lineNumber, int colNumber){
        this.lineNumber = lineNumber;
        this.colNumber = colNumber;
    }

    public static IrExpr canonicalizeExpr(IrExpr Operand) {
        //TODO:  规范化
        return Operand;
    }
    public abstract VarTypeEnum getExpressionType();

    public String toString() {
        return "IrExpr";
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
