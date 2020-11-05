package ir.Operation;


import helper.LlBuilder;
import helper.LlSymbolTable;
import ir.IrExpr;
import ir.VarTypeEnum;
import ll.assignStmt.LlAssignStmtUnaryOp;
import ll.location.LlLocation;
import ll.location.LlLocationVar;
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

    @Override
    public LlLocation generateLlIr(LlBuilder builder, LlSymbolTable symbolTable) {
        LlLocation operandTemp = this.operand.generateLlIr(builder, symbolTable);

        LlLocationVar returnTemp = builder.generateTemp();
        LlAssignStmtUnaryOp unaryOp = new LlAssignStmtUnaryOp(returnTemp, operandTemp, "NOT");
        builder.appendStatement(unaryOp);
        return returnTemp;
    }

}
