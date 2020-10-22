package ir.CtrlFlow;

import SymbolTable.SymTable;
import ir.BaseVisitor;
import ir.IrCodeBlock;
import ir.IrExpr;

public class IrCtrlFlowIf extends IrCtrlFlow {
    public IrCtrlFlowIf(IrExpr condExpr, IrCodeBlock stmtBody) {
        super(condExpr, stmtBody);
    }
    public IrExpr getIfCondition() {
        return this.condExpr;
    }

    public IrCodeBlock getIfBodyBlock() {
        return this.stmtBody;
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
        visitor.visitIrCtrlFlowIf(this);
    }
}
