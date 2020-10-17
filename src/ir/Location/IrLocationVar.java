package ir.Location;


import ir.Where;
import ir.IrIdent;
import ir.VarTypeEnum;

/**
 * Created by geo on 2020/10/13.
 */
public class IrLocationVar extends IrLocation {
    protected Where irDeclObject; // IrFieldDeclArray, IrFieldDeclVar, IrParamDecl

    public IrLocationVar(IrIdent varName) {
        super(varName);
    }

    protected void setIrDecl(Where irDeclObject) {
        this.irDeclObject = irDeclObject;
    }

    public Where getIrDecl() {
        return this.irDeclObject;
    }

    @Override
    public VarTypeEnum getExpressionType() {
        return this.varType;
    }



}
