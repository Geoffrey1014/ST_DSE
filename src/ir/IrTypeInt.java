package ir;


public class IrTypeInt extends IrType {
    public IrTypeInt(int lineNumber, int colNumber) {
        super(lineNumber, colNumber);
    }

    @Override
    public boolean equals(Object that) {
        if (that == this) {
            return true;
        }
        if (!(that instanceof IrTypeInt)) {
            return false;
        }
        return true;
    }

//    @Override
//    public int hashCode() {
//        return 11; // some arbitrary prime
//    }
//
//    @Override
//    public String prettyPrint(String indentSpace) {
//        return indentSpace + "|--type: int\n";
//    }
//
//    @Override
//    public LlLocation generateLlIr(LlBuilder builder, LlSymbolTable symbolTable) {
//        return null;
//    }
}