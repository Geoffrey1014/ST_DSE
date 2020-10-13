package ir;

public class IrStatAssign extends IrStat {
    private IrIdent location;
    private IrExpr expr;
    public IrStatAssign(int lineNumber, int colNumber) {
        super(lineNumber, colNumber);
    }
}
