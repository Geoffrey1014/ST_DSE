package ir;

import ir.Location.IrLocation;

public abstract class IrAssignStmt extends IrStmt {
    private final IrLocation storeLocation;
    public IrAssignStmt( IrLocation storeLocation, int lineNumber, int colNumber) {
        super(lineNumber, colNumber);
        this.storeLocation = storeLocation;
    }

    public IrLocation getStoreLocation() {
        return storeLocation;
    }
}
