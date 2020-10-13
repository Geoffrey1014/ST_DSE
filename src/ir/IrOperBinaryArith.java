package ir;

/**
 * Created by geo on 2020/10/13.
 */
public class IrOperBinaryArith extends IrOperBinary {

    public IrOperBinaryArith(String operation, IrExpr leftOperand, IrExpr rightOperand) {
        super(operation, leftOperand, rightOperand);
    }

    @Override
    public IrType getExpressionType() {
        return new IrTypeInt(this.leftOperand.getLineNumber(), this.leftOperand.getColNumber());
    }
    
}
