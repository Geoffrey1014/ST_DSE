package ir.CtrlFlow;

import helper.LlBuilder;
import helper.LlSymbolTable;
import ir.IrCodeBlock;
import ir.IrStmt;
import ir.Location.IrLocationVar;
import ll.LlComponent;
import ll.LlEmptyStmt;
import ll.assignStmt.LlAssignStmtBinaryOp;
import ll.assignStmt.LlAssignStmtRegular;
import ll.jump.LlJumpConditional;
import ll.jump.LlJumpUnconditional;
import ll.literal.LlLiteralInt;
import ll.location.LlLocation;
import ll.location.LlLocationVar;
import visitor.BaseVisitor;

public class IrCtrlFlowFor extends IrStmt {
    private final IrLocationVar counter;
    private final IrCtrlFlowForRange range;
    private final IrCodeBlock codeBlock;


    public IrCtrlFlowFor(IrLocationVar counter, IrCtrlFlowForRange range, IrCodeBlock codeBlock) {

        super( counter.getLineNumber(), counter.getColNumber());
        this.counter = counter;
        this.range = range;
        this.codeBlock = codeBlock;
    }


    @Override
    public String prettyPrint(String indentSpace) {

        String prettyString = indentSpace + "|--forLoop\n";

        // print the counter and range stmt
        prettyString += ("  " + indentSpace + "|--counter\n");
        prettyString += (this.counter.prettyPrint("    " + indentSpace));
        prettyString += ("    " + indentSpace + "|--range\n");
        prettyString += (this.range.prettyPrint("    " + indentSpace));


        // print the for loop body
        prettyString += "  " + indentSpace + "|--body\n";
        prettyString += this.codeBlock.prettyPrint("    " + indentSpace);

        return prettyString;
    }

    @Override
    public void accept(BaseVisitor<Void> visitor) {
        visitor.visitIrCtrlFlowFor(this);
    }

    /**
     * 1）先给 counter 初始化
     * 2）判断 counter 是否越界，如果越界则跳出loop
     * 3）计算循环体
     * 4）counter 加上 step， 返回 2）
     *
     * @param builder
     * @param symbolTable
     * @return
     */
    @Override
    public LlLocation generateLlIr(LlBuilder builder, LlSymbolTable symbolTable) {
        String loopCondition = "FOR_COND_" +  builder.generateLabel();
        String startLoopLabel = "FOR_" + builder.generateLabel();
        String endLoopLabel = "END_" +startLoopLabel;
        LlEmptyStmt emptyStmt = new LlEmptyStmt();

//        1）先给 counter 初始化
        LlLocationVar llCounter = (LlLocationVar) this.counter.generateLlIr(builder, symbolTable);
        LlLocation llLow = this.range.getLow().generateLlIr(builder, symbolTable);
        builder.appendStatement(new LlAssignStmtRegular(llCounter, llLow));

        // 添加标签
        builder.appendStatement(loopCondition, emptyStmt );

        //判断 counter 是否越界，如果越界则跳出loop
        LlLocation llHigh = this.range.getHigh().generateLlIr(builder, symbolTable);
        LlLocation condition = builder.generateTemp() ;
        builder.appendStatement(new LlAssignStmtBinaryOp(condition ,llCounter, "<", llHigh ));

        builder.appendStatement(new LlJumpConditional(startLoopLabel, condition));
        builder.appendStatement(new LlJumpUnconditional(endLoopLabel));

        // 3）计算循环体
        builder.appendStatement(startLoopLabel, new LlEmptyStmt());
        builder.getInBlock(startLoopLabel);
        this.codeBlock.generateLlIr(builder, symbolTable);
        builder.getOutOfBlock();

        // 4）counter 加上 step， 返回 2）
        if (this.range.getStep() != null){
            LlLocation llStep = this.range.getStep().generateLlIr(builder, symbolTable);
            builder.appendStatement(new LlAssignStmtBinaryOp(llCounter, llCounter, "+", llStep));
        }
        else{
            LlComponent llStep = new LlLiteralInt(this.range.stepNum);
            builder.appendStatement(new LlAssignStmtBinaryOp(llCounter, llCounter, "+", llStep));
        }

        builder.appendStatement(new LlJumpUnconditional(loopCondition));
        builder.appendStatement(endLoopLabel, new LlEmptyStmt());

        return null;
    }

    public IrLocationVar getCounter() {
        return counter;
    }

    public IrCtrlFlowForRange getRange() {
        return range;
    }

    public IrCodeBlock getCodeBlock() {
        return codeBlock;
    }
}
