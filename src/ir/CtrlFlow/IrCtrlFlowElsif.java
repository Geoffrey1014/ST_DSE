package ir.CtrlFlow;

import ir.BaseVisitor;
import ir.IrCodeBlock;
import ir.IrExpr;

public class IrCtrlFlowElsif extends IrCtrlFlowIf {
    public IrCtrlFlowElsif(IrExpr condExpr, IrCodeBlock stmtBody) {
        super(condExpr, stmtBody);

    }
    @Override
    public void accept(BaseVisitor<Void> visitor) {
        visitor.visitIrCtrlFlowElsif(this);
    }

    @Override
    public String prettyPrint(String indentSpace) {
        String prettyString = indentSpace + "|--elsifStmt\n";

        // print the condition expr
        prettyString += ("  " + indentSpace + "|--condExpr\n");
        prettyString += (this.condExpr.prettyPrint("    " + indentSpace));

        // print the if loop body
        prettyString += "  " + indentSpace + "|--body\n";
        prettyString += this.stmtBody.prettyPrint("    " + indentSpace);

        return prettyString;
    }

}
