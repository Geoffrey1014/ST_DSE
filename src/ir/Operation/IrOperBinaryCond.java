package ir.Operation;


import ir.IrExpr;
import ir.VarTypeEnum;

/**
 * Created by geo on 2020/10/13.
 */
public class IrOperBinaryCond extends IrOperBinary {

    public IrOperBinaryCond(OperKeyWordEnum operation, IrExpr leftOperand, IrExpr rightOperand) {
        super(operation, leftOperand, rightOperand);
    }

    @Override
    public VarTypeEnum getExpressionType() {
        return VarTypeEnum.RES_BOOL;
    }
}
