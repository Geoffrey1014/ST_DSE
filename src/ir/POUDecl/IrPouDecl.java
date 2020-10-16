package ir.POUDecl;

import ir.Ir;
import ir.IrIdent;
import ir.IrMemberDecl;

public abstract class IrPouDecl extends IrMemberDecl {

    public IrPouDecl(IrIdent name) {
        super(name, name.getLineNumber(), name.getColNumber());
    }
    
}
