package ir.VARBlockDecl;

import helper.LlBuilder;
import helper.LlSymbolTable;
import ir.Literal.IrIntLiteral;
import ir.VarTypeEnum;
import ll.location.LlLocation;
import visitor.BaseVisitor;

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
    public String prettyPrint(String indentSpace) {
        String prettyString  = indentSpace + "|--array type: " + this.type.toString() + "\n";
        prettyString += indentSpace +  "|--size: " + this.low.getValue().toString() + " to " + this.high.getValue().toString() + "\n";
        return prettyString;
    }

    @Override
    public void accept(BaseVisitor<Void> visitor) {
        visitor.visitIrTypeArray(this);
    }

    @Override
    public LlLocation generateLlIr(LlBuilder builder, LlSymbolTable symbolTable) {
        return null;
    }

    public int getArraySize(){
        return this.size;
    }


}
