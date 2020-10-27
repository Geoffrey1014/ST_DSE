package ir;

import SymbolTable.SymTable;

import java.util.List;

public class IrCodeBlock extends Ir {


    public final List<IrStmt> stmtsList;
    public IrCodeBlock(int lineNumber, int colNumber, List<IrStmt> statList) {
        super(lineNumber, colNumber);
        this.stmtsList = statList;
    }

    @Override
    public String semanticCheck(SymTable symTable) {
        return null;
    }

    @Override
    public String prettyPrint(String indentSpace) {

        StringBuilder prettyString = new StringBuilder(indentSpace + "|--codeBlock:\n");

        // pretty print statement
        for (IrStmt statement : this.stmtsList) {
            prettyString.append(statement.prettyPrint("  " + indentSpace));
        }

        return prettyString.toString();
    }

    @Override
    public void visit(BaseVisitor<Void> visitor) {
        visitor.visitIrCodeBlock(this);
    }

    public List<IrStmt> getStmtsList() {
        return stmtsList;
    }
}