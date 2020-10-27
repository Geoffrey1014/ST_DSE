package ir.POUDecl;

import SymbolTable.SymTable;
import ir.BaseVisitor;
import ir.IrCodeBlock;
import ir.IrIdent;
import ir.VARBlockDecl.IrType;
import ir.VARBlockDecl.IrVARBlockDecl;


public class IrFunctionDecl extends IrPouDecl {

    private final IrType type;  // TODO 目前function的返回类型不能是 array
    public IrFunctionDecl(IrIdent name, IrType type,
                          IrVARBlockDecl varBlockVAR, IrVARBlockDecl varBlockVAR_input, IrVARBlockDecl varBlockVAR_output,
                          IrVARBlockDecl varBlockVAR_input_output, IrVARBlockDecl varBlockVAR_temp, IrCodeBlock codeBlock) {
        super(name, varBlockVAR, varBlockVAR_input, varBlockVAR_output, varBlockVAR_input_output, varBlockVAR_temp, codeBlock);
        this.type = type;
    }

    public IrType getType() {
        return type;
    }

    @Override
    public String semanticCheck(SymTable symTable) {
        return null;
    }

    @Override
    public String prettyPrint(String indentSpace) {
        return null;
    }

    @Override
    public void visit(BaseVisitor<Void> visitor) {
        visitor.visitIrFunctionDecl(this);
    }
}
