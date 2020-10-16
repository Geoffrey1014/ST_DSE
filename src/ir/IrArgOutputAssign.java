package ir;

public class IrArgOutputAssign extends IrArg {
    private final IrIdent fbOutput;
    private final IrIdent acceptLocation;
    public IrArgOutputAssign(int lineNumber, int colNumber, IrIdent fbOutput, IrIdent acceptLocation) {
        super(lineNumber, colNumber);
        this.fbOutput = fbOutput;
        this.acceptLocation = acceptLocation;
    }
}
