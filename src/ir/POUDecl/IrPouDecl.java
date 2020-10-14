package ir.POUDecl;

import ir.Ir;
import ir.IrIdent;

public class IrPouDecl extends Ir {
    private final IrIdent name;
    public IrPouDecl(IrIdent name, int lineNumber, int colNumber) {
        super(lineNumber, colNumber);
        this.name = name;
    }
}
