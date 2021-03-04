package ir.CtrlFlow;

import helper.LlBuilder;
import helper.LlSymbolTable;
import ir.IrCodeBlock;
import ir.IrExpr;
import ll.LlEmptyStmt;
import ll.jump.LlJumpConditional;
import ll.jump.LlJumpUnconditional;
import ll.location.LlLocation;
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

    @Override
    public LlLocation generateLlIr(LlBuilder builder, LlSymbolTable symbolTable) {
        // condition goto(label)
        // if not go to end of the if block
        String ifBlockLabel = "IF_" + builder.generateLabel();
        String endIfLabel = "END_" + ifBlockLabel;

        // Generate the conditional statement.
        LlLocation conditionalTemp = this.condExpr.generateLlIr(builder, symbolTable);
        LlJumpConditional conditionalJump = new LlJumpConditional(ifBlockLabel, conditionalTemp);
        builder.appendConditionJumpStatement(conditionalJump);


        // if the conditional doesnt work, go to the elsif part
        String elsifBlockLabel = "START_ELSIF_" + builder.generateLabel();
        LlJumpUnconditional unconditionalJumpElseif = new LlJumpUnconditional(elsifBlockLabel);
        builder.appendUnConditionJumpStatement(unconditionalJumpElseif);

        // add the label to the if body block
        LlEmptyStmt emptyStmt = new LlEmptyStmt();
        builder.appendStatement(ifBlockLabel, emptyStmt);

        //   generate the if statement body itself
        this.stmtBody.generateLlIr(builder, symbolTable);
        // jump to the end of this if_elsif block
        LlJumpUnconditional unconditionalJumpEnd = new LlJumpUnconditional(endIfLabel);
        builder.appendUnConditionJumpStatement(unconditionalJumpEnd);

        // generate all elsif part
        builder.getInBlock(ifBlockLabel);
        builder.appendStatement(elsifBlockLabel, emptyStmt);
        for (IrCtrlFlowElsif ctrlFlowElsif : this.elsifArrayList){
//            LlEmptyStmt elsifLabelEmptyStmt = new LlEmptyStmt();
//            builder.appendStatement(elsifBlockLabel, elsifLabelEmptyStmt);

            ctrlFlowElsif.generateLlIr(builder, symbolTable);

        }
        builder.getOutOfBlock();

        // append end-if label
        LlEmptyStmt endIfEmptyStmt = new LlEmptyStmt();
        builder.appendStatement(endIfLabel, endIfEmptyStmt);

        // return the last known ...
        return null;
    }

}
