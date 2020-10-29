package ir;

import SymbolTable.SymTable;
import ir.Arg.IrArg;
import ir.Arg.IrArgOutputAssign;
import ir.Location.IrFbStLocation;

import java.util.List;

public class IrFunctionCallStmt extends IrStmt{

    public final IrIdent functionBlockName;
    public final List<IrArg> argsList;
    public final List<IrArgOutputAssign> assignOutputList;
    public List<IrFbStLocation> fbStLocationList; // 和 assignOutputList 是一一对应的，顺序也是一一对应的
//    public IrType functionType = null; // 永远 为 null

    public IrFunctionCallStmt(IrIdent functionBlockName, List<IrArg> argsList, List<IrArgOutputAssign> assignOutputList) {
        super(functionBlockName.getLineNumber(), functionBlockName.getColNumber());
        this.functionBlockName = functionBlockName;
        this.argsList = argsList;
        this.assignOutputList = assignOutputList;
    }

//    public VarTypeEnum getExpressionType() {
//        return this.functionType.type;
//    }

    public String toString() {
        return this.functionBlockName + "(" + this.argsList.toString() + ")";
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
    public void accept(BaseVisitor<Void> visitor) {
        visitor.visitIrFunctionCallStmt(this);
    }
}
