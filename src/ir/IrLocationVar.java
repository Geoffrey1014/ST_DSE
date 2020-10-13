package ir;


/**
 * Created by geo on 2020/10/13.
 */
public class IrLocationVar extends IrLocation {
    protected Ir irDeclObject; // IrFieldDeclArray, IrFieldDeclVar, IrParamDecl

    public IrLocationVar(IrIdent varName, int lineNumber, int colNumber) {
        super(varName, lineNumber, colNumber);
    }

    protected void setIrDecl(Ir irDeclObject) {
        this.irDeclObject = irDeclObject;
    }

    public Ir getIrDecl() {
        return this.irDeclObject;
    }

    @Override
    public IrType getExpressionType() {
        return this.varType;
    }



}
