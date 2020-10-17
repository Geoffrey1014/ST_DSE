package ir.VARBlockDecl;

import ir.Ir;
import ir.VarTypeEnum;

public abstract class IrType extends Ir {
    public IrType(int lineNumber, int colNumber) {
        super(lineNumber, colNumber);
    }
    public abstract int getArraySize();
    public abstract VarTypeEnum getTypeEnum();

}
