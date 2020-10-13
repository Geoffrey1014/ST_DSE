package ir.VARBlockDecl;

import ir.IrVar;

import java.util.List;

public class IrVARBlockDeclInput extends IrVARBlockDecl {
    public IrVARBlockDeclInput(int lineNumber, int colNumber, List<IrVar> varList) {
        super(lineNumber, colNumber, varList);
    }
}
