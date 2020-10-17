package ir.VARBlockDecl;

import ir.IrIdent;
import ir.IrMemberDecl;
import ir.VarTypeEnum;

public class IrVarDecl extends IrMemberDecl {
    private final VarTypeEnum type;
    public IrVarDecl(int lineNumber, int colNumber, IrIdent name, VarTypeEnum type) {
        super(name, lineNumber, colNumber);
        this.type = type;

    }
}
