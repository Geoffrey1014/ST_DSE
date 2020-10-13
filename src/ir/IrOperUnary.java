package ir;

public abstract class IrOperUnary extends IrOper {
    public IrExpr operand;

    public IrOperUnary(IrExpr operand) {
        super(operand.getLineNumber(), operand.getColNumber());
        this.operand = canonicalizeExpr(operand);
    }
}
