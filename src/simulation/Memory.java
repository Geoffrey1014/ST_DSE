package simulation;

import ll.LlComponent;

import java.util.Hashtable;

public class Memory {
    public Hashtable<LlComponent, ValueOfDiffType> locationTable;

    public Memory() {
        this.locationTable = new Hashtable<LlComponent, ValueOfDiffType>();
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

    public Integer getLocationInt(LlComponent location) {
        return this.locationTable.get(location).getvInteger();
    }

    public Float getLocationFloat(LlComponent location) {
        {
            return this.locationTable.get(location).getvFloat();
        }
    }

    public String getLocationString(LlComponent location) {
        return this.locationTable.get(location).getvString();
    }

    public void setLocationAndValue(LlComponent location, ValueOfDiffType value){
        this.locationTable.put(location,value);
    }


}
