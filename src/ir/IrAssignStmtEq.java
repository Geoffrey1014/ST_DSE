package ir;

import SymbolTable.SymTable;
import ir.Location.IrLocation;

public class IrAssignStmtEq extends IrAssignStmt {
    private final IrExpr expr;

    public IrAssignStmtEq(IrLocation storeLocation, IrExpr newValue, int lineNumber, int colNumber) {
        super(storeLocation, lineNumber, colNumber);
        this.expr = newValue;
    }

    @Override
    public String semanticCheck(SymTable symTable) {
        return null;
    }

    @Override
    public String prettyPrint(String indentSpace) {
        return null;
    }

    @Override
    public void visit(BaseVisitor<Void> visitor) {
        visitor.visitIrAssignStmtEq(this);
    }

    public IrExpr getExpr() {
        return expr;
    }
}
