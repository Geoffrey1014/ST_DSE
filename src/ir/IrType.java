package ir;


/**
 * Created by devinmorgan on 10/11/16.
 */
public abstract class IrType extends Ir {
    public IrType(int lineNumber, int colNumber) {
        super(lineNumber, colNumber);
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
