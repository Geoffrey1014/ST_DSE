package ir.Location;


import SymbolTable.SymTable;
import ir.*;
import ir.VARBlockDecl.IrTypeArray;

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
    public String semanticCheck(SymTable symTable) {
        String errorMessage = "";

        // 1) verify that the IrExpr is semantically correct
        errorMessage += this.elementIndex.semanticCheck(symTable);

        // 2) make sure the array has been declared already
        if (symTable.checkIfSymbolExistsAtAnyScope(this.getLocationName().getValue())) {
            Ir object = symTable.getSymbol(this.getLocationName().getValue());
            // 3) make sure that var is an array (and not a method or non-array)

            if (! (object instanceof IrTypeArray) ) {
                errorMessage += "Non-array variable be accessed as an array" +
                        " line: " + this.elementIndex.getLineNumber() + " col: " + this.elementIndex.getColNumber() + "\n";
            }
            else {
                IrTypeArray array = (IrTypeArray) object;

                // IMPORTANT: set the IrType of the IrLocationArray
                this.setLocationType(array.getTypeEnum());
            }
        } else {
            errorMessage += "Array variable used before declared" +
                    " line: " + this.elementIndex.getLineNumber() + " col: " + this.elementIndex.getColNumber() + "\n";
        }

        // 4) make sure that the IrExpr offset is an IrTypeInt
        if (!(this.elementIndex.getExpressionType() == VarTypeEnum.RES_INT)) {
            errorMessage += "Element offset must be of type int" +
                    " line: " + this.elementIndex.getLineNumber() + " col: " + this.elementIndex.getColNumber() + "\n";
        }

        return errorMessage;
    }

    @Override
    public String prettyPrint(String indentSpace) {
        String prettyString = indentSpace + "|--arrayAccess\n";

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
