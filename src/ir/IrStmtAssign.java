package ir;

public class IrStmtAssign extends IrStmt {
    private final IrIdent location;
    private final IrExpr expr;
    public IrStmtAssign(int lineNumber, int colNumber, IrIdent location, IrExpr expr) {
        super(lineNumber, colNumber);
        this.location = location;
        this.expr = expr;
    }
}
