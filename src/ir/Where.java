package ir;

public interface Where {
    // keep track of the line & column number for
    // for error reporting purposes

    public int getLineNumber();
    public int getColNumber();

}