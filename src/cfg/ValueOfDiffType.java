package cfg;

public class ValueOfDiffType {
    private Boolean vBoolean;
    private Integer vInteger;
    private Float vFloat;
    private String vString;
    private int type;  // 0: bool, 1: int, 2:float, 3: String

    public ValueOfDiffType(Boolean value){
        this.vBoolean = value;
        this.type = 0;
    }
    public ValueOfDiffType(Integer value){
        this.vInteger = value;
        this.type = 1;
    }

    public ValueOfDiffType(Float value){
        this.vFloat = value;
        this.type = 2;
    }
    public ValueOfDiffType(String value){
        this.vString = value;
        this.type = 3;
    }

    public int getType(){
        return this.type;
    }

    public Boolean getvBoolean() {
        return vBoolean;
    }

    public void setvBoolean(Boolean vBoolean) {
        this.vBoolean = vBoolean;
    }

    public Integer getvInteger() {
        return vInteger;
    }

    public void setvInteger(Integer vInteger) {
        this.vInteger = vInteger;
    }

    public Float getvFloat() {
        return vFloat;
    }

    public void setvFloat(Float vFloat) {
        this.vFloat = vFloat;
    }

    public String getvString() {
        return vString;
    }

    public void setvString(String vString) {
        this.vString = vString;
    }
}
