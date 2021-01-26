package cfg;

import ll.LlEmptyStmt;
import ll.LlMethodCallStmt;
import ll.LlReturn;
import ll.assignStmt.LlAssignStmtBinaryOp;
import ll.assignStmt.LlAssignStmtRegular;
import ll.assignStmt.LlAssignStmtUnaryOp;
import ll.jump.LlJumpConditional;
import ll.jump.LlJumpUnconditional;
import ll.literal.*;

public class LlStatementExeutor implements LlStatementVisitor {
    @Override
    public void visitor(LlAssignStmtBinaryOp llAssignStmtBinaryOp, Memory memory) {

        System.out.println("exe\t" + llAssignStmtBinaryOp);
        ValueOfDiffType left, right;
        if (llAssignStmtBinaryOp.getLeftOperand() instanceof LlLiteral) {
            left = getLlLiteralValue((LlLiteral) llAssignStmtBinaryOp.getLeftOperand());
        } else {
            ValueOfDiffType leftValue = memory.getLocationvalue(llAssignStmtBinaryOp.getLeftOperand());
            left = genLocationValue(leftValue.getType(), leftValue);
        }

        if (llAssignStmtBinaryOp.getRightOperand() instanceof LlLiteral) {
            right = getLlLiteralValue((LlLiteral) llAssignStmtBinaryOp.getRightOperand());
        } else {
            ValueOfDiffType rightValue = memory.getLocationvalue(llAssignStmtBinaryOp.getRightOperand());
            right = genLocationValue(rightValue.getType(), rightValue);
        }
        Operation operationOfSimulor = new Operation();
        ValueOfDiffType result = null;// after get the result, put it in the memory
        switch (llAssignStmtBinaryOp.getOperation()) {
            case "+":
                assert left != null;
                result = operationOfSimulor.add(left, right);
                break;
            case "-":
                assert left != null;
                result = operationOfSimulor.sub(left, right);
                break;
            case "*":
                assert left != null;
                result = operationOfSimulor.mul(left, right);
                break;
            case "/":
                assert left != null;
                result = operationOfSimulor.devide(left, right);
                break;
            case "%":
                assert left != null;
                result = operationOfSimulor.mod(left, right);
                break;
            case ">":
                assert left != null; // is it possible that left is null??
                result = operationOfSimulor.GT(left, right);
                break;
            case "<":
                assert left != null;
                result = operationOfSimulor.LT(left, right);
                break;
            case ">=":
                assert left != null;
                result = operationOfSimulor.EGT(left, right);
                break;
            case "<=":
                assert left != null;
                result = operationOfSimulor.ELT(left, right);
                break;
            case "==":
                assert left != null;
                result = operationOfSimulor.equal(left, right);
                break;
            case "!=":
                assert left != null;
                result = operationOfSimulor.notEqual(left, right);
                break;
            case "||":
                assert left != null;
                result = operationOfSimulor.or(left, right);
                break;
            case "&&":
                assert left != null;
                result = operationOfSimulor.and(left, right);
                break;
            default:
                System.err.println("Runtime Error: Unrecognized Operation");
                System.err.println(llAssignStmtBinaryOp.getOperation());
                break;
        }

        memory.put(llAssignStmtBinaryOp.getStoreLocation(), result);
    }



    @Override
    public void visitor(LlAssignStmtUnaryOp llAssignStmtUnaryOp, Memory memory) {
        System.out.println("exe\t" + llAssignStmtUnaryOp);
    }

    @Override
    public void visitor(LlAssignStmtRegular llAssignStmtRegular, Memory memory) {
        System.out.println("exe\t" + llAssignStmtRegular);
        ValueOfDiffType right;
        if (llAssignStmtRegular.getOperand() instanceof LlLiteral) {
            right = getLlLiteralValue((LlLiteral) llAssignStmtRegular.getOperand() );
        } else {
            ValueOfDiffType leftValue = memory.getLocationvalue(llAssignStmtRegular.getOperand() );
            right = genLocationValue(leftValue.getType(), leftValue);
        }
        memory.put(llAssignStmtRegular.getStoreLocation(), right);
    }

    @Override
    public void visitor(LlJumpConditional llJumpConditional, Memory memory) {
        System.out.println("exe\t" + llJumpConditional);

    }

    @Override
    public void visitor(LlJumpUnconditional llJumpUnconditional, Memory memory) {
        System.out.println("exe\t" + llJumpUnconditional);
    }

    @Override
    public void visitor(LlEmptyStmt llEmptyStmt, Memory memory) {
        System.out.println("exe\t" + llEmptyStmt);
    }

    @Override
    public void visitor(LlMethodCallStmt llMethodCallStmt, Memory memory) {
        System.out.println("exe\t" + llMethodCallStmt);
    }

    @Override
    public void visitor(LlReturn llReturn, Memory memory) {
        System.out.println("exe" + llReturn);
    }

    public ValueOfDiffType getLlLiteralValue(LlLiteral operand) {
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

    public ValueOfDiffType genLocationValue(BasicTypeEnum type, ValueOfDiffType value) {
        switch (type) {
            case BOOLEAN:
                return new ValueOfDiffType(value.getvBoolean());
            case INTEGER:
                return new ValueOfDiffType(value.getvInteger());
            case FLOAT:
                return new ValueOfDiffType(value.getvFloat());
            case STRING:
                return new ValueOfDiffType(value.getvString());
            default:
                System.err.println("Runtime Error: Unrecognized BasicTypeEnum");
                break;
        }
        return null;
    }
}
