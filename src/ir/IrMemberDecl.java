package ir;

public abstract class IrMemberDecl extends Ir {
    private final IrIdent name;

    public IrMemberDecl(IrIdent name, int lineNumber, int colNumber) {
        super(lineNumber, colNumber);
        this.name = name;
    }


    public String getName() {
        return this.name.getValue();
    }

    public IrIdent getIdentName() {
        return this.name;
    }


//    public IrType getType() {
//        return this.type;
//    }
}