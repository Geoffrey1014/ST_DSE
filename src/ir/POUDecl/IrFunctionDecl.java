package ir.POUDecl;

import ir.IrCodeBlock;
import ir.IrIdent;
import ir.IrType;
import ir.VARBlockDecl.IrVARBlockDecl;


public class IrFunctionDecl extends IrPouDecl {

    private final IrType type;
    public IrFunctionDecl(IrIdent name, IrType type,
                          IrVARBlockDecl varBlockVAR, IrVARBlockDecl varBlockVAR_input, IrVARBlockDecl varBlockVAR_output,
                          IrVARBlockDecl varBlockVAR_input_output, IrVARBlockDecl varBlockVAR_temp, IrCodeBlock codeBlock) {
        super(name, varBlockVAR, varBlockVAR_input, varBlockVAR_output, varBlockVAR_input_output, varBlockVAR_temp, codeBlock);
        this.type = type;
    }
}
