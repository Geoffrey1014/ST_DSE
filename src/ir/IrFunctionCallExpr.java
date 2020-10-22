package ir;

import SymbolTable.SymTable;
import ir.Arg.IrArg;

import java.util.List;

public class IrFunctionCallExpr extends IrExpr {
    private final IrIdent methodName;
    private final List<IrArg> argsList;
    private VarTypeEnum methodType;

    public IrFunctionCallExpr(IrIdent methodName, List<IrArg> argsList) {
        super(methodName.getLineNumber(), methodName.getColNumber());
        this.methodName = methodName;
        this.argsList = argsList;
    }

    public VarTypeEnum getExpressionType() {
        return this.methodType;
    }

    public String toString() {
        return this.methodName + "(" + this.argsList.toString() + ")";
    }

    @Override
    public String semanticCheck(SymTable symTable) {
        return null;
    }

    @Override
    public String prettyPrint(String indentSpace) {
        return null;
    }

    @Override
    public void visit(BaseVisitor<Void> visitor) {
        visitor.visitIrFunctionCallExpr(this);
    }
}
