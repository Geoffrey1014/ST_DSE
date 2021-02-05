package ll.assignStmt;


import cfg.LlStatementVisitor;
import ll.LlComponent;
import ll.literal.*;
import ll.location.LlLocation;
import simulation.BasicTypeEnum;
import simulation.Memory;
import simulation.Operation;
import simulation.ValueOfDiffType;

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


    private boolean isComparisonORLogic(String operation) {
        String opers = "< <= > >= == != || &&";
        return opers.contains(operation);
    }

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

    /**
     * 这里有个问题，leftOperand 和 rightOperand 不知道是什么类型，我该怎么让他们进行运算？
     * 前面的语义检查保证了类型的一致，所以不必担心。但我需要知道是两个整数相加还是两个浮点数相加吧！！
     *
     * @param memory
     */
    @Override
    public void exe(Memory memory) {
        ValueOfDiffType left, right;
        if (this.leftOperand instanceof LlLiteral) {
            left = getLlLiteralValue((LlLiteral) this.leftOperand);
        } else {
            ValueOfDiffType leftValue = memory.getLocationvalue(this.leftOperand);
            left = genLocationValue(leftValue.getType(), leftValue);
        }

        if (this.rightOperand instanceof LlLiteral) {
            right = getLlLiteralValue((LlLiteral) this.rightOperand);
        } else {
            ValueOfDiffType rightValue = memory.getLocationvalue(this.rightOperand);
            right = genLocationValue(rightValue.getType(), rightValue);
        }
        Operation operationOfSimulor = new Operation();
        ValueOfDiffType result = null;// after get the result, put it in the memory
        switch (operation) {
            case "+":
                result = operationOfSimulor.add(left, right);
                break;
            case "-":
                result = operationOfSimulor.sub(left, right);
                break;
            case "*":
                result = operationOfSimulor.mul(left, right);
                break;
            case "/":
                result = operationOfSimulor.devide(left, right);
                break;
            case "%":
                result = operationOfSimulor.mod(left, right);
                break;
            case ">":
                assert left != null; // is it possible that left is null??
                result = operationOfSimulor.GT(left, right);
                break;
            case "<":
                result = operationOfSimulor.LT(left, right);
                break;
            case ">=":
                result = operationOfSimulor.EGT(left, right);
                break;
            case "<=":
                result = operationOfSimulor.ELT(left, right);
                break;
            case "==":
                result = operationOfSimulor.equal(left, right);
                break;
            case "!=":
                result = operationOfSimulor.notEqual(left, right);
                break;
            case "||":
                result = operationOfSimulor.or(left, right);
                break;
            case "&&":
                result = operationOfSimulor.and(left, right);
                break;
            default:
                System.err.println("Runtime Error: Unrecognized Operation");
                System.err.println(operation);
                break;
        }
        
        memory.put(this.storeLocation, result);


    }

    @Override
    public void accept(LlStatementVisitor llStatementVisitor, Memory memory) {
        llStatementVisitor.visitor(this,memory);
    }


}