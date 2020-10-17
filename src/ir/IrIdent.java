package ir;

import java.util.ArrayList;

public class IrIdent implements Where {
    private final int lineNumber;
    private final int colNumber;

    private final String name;

    public IrIdent(String name, int lineNumber, int colNumber){
        this.lineNumber = lineNumber;
        this.colNumber = colNumber;
        this.name = name;
    }

    public IrIdent(ArrayList<String> arrayListName, int lineNumber, int colNumber){
        this.lineNumber = lineNumber;
        this.colNumber = colNumber;
        String name = "";
        for (String s : arrayListName){
            name += s;
        }
        this.name = name;
    }

    public String getName() {
        return name;
    }
    @Override
    public int getLineNumber() {
        return lineNumber;
    }

    @Override
    public int getColNumber() {
        return colNumber;
    }
}