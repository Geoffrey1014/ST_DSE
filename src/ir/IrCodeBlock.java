package ir;

import java.util.List;

public class IrCodeBlock extends Ir {
    private final List<IrStmt> StmtList;
    public IrCodeBlock(int lineNumber, int colNumber, List<IrStmt> statList) {
        super(lineNumber, colNumber);
        this.StmtList = statList;
    }
}