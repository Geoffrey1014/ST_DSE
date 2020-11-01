package ir.Literal;

import ir.VarTypeEnum;
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
}