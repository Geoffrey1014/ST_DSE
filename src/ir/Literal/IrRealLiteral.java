package ir.Literal;

import helper.LlBuilder;
import helper.LlSymbolTable;
import ir.VarTypeEnum;
import ll.assignStmt.LlAssignStmtRegular;
import ll.literal.LlLiteralReal;
import ll.location.LlLocation;
import ll.location.LlLocationVar;
import visitor.BaseVisitor;

public class IrRealLiteral extends IrLiteral {
    private final Double value;

    public IrRealLiteral(Double value, int lineNumber, int colNumber) {
        super(lineNumber, colNumber);
        this.value = value;
    }

    @Override
    public VarTypeEnum getExpressionType() {
        return  VarTypeEnum.RES_REAL;
    }

    public Double getValue() {
        return value;
    }

    @Override
    public String prettyPrint(String indentSpace) {
        String prettyPrint = indentSpace + "|--FloatLiteral\n";
        prettyPrint += ("  " + indentSpace + "|--value: " + this.value + "\n");
        return prettyPrint;
    }

    @Override
    public void accept(BaseVisitor<Void> visitor) {
        visitor.visitIrFloatLiteral(this);
    }

    @Override
    public LlLocation generateLlIr(LlBuilder builder, LlSymbolTable symbolTable) {
        LlLiteralReal llLiteral = new LlLiteralReal(this.value);
        LlLocationVar var = builder.generateTemp();
        LlAssignStmtRegular regularAssignment = new LlAssignStmtRegular(var, llLiteral);
        builder.appendStatement(regularAssignment);
        return var;
    }

}
