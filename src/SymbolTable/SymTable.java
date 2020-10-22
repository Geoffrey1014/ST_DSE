package SymbolTable;

import ir.Ir;

/**
 * Created by geo on 2020/10/20
 */
public class SymTable {

    public static AbsTreeProperty<Scope> treeProperty = new AbsTreeProperty<Scope>();
    static Scope currentScope; // define symbols in this scope
    static GlobalScope globals;

    public void addObjectToCurrentScope(String id, Ir object) {
        currentScope.define(id, object);
    }

    public boolean checkIfSymbolExistsAtAnyScope(String id) {
        return currentScope.resolve(id) != null;
    }
    public Ir getSymbol(String id) {
        return currentScope.resolve(id);
    }

    void saveScope(Ir node, Scope s) { treeProperty.put(node, s); }

}


