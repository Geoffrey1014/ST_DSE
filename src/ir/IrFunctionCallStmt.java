package ir;

import SymbolTable.SymTable;
import ir.Arg.IrArg;
import ir.Arg.IrArgOutputAssign;

import java.util.List;

public class IrFunctionCallStmt extends IrStmt{

    public final IrIdent functionBlockName;
    public final List<IrArg> argsList;
    public final List<IrArgOutputAssign> assignOutputList;
//    public List<IrFbStLocation> fbStLocationList; // 和 assignOutputList 是一一对应的，顺序也是一一对应的
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
        StringBuilder prettyString = new StringBuilder(indentSpace + "|--functionCallExpr\n");

        // print the method name
        prettyString.append("  ").append(indentSpace).append("|--name: ").append(this.functionBlockName.getValue()).append("\n");


        // print the method args_list
        prettyString.append("  ").append(indentSpace).append("|--argsList:\n");
        for (IrArg arg : this.argsList) {
            prettyString.append(arg.prettyPrint("    " + indentSpace));
        }

        if (this.assignOutputList != null){
            for (IrArgOutputAssign arg : this.assignOutputList) {
                prettyString.append(arg.prettyPrint("    " + indentSpace));
            }
        }

        return prettyString.toString();    }

    @Override
    public void accept(BaseVisitor<Void> visitor) {
        visitor.visitIrFunctionCallStmt(this);
    }
}
