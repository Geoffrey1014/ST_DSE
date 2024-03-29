package ir;

import helper.LlBuilder;
import helper.LlSymbolTable;
import ll.location.LlLocation;
import visitor.BaseVisitor;

import java.util.ArrayList;

public class IrIdent extends Ir {

    private final String name;

    public IrIdent(String name, int lineNumber, int colNumber){
        super(lineNumber, colNumber);
        this.name = name;
    }

    public IrIdent(ArrayList<String> arrayListName, int lineNumber, int colNumber){


        super(lineNumber, colNumber);
        String name = "";
        for (String s : arrayListName){
            name += ".";
            name += s;
        }
        this.name = name;
    }

    public String getValue() {
        return name;
    }


    @Override
    public String prettyPrint(String indentSpace) {
        return "";
    }

    @Override
    public void accept(BaseVisitor<Void> visitor) {
        visitor.visitIrIdent(this);
    }

    @Override
    public LlLocation generateLlIr(LlBuilder builder, LlSymbolTable symbolTable) {
        return null;
    }
}