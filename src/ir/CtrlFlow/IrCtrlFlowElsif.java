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

    @Override
    public LlLocation generateLlIr(LlBuilder builder, LlSymbolTable symbolTable) {
        // condition goto(label)
        // if not go to end of the if block
        String elsifBlockLabel = "ELSIF_" + builder.generateLabel();
        String endIfLabel = "END_" + elsifBlockLabel;

        // Generate the conditional statement.
        LlLocation conditionalTemp = this.condExpr.generateLlIr(builder, symbolTable);
        LlJumpConditional conditionalJump = new LlJumpConditional(elsifBlockLabel, conditionalTemp);
        builder.appendConditionJumpStatement(conditionalJump);

        // if the conditional doesnt work, jump to the end of the block.
        LlJumpUnconditional unconditionalJump = new LlJumpUnconditional(endIfLabel);
        builder.appendUnConditionJumpStatement(unconditionalJump);

        // add the label to the if body block
        LlEmptyStmt emptyStmt = new LlEmptyStmt();
        builder.appendStatement(elsifBlockLabel, emptyStmt);

        //  finally generate the if statement body itself
        this.stmtBody.generateLlIr(builder, symbolTable);

        // after the elsif block is executed, jump to the end of the if-elsif block.
        String endBlock = "END_" + builder.getCurrentBlock();
        LlJumpUnconditional unconditionalJumpEndIf = new LlJumpUnconditional(endBlock);
        builder.appendUnConditionJumpStatement(unconditionalJumpEndIf);

        // append end if label
        LlEmptyStmt endIfEmptyStmt = new LlEmptyStmt();
        builder.appendStatement(endIfLabel, endIfEmptyStmt);

        return null;
    }

}
