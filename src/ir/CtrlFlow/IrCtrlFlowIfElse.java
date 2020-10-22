package ir.CtrlFlow;

import SymbolTable.SymTable;
import ir.BaseVisitor;
import ir.IrCodeBlock;
import ir.IrExpr;

/**
 */
public class IrCtrlFlowIfElse extends IrCtrlFlow {
    private final IrCodeBlock elseBlock;

    public IrCtrlFlowIfElse(IrExpr condExpr, IrCodeBlock stmtBody, IrCodeBlock elseBlock) {
        super(condExpr, stmtBody);
        this.elseBlock = elseBlock;
    }



    public IrCodeBlock getElseBlock() {
        return elseBlock;
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
        visitor.visitIrCtrlFlowIfElse(this);
    }
}
