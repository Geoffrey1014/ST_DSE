package ir.Operation;


import SymbolTable.SymTable;
import ir.BaseVisitor;
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
        return this.operand.getExpressionType(); //返回下层的 IrExpr 即可

    }


    @Override
    public String semanticCheck(SymTable symTable) {
        String errorMessage = "";

        // 1) check that the operand is valid
        errorMessage += this.operand.semanticCheck(symTable);

        // 2) verify that the operand is int or real
        if (!(this.operand.getExpressionType() == VarTypeEnum.RES_INT) && !(this.operand.getExpressionType() == VarTypeEnum.RES_REAL)) {
            errorMessage += "The negation '-' operand must be used on an int or real" +
                    " line: " + this.getLineNumber() + " col: " + this.getColNumber() + "\n";
        }

        return errorMessage;
    }


    @Override
    public String prettyPrint(String indentSpace) {
        String prettyString = indentSpace + "|--unaryNegateOp\n";

        // pretty print the operand
        prettyString += this.operand.prettyPrint("  " + indentSpace);

        return prettyString;
    }

    @Override
    public void visit(BaseVisitor<Void> visitor) {
        visitor.visitIrOperUnaryNeg(this);
    }

}
