package ir;

import SymbolTable.SymTable;
import ir.Arg.IrArg;
import ir.Arg.IrArgOutputAssign;
import ir.VARBlockDecl.IrType;

import java.util.List;

public class IrFunctionCallExpr extends IrExpr {
    public final IrIdent functionName;
    public final List<IrArg> argsList;
    public final List<IrArgOutputAssign> assignOutputList;
    public IrType functionType;

    public IrFunctionCallExpr(IrIdent functionName, List<IrArg> argsList, List<IrArgOutputAssign> assignOutputList) {
        super(functionName.getLineNumber(), functionName.getColNumber());
        this.functionName = functionName;
        this.argsList = argsList;
        this.assignOutputList = assignOutputList;
    }

    public VarTypeEnum getExpressionType() {
        return this.functionType.type;
    }

    public String toString() {
        return this.functionName + "(" + this.argsList.toString() + ")";
    }

    @Override
    public String semanticCheck(SymTable symTable) {
        return null;
    }

    @Override
    public String prettyPrint(String indentSpace) {

        StringBuilder prettyString = new StringBuilder(indentSpace + "|--methodCallExpr\n");

        // print the method name
        prettyString.append("  ").append(indentSpace).append("|--name: ").append(this.functionName.getValue()).append("\n");

        // print the method type
        prettyString.append("  ").append(indentSpace).append("|--type: ").append(this.functionType);

        // print the method args_list
        prettyString.append("  ").append(indentSpace).append("|--argsList:\n");
        for (IrArg arg : this.argsList) {
            prettyString.append(arg.prettyPrint("    " + indentSpace));
        }

        return prettyString.toString();
    }

    @Override
    public void visit(BaseVisitor<Void> visitor) {
        visitor.visitIrFunctionCallExpr(this);
    }
}
