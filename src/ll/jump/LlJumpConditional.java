package ll.jump;


import ll.LlComponent;

public class LlJumpConditional extends LlJump {

    private final LlComponent condition;

    public LlJumpConditional(String jumpToLabel, LlComponent condition) {
        super(jumpToLabel);
        this.condition = condition;
    }

    public LlComponent getCondition() {
        return this.condition;
    }

    @Override
    public String toString() {
        return "if " + condition.toString() + " goto " + this.jumpToLabel.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof LlJumpConditional)) {
            return false;
        }
        return ((LlJumpConditional) obj).jumpToLabel.equals(this.jumpToLabel);
    }

    @Override
    public int hashCode() {
        return this.jumpToLabel.hashCode();
    }

}
