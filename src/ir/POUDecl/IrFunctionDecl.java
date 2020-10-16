package ir.POUDecl;

import ir.IrCodeBlock;
import ir.IrIdent;
import ir.IrType;
import ir.VARBlockDecl.IrVARBlockDecl;

public class IrFunctionDecl extends IrPouDecl {
    private final IrVARBlockDecl VarBlockVAR;
    private final IrVARBlockDecl VarBlockVAR_INPUT;
    private final IrVARBlockDecl VarBlockVAR_OUTPUT;
    private final IrCodeBlock CodeBlock;
    private final IrType type;
    public IrFunctionDecl(IrIdent name, IrType type, IrVARBlockDecl varBlockVAR, IrVARBlockDecl varBlockVAR_input, IrVARBlockDecl varBlockVAR_output, IrCodeBlock codeBlock) {
        super(name);
        this.VarBlockVAR = varBlockVAR;
        this.VarBlockVAR_INPUT = varBlockVAR_input;
        this.VarBlockVAR_OUTPUT = varBlockVAR_output;
        this.CodeBlock = codeBlock;
        this.type = type;
    }
}
