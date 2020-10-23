package ir.VARBlockDecl;

import SymbolTable.SymTable;
import ir.BaseVisitor;
import ir.Ir;
import ir.IrIdent;

import java.util.ArrayList;

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
    public String semanticCheck(SymTable symTable) {
        String errorMessage = "";
        errorMessage += this.type.semanticCheck(symTable);


        // 1) keep the type of Irvalue and Irtype are the same
        // 2） if this is a array declaration, the size of nameArrayList is the same with it's values'.
        if (value != null){
            errorMessage += this.value.semanticCheck(symTable);

            if ( this.type.getTypeEnum() != this.value.getType() ){
                errorMessage += "the type of Irvalue and Irtype should be the same " +
                        " line: " + this.getLineNumber() + " col: " + this.getColNumber() + "\n";
            }

            if (type instanceof IrTypeArray){
                if ( ((ArrayList)value.getValue()).size() != this.type.getArraySize() ){
                    errorMessage += "the size of nameArrayList should be the same with values " +
                            " line: " + this.getLineNumber() + " col: " + this.getColNumber() + "\n";
                }

            }


        }
        return errorMessage;
    }

    @Override
    public String prettyPrint(String indentSpace) {
        String prettyString = indentSpace + "|--varDecl\n";
        prettyString += ("  " + indentSpace + "|--name: " + this.getName() + "\n");
        prettyString += this.getType().prettyPrint("  " + indentSpace);
        if (value != null){
            prettyString += this.getValue().prettyPrint("  " + indentSpace);
        }
        return prettyString;
    }

    @Override
    public void visit(BaseVisitor<Void> visitor) {
        visitor.visitIrVarDecl(this);
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
