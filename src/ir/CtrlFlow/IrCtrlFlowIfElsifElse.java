package ir.CtrlFlow;

import ir.BaseVisitor;
import ir.IrCodeBlock;
import ir.IrExpr;

import java.util.ArrayList;

public class IrCtrlFlowIfElsifElse extends IrCtrlFlowIf {
    private  final ArrayList<IrCtrlFlowElsif> elsifArrayList;
    private  final IrCodeBlock elseBlock;
    public IrCtrlFlowIfElsifElse(IrExpr condExpr, IrCodeBlock stmtBody,
                                 ArrayList<IrCtrlFlowElsif> elsifArrayList, IrCodeBlock elseBlock) {
        super(condExpr, stmtBody);
        this.elsifArrayList = elsifArrayList;
        this.elseBlock = elseBlock;
    }

    public IrCodeBlock getElseBlock() {
        return elseBlock;
    }

    public ArrayList<IrCtrlFlowElsif> getElsifArrayList() {
        return elsifArrayList;
    }

    @Override
    public void accept(BaseVisitor<Void> visitor) {
        visitor.visitIrCtrlFlowIfElsifElse(this);
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

        // print the else body
        prettyString.append(indentSpace).append("|--elseBody\n");
        prettyString.append(this.elseBlock.prettyPrint("  " + indentSpace));


        return prettyString.toString();
    }
}
