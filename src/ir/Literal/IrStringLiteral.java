package ir.Literal;

import ir.VarTypeEnum;

/**
 这个应该是用不上的 TODO: 应该是没有这个类型
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
}