package ir.Operation;


import SymbolTable.SymTable;
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

}
