package ir.Arg;

import helper.LlBuilder;
import helper.LlSymbolTable;
import ir.IrExpr;
import ir.IrIdent;
import ir.POUDecl.IrPouDecl;
import ir.VARBlockDecl.IrVarDecl;
import ll.location.LlLocation;
import visitor.BaseVisitor;

public class IrArgInputAssign extends IrArg {
    public final IrIdent storeLocationName;
    public final IrExpr argValue;

    public IrPouDecl irDeclPou;
    public IrVarDecl irDeclVar; // IrVarDecl

    public IrArgInputAssign(IrExpr argValue, IrIdent argName) {
        super(argName.getLineNumber(), argName.getLineNumber());
        this.storeLocationName = argName;
        this.argValue = argValue;
    }


    @Override
    public String prettyPrint(String indentSpace) {
        String prettyString = indentSpace + "|--assignStmtEquals\n";

        // pretty print the lhs
        prettyString += ("  " + indentSpace + "|--lhs\n");
        prettyString += this.storeLocationName.prettyPrint("    " + indentSpace);

        // print the rhs
        prettyString += ("  " + indentSpace + "|--rhs\n");
        prettyString += this.argValue.prettyPrint("    " + indentSpace);

        return prettyString;

    }


    @Override
    public void accept(BaseVisitor<Void> visitor) {
        visitor.visitIrArgInputAssign(this);
    }

    @Override
    public LlLocation generateLlIr(LlBuilder builder, LlSymbolTable symbolTable) {

        return this.argValue.generateLlIr(builder, symbolTable);
    }

}
