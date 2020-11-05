package ir.Operation;


import helper.LlBuilder;
import helper.LlSymbolTable;
import ir.IrExpr;
import ir.VarTypeEnum;
import ll.assignStmt.LlAssignStmtBinaryOp;
import ll.location.LlLocation;
import ll.location.LlLocationVar;
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

    @Override
    public LlLocation generateLlIr(LlBuilder builder, LlSymbolTable symbolTable) {
        LlLocation rightTemp = this.rightOperand.generateLlIr(builder, symbolTable);
        LlLocation leftTemp = this.leftOperand.generateLlIr(builder, symbolTable);
        LlLocationVar returnTemp = builder.generateTemp();
        LlAssignStmtBinaryOp assignStmtBinaryOp = new LlAssignStmtBinaryOp(returnTemp, leftTemp, this.getOperation() ,rightTemp);
        builder.appendStatement(assignStmtBinaryOp);
        return returnTemp;
    }

}
