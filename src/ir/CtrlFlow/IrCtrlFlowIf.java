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

public class IrCtrlFlowIf extends IrCtrlFlow {
    public IrCtrlFlowIf(IrExpr condExpr, IrCodeBlock stmtBody) {
        super(condExpr, stmtBody);
    }
    public IrExpr getIfCondition() {
        return this.condExpr;
    }

    public IrCodeBlock getIfBodyBlock() {
        return this.stmtBody;
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

        return prettyString;
    }

    @Override
    public void accept(BaseVisitor<Void> visitor) {
        visitor.visitIrCtrlFlowIf(this);
    }

    @Override
    public LlLocation generateLlIr(LlBuilder builder, LlSymbolTable symbolTable) {
        // condition goto(label)
        // if not, go to end of the if block
        String ifBlockLabel = "IF_" + builder.generateLabel();
        String endIfLabel = "END_" + ifBlockLabel;

        // Generate the conditional statement.
        LlLocation conditionalTemp = this.condExpr.generateLlIr(builder, symbolTable);
        LlJumpConditional conditionalJump = new LlJumpConditional(ifBlockLabel, conditionalTemp);
        builder.appendConditionJumpStatement(conditionalJump);

        // if the conditional doesnt work, jump to the end of the block.
        LlJumpUnconditional unconditionalJump = new LlJumpUnconditional(endIfLabel);
        builder.appendUnConditionJumpStatement(unconditionalJump);

        // add the label to the if body block
        LlEmptyStmt emptyStmt = new LlEmptyStmt();
        builder.appendStatement(ifBlockLabel, emptyStmt);

        //  finally generate the if statement body itself
        this.stmtBody.generateLlIr(builder, symbolTable);

        // append end if label
        LlEmptyStmt endIfEmptyStmt = new LlEmptyStmt();
        builder.appendStatement(endIfLabel, endIfEmptyStmt);

        // return the last known ...
        return null;
    }
}
