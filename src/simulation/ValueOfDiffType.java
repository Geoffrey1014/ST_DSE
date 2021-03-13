package simulation;

public class ValueOfDiffType {
    private Boolean vBoolean;
    private Long vLong;
    private Double vDouble;
    private String vString;
    private BasicTypeEnum type;

    public ValueOfDiffType(Boolean value){
        this.vBoolean = value;
        this.type = BasicTypeEnum.BOOLEAN;
    }
    public ValueOfDiffType(Long value){
        this.vLong = value;
        this.type = BasicTypeEnum.INTEGER;
    }

    public ValueOfDiffType(Double value){
        this.vDouble = value;
        this.type = BasicTypeEnum.FLOAT;
    }
    public ValueOfDiffType(String value){
        this.vString = value;
        this.type = BasicTypeEnum.STRING;
    }

    @Override
    public String toString(){
        switch (type){
            case FLOAT:
                return vDouble.toString();
            case INTEGER:
                return vLong.toString();
            case STRING:
                return vString;
            case BOOLEAN:
                return vBoolean.toString();
        }
        return "";
    }
    @Override
    public boolean equals(Object object){
        if(object instanceof ValueOfDiffType){
            ValueOfDiffType other = (ValueOfDiffType) object;
            return this.type.equals(other.type) && this.vBoolean.equals(other.vBoolean)
                    && this.vLong.equals(other.vLong)&& this.vDouble.equals(other.vDouble) && this.vString.equals(other.vString);
        }
        return false;
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

    public Long getvLong() {
        return vLong;
    }

    public void setvLong(Long vLong) {
        this.vLong = vLong;
    }

    public Double getvDouble() {
        return vDouble;
    }

    public void setvDouble(Double vDouble) {
        this.vDouble = vDouble;
    }

    public String getvString() {
        return vString;
    }

    public void setvString(String vString) {
        this.vString = vString;
    }
}
