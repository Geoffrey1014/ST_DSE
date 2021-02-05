package ll;


import cfg.LlStatementVisitor;
import simulation.Memory;

public class LlEmptyStmt extends LlStatement {
    @Override
    public String toString() {
        return "EMPTY_STATEMENT";
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof LlEmptyStmt;
    }

    @Override
    public int hashCode() {
        return 17; // some arbitrary prime;
    }

    @Override
    public void exe(Memory memory){

    }

    @Override
    public void accept(LlStatementVisitor llStatementVisitor, Memory memory) {
        llStatementVisitor.visitor(this,memory);
    }
}
