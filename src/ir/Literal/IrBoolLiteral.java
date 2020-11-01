package ir.Literal;

import ir.VarTypeEnum;
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
}