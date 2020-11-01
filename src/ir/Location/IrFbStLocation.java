package ir.Location;

import ir.Ir;
import ir.IrExpr;
import ir.IrIdent;
import ir.VARBlockDecl.IrVarDecl;
import ir.VarTypeEnum;
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

    public void setIrDecl(Ir pou, IrVarDecl var) {
        this.irDeclPou = pou;
        this.irDeclObject = var;
    }
}
