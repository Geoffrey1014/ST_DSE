package simulation;

import com.microsoft.z3.Context;
import com.microsoft.z3.Expr;
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

public class SymLlStatementExeutor implements LlStatementVisitor {
    Context ctx;
    public SymLlStatementExeutor(Context context){this.ctx = context;}


    @Override
    public void visitor(LlAssignStmtBinaryOp llAssignStmtBinaryOp, Memory memory) {
        SymMemory symMemory = (SymMemory) memory;
        System.out.println("symExe\t" + llAssignStmtBinaryOp);
        Expr left, right;
        if (llAssignStmtBinaryOp.getLeftOperand() instanceof LlLiteral) {
            left = getLlLiteralValue( (LlLiteral) llAssignStmtBinaryOp.getLeftOperand());
        } else {
            left = symMemory.get(llAssignStmtBinaryOp.getLeftOperand());
        }

        if (llAssignStmtBinaryOp.getRightOperand() instanceof LlLiteral) {
            right = getLlLiteralValue( (LlLiteral) llAssignStmtBinaryOp.getRightOperand());
        } else {
            right = symMemory.get(llAssignStmtBinaryOp.getRightOperand());
        }

        Expr result = null;// after get the result, put it in the memory
        switch (llAssignStmtBinaryOp.getOperator()) {
            case "+":
                assert left != null;
                result = ctx.mkAdd(left, right);
                break;
            case "-":
                assert left != null;
                result = ctx.mkSub(left, right);
                break;
            case "*":
                assert left != null;
                result = ctx.mkMul(left, right);
                break;
            case "/":
                assert left != null;
                result = ctx.mkDiv(left, right);
                break;
            case "MOD":
                assert left != null;
                result = ctx.mkMod(left, right);
                break;
            case ">":
                assert left != null; // is it possible that left is null??
                result = ctx.mkGt(left, right);
                break;
            case "<":
                assert left != null;
                result = ctx.mkLt(left, right);
                break;
            case ">=":
                assert left != null;
                result = ctx.mkGe(left, right);
                break;
            case "<=":
                assert left != null;
                result = ctx.mkLe(left, right);
                break;
            case "==":
                assert left != null;
                result = ctx.mkEq(left, right);
                break;
            case "<>":
                assert left != null;
                result = ctx.mkNot(ctx.mkEq(left, right));
                break;
            case "OR":
                assert left != null;
                result = ctx.mkOr(left, right);
                break;
            case "&":
            case "AND":
                assert left != null;
                result = ctx.mkAnd(left, right);
                break;
            default:
                System.err.println("Runtime Error: Unrecognized Operation");
                System.err.println(llAssignStmtBinaryOp.getOperator());
                break;
        }

        symMemory.put(llAssignStmtBinaryOp.getStoreLocation(), result);
    }


    @Override
    public void visitor(LlAssignStmtUnaryOp llAssignStmtUnaryOp, Memory memory) {
        SymMemory symMemory = (SymMemory) memory;
        System.out.println("symExe\t" + llAssignStmtUnaryOp);
        Expr right;
        if (llAssignStmtUnaryOp.getOperand() instanceof LlLiteral) {
            right = getLlLiteralValue( (LlLiteral) llAssignStmtUnaryOp.getOperand());
        } else {
            right = symMemory.get(llAssignStmtUnaryOp.getOperand());

        }
        Expr result = null;// after get the result, put it in the memory
        switch (llAssignStmtUnaryOp.getOperator()) {
            case "NOT":
                result = ctx.mkNot(right);
                break;
            case "-":
                result = ctx.mkSub(ctx.mkInt(0), right);
                break;
            case "+":
                result = right;
            default:
                System.err.println("Runtime Error: Unrecognized Operation");
                System.err.println(llAssignStmtUnaryOp.getOperator());
                break;
        }
        symMemory.put(llAssignStmtUnaryOp.getStoreLocation(), result);
    }

    @Override
    public void visitor(LlAssignStmtRegular llAssignStmtRegular, Memory memory) {
        SymMemory symMemory = (SymMemory) memory;
        System.out.println("symExe\t" + llAssignStmtRegular);
        Expr right;
        if (llAssignStmtRegular.getOperand() instanceof LlLiteral) {
            right = getLlLiteralValue((LlLiteral) llAssignStmtRegular.getOperand());
        } else {
            right = symMemory.get(llAssignStmtRegular.getOperand());
        }
        symMemory.put(llAssignStmtRegular.getStoreLocation(), right);
    }

    @Override
    public void visitor(LlJumpConditional llJumpConditional, Memory memory) {
        System.out.println("symExe\t" + llJumpConditional);

    }

    @Override
    public void visitor(LlJumpUnconditional llJumpUnconditional, Memory memory) {
        System.out.println("symExe\t" + llJumpUnconditional); // it is not been symExecuted
    }

    @Override
    public void visitor(LlEmptyStmt llEmptyStmt, Memory memory) {
//        System.out.println("symExe\t" + llEmptyStmt);
    }

    @Override
    public void visitor(LlMethodCallStmt llMethodCallStmt,  Memory memory) {
        SymMemory symMemory = (SymMemory) memory;
        System.out.println("symExe\t" + llMethodCallStmt);
        if (llMethodCallStmt.getMethodName().equals("print")) {
            LlComponent arg = llMethodCallStmt.getArgsList().get(0);
            System.out.println(arg + " = " + symMemory.get(arg));
        }
        if (llMethodCallStmt.getMethodName().equals("read")) {
            readFunctionExe(llMethodCallStmt, ctx, symMemory);
        }

    }

    public void readFunctionExe(LlMethodCallStmt llMethodCallStmt, Context ctx, SymMemory memory) {

    }

    @Override
    public void visitor(LlReturn llReturn, Memory memory) {
        System.out.println("symExe" + llReturn);
    }

    public Expr getLlLiteralValue( LlLiteral operand) {
        if (operand instanceof LlLiteralBool) {
            return ctx.mkBool(((LlLiteralBool) operand).getBoolValue());
        } else if (operand instanceof LlLiteralInt) {
            return ctx.mkInt(((LlLiteralInt) operand).getIntValue());
        } else if (operand instanceof LlLiteralReal) {
            return ctx.mkReal(String.valueOf(((LlLiteralReal) operand).getRealValue()));
        } else if (operand instanceof LlLiteralString) {
            System.err.println("string is not supported!");
        }
        return null;
    }


}
