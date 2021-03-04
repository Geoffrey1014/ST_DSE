package simulation;

import ll.LlComponent;
import ll.LlEmptyStmt;
import ll.LlMethodCallStmt;
import ll.LlReturn;
import ll.assignStmt.LlAssignStmtBinaryOp;
import ll.assignStmt.LlAssignStmtRegular;
import ll.assignStmt.LlAssignStmtUnaryOp;
import ll.jump.LlJumpConditional;
import ll.jump.LlJumpUnconditional;
import ll.literal.*;
import ll.location.LlLocation;

import java.util.Scanner;

public class LlStatementExeutor implements LlStatementVisitor {
    @Override
    public void visitor(LlAssignStmtBinaryOp llAssignStmtBinaryOp, Memory memory) {
        ConMemory conMemory = (ConMemory) memory;
        System.out.println("exe\t" + llAssignStmtBinaryOp);
        ValueOfDiffType left, right;
        if (llAssignStmtBinaryOp.getLeftOperand() instanceof LlLiteral) {
            left = getLlLiteralValue((LlLiteral) llAssignStmtBinaryOp.getLeftOperand());
        } else {
            ValueOfDiffType leftValue = conMemory.getLocationvalue(llAssignStmtBinaryOp.getLeftOperand());
            left = genLocationValue(leftValue.getType(), leftValue);
        }

        if (llAssignStmtBinaryOp.getRightOperand() instanceof LlLiteral) {
            right = getLlLiteralValue((LlLiteral) llAssignStmtBinaryOp.getRightOperand());
        } else {
            ValueOfDiffType rightValue = conMemory.getLocationvalue(llAssignStmtBinaryOp.getRightOperand());
            right = genLocationValue(rightValue.getType(), rightValue);
        }
        Operation operationOfSimulor = new Operation();
        ValueOfDiffType result = null;// after get the result, put it in the memory
        switch (llAssignStmtBinaryOp.getOperator()) {
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
            case "MOD":
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
            case "<>":
                assert left != null;
                result = operationOfSimulor.notEqual(left, right);
                break;
            case "OR":
                assert left != null;
                result = operationOfSimulor.or(left, right);
                break;
            case "&":
            case "AND":
                assert left != null;
                result = operationOfSimulor.and(left, right);
                break;
            default:
                System.err.println("Runtime Error: Unrecognized Operation");
                System.err.println(llAssignStmtBinaryOp.getOperator());
                break;
        }

        conMemory.put(llAssignStmtBinaryOp.getStoreLocation(), result);
    }


    @Override
    public void visitor(LlAssignStmtUnaryOp llAssignStmtUnaryOp, Memory memory) {
        ConMemory conMemory = (ConMemory) memory;
        System.out.println("exe\t" + llAssignStmtUnaryOp);
        ValueOfDiffType right;
        if (llAssignStmtUnaryOp.getOperand() instanceof LlLiteral) {
            right = getLlLiteralValue((LlLiteral) llAssignStmtUnaryOp.getOperand());
        } else {
            ValueOfDiffType leftValue = conMemory.getLocationvalue(llAssignStmtUnaryOp.getOperand());
            right = genLocationValue(leftValue.getType(), leftValue);
        }
        Operation operationOfSimulor = new Operation();
        ValueOfDiffType result = null;// after get the result, put it in the memory
        switch (llAssignStmtUnaryOp.getOperator()) {
            case "NOT":
                result = operationOfSimulor.not(right);
                break;
            case "-":
                result = operationOfSimulor.neg(right);
                break;
            case  "+":
                result = right;
            default:
                System.err.println("Runtime Error: Unrecognized Operation");
                System.err.println(llAssignStmtUnaryOp.getOperator());
                break;
        }
        conMemory.put(llAssignStmtUnaryOp.getStoreLocation(), result);
    }

