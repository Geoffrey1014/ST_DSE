package ir.VARBlockDecl;

import ir.IrIdent;
import ir.IrMemberDecl;
import ir.IrType;

public class IrVarDecl extends IrMemberDecl {
    private final IrType type;
    public IrVarDecl(int lineNumber, int colNumber, IrIdent name, IrType type) {
        super(name, lineNumber, colNumber);
        this.type = type;

    }
}
