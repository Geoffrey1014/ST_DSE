package ir;

import SymbolTable.Scope;
import helper.LlBuilder;
import helper.LlSymbolTable;
import ll.location.LlLocation;
import visitor.BaseVisitor;

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


    public abstract String prettyPrint(String indentSpace);

    public abstract void accept(BaseVisitor<Void> visitor);

    public abstract LlLocation generateLlIr(LlBuilder builder, LlSymbolTable symbolTable);
}
