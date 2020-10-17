package ir.VARBlockDecl;

import ir.Literal.IrIntLiteral;
import ir.VarTypeEnum;

public class IrTypeArray extends IrType {
    private final IrIntLiteral low;
    private final IrIntLiteral high;
    private final VarTypeEnum type;


    public IrTypeArray(int lineNumber, int colNumber, IrIntLiteral low, IrIntLiteral high, VarTypeEnum type) {
        super(lineNumber, colNumber);
        this.low = low;
        this.high = high;
        this.type = type;
    }
}
