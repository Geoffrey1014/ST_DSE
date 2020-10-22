package ir.VARBlockDecl;

import SymbolTable.SymTable;
import ir.BaseVisitor;
import ir.VarTypeEnum;

public class IrTypeSimple extends IrType {
    private final VarTypeEnum type;
    public IrTypeSimple(VarTypeEnum type) {
        super(type.getLineNumber(), type.getColNumber());
        this.type = type;

    }

    @Override
    public String semanticCheck(SymTable symTable) {
        return "";
    }

    @Override
    public String prettyPrint(String indentSpace) {
        return indentSpace + "|--type: " + this.type.toString() + "\n";
    }

    @Override
    public int getArraySize() {
        return 1;
    }

    @Override
    public VarTypeEnum getTypeEnum() {
        return this.type;
    }

    @Override
    public void visit(BaseVisitor<Void> visitor) {
        visitor.visitIrTypeSimple(this);
    }
}
