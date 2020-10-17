package ir.Operation;

public enum OperKeyWordEnum {
    ADD_OP("+"),SUB_OP("-"),MUL_OP("*"),DIV_OP("?"), MOD_OP("MOD"), POWER_OP("**"),
    LT_OP("<") , GT_OP(">") , LEQ_OP("<=") , GEQ_OP(">="),
    EQ_OP("=") , NEQ_OP("<>"),
    AND_OP("AND") , AND_S_OP("&"), OR_OP("OR"), XOR_OP("XOR");

    private String typeName;
    OperKeyWordEnum(String s) {
        this.typeName = s;
    }

    public static OperKeyWordEnum fromOperTpye(String typeName) {
        for (OperKeyWordEnum type : OperKeyWordEnum.values()) {
            if (type.getTypeName().equals(typeName)) {
                return type;
            }
        }
        return null;
    }

    public String getTypeName() {
        return this.typeName;
    }
}
