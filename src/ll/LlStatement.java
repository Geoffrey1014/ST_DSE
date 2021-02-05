package ll;

import cfg.LlStatementVisitor;
import simulation.Memory;

/**
 * Created by geo 2020-11-01 13:29:50
 */
public abstract class LlStatement extends Ll {
    public abstract void exe(Memory memory);
    public abstract void accept(LlStatementVisitor llStatementVisitor, Memory memory);
}
