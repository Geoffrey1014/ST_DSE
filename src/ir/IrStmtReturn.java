package ir;


import helper.LlBuilder;
import helper.LlSymbolTable;
import ll.LlReturn;
import ll.location.LlLocation;
import visitor.BaseVisitor;

public class IrStmtReturn extends IrStmt {
    public IrStmtReturn(int lineNumber, int colNumber) {
        super(lineNumber, colNumber);
    }


    @Override
    public String prettyPrint(String indentSpace) {
        String prettyString = indentSpace + "|--returnStmt";
        return prettyString;
    }

    @Override
    public void accept(BaseVisitor<Void> visitor) {

    }

    @Override
    public LlLocation generateLlIr(LlBuilder builder, LlSymbolTable symbolTable) {

        LlReturn returnStatement = new LlReturn();
        builder.appendStatement(returnStatement);return null;
    }
}