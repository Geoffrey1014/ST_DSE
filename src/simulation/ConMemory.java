package simulation;

import ll.LlComponent;

import java.util.Hashtable;

public class ConMemory extends Memory{
    public Hashtable<LlComponent, ValueOfDiffType> locationTable;

    public ConMemory() {
        this.locationTable = new Hashtable<>();
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

}
