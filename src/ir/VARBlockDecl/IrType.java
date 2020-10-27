package ir.VARBlockDecl;

import ir.Ir;
import ir.VarTypeEnum;

public abstract class IrType extends Ir {
    public final VarTypeEnum type;
    public IrType(int lineNumber, int colNumber, VarTypeEnum type) {
        super(lineNumber, colNumber);
        this.type = type;
    }
    public abstract int getArraySize();

    public VarTypeEnum getTypeEnum() {
        return this.type;
    }

}
