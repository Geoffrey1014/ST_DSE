package simulation;

import ll.LlComponent;

import java.util.Hashtable;

public class ConMemory extends Memory{
    public Hashtable<LlComponent, ValueOfDiffType> locationTable; // 基本类型不可变，其实设置越多的类型不可变，副作用越小，同时内存开销越大。这是个tradeoff。

    public ConMemory() {
        this.locationTable = new Hashtable<>();
    }

    public ConMemory(ConMemory conMemory) {
        this.locationTable = new Hashtable<>(conMemory.locationTable);
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
    public boolean equals(Object object){
        if(object instanceof ConMemory){
            ConMemory other = (ConMemory) object;
            return this.locationTable.equals(other.locationTable); // TODO: dose the equality of 2 hashtable compare the items?
        }
        return false;
    }

}
