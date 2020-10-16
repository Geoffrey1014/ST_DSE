package ir.Operation;

import ir.IrExpr;

public abstract class IrOper extends IrExpr {

    public IrOper(int lineNumber, int colNumber) {
        super(lineNumber, colNumber);
    }

    public String toString() {
        return "IrOper";
    }

}
