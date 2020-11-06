package ir;

import helper.LlBuilder;
import helper.LlSymbolTable;
import ir.Arg.IrArg;
import ir.Arg.IrArgInputAssign;
import ir.Arg.IrArgOutputAssign;
import ir.POUDecl.IrPouDecl;
import ll.LlComponent;
import ll.LlMethodCallStmt;
import ll.assignStmt.LlAssignStmtRegular;
import ll.location.LlFbStLocation;
import ll.location.LlLocation;
import ll.location.LlLocationVar;
import visitor.BaseVisitor;

import java.util.ArrayList;
import java.util.List;

public class IrFunctionCallStmt extends IrStmt{

    public final IrIdent functionBlockName;
    public IrPouDecl functionBlock;

    public final List<IrArg> argsList;
    public final List<IrArgOutputAssign> assignOutputList;
    public List<IrArgInputAssign> argInputAssignsList = null; // 如果是 null, 代表 IrArg 类型不是 IrArgInputAssign
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

    @Override
    public LlLocation generateLlIr(LlBuilder builder, LlSymbolTable symbolTable) {

        List<LlComponent> argsList = new ArrayList<>();
        if (this.argInputAssignsList != null && this.argInputAssignsList.size() != 0){
            for (IrArgInputAssign argInputAssign : this.argInputAssignsList){
                argsList.add(argInputAssign.generateLlIr(builder,symbolTable));
            }
        }
        else {
            for(IrArg arg : this.argsList){
                argsList.add(arg.generateLlIr(builder, symbolTable));
            }
        }
        LlMethodCallStmt methodCallStmt = new LlMethodCallStmt(this.functionBlockName.getValue(), argsList);
        builder.appendStatement(methodCallStmt);

        // TODO List<IrArgOutputAssign> assignOutputList  也是要把顺序对上才行 怎么办呢
        // 生成 LlFbStLocation, 交给 LlFbStLocation 处理 , 目前是很粗糙的，传递的信息很少
        for(IrArgOutputAssign arg : this.assignOutputList){
            LlFbStLocation llFbStLocation = new LlFbStLocation(arg.fbOutput.getValue(), this.functionBlockName.getValue());
            LlLocationVar var = new LlLocationVar(arg.acceptLocation.varName.getValue());
            builder.appendStatement(new LlAssignStmtRegular(var, llFbStLocation));

        }


        return null;

    }
}
