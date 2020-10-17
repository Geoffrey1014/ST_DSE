package ir.Location;

import ir.IrExpr;
import ir.IrIdent;
import ir.VarTypeEnum;

public class IrFbStLocation extends IrExpr {
    protected IrIdent varName;
    protected VarTypeEnum varType;

    public IrFbStLocation(IrIdent varName) {
        super(varName.getLineNumber(), varName.getColNumber());
        this.varName = varName;
    }

    public IrIdent getLocationName() {
        return this.varName;
    }

    public VarTypeEnum getLocationType() {
        return this.varType;
    }

    protected void setLocationType(VarTypeEnum type) {
        this.varType = type;
    }
    @Override
    public VarTypeEnum getExpressionType() {
        return this.varType;
    }
}
