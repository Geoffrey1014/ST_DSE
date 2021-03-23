package ir.CtrlFlow;

import helper.LlBuilder;
import helper.LlSymbolTable;
import ir.Ir;
import ir.IrExpr;
import ll.location.LlLocation;
import visitor.BaseVisitor;

public class IrCtrlFlowForRange extends Ir {
    private final IrExpr left;
    private final IrExpr right;
    private final IrExpr step;
    public Integer stepNum;

    // TODO : 这里应该是被我复杂化了，一般情况下IrCtrlFlowForRange 只能是两个数值


    public IrCtrlFlowForRange(IrExpr left, IrExpr right, IrExpr step) {

        super( left.getLineNumber(), left.getColNumber());
        this.left = left;
        this.right = right;
        this.step = step;
    }


    @Override
    public String prettyPrint(String indentSpace) {
        String prettyString = indentSpace + "|--range\n";

        prettyString += ("  " + indentSpace + "|--leftBoundary\n");
        prettyString += (this.left.prettyPrint("    " + indentSpace));

        prettyString += ("  " + indentSpace + "|--rightBoundary\n");
        prettyString += (this.right.prettyPrint("    " + indentSpace));

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

    public IrExpr getLeft() {
        return left;
    }

    public IrExpr getRight() {
        return right;
    }

    public IrExpr getStep() {
        return step;
    }
}
