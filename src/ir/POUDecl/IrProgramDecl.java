package ir.POUDecl;

import ir.IrCodeBlock;
import ir.IrIdent;
import ir.VARBlockDecl.IrVARBlockDecl;

public class IrProgramDecl extends IrPouDecl {
    private final IrVARBlockDecl VarBlockVAR;
    private final IrVARBlockDecl VarBlockVAR_INPUT;
    private final IrVARBlockDecl VarBlockVAR_OUTPUT;
    private final IrCodeBlock CodeBlock;

    public IrProgramDecl(IrIdent name, IrVARBlockDecl varBlockVAR, IrVARBlockDecl varBlockVAR_input, IrVARBlockDecl varBlockVAR_output, IrCodeBlock codeBlock) {
        super(name);
        VarBlockVAR = varBlockVAR;
        VarBlockVAR_INPUT = varBlockVAR_input;
        VarBlockVAR_OUTPUT = varBlockVAR_output;

        this.CodeBlock = codeBlock;
    }

    public IrVARBlockDecl getVarBlockVAR() {
        return VarBlockVAR;
    }

    public IrVARBlockDecl getVarBlockVAR_INPUT() {
        return VarBlockVAR_INPUT;
    }

    public IrVARBlockDecl getVarBlockVAR_OUTPUT() {
        return VarBlockVAR_OUTPUT;
    }

    public IrCodeBlock getCodeBlock() {
        return CodeBlock;
    }
}
