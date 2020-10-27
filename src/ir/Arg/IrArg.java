package ir.Arg;

import SymbolTable.SymTable;
import ir.Ir;
import ir.VarTypeEnum;

public abstract class IrArg extends Ir {
    public VarTypeEnum typeEnum;

    public IrArg(int lineNumber, int colNumber) {
        super(lineNumber, colNumber);
    }

    public abstract  String semanticCheck(SymTable symTable);



    public abstract String prettyPrint(String indentSpace);

}
