package ir;

import java.util.List;

public class IrCodeBlock extends Ir {
    private final List<IrStat> StatList;
    public IrCodeBlock(int lineNumber, int colNumber, List<IrStat> statList) {
        super(lineNumber, colNumber);
        this.StatList = statList;
    }
}