    @Override
    public void visitor(LlAssignStmtRegular llAssignStmtRegular, Memory memory) {
        ConMemory conMemory = (ConMemory) memory;
        System.out.println("exe\t" + llAssignStmtRegular);
        ValueOfDiffType right;
        if (llAssignStmtRegular.getOperand() instanceof LlLiteral) {
            right = getLlLiteralValue((LlLiteral) llAssignStmtRegular.getOperand());
        } else {
            ValueOfDiffType leftValue = conMemory.getLocationvalue(llAssignStmtRegular.getOperand());
            right = genLocationValue(leftValue.getType(), leftValue);
        }
        conMemory.put(llAssignStmtRegular.getStoreLocation(), right);
    }

    @Override
    public void visitor(LlJumpConditional llJumpConditional, Memory memory) {
        System.out.println("exe\t" + llJumpConditional);

    }

    @Override
    public void visitor(LlJumpUnconditional llJumpUnconditional, Memory memory) {
        System.out.println("exe\t" + llJumpUnconditional); // it is not been executed
    }

    @Override
    public void visitor(LlEmptyStmt llEmptyStmt, Memory memory) {
//        System.out.println("exe\t" + llEmptyStmt);
    }
    @Override
    public void visitor(LlReturn llReturn, Memory memory) {
        System.out.println("exe" + llReturn);
    }


    @Override
    public void visitor(LlMethodCallStmt llMethodCallStmt, Memory memory) {
        ConMemory conMemory = (ConMemory) memory;
        System.out.println("exe\t" + llMethodCallStmt);
        if (llMethodCallStmt.getMethodName().equals("print")) {
            LlComponent arg = llMethodCallStmt.getArgsList().get(0);
            System.out.println(arg + " = " + conMemory.getLocationvalue(arg));
        }
        if (llMethodCallStmt.getMethodName().equals("read")) {
            readFunctionExe(llMethodCallStmt, conMemory);
        }

    }



    public void readFunctionExe(LlMethodCallStmt llMethodCallStmt, ConMemory conMemory) {
        Scanner sc = new Scanner(System.in);
        LlLocation location = llMethodCallStmt.getReturnLocation();
        BasicTypeEnum type = conMemory.getLocationvalue(location).getType();

        System.out.print("Please enter a " + type + " string : ");

        String inPut = sc.next();
        try {
            switch (type) {
                case BOOLEAN:
                    if (!inPut.equalsIgnoreCase("true") && !inPut.equalsIgnoreCase("false")) {
                        System.err.println("input is not boolean");
                        readFunctionExe(llMethodCallStmt, conMemory);
                    }
                    conMemory.put(location, new ValueOfDiffType(Boolean.parseBoolean(inPut)));
                    break;
                case INTEGER:
                    conMemory.put(location, new ValueOfDiffType(Long.parseLong(inPut)));
                    break;
                case FLOAT:
                    conMemory.put(location, new ValueOfDiffType(Double.parseDouble(inPut)));
                    break;
                case STRING:
                    conMemory.put(location, new ValueOfDiffType(inPut));
                    break;
                default:
                    System.err.println("wrong type!");
            }
        } catch (NumberFormatException e) {
            System.err.println(e.getMessage());
            System.out.println("input is not valid");
            readFunctionExe(llMethodCallStmt, conMemory);
        }
    }


    public ValueOfDiffType getLlLiteralValue(LlLiteral operand) {
        if (operand instanceof LlLiteralBool) {
            return new ValueOfDiffType(((LlLiteralBool) operand).getBoolValue());
        } else if (operand instanceof LlLiteralInt) {
            return new ValueOfDiffType( ((LlLiteralInt) operand).getIntValue());
        } else if (operand instanceof LlLiteralReal) {
            return new ValueOfDiffType(((LlLiteralReal) operand).getRealValue());
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
                return new ValueOfDiffType(value.getvLong());
            case FLOAT:
                return new ValueOfDiffType(value.getvDouble());
            case STRING:
                return new ValueOfDiffType(value.getvString());
            default:
                System.err.println("Runtime Error: Unrecognized BasicTypeEnum");
                break;
        }
        return null;
    }
}
