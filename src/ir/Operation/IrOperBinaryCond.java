package ir.Operation;


import ir.IrExpr;
import ir.IrType;
import ir.IrTypeBool;

/**
 * Created by geo on 2020/10/13.
 */
public class IrOperBinaryCond extends IrOperBinary {

    public IrOperBinaryCond(OperKeyWords operation, IrExpr leftOperand, IrExpr rightOperand) {
        super(operation, leftOperand, rightOperand);
    }

    @Override
    public IrType getExpressionType() {
        return new IrTypeBool(this.leftOperand.getLineNumber(), this.leftOperand.getColNumber());

    }
}
