package ir;

import java.util.List;

public abstract class IrVarBlock extends Ir {
    private final List<IrVar> VarList;
    public IrVarBlock(int lineNumber, int colNumber, List<IrVar> varList) {
        super(lineNumber, colNumber);
        this.VarList = varList;
    }
}
