package simulation;

import cfg.CFG;

public class ConcreteExecutor {
    private final CFG cfg;
    private final LlStatementExeutor llStatementExeutor;
    public ConcreteExecutor(CFG cfg) {
        this.cfg = cfg;
        this.llStatementExeutor = new LlStatementExeutor();
    }


}
