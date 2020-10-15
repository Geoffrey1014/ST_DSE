package ir.POUDecl;

import ir.Ir;
import ir.IrIdent;

public abstract class IrPouDecl extends Ir {
    private final IrIdent name;
    public IrPouDecl(IrIdent name) {
        super(name.getLineNumber(), name.getColNumber());
        this.name = name;
    }

    public IrIdent getName() {
        return name;
    }
}
