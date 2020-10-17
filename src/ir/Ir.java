package ir;

public interface Ir {
    // keep track of the line & column number for
    // for error reporting purposes

    public int getLineNumber();
    public int getColNumber();

}