package ir.Operation;

import ir.Where;

public enum OperKeyWordEnum implements Where {
    ADD_OP("+"),SUB_OP("-"),MUL_OP("*"),DIV_OP("?"), MOD_OP("MOD"), POWER_OP("**"),
    LT_OP("<") , GT_OP(">") , LEQ_OP("<=") , GEQ_OP(">="),
    EQ_OP("=") , NEQ_OP("<>"),
    AND_OP("AND") , AND_S_OP("&"), OR_OP("OR"), XOR_OP("XOR");

    private String typeName;
    private  int lineNumber;
    private  int colNumber;  //TODO: 这个目前没有用上
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

    @Override
    public int getLineNumber() {
        return this.lineNumber;
    }

    @Override
    public int getColNumber() {
        return this.colNumber;
    }

    public void setLineNumber(int lineNumber){
        this.lineNumber = lineNumber;
    }
    public void setColNumber(int colNumber){
        this.colNumber = colNumber;
    }
}
