package ir.VARBlockDecl;

import ir.IrIdent;
import ir.Literal.IrLiteral;
import ir.VarTypeEnum;

import java.util.ArrayList;

public class IrValueArray extends IrValue {
    private final ArrayList<IrLiteral>  valueList;

    public IrValueArray(int lineNumber, int colNumber, ArrayList<IrLiteral>  valueList) {
        super(lineNumber, colNumber);
        this.valueList = valueList;
    }
}
