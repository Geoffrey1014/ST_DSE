package ir;

public class IrArgAssignInputAssign extends IrArgAssign {
    private final IrIdent argName;
    public IrArgAssignInputAssign(Object argValue, IrIdent argName) {
        super(argValue, argName.getLineNumber(), argName.getLineNumber());
        this.argName = argName;
    }
}
