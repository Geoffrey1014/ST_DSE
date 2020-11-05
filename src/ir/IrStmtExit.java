package ir;


import helper.LlBuilder;
import helper.LlSymbolTable;
import ll.jump.LlJumpUnconditional;
import ll.location.LlLocation;
import visitor.BaseVisitor;

/**
 */
public class IrStmtExit extends IrStmt {
    public IrStmtExit(int lineNumber, int colNumber) {
        super(lineNumber, colNumber);
    }



    @Override
    public String prettyPrint(String indentSpace) {
        String prettyString = indentSpace + "|--breakStmt";
        return prettyString;
    }

    @Override
    public void accept(BaseVisitor<Void> visitor) {

    }

    @Override
    public LlLocation generateLlIr(LlBuilder builder, LlSymbolTable symbolTable) {
        String endBlock = "END_" + builder.getCurrentBlock();
        LlJumpUnconditional unconditionalJump = new LlJumpUnconditional(endBlock);
        builder.appendStatement(unconditionalJump);

        return null;
    }

}
