package ir.VARBlockDecl;

import ir.Ir;
import ir.IrIdent;
import visitor.BaseVisitor;

import java.util.ArrayList;

/**
 * 这个类不在AST中，只在由CST到AST到转换中临时出现，过度用的
 */
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
    public String prettyPrint(String indentSpace) {
        String prettyString = indentSpace + "|--varDecl\n";
        prettyString += ("  " + indentSpace + "|--name: " + this.getName() + "\n");
        prettyString += this.getType().prettyPrint("     " + indentSpace);
        if (values != null){
            prettyString += this.getValues().prettyPrint("    " + indentSpace);
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
