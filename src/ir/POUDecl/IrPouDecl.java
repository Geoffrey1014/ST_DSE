package ir.POUDecl;

import ir.IrCodeBlock;
import ir.IrIdent;
import ir.IrMemberDecl;
import ir.VARBlockDecl.IrVARBlockDecl;


public abstract class IrPouDecl extends IrMemberDecl {
    private final IrVARBlockDecl VarBlockVAR;
    private final IrVARBlockDecl VarBlockVAR_INPUT;
    private final IrVARBlockDecl VarBlockVAR_OUTPUT;
    private final IrVARBlockDecl varBlockVAR_INPUT_OUTPUT;
    private final IrVARBlockDecl varBlockVAR_TEMP;
    private final IrCodeBlock CodeBlock;

    public IrPouDecl(IrIdent name, IrVARBlockDecl varBlockVAR, IrVARBlockDecl varBlockVAR_input, IrVARBlockDecl varBlockVAR_output,
                     IrVARBlockDecl varBlockVAR_input_output, IrVARBlockDecl varBlockVAR_temp, IrCodeBlock codeBlock) {
        super(name, name.getLineNumber(), name.getColNumber());
        VarBlockVAR = varBlockVAR;
        VarBlockVAR_INPUT = varBlockVAR_input;
        VarBlockVAR_OUTPUT = varBlockVAR_output;
        varBlockVAR_INPUT_OUTPUT = varBlockVAR_input_output;
        varBlockVAR_TEMP = varBlockVAR_temp;
        CodeBlock = codeBlock;
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

    public IrVARBlockDecl getVarBlockVAR_INPUT_OUTPUT() {
        return varBlockVAR_INPUT_OUTPUT;
    }

    public IrVARBlockDecl getVarBlockVAR_TEMP() {
        return varBlockVAR_TEMP;
    }
}
