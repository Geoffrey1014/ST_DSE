package ir;

public abstract class IrAssignStmt extends IrStmt {
    private final IrLocation storeLocation;
    public IrAssignStmt( IrLocation storeLocation) {
        super(storeLocation.getLineNumber(), storeLocation.getColNumber());
        this.storeLocation = storeLocation;
    }
}
