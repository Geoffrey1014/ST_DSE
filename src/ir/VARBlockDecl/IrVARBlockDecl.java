package ir.VARBlockDecl;

import ir.Ir;
import ir.IrVar;

import java.util.List;

public abstract class IrVARBlockDecl extends Ir {
    private final List<IrVar> VarList;
    public IrVARBlockDecl(int lineNumber, int colNumber, List<IrVar> varList) {
        super(lineNumber, colNumber);
        this.VarList = varList;
    }
}
