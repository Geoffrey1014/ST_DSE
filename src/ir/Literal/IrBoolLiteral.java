package ir.Literal;

import helper.LlBuilder;
import helper.LlSymbolTable;
import ir.VarTypeEnum;
import ll.assignStmt.LlAssignStmtRegular;
import ll.literal.LlLiteralBool;
import ll.location.LlLocation;
import ll.location.LlLocationVar;
import visitor.BaseVisitor;

public class IrBoolLiteral extends IrLiteral {
    private final Boolean value;

    public IrBoolLiteral(Boolean value, int lineNumber, int colNumber) {
        super(lineNumber, colNumber);
        this.value = value;
    }

    @Override
    public VarTypeEnum getExpressionType() {
        return  VarTypeEnum.RES_BOOL ;

    }

    public Boolean getValue() {
        return value;
    }


    @Override
    public String prettyPrint(String indentSpace) {
        String prettyPrint = indentSpace + "|--boolLiteral\n";
        prettyPrint += "  " + indentSpace + "|--value: " + this.value + "\n";
        return prettyPrint;
    }

    @Override
    public void accept(BaseVisitor<Void> visitor) {
        visitor.visitIrBoolLiteral(this);
    }

    @Override
    public LlLocation generateLlIr(LlBuilder builder, LlSymbolTable symbolTable) {
        LlLiteralBool llLiteralBool = new LlLiteralBool(this.value);
        LlLocationVar var = builder.generateTemp();
        LlAssignStmtRegular regularAssignment = new LlAssignStmtRegular(var, llLiteralBool);
        builder.appendStatement(regularAssignment);
        return var;
    }
}