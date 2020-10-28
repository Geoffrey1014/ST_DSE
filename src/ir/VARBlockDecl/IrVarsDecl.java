package ir.VARBlockDecl;

import SymbolTable.SymTable;
import ir.BaseVisitor;
import ir.Ir;
import ir.IrIdent;

import java.util.ArrayList;

public class IrVarsDecl extends Ir {
    private final ArrayList<IrIdent> nameArrayList;
    private final IrType type;
    private final IrValue values;
    public IrIdent name;
    public VarAccessTypeEnum accessType;

    public IrVarsDecl(int lineNumber, int colNumber, ArrayList<IrIdent> name, IrType type, IrValue values) {
        super(lineNumber, colNumber);
        this.nameArrayList = name;
        this.type = type;
        this.values = values;
    }

    public ArrayList<IrIdent> getNameArrayList() {
        return nameArrayList;
    }

    @Override
    public String semanticCheck(SymTable symTable) {
        String errorMessage = "";
        errorMessage += this.type.semanticCheck(symTable);


        // 1) keep the type of Irvalue and Irtype are the same
        // 2） if this is a array declaration, the size of nameArrayList is the same with it's values'.
        if (values != null){
            errorMessage += this.values.semanticCheck(symTable);

            if ( this.type.getTypeEnum() != this.values.getType() ){
                errorMessage += "the type of Irvalue and Irtype should be the same " +
                        " line: " + this.getLineNumber() + " col: " + this.getColNumber() + "\n";
            }

            if (type instanceof IrTypeArray){
                if ( ((ArrayList) values.getValue()).size() != this.type.getArraySize() ){
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
        if (values != null){
            prettyString += this.getValues().prettyPrint("  " + indentSpace);
        }
        return prettyString;
    }

    @Override
    public void accept(BaseVisitor<Void> visitor) {
        visitor.visitIrVarsDecl(this);
    }

    public String getName() {
        String names = "";
        for (IrIdent id : this.nameArrayList){
            names += id.getValue();
            names += ", ";
        }
        return names;
    }

    public IrType getType(){
        return type;
    }

    public IrValue getValues() {
        return values;
    }
}
