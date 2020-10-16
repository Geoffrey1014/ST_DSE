package ir;

public class IrArgInputAssign extends IrArgAssign {
    private final IrIdent argName;
    public IrArgInputAssign(Object argValue, IrIdent argName) {
        super(argValue, argName.getLineNumber(), argName.getLineNumber());
        this.argName = argName;
    }
}
