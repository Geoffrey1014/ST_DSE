package ir.Literal;

import SymbolTable.SymTable;
import ir.BaseVisitor;
import ir.VarTypeEnum;

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
    public String semanticCheck(SymTable symTable) {
        return "";
    }

    @Override
    public String prettyPrint(String indentSpace) {
        String prettyPrint = indentSpace + "|--stringLiteral\n";
        prettyPrint += "  " + indentSpace + "|--value: " + this.value + "\n";
        return prettyPrint;
    }

    @Override
    public void visit(BaseVisitor<Void> visitor) {
        visitor.visitIrStringLiteral(this);
    }
}