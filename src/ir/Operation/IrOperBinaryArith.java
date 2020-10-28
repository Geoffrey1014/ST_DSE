package ir.Operation;

import SymbolTable.SymTable;
import ir.BaseVisitor;
import ir.IrExpr;
import ir.VarTypeEnum;

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
    public String semanticCheck(SymTable symTable) {
        String errorMessage = "";

        // 1) check that rhs and lhs are valid
        errorMessage += this.rightOperand.semanticCheck(symTable);
        errorMessage += this.leftOperand.semanticCheck(symTable);

        // 2) veriry power operation
        if (this.operation == OperKeyWordEnum.POWER_OP){
            if (this.rightOperand.getExpressionType() != VarTypeEnum.RES_INT){
                errorMessage += "In the power operation, the rhs of an arithmetic expression must be of type int" +
                        " line: " + this.getLineNumber() + " col: " + this.getColNumber() + "\n";
            }
        }
        else if (this.operation == OperKeyWordEnum.DIV_OP){
            // TODO : verify divisor can not be zero
//            if (this.rightOperand){
//                errorMessage += "In the power operation, the rhs of an arithmetic expression must be of type int" +
//                        " line: " + this.getLineNumber() + " col: " + this.getColNumber() + "\n";
//            }

        }
        else{
            // 3) verify that both lhs and rhs are  int or real
            boolean bothAreReals = (this.rightOperand.getExpressionType() == VarTypeEnum.RES_REAL)
                    && (this.leftOperand.getExpressionType() == VarTypeEnum.RES_REAL );
            boolean bothAreInts = (this.rightOperand.getExpressionType()  == VarTypeEnum.RES_INT)
                    && (this.leftOperand.getExpressionType() == VarTypeEnum.RES_INT );
            if (bothAreInts){
                this.exprType = VarTypeEnum.RES_INT;
            }
            else if (bothAreReals){
                this.exprType = VarTypeEnum.RES_REAL;
            }
            else {
                errorMessage += "The lhs and rhs of an arithmetic expression must be of type int or real" +
                        " line: " + this.getLineNumber() + " col: " + this.getColNumber() + "\n";
            }
        }

        return errorMessage;
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
