package ir.Operation;

public enum OperKeyWords {
    ADD_OP("+"),SUB_OP("-"),MUL_OP("*"),DIV_OP("?"), MOD_OP("MOD"), POWER_OP("**"),
    LT_OP("<") , GT_OP(">") , LEQ_OP("<=") , GEQ_OP(">="),
    EQ_OP("=") , NEQ_OP("<>"),
    AND_OP("AND") , AND_S_OP("&"), OR_OP("OR"), XOR_OP("XOR");

    private String operType;
    OperKeyWords(String s) {
        this.operType = s;
    }

    public static OperKeyWords fromOperTpye(String typeName) {
        for (OperKeyWords type : OperKeyWords.values()) {
            if (type.getTypeName().equals(typeName)) {
                return type;
            }
        }
        return null;
    }

    public String getTypeName() {
        return this.operType;
    }
}
