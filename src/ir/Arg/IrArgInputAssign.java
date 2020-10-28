package ir.Arg;

import SymbolTable.SymTable;
import ir.BaseVisitor;
import ir.IrExpr;
import ir.IrIdent;
import ir.POUDecl.IrPouDecl;
import ir.VARBlockDecl.IrVarDecl;

public class IrArgInputAssign extends IrArgExpr {
    public final IrIdent storeLocationName;

    public IrPouDecl irDeclPou;
    public IrVarDecl irDeclVar; // IrVarDecl

    public IrArgInputAssign(IrExpr argValue, IrIdent argName) {
        super(argValue, argName.getLineNumber(), argName.getLineNumber());
        this.storeLocationName = argName;
    }


    @Override
    public String semanticCheck(SymTable symTable) {
        String errorMessage = "";

        return errorMessage;
    }

    @Override
    public String prettyPrint(String indentSpace) {
        String prettyString = indentSpace + "|--assignStmtEquals\n";

        // pretty print the lhs
        prettyString += ("  " + indentSpace + "|--lhs\n");
        prettyString += this.storeLocationName.prettyPrint("    " + indentSpace);

        // print the rhs
        prettyString += ("  " + indentSpace + "|--rhs\n");
        prettyString += this.getArgValue().prettyPrint("    " + indentSpace);

        return prettyString;
    }


    @Override
    public void accept(BaseVisitor<Void> visitor) {
        visitor.visitIrArgInputAssign(this);
    }

}
