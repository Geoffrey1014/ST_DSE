package ll.assignStmt;

import cfg.BasicTypeEnum;
import cfg.LlStatementVisitor;
import cfg.Memory;
import cfg.ValueOfDiffType;
import ll.LlComponent;
import ll.literal.*;
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
        String s = this.storeLocation.toString();
        return s + " = " + this.operand.toString();
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

    /**
     * maybe it is better to put it in the LLLiteral class
     * @param operand
     * @return
     */
    private ValueOfDiffType getLlLiteralValue(LlLiteral operand) {
        if (operand instanceof LlLiteralBool) {
            return new ValueOfDiffType(((LlLiteralBool) operand).getBoolValue());
        } else if (operand instanceof LlLiteralInt) {
            return new ValueOfDiffType((int) ((LlLiteralInt) operand).getIntValue());
        } else if (operand instanceof LlLiteralReal) {
            return new ValueOfDiffType((float) ((LlLiteralReal) operand).getRealValue());
        } else if (operand instanceof LlLiteralString) {
            return new ValueOfDiffType(((LlLiteralString) operand).getStringValue());
        }
        return null;
    }

    private ValueOfDiffType genLocationValue(BasicTypeEnum type, ValueOfDiffType value) {
        switch (type) {
            case INTEGER:
                return new ValueOfDiffType(value.getvInteger());

            case FLOAT:
                return new ValueOfDiffType(value.getvFloat());
        }
        return null;
    }

    @Override
    public void exe(Memory memory){
        ValueOfDiffType right;
        if (this.operand instanceof LlLiteral) {
            right = getLlLiteralValue((LlLiteral) this.operand);
        } else {
            ValueOfDiffType leftValue = memory.getLocationvalue(this.operand);
            right = genLocationValue(leftValue.getType(), leftValue);
        }
        memory.put(this.storeLocation, right);

    }

    @Override
    public void accept(LlStatementVisitor llStatementVisitor, Memory memory) {
        llStatementVisitor.visitor(this, memory);
    }
}
