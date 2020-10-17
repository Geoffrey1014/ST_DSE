package SymbolTable;

import ir.Ir;
import ir.VARBlockDecl.IrType;

import java.util.Hashtable;
import java.util.Stack;

/**
 * Created by geo on 2020/10/20
 */
public class SymTable {
    public static AbsTreeProperty<SymbolTable> treeProperty = new AbsTreeProperty<SymbolTable>();
    private final Stack<SymbolTable> stack;

    public SymTable() {
        this.stack = new Stack<SymbolTable>();
    }

    public void addObjectToCurrentScope(String id, Ir object) {
        this.stack.peek().hashtable.put(id, object);
    }

    public boolean checkIfSymbolExistsAtAnyScope(String id) {
        for (SymbolTable e = this.stack.peek(); e != null; e = e.parentScope) {
            Ir found = e.hashtable.get(id);
            if (found != null) {
                return true;
            }
        }
        return false;
    }

    public boolean checkIfSymbolExistsAtCurrentScope(String id) {
        return this.stack.peek().hashtable.containsKey(id);
    }

    public void addSymbolToGlobalScope(String id, Ir object) {
        // get the parent-most (global) scope
        SymbolTable globalScope = this.stack.peek();
        while (globalScope.parentScope != null) {
            globalScope = globalScope.parentScope;
        }

        globalScope.hashtable.put(id, object);
    }

    public boolean checkIfSymbolExistsInGlobalScope(String id) {
        // get the parent-most (global) scope
        SymbolTable globalScope = this.stack.peek();
        while (globalScope.parentScope != null) {
            globalScope = globalScope.parentScope;
        }

        return globalScope.hashtable.containsKey(id);
    }

    public Ir getSymbol(String id) {
        for (SymbolTable e = this.stack.peek(); e != null; e = e.parentScope) {
            Ir found = e.hashtable.get(id);
            if (found != null) {
                return found;
            }
        }
        return null;
    }

    public SymbolTable deleteCurrentScope() {
        if (this.stack.size() > 0) {
            this.stack.pop();
        }
        return null;
    }

    public SymbolTable createNewBlockScope() {
        // start the ScopeStack out with a Global Scope
        if (this.stack.size() == 0) {
            SymTable.SymbolTable globalScope = this.new SymbolTable();
            this.stack.add(globalScope);
            return globalScope;
        }
        // add child scopes if a Global Scope already exists
        else {
            SymTable.SymbolTable parentScope = this.stack.peek();
            SymTable.SymbolTable newScope = this.new SymbolTable(parentScope);
            this.stack.push(newScope);
            return newScope;
        }
    }

    public SymbolTable createNewMethodScope(IrType methodType) {
        SymTable.SymbolTable parentScope = this.stack.peek();
        SymTable.SymbolTable newScope = this.new SymbolTable(parentScope, methodType);
        this.stack.push(newScope);
        return newScope;
    }

    public SymbolTable createNewLoopScope() {
        SymTable.SymbolTable parentScope = this.stack.peek();
        SymTable.SymbolTable newScope = this.new SymbolTable(parentScope, true);
        this.stack.push(newScope);
        return newScope;
    }

    public IrType getScopeReturnType() {
        for (SymbolTable e = this.stack.peek(); e != null; e = e.parentScope) {
            IrType methodType = e.scopeReturnType;
            if (methodType != null) {
                return methodType;
            }
        }
        return null;
    }

    public boolean isScopeForALoop() {
        for (SymbolTable e = this.stack.peek(); e != null; e = e.parentScope) {
            boolean isALoop = e.isLoop;
            if (isALoop) {
                return true;
            }
        }
        return false;
    }

    class SymbolTable {
        protected Hashtable<String, Ir> hashtable;
        protected SymbolTable parentScope;
        protected IrType scopeReturnType;
        protected boolean isLoop = false;

        protected SymbolTable() {
            this.hashtable = new Hashtable<String, Ir>();
            this.parentScope = null;
        }

        protected SymbolTable(SymbolTable parentScope) {
            this.hashtable = new Hashtable<String, Ir>();
            this.parentScope = parentScope;
        }

        protected SymbolTable(SymbolTable parentScope, IrType scopeReturnType) {
            this.hashtable = new Hashtable<String, Ir>();
            this.parentScope = parentScope;
            this.scopeReturnType = scopeReturnType;
        }

        protected SymbolTable(SymbolTable parentScope, boolean isLoop) {
            this.hashtable = new Hashtable<String, Ir>();
            this.parentScope = parentScope;
            this.isLoop = isLoop;
        }
    }

}


