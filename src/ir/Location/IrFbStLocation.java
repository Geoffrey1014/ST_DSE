package ir.Location;

import SymbolTable.SymTable;
import ir.*;
import ir.VARBlockDecl.IrVarDecl;

public class IrFbStLocation extends IrExpr {
    // TODO ；  这里比较复杂，因为引用的是其他POU内声明的变量，并且一定是 VAR_OUTPUT or VAR_INPUT_OUTPUT 类型

    public Ir irDeclPou; // IrVarDecl
    public Ir irDeclObject; // IrVarDecl


    public IrIdent varNameFirst;
    public IrIdent varNameLast;
    public VarTypeEnum varType;

    public IrFbStLocation(IrIdent varName1, IrIdent varName2) {
        super(varName1.getLineNumber(), varName2.getColNumber());
        this.varNameFirst = varName1;
        this.varNameLast = varName2;
    }

    public IrIdent getFirstLocationName() {
        return this.varNameFirst;
    }
    public IrIdent getLastLocationName() {
        return this.varNameLast;
    }

    public VarTypeEnum getLocationType() {
        return this.varType;
    }

    public void setLocationType(VarTypeEnum type) {
        this.varType = type;
    }
    @Override
    public VarTypeEnum getExpressionType() {
        return this.varType;
    }

    @Override
    public String semanticCheck(SymTable symTable) {
        String errorMessage = "";
        // 1) make sure the variable has been declared already  TODO: 其实只分成global 和 local scope 两个，所以 FbStLocation 会比较麻烦
        if (symTable.checkIfSymbolExistsAtAnyScope(this.getFirstLocationName().getValue())) {
            // 3) make sure that var is an array (and not a method or non-array)
            Ir object = symTable.getSymbol(this.getFirstLocationName().getValue());
        }
        return null;
    }

    @Override
    public String prettyPrint(String indentSpace) {

        return null;
    }

    @Override
    public void accept(BaseVisitor<Void> visitor) {
        visitor.visitIrFbStLocation(this);
    }

    public void setIrDecl(Ir pou, IrVarDecl var) {
        this.irDeclPou = pou;
        this.irDeclObject = var;
    }
}
