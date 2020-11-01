package ir.CtrlFlow;

import ir.IrCodeBlock;
import ir.IrExpr;
import visitor.BaseVisitor;

/**
 */
public class IrCtrlFlowWhile extends IrCtrlFlow {
    public IrCtrlFlowWhile(IrExpr condExpr, IrCodeBlock stmtBody) {
        super(condExpr, stmtBody);
    }


    @Override
    public void accept(BaseVisitor<Void> visitor) {
        visitor.visitIrCtrlFlowWhile(this);
    }

    @Override
    public String prettyPrint(String indentSpace) {

        String prettyString = indentSpace + "|--whileStmt\n";

        // print the condition expr
        prettyString += ("  " + indentSpace + "|--condExpr\n");
        prettyString += (this.condExpr.prettyPrint("    " + indentSpace));

        // print the while loop body
        prettyString += "  " + indentSpace + "|--body\n";
        prettyString += this.stmtBody.prettyPrint("    " + indentSpace);

        return prettyString;
    }

}
