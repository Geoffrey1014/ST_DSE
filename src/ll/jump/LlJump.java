package ll.jump;


import ll.LlStatement;

public abstract class LlJump extends LlStatement {
    protected final String jumpToLabel;

    public LlJump(String jumpToLabel) {
        this.jumpToLabel = jumpToLabel;
    }

    public String getJumpToLabel() {
        return jumpToLabel;
    }


}
