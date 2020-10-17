package ir;

import java.util.List;

public class IrCodeBlock implements Where {
    private final int lineNumber;
    private final int colNumber;

    private final List<IrStmt> StmtList;
    public IrCodeBlock(int lineNumber, int colNumber, List<IrStmt> statList) {
        this.lineNumber = lineNumber;
        this.colNumber = colNumber;
        this.StmtList = statList;
    }
    @Override
    public int getLineNumber() {
        return lineNumber;
    }

    @Override
    public int getColNumber() {
        return colNumber;
    }
}