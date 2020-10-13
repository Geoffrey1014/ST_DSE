package ir.POUDecl;

import ir.IrIdent;
import ir.IrMemberDecl;
import ir.POUDecl.IrPouDecl;

public class IrFunctionBlockDecl extends IrPouDecl {
    public IrFunctionBlockDecl(int lineNumber, int colNumber, IrIdent name) {
        super(name, lineNumber, colNumber);
    }
}
