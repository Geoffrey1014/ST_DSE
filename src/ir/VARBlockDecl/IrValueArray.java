package ir.VARBlockDecl;

import ir.Literal.IrLiteral;
import ir.VarTypeEnum;
import visitor.BaseVisitor;

import java.util.ArrayList;

public class IrValueArray extends IrValue {
    public final ArrayList<IrLiteral>  valueList;
    private VarTypeEnum type;

    public IrValueArray(int lineNumber, int colNumber, ArrayList<IrLiteral>  valueList) {
        super(lineNumber, colNumber);
        this.valueList = valueList;
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
    public ArrayList<IrLiteral> getValue() {
        return this.valueList;
    }

    @Override
    public VarTypeEnum getType() {
        return type;
    }

    public void setType(VarTypeEnum type) {
        this.type = type;
    }

    @Override
    public void accept(BaseVisitor<Void> visitor) {
        visitor.visitIrValueArray(this);
    }
}
