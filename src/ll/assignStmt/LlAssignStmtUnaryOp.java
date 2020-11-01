package ll.assignStmt;


import ll.LlComponent;
import ll.location.LlLocation;

public class LlAssignStmtUnaryOp extends LlAssignStmt {

    private final LlComponent operand;
    private final String operator;

    public LlAssignStmtUnaryOp(LlLocation storeLocation, LlComponent operand, String operator) {
        super(storeLocation);
        this.operand = operand;
        this.operator = operator;
    }

    public LlComponent getOperand() {
        return this.operand;
    }

    public String getOperator() {
        return this.operator;
    }

    @Override
    public String toString() {
        return this.storeLocation.toString() + " = " + operator + " " + operand.toString();
    }


    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof LlAssignStmtUnaryOp)) {
            return false;
        }
        return ((LlAssignStmtUnaryOp) obj).operand.equals(this.operand)
                && ((LlAssignStmtUnaryOp) obj).operator.equals(this.operator)
                && ((LlAssignStmtUnaryOp) obj).storeLocation.equals(this.storeLocation);
    }

    @Override
    public int hashCode() {
        return this.operator.hashCode() * this.operand.hashCode() * this.storeLocation.hashCode();
    }

    private boolean isRegister(String loc) {
        String r = "%r12 %r13 %r14 %r15 %rbx";
        return r.contains(loc);

    }

}
