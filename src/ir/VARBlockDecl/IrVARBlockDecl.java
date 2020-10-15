package ir.VARBlockDecl;

import ir.Ir;
import ir.IrVar;

import java.util.List;



public abstract class IrVARBlockDecl extends Ir {
    private final List<IrVar> VarList;
//    private final String type; //TODO: 这个可以用 enumerate 类型
    public IrVARBlockDecl(int lineNumber, int colNumber, List<IrVar> varList) {
        super(lineNumber, colNumber);
        this.VarList = varList;
    }

}
