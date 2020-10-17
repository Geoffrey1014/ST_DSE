package ir.VARBlockDecl;

import ir.Ir;
import ir.IrIdent;
import ir.VarTypeEnum;

import java.util.ArrayList;

public class IrVarDecl extends Ir {
    private final ArrayList<IrIdent> nameArrayList;
    private final IrType type;
    private final IrValue value;
    public IrVarDecl(int lineNumber, int colNumber, ArrayList<IrIdent> name, IrType type, IrValue value) {
        super(lineNumber, colNumber);

        this.nameArrayList = name;
        this.type = type;

        this.value = value;
    }
}
