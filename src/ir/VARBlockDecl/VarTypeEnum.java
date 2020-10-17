package ir.VARBlockDecl;

import ir.Ir;

public enum VarTypeEnum implements Ir {
    RES_BOOL("BOOL"),
    RES_SINT("SINT"), RES_INT("INT"), RES_DINT("DINT"), RES_LINT("LINT"),
    RES_REAL("REAL"), RES_LREAL("LREAL"),
    RES_USINT("USINT"), RES_UINT("UINT"), RES_UDINT("UDINT"), RES_ULINT("ULINT"),
    RES_DATE("DATE"), RES_TIME_OF_DAY("TIME_OF_DAY"), RES_TOD("TOD"), DRES_ATE_AND_TIME("DATE_AND_TIME"), RES_DT("DT"),
    RES_BYTE("BYTE"),
    RES_WORD("WORD"),  RES_DWORD("DWORD"), RES_LWORD("LWORD");

    private  String typeName;
    private  int lineNumber;
    private  int colNumber;

    VarTypeEnum(String s) {
        this.typeName = s;
    }


    public static VarTypeEnum fromVarTpye(String typeName) {
        for (VarTypeEnum type : VarTypeEnum.values()) {
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
