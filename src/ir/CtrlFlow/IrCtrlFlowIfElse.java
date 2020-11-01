package ir.CtrlFlow;

import ir.IrCodeBlock;
import ir.IrExpr;
import visitor.BaseVisitor;

/**
 */
public class IrCtrlFlowIfElse extends IrCtrlFlow {
    private final IrCodeBlock elseBlock;

    public IrCtrlFlowIfElse(IrExpr condExpr, IrCodeBlock stmtBody, IrCodeBlock elseBlock) {
        super(condExpr, stmtBody);
        this.elseBlock = elseBlock;
    }



    public IrCodeBlock getElseBlock() {
        return elseBlock;
    }


    @Override
    public String prettyPrint(String indentSpace) {


        String prettyString = indentSpace + "|--ifStmt\n";

        // print the condition expr
        prettyString += ("  " + indentSpace + "|--condExpr\n");
        prettyString += (this.condExpr.prettyPrint("    " + indentSpace));

        // print the if loop body
        prettyString += "  " + indentSpace + "|--body\n";
        prettyString += this.stmtBody.prettyPrint("    " + indentSpace);
        // print the else body
        prettyString += indentSpace + "|--elseBody\n";
        prettyString += this.elseBlock.prettyPrint("  " + indentSpace);

        return prettyString;
    }

    @Override
    public void accept(BaseVisitor<Void> visitor) {
        visitor.visitIrCtrlFlowIfElse(this);
    }
}
