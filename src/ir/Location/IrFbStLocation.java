package ir.Location;

import ir.IrExpr;
import ir.IrIdent;
import ir.IrType;

public class IrFbStLocation extends IrExpr {
    protected IrIdent varName;
    protected IrType varType;

    public IrFbStLocation(IrIdent varName) {
        super(varName.getLineNumber(), varName.getColNumber());
        this.varName = varName;
    }

    public IrIdent getLocationName() {
        return this.varName;
    }

    public IrType getLocationType() {
        return this.varType;
    }

    protected void setLocationType(IrType type) {
        this.varType = type;
    }
    @Override
    public IrType getExpressionType() {
        return this.varType;
    }
}
