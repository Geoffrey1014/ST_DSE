package ll.assignStmt;


import ll.LlComponent;
import ll.location.LlLocation;

public class LlAssignStmtBinaryOp extends LlAssignStmt {

    private final LlComponent leftOperand;
    private final String operation;
    private final LlComponent rightOperand;

    public LlAssignStmtBinaryOp(LlLocation storeLocation, LlComponent leftOperand, String operation, LlComponent rightOperand) {
        super(storeLocation);
        this.leftOperand = leftOperand;
        this.operation = operation;
        this.rightOperand = rightOperand;
    }

    public LlComponent getLeftOperand() {
        return this.leftOperand;
    }

    public String getOperation() {
        return this.operation;
    }

    public LlComponent getRightOperand() {
        return this.rightOperand;
    }

    @Override
    public String toString() {
        return this.storeLocation.toString() + " = " + this.leftOperand.toString() + " " + this.operation + " " + this.rightOperand.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof LlAssignStmtBinaryOp)) {
            return false;
        }
        return ((LlAssignStmtBinaryOp) obj).rightOperand.equals(this.rightOperand)
                && ((LlAssignStmtBinaryOp) obj).leftOperand.equals(this.leftOperand)
                && ((LlAssignStmtBinaryOp) obj).operation.equals(this.operation)
                && ((LlAssignStmtBinaryOp) obj).storeLocation.equals(this.storeLocation);
    }

    @Override
    public int hashCode() {
        return this.rightOperand.hashCode()
                * this.leftOperand.hashCode()
                * this.operation.hashCode()
                * this.storeLocation.hashCode();
    }



}