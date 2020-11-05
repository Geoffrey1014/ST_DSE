package ir;

import helper.LlBuilder;
import helper.LlSymbolTable;
import ll.location.LlLocation;
import visitor.BaseVisitor;

import java.util.List;

public class IrCodeBlock extends Ir {


    public final List<IrStmt> stmtsList;
    public IrCodeBlock(int lineNumber, int colNumber, List<IrStmt> statList) {
        super(lineNumber, colNumber);
        this.stmtsList = statList;
    }

    @Override
    public String prettyPrint(String indentSpace) {

        StringBuilder prettyString = new StringBuilder(indentSpace + "|--codeBlock:\n");

        // pretty print statement
        for (IrStmt statement : this.stmtsList) {
            prettyString.append(statement.prettyPrint("  " + indentSpace));
        }

        return prettyString.toString();
    }

    @Override
    public void accept(BaseVisitor<Void> visitor) {
        visitor.visitIrCodeBlock(this);
    }

    @Override
    public LlLocation generateLlIr(LlBuilder builder, LlSymbolTable symbolTable) {
        for (IrStmt statement: this.stmtsList) {
//            if(statement instanceof IrStmtContinue){
//                if(builder.pickPocket() != null) {
//                    ((IrStmt) builder.pickPocket()).generateLlIr(builder, symbolTable);
//                }
//            } // TODO： 这个的作用是为了在 for loop 中 continue 之后 能对 counter进行update
            statement.generateLlIr(builder, symbolTable);
        }
        return null;
    }

    public List<IrStmt> getStmtsList() {
        return stmtsList;
    }
}