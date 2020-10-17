package ir.Arg;

import SymbolTable.SymTable;
import ir.Ir;

public abstract class IrArg extends Ir {

    public IrArg(int lineNumber, int colNumber) {
        super(lineNumber, colNumber);
    }

    public abstract  String semanticCheck(SymTable symTable);



    public abstract String prettyPrint(String indentSpace);

}
