package ir;

import SymbolTable.SymTable;

import java.util.List;

public class IrCodeBlock extends Ir {


    private final List<IrStmt> StmtList;
    public IrCodeBlock(int lineNumber, int colNumber, List<IrStmt> statList) {
        super(lineNumber, colNumber);
        this.StmtList = statList;
    }

    @Override
    public String semanticCheck(SymTable symTable) {
        return null;
    }

    @Override
    public String prettyPrint(String indentSpace) {
        return null;
    }

    @Override
    public void visit(BaseVisitor<Void> visitor) {
        visitor.visitIrCodeBlock(this);
    }

    public List<IrStmt> getStmtList() {
        return StmtList;
    }
}