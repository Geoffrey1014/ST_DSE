package ir;


import visitor.BaseVisitor;

/**
 */
public class IrStmtExit extends IrStmt {
    public IrStmtExit(int lineNumber, int colNumber) {
        super(lineNumber, colNumber);
    }



    @Override
    public String prettyPrint(String indentSpace) {
        String prettyString = indentSpace + "|--breakStmt";
        return prettyString;
    }

    @Override
    public void accept(BaseVisitor<Void> visitor) {

    }

}
