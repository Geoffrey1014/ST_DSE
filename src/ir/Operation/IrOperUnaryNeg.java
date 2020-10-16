package ir.Operation;


import ir.IrExpr;
import ir.IrType;
import ir.IrTypeInt;

/**
 * Created by geo on 2020/10/13.
 */
public class IrOperUnaryNeg extends IrOperUnary {
    public IrOperUnaryNeg(IrExpr operand) {
        super(operand);
    }

    @Override
    public IrType getExpressionType() {
        return new IrTypeInt(this.operand.getLineNumber(), this.operand.getColNumber());
    }

}
