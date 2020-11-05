package ir.CtrlFlow;

import helper.LlBuilder;
import helper.LlSymbolTable;
import ir.IrCodeBlock;
import ir.IrStmt;
import ir.Location.IrLocationVar;
import ll.location.LlLocation;
import visitor.BaseVisitor;

public class IrCtrlFlowFor extends IrStmt {
    private final IrLocationVar counter;
    private final IrCtrlFlowForRange range;
    private final IrCodeBlock codeBlock;


    public IrCtrlFlowFor(IrLocationVar counter, IrCtrlFlowForRange range, IrCodeBlock codeBlock) {

        super( counter.getLineNumber(), counter.getColNumber());
        this.counter = counter;
        this.range = range;
        this.codeBlock = codeBlock;
    }


    @Override
    public String prettyPrint(String indentSpace) {

        String prettyString = indentSpace + "|--forLoop\n";

        // print the counter and range stmt
        prettyString += ("  " + indentSpace + "|--counter\n");
        prettyString += (this.counter.prettyPrint("    " + indentSpace));
        prettyString += ("    " + indentSpace + "|--range\n");
        prettyString += (this.range.prettyPrint("    " + indentSpace));


        // print the for loop body
        prettyString += "  " + indentSpace + "|--body\n";
        prettyString += this.codeBlock.prettyPrint("    " + indentSpace);

        return prettyString;
    }

    @Override
    public void accept(BaseVisitor<Void> visitor) {
        visitor.visitIrCtrlFlowFor(this);
    }

    @Override
    public LlLocation generateLlIr(LlBuilder builder, LlSymbolTable symbolTable) {
        return null;
    }

    public IrLocationVar getCounter() {
        return counter;
    }

    public IrCtrlFlowForRange getRange() {
        return range;
    }

    public IrCodeBlock getCodeBlock() {
        return codeBlock;
    }
}
