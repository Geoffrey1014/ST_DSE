package ir.CtrlFlow;

import SymbolTable.SymTable;
import ir.BaseVisitor;
import ir.IrCodeBlock;
import ir.IrExpr;

/**
 */
public class IrCtrlFlowWhile extends IrCtrlFlow {
    public IrCtrlFlowWhile(IrExpr condExpr, IrCodeBlock stmtBody) {
        super(condExpr, stmtBody);
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
        visitor.visitIrCtrlFlowWhile(this);
    }
}
