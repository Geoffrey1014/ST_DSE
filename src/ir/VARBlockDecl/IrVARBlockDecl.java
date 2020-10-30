package ir.VARBlockDecl;

import SymbolTable.SymTable;
import ir.BaseVisitor;
import ir.Ir;

import java.util.ArrayList;


public class IrVARBlockDecl extends Ir {

    public final ArrayList<IrVarDecl> VarList;
    public final VarAccessTypeEnum accessType;
    public IrVARBlockDecl(int lineNumber, int colNumber, ArrayList<IrVarDecl> varList, VarAccessTypeEnum accessType) {
        super(lineNumber, colNumber);
        this.VarList = varList;
        this.accessType = accessType;
        for (IrVarDecl varDecl : varList){
            varDecl.accessType = this.accessType;
        }
    }

    public VarAccessTypeEnum getAccessType() {
        return accessType;
    }


    public ArrayList<IrVarDecl> getVarList() {
        return VarList;
    }

    @Override
    public String semanticCheck(SymTable symTable) {
        String errorMessage = "";

        // 1) check that no identifiers declared twice in same scope
        for (IrVarDecl varDecl : this.VarList) {
            if (symTable.checkIfSymbolExistsAtAnyScope(varDecl.getName())) {
                errorMessage += "Duplicate declaration in same scope __filename__" +
                        " line: " + varDecl.getLineNumber() + " col: " + varDecl.getColNumber() + "\n";
            }
            symTable.addObjectToCurrentScope(varDecl.getName(), varDecl);

            // make sure each vardDecl is correct
            errorMessage += varDecl.semanticCheck(symTable);
        }
        return errorMessage;
    }

    @Override
    public String prettyPrint(String indentSpace) {
        StringBuilder prettyString = new StringBuilder(indentSpace + "|--VARBlock:\n");
        prettyString.append("  ").append(indentSpace).append("|--accessType: ").append(this.accessType).append("\n");

        // pretty print statement
        for (IrVarDecl varDecl : this.VarList) {
            prettyString.append(varDecl.prettyPrint("  " + indentSpace));
        }

        return prettyString.toString();
    }

    @Override
    public void accept(BaseVisitor<Void> visitor) {
        visitor.visitIrVARBlockDecl(this);
    }
}
