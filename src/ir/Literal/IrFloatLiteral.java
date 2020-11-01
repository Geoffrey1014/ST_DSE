package ir.Literal;

import ir.VarTypeEnum;
import visitor.BaseVisitor;

public class IrFloatLiteral extends IrLiteral {
    private final Double value;

    public IrFloatLiteral(Double value, int lineNumber, int colNumber) {
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

}
