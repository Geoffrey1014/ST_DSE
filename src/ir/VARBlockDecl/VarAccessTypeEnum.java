package ir.VARBlockDecl;

import ir.Ir;

public enum VarAccessTypeEnum {
    /**可能用得上*/// TODO: TypeEnum， 各种类型的type也许不用都用class表示，直接用枚举，等待后面尝试
    VAR("VAR"), VAR_OUTPUT("VAR_OUTPUT"),  VAR_INPUT("VAR_INPUT"),
    VAR_INPUT_OUTPUT("VAR_INPUT_OUTPUT"), RES_VAR_TEMP("VAR_TEMP");

    private String varAcceceType;
    VarAccessTypeEnum(String s) {
        this.varAcceceType = s;
    }

    public static VarAccessTypeEnum fromVarAccessType(String s){
        for (VarAccessTypeEnum type : VarAccessTypeEnum.values()){
            if (type.getTypeName().equals(s)){
                return type;
            }
        }
        return null;
    }

    public String getTypeName() {
        return this.varAcceceType;
    }

}
