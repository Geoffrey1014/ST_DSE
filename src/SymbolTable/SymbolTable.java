package SymbolTable;

import ir.Ir;


public class SymbolTable {
    public static AbsTreeProperty<Scope> treeProperty = new AbsTreeProperty<Scope>();
    Scope currentScope; // define symbols in this scope
    GlobalScope globals;

    void saveScope(Ir ctx, Scope s) { treeProperty.put(ctx, s); }

}
