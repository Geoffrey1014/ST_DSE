package ir.Location;


import ir.IrExpr;
import ir.IrIdent;
import ir.VarTypeEnum;

/**
 * Created by geo on 2020/10/13.
 */
public class IrLocationArray extends IrLocation {
    public IrExpr getElementIndex() {
        return elementIndex;
    }

    public void setElementIndex(IrExpr elementIndex) {
        this.elementIndex = elementIndex;
    }

    private IrExpr elementIndex;

    public IrLocationArray(IrIdent varName , IrExpr elementIndex) {
        super(varName);
        this.elementIndex = canonicalizeExpr(elementIndex);
    }

    @Override
    public VarTypeEnum getExpressionType() {
        return this.varType;
    }


}
