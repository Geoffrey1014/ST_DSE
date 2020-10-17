package ir.VARBlockDecl;

import SymbolTable.SymTable;
import ir.Literal.IrLiteral;
import ir.VarTypeEnum;

import java.util.ArrayList;

public class IrValueArray extends IrValue {
    private final ArrayList<IrLiteral>  valueList;
    private VarTypeEnum type;

    public IrValueArray(int lineNumber, int colNumber, ArrayList<IrLiteral>  valueList) {
        super(lineNumber, colNumber);
        this.valueList = valueList;
    }

    @Override
    public String semanticCheck(SymTable symTable) {
        //  所有多数值类型一致
        String errorMessage = "";
        if (valueList != null){
            VarTypeEnum type = valueList.get(0).getExpressionType();
            setType(type);
            for (IrLiteral v : valueList){
                if (type != v.getExpressionType()){
                    errorMessage += "the type of array value are not the same " +
                            " line: " + this.getLineNumber() + " col: " + this.getColNumber() + "\n";
                }
            }
        }
        return errorMessage;

    }

    @Override
    public String prettyPrint(String indentSpace) {
        String values = "[";
        for (IrLiteral v : valueList){
            values += v.getValue().toString();
            values += ", "; // 最后会多出一个逗号
        }
        values += "]";
        return indentSpace + "|--valuse: " + values + "\n" ;
    }

    @Override
    public Object getValue() {
        return this.valueList;
    }

    @Override
    public VarTypeEnum getType() {
        return type;
    }

    public void setType(VarTypeEnum type) {
        this.type = type;
    }
}
