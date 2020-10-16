package ir.Location;

import ir.Ir;
import ir.IrExpr;
import ir.IrIdent;
import ir.IrType;

/**
 * Created by geo on 2020/10/13.
 */
public abstract class IrLocation extends IrExpr {
    protected IrIdent varName;
    protected IrType varType;

    public IrLocation(IrIdent varName) {
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
    public boolean equals(Object that) {
        if (that == this) {
            return true;
        }
        if (!(that instanceof IrLocation)) {
            return false;
        }
        Ir otherIr = (IrLocation) that;
        return (this.getLocationName().equals(((IrLocation) otherIr).getLocationName())) && (this.getLocationType().equals(((IrLocation) otherIr).getLocationType()));
    }

    @Override
    public int hashCode() {
        return this.varName.hashCode() * this.varType.hashCode();
    }
}