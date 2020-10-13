package ir;

public class IrExprBin extends IrExpr {
    private IrExpr left;
    private IrExpr right;
    public IrExprBin(int lineNumber, int colNumber) {
        super(lineNumber, colNumber);
    }

//    @Override
//    public IrType getExpressionType() {
//        return null;
//    }
}
