package ir.CtrlFlow;

import SymbolTable.SymTable;
import ir.BaseVisitor;
import ir.Ir;
import ir.IrExpr;

public class IrCtrlFlowForRange extends Ir {
    private final IrExpr low;
    private final IrExpr high;
    private final Integer step;

    // TODO : 这里应该是被我复杂化了，一般情况下IrCtrlFlowForRange 只能是两个数值


    public IrCtrlFlowForRange( IrExpr low, IrExpr high, Integer step) {

        super( low.getLineNumber(), low.getColNumber());
        this.low = low;
        this.high = high;
        this.step = step;
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
        visitor.visitIrCtrlFlowForRange(this);
    }
}
