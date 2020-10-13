package ir.POUDecl;

import ir.IrIdent;
import ir.IrMemberDecl;
import ir.POUDecl.IrPouDecl;

public class IrFunctionDecl extends IrPouDecl {
    public IrFunctionDecl(IrIdent name, int lineNumber, int colNumber) {
        super(name, lineNumber, colNumber);
    }
}
