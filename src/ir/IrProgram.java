package ir;

import java.util.List;

public class IrProgram extends IrPou {
    private final List<IrVarBlock> VarBlockList;
    private final IrCodeBlock CodeBlock;
    public IrProgram(int lineNumber, int colNumber, List<IrVarBlock> varBlockList, IrCodeBlock codeBlock) {
        super(lineNumber, colNumber);
        this.VarBlockList = varBlockList;
        CodeBlock = codeBlock;
    }
}
