package ir;

import ir.POUDecl.*;

import java.util.ArrayList;

public class IrPousDecl implements Where {
    /**  POUS  的 lineNumber, colNumber) 是程序最开始的那个字符
    POUS应该能包含很多POU，很多POU中只有一个是PROGRAM
     还有其他要实现的功能和语法检查吗？
     */
    private final int lineNumber;
    private final int colNumber;
    private ArrayList<IrProgramDecl> programDeclsArrayList;
    private ArrayList<IrFunctionBlockDecl> functionBlockDeclsArrayList;
    private ArrayList<IrFunctionDecl> functionDeclArrayList;


    public IrPousDecl(ArrayList<IrProgramDecl> programDeclsArrayList, ArrayList<IrFunctionBlockDecl> functionBlockDeclsArrayList,
                      ArrayList<IrFunctionDecl> functionDeclArrayList, int lineNumber, int colNumber) {
        this.lineNumber = lineNumber;
        this.colNumber = colNumber;
        this.programDeclsArrayList = programDeclsArrayList;
        this.functionBlockDeclsArrayList = functionBlockDeclsArrayList;
        this.functionDeclArrayList = functionDeclArrayList;

    }

    public ArrayList<IrFunctionDecl> getFunctionDeclArrayList() {
        return functionDeclArrayList;
    }

    public void setFunctionDeclArrayList(ArrayList<IrFunctionDecl> functionDeclArrayList) {
        this.functionDeclArrayList = functionDeclArrayList;
    }

    public ArrayList<IrFunctionBlockDecl> getFunctionBlockDeclsArrayList() {
        return functionBlockDeclsArrayList;
    }

    public void setFunctionBlockDeclsArrayList(ArrayList<IrFunctionBlockDecl> functionBlockDeclsArrayList) {
        this.functionBlockDeclsArrayList = functionBlockDeclsArrayList;
    }

    public ArrayList<IrProgramDecl> getProgramDeclsArrayList() {
        return programDeclsArrayList;
    }

    public void setProgramDeclsArrayList(ArrayList<IrProgramDecl> programDeclsArrayList) {
        this.programDeclsArrayList = programDeclsArrayList;
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
