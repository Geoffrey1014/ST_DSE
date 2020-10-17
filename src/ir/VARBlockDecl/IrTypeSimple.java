package ir.VARBlockDecl;

import ir.VarTypeEnum;

public class IrTypeSimple extends IrType {
    private final VarTypeEnum type;
    public IrTypeSimple(VarTypeEnum type) {
        super(type.getLineNumber(), type.getColNumber());
        this.type = type;
    }
}
