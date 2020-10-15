package ir;

import java.util.ArrayList;

public class IrCtrlFlowIfElsif extends  IrCtrlFlowIf{
    private  final ArrayList<IrCtrlFlowElsif> elsifArrayList;
    public IrCtrlFlowIfElsif(IrExpr condExpr, IrCodeBlock stmtBody, ArrayList<IrCtrlFlowElsif> elsifArrayList) {
        super(condExpr, stmtBody);
        this.elsifArrayList = elsifArrayList;
    }

    public ArrayList<IrCtrlFlowElsif> getElsifArrayList() {
        return elsifArrayList;
    }
}
