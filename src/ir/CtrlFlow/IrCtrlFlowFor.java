package ir.CtrlFlow;

import helper.LlBuilder;
import helper.LlSymbolTable;
import ir.IrCodeBlock;
import ir.IrStmt;
import ir.Literal.IrIntLiteral;
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
     */
    @Override
    public LlLocation generateLlIr(LlBuilder builder, LlSymbolTable symbolTable) {
        String loopCondition = "FOR_COND_" +  builder.generateLabel();
        String startLoopLabel = "FOR_" + builder.generateLabel();
        String endLoopLabel = "END_" +startLoopLabel;
        LlEmptyStmt emptyStmt = new LlEmptyStmt();

//        1）先给 counter 初始化
        LlLocationVar llCounter = (LlLocationVar) this.counter.generateLlIr(builder, symbolTable);
        LlLocation llLeft = this.range.getLeft().generateLlIr(builder, symbolTable);
        builder.appendStatement(new LlAssignStmtRegular(llCounter, llLeft));

        // 添加标签
        builder.appendStatement(loopCondition, emptyStmt );

        //判断 counter 是否越界，如果越界则跳出loop
        LlLocation llRight = this.range.getRight().generateLlIr(builder, symbolTable);
        LlLocation condition = builder.generateTemp() ;
        if(this.range.getStep() != null && ((IrIntLiteral)this.range.getStep()).getValue() < 0){
            builder.appendStatement(new LlAssignStmtBinaryOp(condition ,llCounter, ">", llRight ));
        }
        else{
            builder.appendStatement(new LlAssignStmtBinaryOp(condition ,llCounter, "<", llRight ));
        }


        builder.appendConditionJumpStatement(new LlJumpConditional(startLoopLabel, condition));
        builder.appendUnConditionJumpStatement(new LlJumpUnconditional(endLoopLabel));

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

        // 添加结束标签
        builder.appendUnConditionJumpStatement(new LlJumpUnconditional(loopCondition));
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
