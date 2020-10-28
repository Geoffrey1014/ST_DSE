package ir.Arg;


import SymbolTable.SymTable;
import ir.BaseVisitor;
import ir.IrExpr;
import ir.VarTypeEnum;

public class IrArgExpr extends IrArg {
    public final IrExpr argValue;

    public IrArgExpr(IrExpr argValue, int lineNum, int colNum) {
        super(lineNum, colNum);

        // we would like to canonicalize the expression if possible
        if (argValue != null) {
            IrExpr expr = (IrExpr) argValue;
            this.argValue = IrExpr.canonicalizeExpr(expr);
        } else {
            this.argValue = null;
        }
    }

    public IrExpr getArgValue() {
        return argValue;
    }

    public VarTypeEnum getArgumentType() {
        return ((IrExpr) this.getArgValue()).getExpressionType();
    }

    @Override
    public  String semanticCheck(SymTable symTable) {
        String errorMessage = ((IrExpr) this.getArgValue()).semanticCheck(symTable);

        return errorMessage;
    }

    @Override
    public String prettyPrint(String indentSpace) {
        String prettyString = indentSpace + "|--argExpr:\n";

        // pretty print the expression
        prettyString += ((IrExpr) this.getArgValue()).prettyPrint("  " + indentSpace);

        return prettyString;
    }

    @Override
    public void accept(BaseVisitor<Void> visitor) {
         visitor.visitIrArgExpr(this);
    }
}
