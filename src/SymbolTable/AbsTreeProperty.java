package SymbolTable;

import ir.Ir;

import java.util.IdentityHashMap;
import java.util.Map;

public class AbsTreeProperty<V> {
    protected Map<Ir, V> annotations = new IdentityHashMap();

    public AbsTreeProperty() {
    }

    public V get(Ir node) {
        return this.annotations.get(node);
    }

    public void put(Ir node, V value) {
        this.annotations.put(node, value);
    }
}
