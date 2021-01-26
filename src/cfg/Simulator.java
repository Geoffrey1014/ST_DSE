package cfg;

import ll.LlComponent;
import ll.LlStatement;
import ll.assignStmt.LlAssignStmt;
import ll.jump.LlJumpConditional;
import ll.literal.LlLiteral;

/**
在CFG上模拟执行
 需要模拟内存状态，使用map
 处理分枝
 调用每个LLIR 的exe 函数

 */
public class Simulator {
    private Memory memory;
    private final CFG cfg;
    private final LlStatementExeutor llStatementExeutor;


    public Simulator(CFG cfg, Memory memory, LlStatementExeutor llStatementExeutor) {
        this.memory = memory;
        this.cfg = cfg;
        this.llStatementExeutor = llStatementExeutor;
    }

    /**
     * 使用访问者模式
     * @return
     */
    public void execute(){
        BasicBlock entryNode = this.cfg.getBasicBlocks().get(0);
        BasicBlock nextBlock = entryNode.getDefaultBranch();
        while (nextBlock != null){
            nextBlock = executeBasicBlock(nextBlock);
        }

    }

    public BasicBlock executeBasicBlock(BasicBlock currentBolock){
        BasicBlock nextBlock = null;
        for(LlStatement llStatement :currentBolock.getStmtsList()){
            if(llStatement instanceof LlAssignStmt){
                llStatement.accept(llStatementExeutor,this.memory);
            }
            else if (llStatement instanceof LlJumpConditional){
                LlComponent condition = ((LlJumpConditional) llStatement).getCondition();
                ValueOfDiffType conditionValue = null;
                if ( condition instanceof LlLiteral){
                    conditionValue = this.llStatementExeutor.getLlLiteralValue((LlLiteral) condition);
                }
                else{
                    conditionValue = this.memory.getLocationvalue(condition);
                }

                llStatement.accept(llStatementExeutor, this.memory);
                // get the next bb according to the condition
                if(conditionValue.getvBoolean()){
                    // getAlternativeBranch and getDefaultBranch have been done in creating CFG
//                    nextBlock = this.cfg.getLeadersToBBMap().get(((LlJumpConditional) llStatement).getJumpToLabel());
                    nextBlock = currentBolock.getAlternativeBranch();
                }
                else{
                    nextBlock = currentBolock.getDefaultBranch();
                }
            }
            else {
                nextBlock = currentBolock.getDefaultBranch();
            }

        }
        return nextBlock;
    }

}
