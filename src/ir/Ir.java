package ir;

import SymbolTable.Scope;
import SymbolTable.SymTable;

public abstract class Ir implements Where {
    private final int lineNumber;
    private final int colNumber;
    public Scope scope;

    public Ir(int lineNumber, int colNumber) {
        this.lineNumber = lineNumber;
        this.colNumber = colNumber;
    }

    @Override
    public int getLineNumber() {
        return lineNumber;
    }

    @Override
    public int getColNumber() {
        return colNumber;
    }


    public abstract  String semanticCheck(SymTable symTable);

    public abstract String prettyPrint(String indentSpace);

    public abstract void accept(BaseVisitor<Void> visitor);
}
