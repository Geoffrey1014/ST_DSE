package ir.Operation;


import SymbolTable.SymTable;
import ir.BaseVisitor;
import ir.IrExpr;
import ir.VarTypeEnum;

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
    public String semanticCheck(SymTable symTable) {
        String errorMessage = "";

        // 1) check that rhs and lhs are valid
        errorMessage += this.rightOperand.semanticCheck(symTable);
        errorMessage += this.leftOperand.semanticCheck(symTable);

        // 2) verify that both lhs and rhs are int or real
        boolean bothAreReals = (this.rightOperand.getExpressionType() == VarTypeEnum.RES_REAL)
                && (this.leftOperand.getExpressionType() == VarTypeEnum.RES_REAL );
        boolean bothAreInts = (this.rightOperand.getExpressionType()  == VarTypeEnum.RES_INT)
                && (this.leftOperand.getExpressionType() == VarTypeEnum.RES_INT );

        if (! bothAreInts  && !bothAreReals) {
            errorMessage += "The lhs and rhs of a relational expression must be of type int or real" +
                    " line: " + this.getLineNumber() + " col: " + this.getColNumber() + "\n";
        }

        return errorMessage;
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
