package ir.VARBlockDecl;

import ir.Ir;
import ir.VarTypeEnum;

public abstract class IrValue extends Ir {
    public IrValue(int lineNumber, int colNumber) {
        super(lineNumber, colNumber);
    }
    public abstract Object getValue();
    public abstract VarTypeEnum getType();
}
