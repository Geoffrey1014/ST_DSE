package ir.Location;


import SymbolTable.SymTable;
import ir.BaseVisitor;
import ir.Ir;
import ir.IrIdent;
import ir.VARBlockDecl.IrVarDecl;
import ir.VarTypeEnum;

/**
 * Created by geo on 2020/10/13.
 */
public class IrLocationVar extends IrLocation {
    public IrVarDecl irDeclObject; // IrVarDecl  这里只能引用本 POU 内 声明的变量

    public IrLocationVar(IrIdent varName) {
        super(varName);
    }

    public void setIrDecl(IrVarDecl irDeclObject) {
        this.irDeclObject = irDeclObject;
    }

    public Ir getIrDecl() {
        return this.irDeclObject;
    }

    @Override
    public VarTypeEnum getExpressionType() {
        return this.varType;
    }


    @Override
    public String semanticCheck(SymTable symTable) {
        String errorMessage = "";
        return errorMessage;

    }

    @Override
    public String prettyPrint(String indentSpace) {
        String prettyString = indentSpace + "|--locationVar\n";

        // pretty print name
        prettyString += "  " + indentSpace + "|--name: " + this.varName.getValue() + "\n";

        // pretty print the type
        prettyString +=  "  " +  "|--type: " + indentSpace + this.varType +"\n";

        return prettyString;
    }

    @Override
    public void visit(BaseVisitor<Void> visitor) {
        visitor.visitIrLocationVar(this);
    }
}
