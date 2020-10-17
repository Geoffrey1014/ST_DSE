package ir.Literal;

import ir.VarTypeEnum;

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
}