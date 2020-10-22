package ir.Location;


import SymbolTable.SymTable;
import ir.BaseVisitor;
import ir.Ir;
import ir.IrIdent;
import ir.VARBlockDecl.IrTypeArray;
import ir.VARBlockDecl.IrTypeSimple;
import ir.VARBlockDecl.IrVarDecl;
import ir.VarTypeEnum;

/**
 * Created by geo on 2020/10/13.
 */
public class IrLocationVar extends IrLocation {
    protected Ir irDeclObject; // IrVarDecl  这里只能引用本 POU 内 声明的变量

    public IrLocationVar(IrIdent varName) {
        super(varName);
    }

    protected void setIrDecl(Ir irDeclObject) {
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

        // 1) make sure the variable has been declared already  TODO: 其实只分成global 和 local scope 两个，所以 FbStLocation 会比较麻烦
        if (symTable.checkIfSymbolExistsAtAnyScope(this.getLocationName().getValue())) {
            Ir object = symTable.getSymbol(this.getLocationName().getValue());

            // make sure that the identifier is a var, not a method or array
            if (object instanceof IrVarDecl) {
                IrVarDecl var = (IrVarDecl) object;

                if (var.getType() instanceof IrTypeSimple){
                    // IMPORTANT: set the typeEnum of the IrLocationVar
                    this.setLocationType(var.getType().getTypeEnum());

                    // TODO : IMPORTANT: set the IrDecl of the IrLocationVar
                    this.setIrDecl(object);
                }
                else if (var.getType() instanceof IrTypeArray){
                    // IMPORTANT: set the IrType of the IrLocationVar
                    this.setLocationType(var.getType().getTypeEnum());

                    // IMPORTANT: set the IrDecl of the IrLocationVar
                    this.setIrDecl(object);

                    errorMessage += "Invalid array assignment" +
                            " line: " + this.getLocationName().getLineNumber() + " col: " + this.getLocationName().getColNumber() + "\n";
                }
                else{
                    // this branch should be a POU declaration
                    errorMessage += "Invalid assignment to a POU" +
                            " line: " + this.getLocationName().getLineNumber() + " col: " + this.getLocationName().getColNumber() + "\n";
                }

            }

        }
        else {
            errorMessage += "Variable used before declared" +
                    " line: " + this.getLocationName().getLineNumber() + " col: " + this.getLocationName().getColNumber() + "\n";
        }

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
