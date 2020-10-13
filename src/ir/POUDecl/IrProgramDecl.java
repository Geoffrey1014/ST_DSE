package ir.POUDecl;

import ir.IrCodeBlock;
import ir.IrIdent;
import ir.VARBlockDecl.IrVARBlockDecl;

import java.util.List;

public class IrProgramDecl extends IrPouDecl {
    private final List<IrVARBlockDecl> VarBlockList;
    private final IrCodeBlock CodeBlock;
    public IrProgramDecl(IrIdent name, int lineNumber, int colNumber, List<IrVARBlockDecl> varBlockList, IrCodeBlock codeBlock) {
        super(name, lineNumber, colNumber);
        this.VarBlockList = varBlockList;
        CodeBlock = codeBlock;
    }
}
