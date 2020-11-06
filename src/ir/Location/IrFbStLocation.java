package ir.Location;

import helper.LlBuilder;
import helper.LlSymbolTable;
import ir.Ir;
import ir.IrExpr;
import ir.IrIdent;
import ir.VARBlockDecl.IrVarDecl;
import ir.VarTypeEnum;
import ll.location.LlFbStLocation;
import ll.location.LlLocation;
import visitor.BaseVisitor;

public class IrFbStLocation extends IrExpr {
    // TODO ；  这里比较复杂，因为引用的是其他POU内声明的变量，并且一定是 VAR_OUTPUT or VAR_INPUT_OUTPUT 类型

    public Ir irDeclPou; // IrVarDecl
    public Ir irDeclObject; // IrVarDecl


    public IrIdent varNameFirst;
    public IrIdent varNameLast;
    public VarTypeEnum varType;

    public IrFbStLocation(IrIdent varName1, IrIdent varName2) {
        super(varName1.getLineNumber(), varName2.getColNumber());
        this.varNameFirst = varName1;
        this.varNameLast = varName2;
    }

    public IrIdent getFirstLocationName() {
        return this.varNameFirst;
    }
    public IrIdent getLastLocationName() {
        return this.varNameLast;
    }

    public VarTypeEnum getLocationType() {
        return this.varType;
    }

    public void setLocationType(VarTypeEnum type) {
        this.varType = type;
    }
    @Override
    public VarTypeEnum getExpressionType() {
        return this.varType;
    }


    @Override
    public String prettyPrint(String indentSpace) {
        String pretttString = indentSpace + "|--FbStLocation";
        pretttString += "  " + indentSpace + "|--name: " + this.varNameFirst.getValue() + "." +this.varNameLast.getValue() + "\n";
        pretttString += "  " + indentSpace + "|--type: " + this.varType + "\n";

        return pretttString;
    }

    @Override
    public void accept(BaseVisitor<Void> visitor) {
        visitor.visitIrFbStLocation(this);
    }

    /**
     * 这个有部分面向对象特征啊，怎么搞？
     * 总之没有指针，那么就不能生成FB的不同对象。
     * LlFbStLocation 应该保存那些信息？
     * 运行时，应该做什么动作？内存状态怎么变化？
     * 可以先简化一下，因为FB只有一个 实体对象。
     */
    @Override
    public LlLocation generateLlIr(LlBuilder builder, LlSymbolTable symbolTable) {
        // TODO ： 目前只是简单的把名字穿进去，后续需要更多信息再加吧
        return new LlFbStLocation(this.varNameLast.getValue(), this.varNameFirst.getValue());
    }

    public void setIrDecl(Ir pou, IrVarDecl var) {
        this.irDeclPou = pou;
        this.irDeclObject = var;
    }
}
