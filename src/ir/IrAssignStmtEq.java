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

        String prettyString = indentSpace + "|--assignStmtEquals\n";

        // pretty print the lhs
        prettyString += ("  " + indentSpace + "|--lhs\n");
        prettyString += this.getStoreLocation().prettyPrint("    " + indentSpace);

        // print the rhs
        prettyString += ("  " + indentSpace + "|--rhs\n");
        prettyString += this.expr.prettyPrint("    " + indentSpace);

        return prettyString;
    }

    @Override
    public void accept(BaseVisitor<Void> visitor) {
        visitor.visitIrAssignStmtEq(this);
    }

    public IrExpr getExpr() {
        return expr;
    }
}
