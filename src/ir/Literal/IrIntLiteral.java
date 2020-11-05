package ir.Literal;

import helper.LlBuilder;
import helper.LlSymbolTable;
import ir.VarTypeEnum;
import ll.assignStmt.LlAssignStmtRegular;
import ll.literal.LlLiteralInt;
import ll.location.LlLocation;
import ll.location.LlLocationVar;
import visitor.BaseVisitor;

public class IrIntLiteral extends IrLiteral {
    private final Long value;

    public IrIntLiteral(Long value, int lineNumber, int colNumber) {
        super(lineNumber, colNumber);
        this.value = value;
    }

    @Override
    public VarTypeEnum getExpressionType() {
        return  VarTypeEnum.RES_INT;
    }

    public Long getValue() {
        return value;
    }


    @Override
    public String prettyPrint(String indentSpace) {
        String prettyPrint = indentSpace + "|--IntLiteral\n";
        prettyPrint += ("  " + indentSpace + "|--value: " + this.value + "\n");
        return prettyPrint;    }

    @Override
    public void accept(BaseVisitor<Void> visitor) {
        visitor.visitIrIntLiteral(this);
    }

    @Override
    public LlLocation generateLlIr(LlBuilder builder, LlSymbolTable symbolTable) {
        LlLiteralInt llLiteral = new LlLiteralInt(this.value);
        LlLocationVar var = builder.generateTemp();
        LlAssignStmtRegular regularAssignment = new LlAssignStmtRegular(var, llLiteral);
        builder.appendStatement(regularAssignment);
        return var;
    }
}