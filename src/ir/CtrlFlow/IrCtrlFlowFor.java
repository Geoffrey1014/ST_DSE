package ir.CtrlFlow;

import SymbolTable.SymTable;
import ir.BaseVisitor;
import ir.IrCodeBlock;
import ir.IrStmt;
import ir.Location.IrLocationVar;

public class IrCtrlFlowFor extends IrStmt {
    private final IrLocationVar counter;
    private final IrCtrlFlowForRange range;
    private final IrCodeBlock codeBlock;


    public IrCtrlFlowFor(IrLocationVar counter, IrCtrlFlowForRange range, IrCodeBlock codeBlock) {

        super( counter.getLineNumber(), counter.getColNumber());
        this.counter = counter;
        this.range = range;
        this.codeBlock = codeBlock;
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
        visitor.visitIrCtrlFlowFor(this);
    }
}
