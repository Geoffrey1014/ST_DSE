package ir.VARBlockDecl;

import ir.IrVar;

import java.util.List;

public class IrVARBlockDeclInput extends IrVARBlockDecl {
    private final VarAccessTypeEnum VarBlockType;
    public IrVARBlockDeclInput(int lineNumber, int colNumber, List<IrVar> varList, VarAccessTypeEnum varBlockType) {
        super(lineNumber, colNumber, varList);
        this.VarBlockType = varBlockType;

    }
}
