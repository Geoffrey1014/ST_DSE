package ir;


/**
 * Created by devinmorgan on 10/11/16.
 */
public abstract class IrType implements Ir {
    private final int lineNumber;
    private final int colNumber;
    public IrType(int lineNumber, int colNumber){
        this.lineNumber = lineNumber;
        this.colNumber = colNumber;
    }

    @Override
    public int getLineNumber() {
        return lineNumber;
    }

    @Override
    public int getColNumber() {
        return colNumber;
    }


//    @Override
//    public String semanticCheck(ScopeStack scopeStack) {
//        return "";
//    }
//
//    public AssemblyBuilder generateCode(AssemblyBuilder assembly, Register register, StackFrame stackFrame) {
//
//        return assembly;
//    }


}
