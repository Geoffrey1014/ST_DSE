package ir;

import helper.LlBuilder;
import helper.LlSymbolTable;
import ir.POUDecl.IrFunctionBlockDecl;
import ir.POUDecl.IrFunctionDecl;
import ir.POUDecl.IrPouDecl;
import ir.POUDecl.IrProgramDecl;
import ll.location.LlLocation;
import visitor.BaseVisitor;

import java.util.ArrayList;

public class IrPousDecl extends Ir {
    /**  POUS  的 lineNumber, colNumber) 是程序最开始的那个字符
    POUS应该能包含很多POU，很多POU中只有一个是PROGRAM
     还有其他要实现的功能和语法检查吗？
     */

    private ArrayList<IrProgramDecl> programDeclsArrayList;
    private ArrayList<IrFunctionBlockDecl> functionBlockDeclsArrayList;
    private ArrayList<IrFunctionDecl> functionDeclArrayList;
    private ArrayList<LlBuilder> llBuilderArrayList;


    public IrPousDecl(ArrayList<IrProgramDecl> programDeclsArrayList, ArrayList<IrFunctionBlockDecl> functionBlockDeclsArrayList,
                      ArrayList<IrFunctionDecl> functionDeclArrayList, int lineNumber, int colNumber) {
        super(lineNumber, colNumber);

        this.programDeclsArrayList = programDeclsArrayList;
        this.functionBlockDeclsArrayList = functionBlockDeclsArrayList;
        this.functionDeclArrayList = functionDeclArrayList;

    }

    public ArrayList<IrFunctionDecl> getFunctionDeclArrayList() {
        return functionDeclArrayList;
    }


    public ArrayList<IrFunctionBlockDecl> getFunctionBlockDeclsArrayList() {
        return functionBlockDeclsArrayList;
    }


    public ArrayList<IrProgramDecl> getProgramDeclsArrayList() {
        return programDeclsArrayList;
    }



    @Override
    public String prettyPrint(String indentSpace) {
        StringBuilder prettyString = new StringBuilder(indentSpace + "|--POUS\n");

        if (this.programDeclsArrayList.size() != 0){
            for (IrProgramDecl pou : this.programDeclsArrayList){
                prettyString.append(pou.prettyPrint("  " + indentSpace));
            }
        }


        if (this.functionBlockDeclsArrayList.size() != 0){
            for (IrFunctionBlockDecl pou : this.functionBlockDeclsArrayList){
                prettyString.append(pou.prettyPrint("  " + indentSpace));
            }
        }

        if (this.functionDeclArrayList.size() != 0){
            for (IrFunctionDecl pou : this.functionDeclArrayList){
                prettyString.append(pou.prettyPrint("  " + indentSpace));
            }
        }

        return prettyString.toString();
    }

    @Override
    public void accept(BaseVisitor<Void> visitor) {
        visitor.visitIrPousDecl(this);
    }

    @Override
    public LlLocation generateLlIr(LlBuilder builder, LlSymbolTable symbolTable) {
        return null;
    }

    public ArrayList<LlBuilder> getBuilderList() {
        ArrayList<LlBuilder> buildersList = new ArrayList<>();

        for (IrPouDecl program: this.programDeclsArrayList) {
            LlBuilder llBuilder = new LlBuilder();
            LlSymbolTable llSymbolTable = new LlSymbolTable();
            program.generateLlIr(llBuilder, llSymbolTable);
            buildersList.add(llBuilder);
        }

        for (IrPouDecl fbDecl: this.functionBlockDeclsArrayList) {
            LlBuilder llBuilder = new LlBuilder();
            LlSymbolTable llSymbolTable = new LlSymbolTable();
            fbDecl.generateLlIr(llBuilder, llSymbolTable);

            buildersList.add(llBuilder);
        }
        for (IrPouDecl functionDecl: this.functionDeclArrayList) {
            LlBuilder llBuilder = new LlBuilder();
            LlSymbolTable llSymbolTable = new LlSymbolTable();
            functionDecl.generateLlIr(llBuilder, llSymbolTable);
            buildersList.add(llBuilder);
        }

        return buildersList;
    }
}
