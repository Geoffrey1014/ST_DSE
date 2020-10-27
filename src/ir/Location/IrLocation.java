package ir.Location;

import ir.IrExpr;
import ir.IrIdent;
import ir.VarTypeEnum;
import ir.Where;

/**
 * Created by geo on 2020/10/13.
 */
public abstract class IrLocation extends IrExpr {
    public IrIdent varName;
    protected VarTypeEnum varType;

    public IrLocation(IrIdent varName) {
        super(varName.getLineNumber(), varName.getColNumber());
        this.varName = varName;
    }

    public IrIdent getLocationName() {
        return this.varName;
    }

    public VarTypeEnum getLocationType() {
        return this.varType;
    }

    public void setLocationType(VarTypeEnum type) {
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
        Where otherIr = (IrLocation) that;
        return (this.getLocationName().equals(((IrLocation) otherIr).getLocationName())) && (this.getLocationType().equals(((IrLocation) otherIr).getLocationType()));
    }

    @Override
    public int hashCode() {
        return this.varName.hashCode() * this.varType.hashCode();
    }


}
