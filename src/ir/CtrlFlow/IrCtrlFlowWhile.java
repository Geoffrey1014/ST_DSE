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

        String loopCondition = "WHILE_" + builder.generateLabel();
        String startLoopLabel = "LOOP_" + builder.generateLabel();
        String endWhileLabel = "END_" + loopCondition;

        LlEmptyStmt whileConditionalLabelStmt = new LlEmptyStmt();
        builder.appendStatement(loopCondition, whileConditionalLabelStmt);

        //generate the goto Statemennt
        LlLocation conditionResultTemp = this.condExpr.generateLlIr(builder, symbolTable);
        LlJumpConditional conditionalJump = new LlJumpConditional(startLoopLabel, conditionResultTemp);
        builder.appendConditionJumpStatement(conditionalJump);

        //if the conditional evaluates to false, jump to the end of the loop
        LlJumpUnconditional endOfWhileLoopJump = new LlJumpUnconditional(endWhileLabel);
        builder.appendUnConditionJumpStatement(endOfWhileLoopJump);

        // Else execute the for loop body.
        LlEmptyStmt forStart = new LlEmptyStmt();
        builder.appendStatement(startLoopLabel, forStart);


        // get into block
        builder.getInBlock(loopCondition);
        // generate the code block Ll
        this.stmtBody.generateLlIr(builder, symbolTable);
        //  get out of block
        builder.getOutOfBlock();


        builder.appendUnConditionJumpStatement(new LlJumpUnconditional(loopCondition));


        LlEmptyStmt emptyStmt = new LlEmptyStmt();

        // now add the end of loop label
        builder.appendStatement(endWhileLabel, emptyStmt);

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
