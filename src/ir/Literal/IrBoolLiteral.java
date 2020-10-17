package ir.Literal;

import ir.VarTypeEnum;

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
}