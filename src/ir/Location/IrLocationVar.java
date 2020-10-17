package ir.Location;


import ir.Ir;
import ir.IrIdent;
import ir.VarTypeEnum;

/**
 * Created by geo on 2020/10/13.
 */
public class IrLocationVar extends IrLocation {
    protected Ir irDeclObject; // IrFieldDeclArray, IrFieldDeclVar, IrParamDecl

    public IrLocationVar(IrIdent varName) {
        super(varName);
    }

    protected void setIrDecl(Ir irDeclObject) {
        this.irDeclObject = irDeclObject;
    }

    public Ir getIrDecl() {
        return this.irDeclObject;
    }

    @Override
    public VarTypeEnum getExpressionType() {
        return this.varType;
    }



}
