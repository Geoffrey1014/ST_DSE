package ir.Operation;


import ir.IrExpr;
import ir.IrType;
import ir.IrTypeBool;

/**
 * Created by geo on 2020/10/13.
 */
public class IrOperUnaryNot extends IrOperUnary {
    public IrOperUnaryNot(IrExpr operand) {
        super(operand);
    }

    @Override
    public IrType getExpressionType() {
        return new IrTypeBool(this.operand.getLineNumber(), this.operand.getColNumber());
    }

}
