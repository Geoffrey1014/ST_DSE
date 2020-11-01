package ir.Location;


import ir.Ir;
import ir.IrIdent;
import ir.VARBlockDecl.IrVarDecl;
import ir.VarTypeEnum;
import visitor.BaseVisitor;

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
    public String prettyPrint(String indentSpace) {
        String prettyString = indentSpace + "|--locationVar\n";

        // pretty print name
        prettyString += "  " + indentSpace + "|--name: " + this.varName.getValue() + "\n";

        // pretty print the type
        prettyString +=  "  " +  indentSpace  + "|--type: "  + this.varType +"\n";

        return prettyString;
    }

    @Override
    public void accept(BaseVisitor<Void> visitor) {
        visitor.visitIrLocationVar(this);
    }
}
