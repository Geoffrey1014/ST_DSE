package ir.Operation;


import ir.IrExpr;
import ir.VarTypeEnum;
import visitor.BaseVisitor;

/**
 * Created by geo on 2020/10/13.
 */
public class IrOperBinaryRel extends IrOperBinary {

    public IrOperBinaryRel(OperKeyWordEnum operation, IrExpr leftOperand, IrExpr rightOperand) {
        super(operation, leftOperand, rightOperand);
    }

    @Override
    public VarTypeEnum getExpressionType() {
        return VarTypeEnum.RES_BOOL;
    }


    @Override
    public String prettyPrint(String indentSpace) {
        String prettyString = indentSpace + "|__binaryRelOper\n";

        // pretty print the lhs
        prettyString += "  " + indentSpace + "|__lhs\n";
        prettyString += this.leftOperand.prettyPrint("    " + indentSpace);

        // print the operator
        prettyString += "  " + indentSpace + "|__op: " + this.operation + "\n";

        // pretty print the rhs
        prettyString += "  " + indentSpace + "|__rhs\n";
        prettyString += this.rightOperand.prettyPrint("    " + indentSpace);

        return prettyString;
    }

    @Override
    public void accept(BaseVisitor<Void> visitor) {
        visitor.visitIrOperBinaryRel(this);
    }

}
