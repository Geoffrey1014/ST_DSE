package ir.VARBlockDecl;

import ir.Ir;
import ir.Where;

import java.util.List;



public abstract class IrVARBlockDecl extends Ir {

    private final List<IrVarDecl> VarList;
    private final VarAccessTypeEnum accessType;
    public IrVARBlockDecl(int lineNumber, int colNumber, List<IrVarDecl> varList, VarAccessTypeEnum accessType) {
        super(lineNumber, colNumber);
        this.VarList = varList;
        this.accessType = accessType;
    }

    public VarAccessTypeEnum getAccessType() {
        return accessType;
    }


}
