package ir;


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

    public IrLocationArray(IrExpr elementIndex, IrIdent varName, int lineNumber, int colNumber) {
        super(varName, lineNumber, colNumber);
        this.elementIndex = canonicalizeExpr(elementIndex);
    }

    @Override
    public IrType getExpressionType() {
        return this.varType;
    }






}
