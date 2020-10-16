package ir.Arg;


import ir.IrExpr;

public class IrArgAssign extends IrArg {
    private final Object argValue;

    public IrArgAssign(Object argValue, int lineNum, int colNum) {
        super(lineNum, colNum);

        // we would like to canonicalize the expression if possible
        if (argValue instanceof IrExpr) {
            IrExpr expr = (IrExpr) argValue;
            this.argValue = IrExpr.canonicalizeExpr(expr);
        } else {
            this.argValue = argValue;
        }
    }

    public Object getArgValue() {
        return argValue;
    }

}
