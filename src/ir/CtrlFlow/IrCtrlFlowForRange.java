package ir.CtrlFlow;

import helper.LlBuilder;
import helper.LlSymbolTable;
import ir.Ir;
import ir.IrExpr;
import ll.location.LlLocation;
import visitor.BaseVisitor;

public class IrCtrlFlowForRange extends Ir {
    private final IrExpr low;
    private final IrExpr high;
    private final IrExpr step;
    public Integer stepNum;

    // TODO : 这里应该是被我复杂化了，一般情况下IrCtrlFlowForRange 只能是两个数值


    public IrCtrlFlowForRange( IrExpr low, IrExpr high, IrExpr step) {

        super( low.getLineNumber(), low.getColNumber());
        this.low = low;
        this.high = high;
        this.step = step;
    }


    @Override
    public String prettyPrint(String indentSpace) {
        String prettyString = indentSpace + "|--range\n";

        prettyString += ("  " + indentSpace + "|--lowBoundary\n");
        prettyString += (this.low.prettyPrint("    " + indentSpace));

        prettyString += ("  " + indentSpace + "|--highBoundary\n");
        prettyString += (this.high.prettyPrint("    " + indentSpace));

        if (this.step != null){
            prettyString += ("  " + indentSpace + "|--step\n");
            prettyString += (this.step.prettyPrint("    " + indentSpace));
        }
        return prettyString;


    }

    @Override
    public void accept(BaseVisitor<Void> visitor) {
        visitor.visitIrCtrlFlowForRange(this);
    }

    @Override
    public LlLocation generateLlIr(LlBuilder builder, LlSymbolTable symbolTable) {
        return null;
    }

    public IrExpr getLow() {
        return low;
    }

    public IrExpr getHigh() {
        return high;
    }

    public IrExpr getStep() {
        return step;
    }
}
