package ir.VARBlockDecl;

import ir.IrIdent;
import ir.VarTypeEnum;

public class IrVarDeclSimple extends IrVarDecl {
    public IrVarDeclSimple(int lineNumber, int colNumber, IrIdent name, VarTypeEnum type) {
        super(lineNumber, colNumber, name, type);
    }
}
