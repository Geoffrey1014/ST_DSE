package ir;

import ir.POUDecl.IrPouDecl;

public class IrPousDecl extends IrPouDecl {
    /**  POUS  的 lineNumber, colNumber) 分别是什么
    POUS应该能包含很多POU，很多POU中只有一个是PROGRAM
     */
    public IrPousDecl(int lineNumber, int colNumber) {
        super(lineNumber, colNumber);
    }

}
