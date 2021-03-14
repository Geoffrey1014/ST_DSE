package simulation;

import com.microsoft.z3.Expr;
import ll.LlComponent;

import java.util.HashMap;

public class SymMemory extends Memory{
    private HashMap<LlComponent, Expr> locationTable;
    public SymMemory(){
        this.locationTable = new HashMap<>();
    }
    public SymMemory(SymMemory symMemory){
        this.locationTable = new HashMap<>(symMemory.locationTable);
    }
    public void put(LlComponent location, Expr value){
        this.locationTable.put(location,value);
    }

    public Expr get(LlComponent location){
        return this.locationTable.get(location);
    }
}
