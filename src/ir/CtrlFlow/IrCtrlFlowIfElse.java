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

    @Override
    public LlLocation generateLlIr(LlBuilder builder, LlSymbolTable symbolTable) {
        // condition goto(label)
        // if not go to end of the if block
        String ifBlockLabel = "IF_" + builder.generateLabel();
        String endIfLabel = "END_" + ifBlockLabel;

        String elseBlockLabel = "ELSE_" + builder.generateLabel();

        // Generate the conditional statement.
        LlLocation conditionalTemp = this.condExpr.generateLlIr(builder, symbolTable);
        LlJumpConditional conditionalJump = new LlJumpConditional(ifBlockLabel, conditionalTemp);
        builder.appendStatement(conditionalJump);

        LlEmptyStmt elseLabelEmptyStmt = new LlEmptyStmt();
        LlJumpUnconditional unconditionalJumpToElse = new LlJumpUnconditional(elseBlockLabel);
        builder.appendStatement(unconditionalJumpToElse);

        builder.appendStatement(elseBlockLabel, elseLabelEmptyStmt);
        this.elseBlock.generateLlIr(builder, symbolTable);

        // after the else block is executed, jump to the end of the if block.
        LlJumpUnconditional unconditionalJump = new LlJumpUnconditional(endIfLabel);
        builder.appendStatement(unconditionalJump);

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
