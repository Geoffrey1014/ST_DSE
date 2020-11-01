package ir.Location;


import ir.Ir;
import ir.IrExpr;
import ir.IrIdent;
import ir.VarTypeEnum;
import visitor.BaseVisitor;

/**
 * Created by geo on 2020/10/13.
 */
public class IrLocationArray extends IrLocation {
    public Ir irDeclObject; // IrVarDecl
    private IrExpr elementIndex;

    public IrExpr getElementIndex() {
        return elementIndex;
    }
    public void setElementIndex(IrExpr elementIndex) {
        this.elementIndex = elementIndex;
    }
    public void setIrDecl(Ir irDeclObject) {
        this.irDeclObject = irDeclObject;
    }


    public IrLocationArray(IrIdent varName , IrExpr elementIndex) {
        super(varName);
        this.elementIndex = canonicalizeExpr(elementIndex);
    }

    @Override
    public VarTypeEnum getExpressionType() {
        return this.varType;
    }


    @Override
    public String prettyPrint(String indentSpace) {
        String prettyString = indentSpace + "|--arrayLocation\n";

        // print the name
        prettyString += ("  " + indentSpace + "|--name: " + this.varName.getValue() + "\n");

        // print the type
        prettyString +=  "  " + indentSpace +  "|--type: " + this.varType +"\n";

        prettyString +=  "  " + indentSpace +  "|--index:\n";
        prettyString += this.elementIndex.prettyPrint("    " + indentSpace);

        return prettyString;
    }

    @Override
    public void accept(BaseVisitor<Void> visitor) {
        visitor.visitIrLocationArray(this);
    }
}
