package ir.Arg;

import SymbolTable.SymTable;
import ir.IrExpr;
import ir.Location.IrLocation;
import ir.Location.IrLocationVar;
import ir.VarTypeEnum;

public class IrArgInputAssign extends IrArgExpr {
    private final IrLocation storeLocation;
    public IrArgInputAssign(IrExpr argValue, IrLocation argName) {
        super(argValue, argName.getLineNumber(), argName.getLineNumber());
        this.storeLocation = argName;
    }


    @Override
    public String semanticCheck(SymTable symTable) {
        String errorMessage = "";

        // 1) verify that the storeLocation is semantically correct
        errorMessage += this.getStoreLocation().semanticCheck(symTable);

        if (this.getStoreLocation() instanceof IrLocationVar) {

            // 2) check to make sure the var isn't a lone array var
//            if (scopeStack.checkIfSymbolExistsAtAnyScope(this.getStoreLocation().getLocationName().getValue())) {
//                Ir object = scopeStack.getSymbol(this.getStoreLocation().getLocationName().getValue());
//                if (object instanceof IrFieldDeclArray) {
//                    errorMessage += "Can't re-assign an array to an expression" +
//                            " line: " + this.getLineNumber() + " col: " + this.getColNumber() + "\n";
//                }
//            }
        }


        // 3) verify that the expr is semantically correct
        errorMessage += this.getArgValue().semanticCheck(symTable);

        // 4) make sure that the IrExpr and IrLocation are the same IrType
        boolean bothAreInts = (this.getArgValue().getExpressionType() == VarTypeEnum.RES_INT)
                && (this.getStoreLocation().getExpressionType() == VarTypeEnum.RES_INT);
        boolean bothAreBools = (this.getArgValue().getExpressionType()  == VarTypeEnum.RES_BOOL )
                && (this.getStoreLocation().getExpressionType()  == VarTypeEnum.RES_BOOL);
        if (!bothAreBools && !bothAreInts) {
            errorMessage += "The variable to be assigned and expression must both be of type int or of type bool" +
                    " line: " + this.getLineNumber() + " col: " + this.getColNumber() + "\n";
        }

        return errorMessage;
    }

    @Override
    public String prettyPrint(String indentSpace) {
        String prettyString = indentSpace + "|--assignStmtEquals\n";

        // pretty print the lhs
        prettyString += ("  " + indentSpace + "|--lhs\n");
        prettyString += this.getStoreLocation().prettyPrint("    " + indentSpace);

        // print the rhs
        prettyString += ("  " + indentSpace + "|--rhs\n");
        prettyString += this.getArgValue().prettyPrint("    " + indentSpace);

        return prettyString;
    }

    public IrLocation getStoreLocation() {
        return this.storeLocation;
    }

}
