package ir;

import helper.LlBuilder;
import helper.LlSymbolTable;
import ir.Arg.IrArg;
import ir.Arg.IrArgInputAssign;
import ir.Arg.IrArgOutputAssign;
import ir.VARBlockDecl.IrType;
import ll.LlComponent;
import ll.LlMethodCallStmt;
import ll.location.LlLocation;
import ll.location.LlLocationVar;
import visitor.BaseVisitor;

import java.util.ArrayList;
import java.util.List;

public class IrFunctionCallExpr extends IrExpr {
    public final IrIdent functionName;
    public final List<IrArg> argsList;
    public final List<IrArgOutputAssign> assignOutputList;
    public List<IrArgInputAssign> argInputAssignsList = null; // 如果是 null, 代表 IrArg 类型不是 IrArgInputAssign

    public IrType functionType;

    public IrFunctionCallExpr(IrIdent functionName, List<IrArg> argsList, List<IrArgOutputAssign> assignOutputList) {
        super(functionName.getLineNumber(), functionName.getColNumber());
        this.functionName = functionName;
        this.argsList = argsList;
        this.assignOutputList = assignOutputList;
    }

    public VarTypeEnum getExpressionType() {
        if (this.functionType != null){
            // 考虑到 the situation of that funciont block is used as function
            return this.functionType.type;
        }
        else return null;

    }

    public String toString() {
        return this.functionName + "(" + this.argsList.toString() + ")";
    }



    @Override
    public String prettyPrint(String indentSpace) {

        StringBuilder prettyString = new StringBuilder(indentSpace + "|--functionCallExpr\n");

        // print the method name
        prettyString.append("  ").append(indentSpace).append("|--name: ").append(this.functionName.getValue()).append("\n");

        // print the method type
        prettyString.append("  ").append(indentSpace).append("|--type: ").append(this.functionType).append("\n");

        // print the method args_list
        prettyString.append("  ").append(indentSpace).append("|--argsList:\n");
        for (IrArg arg : this.argsList) {
            prettyString.append(arg.prettyPrint("    " + indentSpace));
        }

        return prettyString.toString();
    }

    @Override
    public void accept(BaseVisitor<Void> visitor) {
        visitor.visitIrFunctionCallExpr(this);
    }

    /**
     * function call  只是要实现call 这个过程的LLIR 形式而已
     * LLIR 再翻译成堆栈的形式
     */
    @Override
    public LlLocation generateLlIr(LlBuilder builder, LlSymbolTable symbolTable) {
        LlLocationVar returnLocation = builder.generateTemp();
        List<LlComponent> argsList = new ArrayList<>();
        // TODO List<IrArgOutputAssign> assignOutputList  也是要把顺序对上才行
        if (this.assignOutputList != null){
            for (IrArgInputAssign argInputAssign : this.argInputAssignsList){
                argsList.add(argInputAssign.generateLlIr(builder,symbolTable));
            }
        }
        else {
            for(IrArg arg : this.argsList){
                argsList.add(arg.generateLlIr(builder, symbolTable));
            }
        }


        LlMethodCallStmt methodCallStmt = new LlMethodCallStmt(this.functionName.getValue(), argsList, returnLocation);
        builder.appendStatement(methodCallStmt);
        return returnLocation;
    }
}
