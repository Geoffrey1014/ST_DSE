package ll.assignStmt;


import ll.LlComponent;
import ll.literal.*;
import ll.location.LlLocation;
import simulation.BasicTypeEnum;
import simulation.LlStatementVisitor;
import simulation.Memory;
import simulation.ValueOfDiffType;

public class LlAssignStmtBinaryOp extends LlAssignStmt {

    private final LlComponent leftOperand;
    private final String operator;
    private final LlComponent rightOperand;

    public LlAssignStmtBinaryOp(LlLocation storeLocation, LlComponent leftOperand, String operator, LlComponent rightOperand) {
        super(storeLocation);
        this.leftOperand = leftOperand;
        this.operator = operator;
        this.rightOperand = rightOperand;
    }

    public LlComponent getLeftOperand() {
        return this.leftOperand;
    }

    public String getOperator() {
        return this.operator;
    }

    public LlComponent getRightOperand() {
        return this.rightOperand;
    }

    @Override
    public String toString() {
        return this.storeLocation.toString() + " = " + this.leftOperand.toString() + " " + this.operator + " " + this.rightOperand.toString();
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
                && ((LlAssignStmtBinaryOp) obj).operator.equals(this.operator)
                && ((LlAssignStmtBinaryOp) obj).storeLocation.equals(this.storeLocation);
    }

    @Override
    public int hashCode() {
        return this.rightOperand.hashCode()
                * this.leftOperand.hashCode()
                * this.operator.hashCode()
                * this.storeLocation.hashCode();
    }


    private boolean isComparisonORLogic(String operation) {
        String opers = "< <= > >= == != || &&";
        return opers.contains(operation);
    }

    private ValueOfDiffType getLlLiteralValue(LlLiteral operand) {
        if (operand instanceof LlLiteralBool) {
            return new ValueOfDiffType(((LlLiteralBool) operand).getBoolValue());
        } else if (operand instanceof LlLiteralInt) {
            return new ValueOfDiffType(((LlLiteralInt) operand).getIntValue());
        } else if (operand instanceof LlLiteralReal) {
            return new ValueOfDiffType(((LlLiteralReal) operand).getRealValue());
        } else if (operand instanceof LlLiteralString) {
            return new ValueOfDiffType(((LlLiteralString) operand).getStringValue());
        }
        return null;
    }

    private ValueOfDiffType genLocationValue(BasicTypeEnum type, ValueOfDiffType value) {
        switch (type) {
            case INTEGER:
                return new ValueOfDiffType(value.getvLong());

            case FLOAT:
                return new ValueOfDiffType(value.getvDouble());
        }
        return null;
    }

    /**
     * 这里有个问题，leftOperand 和 rightOperand 不知道是什么类型，我该怎么让他们进行运算？
     * 前面的语义检查保证了类型的一致，所以不必担心。但我需要知道是两个整数相加还是两个浮点数相加吧！！
     *
     * @param memory
     */
    @Override
    public void exe(Memory memory) {

    }

    @Override
    public void accept(LlStatementVisitor llStatementVisitor, Memory memory) {
        llStatementVisitor.visitor(this,  memory);
    }


}