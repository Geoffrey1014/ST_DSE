package ir.Operation;

import ir.IrExpr;

/**
 * Created by geo on 2020/10/13.
 */
public abstract class IrOperBinary extends IrOper {
    public OperKeyWords operation;
    public IrExpr leftOperand;
    public IrExpr rightOperand;

    public IrOperBinary(OperKeyWords operation, IrExpr leftOperand, IrExpr rightOperand) {
        super(leftOperand.getLineNumber(), leftOperand.getColNumber());
        this.operation = operation;
        this.leftOperand = IrExpr.canonicalizeExpr(leftOperand);
        this.rightOperand = IrExpr.canonicalizeExpr(rightOperand);
    }

    public IrExpr getLeftOperand() {
        return this.leftOperand;
    }

    public IrExpr getRightOperand() {
        return this.rightOperand;
    }

    public OperKeyWords getOperation() {
        return this.operation;
    }

    public String toString() {
        return leftOperand.toString() + " " + operation + " " + rightOperand.toString();
    }

}

