package simulation;

public class ValueOfDiffType {
    private Boolean vBoolean;
    private Integer vInteger;
    private Float vFloat;
    private String vString;
    private BasicTypeEnum type;  // 0: bool, 1: int, 2:float, 3: String

    public ValueOfDiffType(Boolean value){
        this.vBoolean = value;
        this.type = BasicTypeEnum.BOOLEAN;
    }
    public ValueOfDiffType(Integer value){
        this.vInteger = value;
        this.type = BasicTypeEnum.INTEGER;
    }

    public ValueOfDiffType(Float value){
        this.vFloat = value;
        this.type = BasicTypeEnum.FLOAT;
    }
    public ValueOfDiffType(String value){
        this.vString = value;
        this.type = BasicTypeEnum.STRING;
    }
    public String toString(){
        switch (type){
            case FLOAT:
                return vFloat.toString();
            case INTEGER:
                return vInteger.toString();
            case STRING:
                return vString;
            case BOOLEAN:
                return vBoolean.toString();
        }
        return "";
    }

    public BasicTypeEnum getType(){
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
