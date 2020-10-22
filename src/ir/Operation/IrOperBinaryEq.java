package ir.Operation;


import SymbolTable.SymTable;
import ir.BaseVisitor;
import ir.IrExpr;
import ir.VarTypeEnum;

public class IrOperBinaryEq extends IrOperBinary {

    public IrOperBinaryEq(OperKeyWordEnum operation, IrExpr leftOperand, IrExpr rightOperand) {
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

        // 2) verify that both lhs and rhs are either bool, int or real
        boolean bothAreBools = (this.rightOperand.getExpressionType() == VarTypeEnum.RES_BOOL)
                && (this.leftOperand.getExpressionType() == VarTypeEnum.RES_BOOL );
        boolean bothAreInts = (this.rightOperand.getExpressionType()  == VarTypeEnum.RES_INT)
                && (this.leftOperand.getExpressionType() == VarTypeEnum.RES_INT );
        boolean bothAreReals = (this.rightOperand.getExpressionType() == VarTypeEnum.RES_REAL)
                && (this.leftOperand.getExpressionType() == VarTypeEnum.RES_REAL );

        if (!bothAreBools && !bothAreInts && !bothAreReals) {
            errorMessage += "The lhs and rhs of an equator operation must the same type of bool , int ,or real " +
                    " line: " + this.getLineNumber() + " col: " + this.getColNumber() + "\n";
        }

        return errorMessage;
    }



    @Override
    public String prettyPrint(String indentSpace) {
        String prettyString = indentSpace + "|--binaryEquateOper\n";

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
    public void visit(BaseVisitor<Void> visitor) {
        visitor.visitIrOperBinaryEq(this);
    }

}
