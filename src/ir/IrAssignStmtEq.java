package ir;


import helper.LlBuilder;
import helper.LlSymbolTable;
import ir.Location.IrLocation;
import ir.Location.IrLocationArray;
import ll.assignStmt.LlAssignStmtRegular;
import ll.location.LlLocation;
import ll.location.LlLocationVar;
import visitor.BaseVisitor;

public class IrAssignStmtEq extends IrAssignStmt {
    private final IrExpr expr;

    public IrAssignStmtEq(IrLocation storeLocation, IrExpr newValue, int lineNumber, int colNumber) {
        super(storeLocation, lineNumber, colNumber);
        this.expr = newValue;
    }


    @Override
    public String prettyPrint(String indentSpace) {

        String prettyString = indentSpace + "|--assignStmtEquals\n";

        // pretty print the lhs
        prettyString += ("  " + indentSpace + "|--lhs\n");
        prettyString += this.getStoreLocation().prettyPrint("    " + indentSpace);

        // print the rhs
        prettyString += ("  " + indentSpace + "|--rhs\n");
        prettyString += this.expr.prettyPrint("    " + indentSpace);

        return prettyString;
    }

    @Override
    public void accept(BaseVisitor<Void> visitor) {
        visitor.visitIrAssignStmtEq(this);
    }

    @Override
    public LlLocation generateLlIr(LlBuilder builder, LlSymbolTable symbolTable) {
        LlLocation tempVal = expr.generateLlIr(builder, symbolTable);

        if(this.getStoreLocation() instanceof IrLocationArray){
            LlLocation arrayLocation = ((IrLocationArray) this.getStoreLocation()).generateLlIr(builder, symbolTable);
            LlAssignStmtRegular regularAssignment = new LlAssignStmtRegular(arrayLocation, tempVal);
            builder.appendStatement(regularAssignment);
        }
        else {
            LlAssignStmtRegular regularAssignment = new LlAssignStmtRegular(new LlLocationVar(this.getStoreLocation().getLocationName().getValue()), tempVal);
            builder.appendStatement(regularAssignment);
        }
        return null;
    }

    public IrExpr getExpr() {
        return expr;
    }
}
