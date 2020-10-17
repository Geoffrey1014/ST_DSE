package ir.Operation;


import ir.IrExpr;
import ir.VarTypeEnum;

/**
 * Created by geo on 2020/10/13.
 */
public class IrOperUnaryNeg extends IrOperUnary {
    public IrOperUnaryNeg(IrExpr operand) {
        super(operand);
    }

    @Override
    public VarTypeEnum getExpressionType() {
        return VarTypeEnum.RES_INT; //TODO: 这里暂时这么些，是很粗糙的。真的要实现所有的类型检查很麻烦

    }

}
