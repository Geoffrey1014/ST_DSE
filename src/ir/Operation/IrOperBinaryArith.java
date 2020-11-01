package ir.Operation;

import ir.IrExpr;
import ir.VarTypeEnum;
import visitor.BaseVisitor;

/**
 * Created by geo on 2020/10/13.
 */
public class IrOperBinaryArith extends IrOperBinary {
    /**TODO: 目前不可以做自动的类型转化，比如int 不能和 float 相加
     */
    private VarTypeEnum exprType;

    public IrOperBinaryArith(OperKeyWordEnum operation, IrExpr leftOperand, IrExpr rightOperand) {
        super(operation, leftOperand, rightOperand);
    }

    @Override
    public VarTypeEnum getExpressionType() {
        return this.exprType;
    }
    public void setExpressionType(VarTypeEnum typeEnum) {
        this.exprType = typeEnum;
    }



    @Override
    public String prettyPrint(String indentSpace) {
        String prettyString = indentSpace + "|--binaryArithOper\n";

        // pretty print the lhs
        prettyString += "  " + indentSpace + "|--lhs\n";
        prettyString += this.leftOperand.prettyPrint("    " + indentSpace);

        // print the operator
        prettyString += "  " + indentSpace + "|--op: " + this.operation + "\n";

        // pretty print the rhs
        prettyString += "  " + indentSpace + "|--rhs\n";
        prettyString += this.rightOperand.prettyPrint("    " + indentSpace);

        return prettyString;
    }

    @Override
    public void accept(BaseVisitor<Void> visitor) {
        visitor.visitIrOperBinaryArith(this);
    }
}
