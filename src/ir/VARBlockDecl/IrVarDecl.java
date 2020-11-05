package ir.VARBlockDecl;

import helper.LlBuilder;
import helper.LlSymbolTable;
import ir.Ir;
import ir.IrIdent;
import ll.location.LlLocation;
import visitor.BaseVisitor;

public class IrVarDecl extends Ir {
    public final IrType type;
    public final IrValue value;
    public IrIdent name;
    public VarAccessTypeEnum accessType;

    public IrVarDecl(int lineNumber, int colNumber, IrIdent name, IrType type, IrValue value) {
        super(lineNumber, colNumber);
        this.name = name;
        this.type = type;
        this.value = value;
    }


    @Override
    public String prettyPrint(String indentSpace) {
        String prettyString = indentSpace + "|--varDecl\n";
        prettyString += ("  " + indentSpace + "|--name: " + this.getName() + "\n");
        prettyString += this.getType().prettyPrint("    " + indentSpace);
        if (value != null){
            prettyString += this.getValue().prettyPrint( indentSpace);
        }
        return prettyString;
    }

    @Override
    public void accept(BaseVisitor<Void> visitor) {
        visitor.visitIrVarDecl(this);
    }

    @Override
    public LlLocation generateLlIr(LlBuilder builder, LlSymbolTable symbolTable) {
        return null;
    }

    public String getName() {

        return name.getValue();
    }

    public IrType getType(){
        return type;
    }

    public IrValue getValue() {
        return value;
    }
}
