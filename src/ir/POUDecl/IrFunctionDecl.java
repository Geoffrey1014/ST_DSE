package ir.POUDecl;

import SymbolTable.SymTable;
import ir.BaseVisitor;
import ir.IrCodeBlock;
import ir.IrIdent;
import ir.VARBlockDecl.IrType;
import ir.VARBlockDecl.IrVARBlockDecl;
import ir.VARBlockDecl.IrVarDecl;


public class IrFunctionDecl extends IrPouDecl {

    private final IrType returnType;  // TODO 我不知道 目前function的返回类型能不能是 array
    public  final IrVarDecl returnVar;
    public IrFunctionDecl(IrIdent name, IrType returnType,
                          IrVARBlockDecl varBlockVAR, IrVARBlockDecl varBlockVAR_input, IrVARBlockDecl varBlockVAR_output,
                          IrVARBlockDecl varBlockVAR_input_output, IrVARBlockDecl varBlockVAR_temp, IrCodeBlock codeBlock, IrVarDecl returnVar) {
        super(name, varBlockVAR, varBlockVAR_input, varBlockVAR_output, varBlockVAR_input_output, varBlockVAR_temp, codeBlock);
        this.returnType = returnType;
        this.returnVar = returnVar;
    }

    public IrType getReturnType() {
        return returnType;
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
    public void accept(BaseVisitor<Void> visitor) {
        visitor.visitIrFunctionDecl(this);
    }
}
