package helper;

import ll.LlComponent;

import java.util.Hashtable;

public class LlSymbolTable {
    public Hashtable<LlComponent, String> llTable;

    public LlSymbolTable(){
        llTable = new Hashtable<>();
    }

    public void put(LlComponent key, String value){
        this.llTable.put(key, value);
    }

    public String get(LlComponent key){
        return this.llTable.get(key);
    }
}