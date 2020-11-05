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
    public LlLocation generateLlIr(LlBuilder builder, LlSymbolTable symbolTable) {

        String loopCondition = "WHILE_COND_" + builder.generateLabel();
        String startLoopLabel = "WHILE_" + builder.generateLabel();

        LlEmptyStmt forConditionalLabelStmt = new LlEmptyStmt();

        String endLoopLabel = "END_" + startLoopLabel;

        builder.appendStatement(loopCondition, forConditionalLabelStmt);


        //generate the goto Statemennt
        LlLocation conditionResultTemp = this.condExpr.generateLlIr(builder, symbolTable);
        LlJumpConditional conditionalJump = new LlJumpConditional(startLoopLabel, conditionResultTemp);

        builder.appendStatement(conditionalJump);

        //if the conditional evaluates to false, jump to the end of the loop
        LlJumpUnconditional endOfForLoopJump = new LlJumpUnconditional(endLoopLabel);
        builder.appendStatement(endOfForLoopJump);

        // Else execute the for loop body.
        LlEmptyStmt forStart = new LlEmptyStmt();
        builder.appendStatement(startLoopLabel, forStart);

        // if the condition fails, jump to the end of the fotr loop
        LlEmptyStmt endForLabelEmptyStmt = new LlEmptyStmt();


        // get into block
        builder.getInBlock(loopCondition);
        // generate the code block Ll

        this.stmtBody.generateLlIr(builder, symbolTable);



        // update the counter.

        builder.appendStatement(new LlJumpUnconditional(loopCondition));


        LlEmptyStmt emptyStmt = new LlEmptyStmt();

        // now add the end of loop label
        builder.appendStatement(endLoopLabel, emptyStmt);

        return null;
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
