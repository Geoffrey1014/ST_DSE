package ir;


/**
 * Created by geo on 2020/10/13.
 */
public class IrOperBinaryCond extends IrOperBinary {

    public IrOperBinaryCond(String operation, IrExpr leftOperand, IrExpr rightOperand) {
        super(operation, leftOperand, rightOperand);
    }

    @Override
    public IrType getExpressionType() {
        return new IrTypeBool(this.leftOperand.getLineNumber(), this.leftOperand.getColNumber());

    }
}
