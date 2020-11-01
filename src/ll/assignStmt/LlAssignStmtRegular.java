package ll.assignStmt;

import ll.LlComponent;
import ll.location.LlLocation;

public class LlAssignStmtRegular extends LlAssignStmt {

    private final LlComponent operand;

    public LlAssignStmtRegular(LlLocation storeLocation, LlComponent operand) {
        super(storeLocation);
        this.operand = operand;
    }

    public LlComponent getOperand() {
        return operand;
    }

    @Override
    public String toString() {
        return this.storeLocation.toString() + " = " + this.operand.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof LlAssignStmtRegular)) {
            return false;
        }
        return ((LlAssignStmtRegular) obj).operand.equals(this.operand)
                && ((LlAssignStmtRegular) obj).storeLocation.equals(this.storeLocation);
    }

    @Override
    public int hashCode() {
        return this.operand.hashCode() * this.storeLocation.hashCode();
    }

}
