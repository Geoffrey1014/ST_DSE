package SymbolTable; /***
 * Excerpted from "The Definitive ANTLR 4 Reference",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/tpantlr2 for more book information.
***/
import ir.Ir;

import java.util.LinkedHashMap;
import java.util.Map;

public abstract class BaseScope implements Scope {
    Scope enclosingScope; // null if global (outermost) scope
    Map<String, Ir> symbols = new LinkedHashMap<String, Ir>();

    public BaseScope(Scope enclosingScope) {
        this.enclosingScope = enclosingScope;
    }

    public Ir resolve(String name) {
        Ir s = symbols.get(name);
        if ( s!=null ) return s;
        // if not here, check any enclosing scope
        if ( enclosingScope != null ) {
            return enclosingScope.resolve(name);
        }
        return null; // not found
    }

    /**
     * 将符号放入当前 scope 的 map 中
     */
    public void define(String name , Ir sym) {
        symbols.put(name, sym);
        sym.scope = this; // 这个记录的是这个 AST 节点 在那个 scope 中
    }

    public Scope getEnclosingScope() { return enclosingScope; }

    public String toString() { return getScopeName()+":"+symbols.keySet().toString(); }
}
