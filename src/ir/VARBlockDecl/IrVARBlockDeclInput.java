package ir.VARBlockDecl;

import ir.IrVar;

import java.util.List;

public class IrVARBlockDeclInput extends IrVARBlockDecl {
    private final VarBlockTypeEnum VarBlockType;
    public IrVARBlockDeclInput(int lineNumber, int colNumber, List<IrVar> varList, VarBlockTypeEnum varBlockType) {
        super(lineNumber, colNumber, varList);
        this.VarBlockType = varBlockType;
    }
}
