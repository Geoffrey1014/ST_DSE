package ir.VARBlockDecl;

import ir.VarTypeEnum;
import visitor.BaseVisitor;

public class IrTypeSimple extends IrType {

    public IrTypeSimple(VarTypeEnum type) {
        super(type.getLineNumber(), type.getColNumber(), type);

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
    public void accept(BaseVisitor<Void> visitor) {
        visitor.visitIrTypeSimple(this);
    }
}
