package ir.Arg;

import ir.IrIdent;

public class IrArgInputAssign extends IrArgAssign {
    private final IrIdent argName;
    public IrArgInputAssign(Object argValue, IrIdent argName) {
        super(argValue, argName.getLineNumber(), argName.getLineNumber());
        this.argName = argName;
    }
}
