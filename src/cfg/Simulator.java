package cfg;

import ll.LlStatement;

/**
在CFG上模拟执行
 需要模拟内存状态，使用map
 处理分枝
 调用每个LLIR 的exe 函数

 */
public class Simulator {
    private Memory memory; // key 暂时设置成integer
    private final CFG cfg;
    private final LlStatementExeutor llStatementExeutor;


    public Simulator(CFG cfg, Memory memory, LlStatementExeutor llStatementExeutor) {
        this.memory = memory;
        this.cfg = cfg;
        this.llStatementExeutor = llStatementExeutor;
    }

    /**
     * 我觉得应该使用访问者模式
     * 返回执行过程中是否有错误@return
     */
    public void execute(){
        BasicBlock entryNode = this.cfg.getBasicBlocks().get(0);
        BasicBlock startNode = entryNode.getDefaultBranch();
        executeBasicBlock(startNode);


    }

    public void executeBasicBlock(BasicBlock currentBolock){
        for(LlStatement llStatement :currentBolock.getStmtsList()){
            llStatement.accept(llStatementExeutor,this.memory);
        }
    }

}
