package SymbolTable;

import ir.Ir;

/**
 * Created by geo on 2020/10/20
 */
public class SymTable {

    public  AbsTreeProperty<BaseScope> treeProperty = new AbsTreeProperty<>();
    public  BaseScope currentScope; // define symbols in this scope
    public  GlobalScope globals;

    public void addObjectToCurrentScope(String id, Ir object) {
        currentScope.define(id, object);
    }

    public boolean checkIfSymbolExistsAtAnyScope(String id) {
        return currentScope.resolve(id) != null;
    }
    public boolean checkIfSymbolExistsAtCurrentScope(String id){
        return currentScope.getSymbolAtCurrentScope(id) != null;
    }
    public Ir getSymbol(String id) {
        return currentScope.resolve(id);
    }

    /**
     * 将scope s  标注在 AST 上
     */
    public void saveScope(Ir node, BaseScope s) { treeProperty.put(node, s); }

}


