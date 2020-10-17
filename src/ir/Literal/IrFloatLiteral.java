package ir.Literal;

import ir.VarTypeEnum;

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
}
