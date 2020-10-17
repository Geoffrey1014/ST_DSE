package ir.POUDecl;

import ir.IrCodeBlock;
import ir.IrIdent;
import ir.VARBlockDecl.IrType;
import ir.VARBlockDecl.IrVARBlockDecl;
import ir.VarTypeEnum;


public class IrFunctionDecl extends IrPouDecl {

    private final IrType type;
    public IrFunctionDecl(IrIdent name, IrType type,
                          IrVARBlockDecl varBlockVAR, IrVARBlockDecl varBlockVAR_input, IrVARBlockDecl varBlockVAR_output,
                          IrVARBlockDecl varBlockVAR_input_output, IrVARBlockDecl varBlockVAR_temp, IrCodeBlock codeBlock) {
        super(name, varBlockVAR, varBlockVAR_input, varBlockVAR_output, varBlockVAR_input_output, varBlockVAR_temp, codeBlock);
        this.type = type;
    }

    public IrType getType() {
        return type;
    }
}
