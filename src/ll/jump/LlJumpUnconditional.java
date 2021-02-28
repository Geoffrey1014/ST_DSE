package ll.jump;


import simulation.LlStatementVisitor;
import simulation.Memory;

public class LlJumpUnconditional extends LlJump {
    public LlJumpUnconditional(String jumpToLabel) {
        super(jumpToLabel);
    }

    @Override
    public String toString() {
        return "goto " + this.jumpToLabel.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof LlJumpUnconditional)) {
            return false;
        }
        return ((LlJumpUnconditional) obj).jumpToLabel.equals(this.jumpToLabel);

    }

    @Override
    public int hashCode() {
        return this.jumpToLabel.hashCode();
    }


    @Override
    public void exe(Memory memory){

    }

    @Override
    public void accept(LlStatementVisitor llStatementVisitor, Memory memory) {
        llStatementVisitor.visitor(this, memory);
    }
}
