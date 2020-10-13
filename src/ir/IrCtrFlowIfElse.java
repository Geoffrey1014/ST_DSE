package ir;

public class IrCtrFlowIfElse extends IrCtrFlow {
    private final IrCodeBlock elseBody;
    public IrCtrFlowIfElse(IrExpr condExpr, IrCodeBlock stmtBody, IrCodeBlock elseBody) {
        super(condExpr, stmtBody);
        this.elseBody = elseBody;
    }
}
