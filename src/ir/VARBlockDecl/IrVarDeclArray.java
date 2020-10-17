package ir.VARBlockDecl;

import ir.IrIdent;
import ir.VarTypeEnum;

public class IrVarDeclArray extends IrVarDecl {
    public IrVarDeclArray(int lineNumber, int colNumber, IrIdent name, VarTypeEnum type) {
        super(lineNumber, colNumber, name, type);
    }
}
