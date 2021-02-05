package ir.VARBlockDecl;

import helper.LlBuilder;
import helper.LlSymbolTable;
import ir.Literal.IrLiteral;
import ir.VarTypeEnum;
import ll.location.LlLocation;
import visitor.BaseVisitor;

public class IrValueSimple extends IrValue {
    public final IrLiteral value;
    public VarTypeEnum type;
    public IrValueSimple( IrLiteral value) {
        super(value.getLineNumber(), value.getColNumber());
        this.value = value;
    }


    @Override
    public String prettyPrint(String indentSpace) {
        return indentSpace + "|--value: " + this.value.getValue().toString() + "\n";
    }

    @Override
    public Object getValue() {
        return this.value;
    }

    @Override
    public VarTypeEnum getType() {
        return type;
    }

    @Override
    public void accept(BaseVisitor<Void> visitor) {
        visitor.visitIrValueSimple(this);
    }

    @Override
    public LlLocation generateLlIr(LlBuilder builder, LlSymbolTable symbolTable) {

        return this.value.generateLlIr(builder, symbolTable);
    }
}
