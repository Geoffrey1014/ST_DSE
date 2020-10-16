package ir;

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
            name += s;
        }
        this.name = name;
    }

    public String getName() {
        return name;
    }
}