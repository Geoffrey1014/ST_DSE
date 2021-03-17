package simulation;

import ll.LlComponent;
import ll.location.LlLocation;

import java.util.HashMap;
import java.util.HashSet;

public class ConMemory extends Memory{
    private HashSet<LlLocation> inputVars;
    public HashMap<LlComponent, ValueOfDiffType> locationTable; // 基本类型不可变，其实设置越多的类型不可变，副作用越小，同时内存开销越大。这是个tradeoff。

    public ConMemory() {
        this.locationTable = new HashMap<>();
    }
    public ConMemory(HashSet<LlLocation> inputVars) {
        this.inputVars = inputVars;
        this.locationTable = new HashMap<>();
    }

    public ConMemory(ConMemory conMemory) {
        this.locationTable = new HashMap<>(conMemory.locationTable);
        this.inputVars = conMemory.inputVars;
    }

    public void put(LlComponent location, ValueOfDiffType value){
        this.locationTable.put(location,value);
    }

    public ValueOfDiffType getLocationvalue(LlComponent location){
        return this.locationTable.get(location);
    }

    public BasicTypeEnum getLocationType(LlComponent location) {
        return this.locationTable.get(location).getType();
    }

    public Boolean getLocationBool(LlComponent location) {
        return this.locationTable.get(location).getvBoolean();
    }

    public String getLocationString(LlComponent location) {
        return this.locationTable.get(location).getvString();
    }

    public void setLocationAndValue(LlComponent location, ValueOfDiffType value){
        this.locationTable.put(location,value);
    }

    @Override
    public String toString(){
        return this.locationTable.toString();
    }
    @Override
    public boolean equals(Object object){
        if(object == this){
            return true;
        }
        else if(object instanceof ConMemory){
            ConMemory other = (ConMemory) object;
            // compare without inputVar
            for(LlComponent llComponent: this.locationTable.keySet()){
                if(!this.inputVars.contains((LlLocation) llComponent)){
                    if(other.locationTable.containsKey(llComponent)){
                        if(!this.locationTable.get(llComponent).equals(other.locationTable.get(llComponent))){
                            return false;
                        }
                    }else{
                        return false;
                    }
                }
            }
            return true;
        }
        else{
            return false;
        }

    }

    @Override
    public int hashCode(){
        return this.locationTable.hashCode();
    }

}
