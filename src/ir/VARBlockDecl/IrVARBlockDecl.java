package ir.VARBlockDecl;

import ir.Where;

import java.util.List;



public abstract class IrVARBlockDecl implements Where {
    private final int lineNumber;
    private final int colNumber;

    private final List<IrVarDecl> VarList;
    private final VarAccessTypeEnum accessType;
    public IrVARBlockDecl(int lineNumber, int colNumber, List<IrVarDecl> varList, VarAccessTypeEnum accessType) {
        this.lineNumber = lineNumber;
        this.colNumber = colNumber;
        this.VarList = varList;
        this.accessType = accessType;
    }

    public VarAccessTypeEnum getAccessType() {
        return accessType;
    }

    @Override
    public int getLineNumber() {
        return lineNumber;
    }

    @Override
    public int getColNumber() {
        return colNumber;
    }
}
