package ll.assignStmt;


import ll.LlStatement;
import ll.location.LlLocation;

public abstract class LlAssignStmt extends LlStatement {

    protected final LlLocation storeLocation;

    public LlAssignStmt(LlLocation storeLocation) {
        this.storeLocation = storeLocation;
    }

    public LlLocation getStoreLocation() {
        return this.storeLocation;
    }



}
