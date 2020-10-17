package ir.Arg;

import SymbolTable.SymTable;
import ir.IrIdent;

public class IrArgOutputAssign extends IrArg {
    private final IrIdent fbOutput;
    private final IrIdent acceptLocation;
    public IrArgOutputAssign(int lineNumber, int colNumber, IrIdent fbOutput, IrIdent acceptLocation) {
        super(lineNumber, colNumber);
        this.fbOutput = fbOutput;
        this.acceptLocation = acceptLocation;
    }

    @Override
    public String semanticCheck(SymTable symTable) {
        return null;
    }

    @Override
    public String prettyPrint(String indentSpace) {
        return null;
    }
}
