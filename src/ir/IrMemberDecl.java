package ir;

public abstract class IrMemberDecl implements Where {
    private final IrIdent name;
    private final int lineNumber;
    private final int colNumber;

    public IrMemberDecl(IrIdent name, int lineNumber, int colNumber) {
        this.lineNumber = lineNumber;
        this.colNumber = colNumber;
        this.name = name;
    }

    @Override
    public int getLineNumber() {
        return lineNumber;
    }

    @Override
    public int getColNumber() {
        return colNumber;
    }


    public String getName() {
        return this.name.getName();
    }

    public IrIdent getIdentName() {
        return this.name;
    }


//    public IrType getType() {
//        return this.type;
//    }
}