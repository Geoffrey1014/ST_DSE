package ir.Operation;


import SymbolTable.SymTable;
import ir.IrExpr;
import ir.VarTypeEnum;

/**
 * Created by geo on 2020/10/13.
 */
public class IrOperUnaryNot extends IrOperUnary {
    public IrOperUnaryNot(IrExpr operand) {
        super(operand);
    }

    @Override
    public VarTypeEnum getExpressionType() {
        return VarTypeEnum.RES_BOOL;
    }

    @Override
    public String semanticCheck(SymTable symTable) {
        String errorMessage = "";

        // 1) check that the operand is valid
        errorMessage += this.operand.semanticCheck(symTable);

        // 2) verify that the operand is int or real
        if (!(this.operand.getExpressionType() == VarTypeEnum.RES_BOOL)) {
            errorMessage += "The not 'NOT' operand must be used on an bool" +
                    " line: " + this.getLineNumber() + " col: " + this.getColNumber() + "\n";
        }

        return errorMessage;
    }

    @Override
    public String prettyPrint(String indentSpace) {
        String prettyString = indentSpace + "|--unaryNotOp\n";

        // pretty print the operand
        prettyString += this.operand.prettyPrint("  " + indentSpace);

        return prettyString;
    }
}
