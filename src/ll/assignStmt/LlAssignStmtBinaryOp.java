package ll.assignStmt;


import ll.LlComponent;
import ll.location.LlLocation;

import java.util.HashMap;

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


    private boolean isComparison(String operation){
        String opers = "< <= > >= == !=";
        return opers.contains(operation);
    }

    /**
     * 这里有个问题，leftOperand 和 rightOperand 不知道是什么类型，我该怎么让他们进行运算？
     * 前面的语义检查保证了类型的一致，所以不必担心。但我需要知道是两个整数相加还是两个浮点数相加吧！！
     * @param memory
     */
    public void exe(HashMap<LlComponent, Integer> memory){


        switch (operation){
            case "+":

                break;
            case "-":

                break;
            case "*":
                break;
            case "%":

                break;
            case ">":

                break;
            case "<":

                break;
            case ">=":

                break;
            case "<=":

                break;
            case "!=":

                break;
            case "||":

                break;
            case "&&":

                break;
            case "==":

                break;
            default:
                System.err.println("Runtime Error: Unrecognized Operation");
                System.err.println(operation);
                break;
        }
    }



}