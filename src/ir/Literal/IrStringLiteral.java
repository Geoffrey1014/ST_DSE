package ir.Literal;

import helper.LlBuilder;
import helper.LlSymbolTable;
import ir.VarTypeEnum;
import ll.assignStmt.LlAssignStmtRegular;
import ll.literal.LlLiteralString;
import ll.location.LlLocation;
import ll.location.LlLocationVar;
import visitor.BaseVisitor;

/**
 这个应该是用不上的 TODO: 这个类型不太清楚怎么用的
 */
public class IrStringLiteral extends IrLiteral {
    private final String value;

    public IrStringLiteral(String value, int lineNumber, int colNumber) {
        super(lineNumber, colNumber);
        this.value = value;
    }

    @Override
    public VarTypeEnum getExpressionType() {
        return VarTypeEnum.RES_STRING;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String prettyPrint(String indentSpace) {
        String prettyPrint = indentSpace + "|--stringLiteral\n";
        prettyPrint += "  " + indentSpace + "|--value: " + this.value + "\n";
        return prettyPrint;
    }

    @Override
    public void accept(BaseVisitor<Void> visitor) {
        visitor.visitIrStringLiteral(this);
    }

    @Override
    public LlLocation generateLlIr(LlBuilder builder, LlSymbolTable symbolTable) {

        LlLiteralString llLiteral = new LlLiteralString(this.value);
        LlLocationVar var = builder.generateTemp();
        LlAssignStmtRegular regularAssignment = new LlAssignStmtRegular(var, llLiteral);
        builder.appendStatement(regularAssignment);
        return var;
    }
}