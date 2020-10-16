package ir.VARBlockDecl;

public enum VarAccessTypeEnum {
    /**可能用得上*/// TODO: TypeEnum， 各种类型的type也许不用都用class表示，直接用枚举，等待后面尝试
    VAR, VAR_OUTPUT,  VAR_INPUT, VAR_INPUT_OUTPUT, RES_BOOL, RES_SINT, RES_INT, RES_DINT,
    RES_LINT, REAL, LREAL, USINT, UINT, UDINT, ULINT,
    DATE, TIME_OF_DAY, TOD, DATE_AND_TIME, DT, BYTE, WORD,
    DWORD, LWORD

}
