package ir.POUDecl;

import helper.LlBuilder;
import helper.LlSymbolTable;
import ir.IrCodeBlock;
import ir.IrIdent;
import ir.VARBlockDecl.IrType;
import ir.VARBlockDecl.IrVARBlockDecl;
import ir.VARBlockDecl.IrVarDecl;
import ll.LlEmptyStmt;
import ll.location.LlLocation;
import visitor.BaseVisitor;


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
    public String prettyPrint(String indentSpace) {

        String prettyString = indentSpace + "|--Function\n";
        prettyString += "  " + indentSpace + "|--name: " + this.getName() + "\n";
        prettyString += "  " + indentSpace + "|--type: " + this.returnType.type + "\n";

        if (this.varBlockVAR != null){
//            prettyString += "  " + indentSpace + "|--varBlockVAR" + "\n";
            prettyString += this.varBlockVAR.prettyPrint("    " + indentSpace);

        }
        if (this.varBlockVAR_INPUT != null){
//            prettyString += "  " + indentSpace + "|--varBlockVAR_input" + "\n";
            prettyString += this.varBlockVAR_INPUT.prettyPrint("    " + indentSpace);

        }

        if (this.varBlockVAR_OUTPUT != null){
//            prettyString += "  " + indentSpace + "|--varBlockVAR_OUTPUT" + "\n";
            prettyString += this.varBlockVAR_OUTPUT.prettyPrint("    " + indentSpace);

        }

        if (this.varBlockVAR_INPUT_OUTPUT != null){
//            prettyString += "  " + indentSpace + "|--varBlockVAR_INPUT_OUTPUT" + "\n";
            prettyString += this.varBlockVAR_INPUT_OUTPUT.prettyPrint("    " + indentSpace);

        }

        if (this.varBlockVAR_TEMP != null){
//            prettyString += "  " + indentSpace + "|--varBlockVAR_TEMP" + "\n";
            prettyString += this.varBlockVAR_TEMP.prettyPrint("    " + indentSpace);

        }

//        prettyString += "  " + indentSpace + "|--CodeBlock" + "\n";
        prettyString += this.CodeBlock.prettyPrint("    " + indentSpace);

        return prettyString;
    }

    @Override
    public void accept(BaseVisitor<Void> visitor) {
        visitor.visitIrFunctionDecl(this);
    }

    @Override
    public LlLocation generateLlIr(LlBuilder builder, LlSymbolTable symbolTable) {
        String name = this.getName();
        LlEmptyStmt emptyStmt = new LlEmptyStmt();
        builder.appendStatement(name, emptyStmt);

        this.CodeBlock.generateLlIr(builder, symbolTable);
        return null;
    }
}
