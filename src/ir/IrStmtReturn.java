package ir;


import SymbolTable.SymTable;

public class IrStmtReturn extends IrStmt {
    public IrStmtReturn(int lineNumber, int colNumber) {
        super(lineNumber, colNumber);
    }

    @Override
    public String semanticCheck(SymTable symTable) {
        return null;
    }


    @Override
    public String prettyPrint(String indentSpace) {
        String prettyString = indentSpace + "|--returnStmt";
        return prettyString;
    }

    @Override
    public void accept(BaseVisitor<Void> visitor) {

    }
}