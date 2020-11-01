package ir.Operation;


import ir.IrExpr;
import ir.VarTypeEnum;
import visitor.BaseVisitor;

/**
 * Created by geo on 2020/10/13.
 */
public class IrOperUnaryNot extends IrOperUnary {
    public IrOperUnaryNot(IrExpr operand) {
        super(operand);
    }

    @Override
    public VarTypeEnum getExpressionType() {
        return this.operand.getExpressionType(); //返回下层的 IrExpr 即可
    }


    @Override
    public String prettyPrint(String indentSpace) {
        String prettyString = indentSpace + "|--unaryNotOp\n";

        // pretty print the operand
        prettyString += this.operand.prettyPrint("      " + indentSpace);

        return prettyString;
    }

    @Override
    public void accept(BaseVisitor<Void> visitor) {
        visitor.visitIrOperUnaryNot(this);
    }

}
