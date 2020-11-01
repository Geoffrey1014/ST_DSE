package ir.CtrlFlow;

import ir.IrCodeBlock;
import ir.IrExpr;
import visitor.BaseVisitor;

import java.util.ArrayList;

public class IrCtrlFlowIfElsif extends  IrCtrlFlowIf{
    private  final ArrayList<IrCtrlFlowElsif> elsifArrayList;
    public IrCtrlFlowIfElsif(IrExpr condExpr, IrCodeBlock stmtBody, ArrayList<IrCtrlFlowElsif> elsifArrayList) {
        super(condExpr, stmtBody);
        this.elsifArrayList = elsifArrayList;
    }

    public ArrayList<IrCtrlFlowElsif> getElsifArrayList() {
        return elsifArrayList;
    }

    @Override
    public void accept(BaseVisitor<Void> visitor) {
        visitor.visitIrCtrlFlowIfElsif(this);
    }

    @Override
    public String prettyPrint(String indentSpace) {
        StringBuilder prettyString = new StringBuilder(indentSpace + "|--ifStmt\n");

        // print the condition expr
        prettyString.append("  ").append(indentSpace).append("|--condExpr\n");
        prettyString.append(this.condExpr.prettyPrint("    " + indentSpace));

        // print the if loop body
        prettyString.append("  ").append(indentSpace).append("|--body\n");
        prettyString.append(this.stmtBody.prettyPrint("    " + indentSpace));

        // print elsif list
        for (IrCtrlFlowElsif ctrlFlowElsif : this.elsifArrayList){
            prettyString.append("  ").append(indentSpace).append("|--elsif\n");
            prettyString.append(ctrlFlowElsif.prettyPrint("    " + indentSpace));
        }

        return prettyString.toString();
    }
}
