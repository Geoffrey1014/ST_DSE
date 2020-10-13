package ir.POUDecl;

import ir.IrCodeBlock;
import ir.VARBlockDecl.IrVARBlockDecl;

import java.util.List;

public class IrProgramDecl extends IrPouDecl {
    private final List<IrVARBlockDecl> VarBlockList;
    private final IrCodeBlock CodeBlock;
    public IrProgramDecl(int lineNumber, int colNumber, List<IrVARBlockDecl> varBlockList, IrCodeBlock codeBlock) {
        super(lineNumber, colNumber);
        this.VarBlockList = varBlockList;
        CodeBlock = codeBlock;
    }
}
