package ir.POUDecl;

import helper.LlBuilder;
import helper.LlSymbolTable;
import ir.IrCodeBlock;
import ir.IrIdent;
import ir.VARBlockDecl.IrVARBlockDecl;
import ll.LlEmptyStmt;
import ll.location.LlLocation;
import visitor.BaseVisitor;


public class IrFunctionBlockDecl extends IrPouDecl {
    public IrFunctionBlockDecl(IrIdent name, IrVARBlockDecl varBlockVAR, IrVARBlockDecl varBlockVAR_input, IrVARBlockDecl varBlockVAR_output,
            IrVARBlockDecl varBlockVAR_input_output, IrVARBlockDecl varBlockVAR_temp, IrCodeBlock codeBlock) {
        super(name, varBlockVAR, varBlockVAR_input, varBlockVAR_output, varBlockVAR_input_output, varBlockVAR_temp, codeBlock);

    }


    @Override
    public String prettyPrint(String indentSpace) {
        String prettyString = "\n\n"+ indentSpace + "|--FunctionBlock\n";
        prettyString += "  " + indentSpace + "|--name: " + this.getName() + "\n";

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
        visitor.visitIrFunctionBlockDecl(this);
    }

    @Override
    public LlLocation generateLlIr(LlBuilder builder, LlSymbolTable symbolTable) {
        String name = this.getName();
        LlEmptyStmt emptyStmt = new LlEmptyStmt();
        builder.appendStatement(name, emptyStmt);
        LlEmptyStmt emptyStmtInit = new LlEmptyStmt();
        builder.appendStatement("Init", emptyStmtInit);

        if(this.varBlockVAR != null){
            this.varBlockVAR.generateLlIr(builder, symbolTable);
        }
        if (this.varBlockVAR_INPUT != null){
            this.varBlockVAR_INPUT.generateLlIr(builder, symbolTable);
        }
        if (this.varBlockVAR_OUTPUT != null){
            this.varBlockVAR_OUTPUT.generateLlIr(builder, symbolTable);
        }
        if (this.varBlockVAR_TEMP != null){
            this.varBlockVAR_TEMP.generateLlIr(builder, symbolTable);
        }
        if (this.varBlockVAR_INPUT_OUTPUT != null){
            this.varBlockVAR_INPUT_OUTPUT.generateLlIr(builder, symbolTable);
        }

        LlEmptyStmt emptyStmtBody = new LlEmptyStmt();
        builder.appendStatement("Body", emptyStmtBody);
        this.CodeBlock.generateLlIr(builder, symbolTable);
        return null;
    }
}
