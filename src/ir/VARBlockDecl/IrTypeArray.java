package ir.VARBlockDecl;

import SymbolTable.SymTable;
import ir.BaseVisitor;
import ir.Literal.IrIntLiteral;
import ir.VarTypeEnum;

public class IrTypeArray extends IrType {
    public final IrIntLiteral low;
    public final IrIntLiteral high;

    public int size;


    public IrTypeArray(int lineNumber, int colNumber, IrIntLiteral low, IrIntLiteral high, VarTypeEnum type) {
        super(lineNumber, colNumber, type);
        this.low = low;
        this.high = high;

    }

    @Override
    public String semanticCheck(SymTable symTable) {
        String errorMessage = "";

        // make sure that the array size is greater than 0
        this.size = (int) (this.high.getValue() - this.low.getValue());
        if (this.size <= 0) {
            errorMessage += "Array size must be a non-zero positive integer" +
                    " line: " + this.getLineNumber() + " col: " + this.getColNumber();
        }

        return errorMessage;
    }

    @Override
    public String prettyPrint(String indentSpace) {
        String prettyString  = indentSpace + "|--array type: " + this.type.toString() + "\n";
        prettyString += indentSpace +  "|--size: " + this.low.getValue().toString() + " to " + this.high.getValue().toString();
        return prettyString;
    }

    @Override
    public void accept(BaseVisitor<Void> visitor) {
        visitor.visitIrTypeArray(this);
    }

    public int getArraySize(){
        return this.size;
    }


}
