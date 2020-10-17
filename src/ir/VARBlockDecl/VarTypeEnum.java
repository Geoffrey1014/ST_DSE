package ir.VARBlockDecl;

public enum VarTypeEnum {
    RES_BOOL("BOOL"),
    RES_SINT("SINT"), RES_INT("INT"), RES_DINT("DINT"), RES_LINT("LINT"),
    REAL("REAL"), LREAL("LREAL"),
    USINT("USINT"), UINT("UINT"), UDINT("UDINT"), ULINT("ULINT"),
    DATE("DATE"), TIME_OF_DAY("TIME_OF_DAY"), TOD("TOD"), DATE_AND_TIME("DATE_AND_TIME"), DT("DT"),
    BYTE("BYTE"),
    WORD("WORD"),  DWORD("DWORD"), LWORD("LWORD");

    private  String typeName;

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
}
