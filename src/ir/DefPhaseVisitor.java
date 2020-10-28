package ir;

import SymbolTable.GlobalScope;
import SymbolTable.LocalScope;
import SymbolTable.SymTable;
import ir.Arg.IrArgExpr;
import ir.Arg.IrArgInputAssign;
import ir.Arg.IrArgOutputAssign;
import ir.CtrlFlow.*;
import ir.Literal.IrBoolLiteral;
import ir.Literal.IrFloatLiteral;
import ir.Literal.IrIntLiteral;
import ir.Literal.IrStringLiteral;
import ir.Location.IrFbStLocation;
import ir.Location.IrLocationArray;
import ir.Location.IrLocationVar;
import ir.Operation.*;
import ir.POUDecl.IrFunctionBlockDecl;
import ir.POUDecl.IrFunctionDecl;
import ir.POUDecl.IrProgramDecl;
import ir.VARBlockDecl.*;
import tools.MyPrint;

public class DefPhaseVisitor implements BaseVisitor<Void> {
    public SymTable symTable = new SymTable();
    public StringBuilder errorMessage = new StringBuilder();

    @Override
    public Void visitIrArgExpr(IrArgExpr node) {
        return null;
    }

    @Override
    public Void visitIrArgInputAssign(IrArgInputAssign node) {
        return null;
    }

    @Override
    public Void IrArgOutputAssign(IrArgOutputAssign node) {
        return null;
    }

    @Override
    public Void visitIrCtrlFlowIf(IrCtrlFlowIf node) {
        return null;
    }

    @Override
    public Void visitIrCtrlFlowElsif(IrCtrlFlowElsif node) {
        return null;
    }

    @Override
    public Void visitIrCtrlFlowIfElse(IrCtrlFlowIfElse node) {
        return null;
    }

    @Override
    public Void visitIrCtrlFlowIfElsifElse(IrCtrlFlowIfElsifElse node) {
        return null;
    }

    @Override
    public Void visitIrCtrlFlowIfElsif(IrCtrlFlowIfElsif node) {
        return null;
    }

    @Override
    public Void visitIrCtrlFlowFor(IrCtrlFlowFor node) {
        return null;
    }

    @Override
    public Void visitIrCtrlFlowForRange(IrCtrlFlowForRange node) {
        return null;
    }

    @Override
    public Void visitIrCtrlFlowWhile(IrCtrlFlowWhile node) {
        return null;
    }

    @Override
    public Void visitIrBoolLiteral(IrBoolLiteral node) {
        return null;
    }

    @Override
    public Void visitIrFloatLiteral(IrFloatLiteral node) {
        return null;
    }

    @Override
    public Void visitIrIntLiteral(IrIntLiteral node) {
        return null;
    }

    @Override
    public Void visitIrStringLiteral(IrStringLiteral node) {
        return null;
    }

    @Override
    public Void visitIrFbStLocation(IrFbStLocation node) {
        return null;
    }

    @Override
    public Void visitIrLocationArray(IrLocationArray node) {
        return null;
    }

    @Override
    public Void visitIrLocationVar(IrLocationVar node) {
        return null;
    }

    @Override
    public Void visitIrOperBinaryEq(IrOperBinaryEq node) {
        return null;
    }

    @Override
    public Void visitIrOperBinaryArith(IrOperBinaryArith node) {
        return null;
    }

    @Override
    public Void visitIrOperUnaryNeg(IrOperUnaryNeg node) {
        return null;
    }

    @Override
    public Void visitIrOperUnaryNot(IrOperUnaryNot node) {
        return null;
    }

    @Override
    public Void visitIrOperBinaryRel(IrOperBinaryRel node) {
        return null;
    }

    @Override
    public Void visitIrOperBinaryLogic(IrOperBinaryLogic node) {
        return null;
    }

    @Override
    public Void visitIrTypeArray(IrTypeArray node) {
        return null;
    }

    @Override
    public Void visitIrTypeSimple(IrTypeSimple node) {
        return null;
    }

    @Override
    public Void visitIrValueArray(IrValueArray node) {
        return null;
    }

    @Override
    public Void visitIrValueSimple(IrValueSimple node) {
        return null;
    }

    /**
     * if theere is a var has been declared twice, give out the error message
     * when meet the array type, visit it's child node
     * @param node
     * @return
     */
    @Override
    public Void visitIrVARBlockDecl(IrVARBlockDecl node) {
        for (IrVarDecl varDecl : node.getVarList()){
            if (   symTable.checkIfSymbolExistsAtCurrentScope(varDecl.getName()) ){
                errorMessage.append("the symbol has already been declared: ").append(varDecl.getName())
                        .append(" line: ").append(varDecl.getLineNumber()).append(" col: ").append(varDecl.getColNumber());
            }
            symTable.currentScope.define(varDecl.getName(), varDecl);

        }
        return null;
    }

    @Override
    public Void visitIrVarsDecl(IrVarsDecl node) {
        return null;
    }

    @Override
    public Void visitIrVarDecl(IrVarDecl node) {
        return null;
    }


    @Override
    public Void visitIrAssignStmtEq(IrAssignStmtEq node) {
        return null;
    }

    @Override
    public Void visitIrCodeBlock(IrCodeBlock node) {
        //  codeblock 中只有对变量对引用，不存在定义一个符号
        return null;
    }

    @Override
    public Void visitIrFunctionCallExpr(IrFunctionCallExpr node) {
        return null;
    }

    @Override
    public Void visitIrFunctionCallStmt(IrFunctionCallStmt node) {
        return null;
    }

    @Override
    public Void visitIrFunctionDecl(IrFunctionDecl node) {
        symTable.currentScope = new LocalScope(symTable.currentScope);
        symTable.saveScope(node, symTable.currentScope);
        symTable.currentScope.define(node.returnVar.getName(), node.returnVar);

        if (node.getVarBlockVAR() != null){
            node.getVarBlockVAR().accept(this);
        }

        if (node.getVarBlockVAR_INPUT() != null){
            node.getVarBlockVAR_INPUT().accept(this);
        }

        if (node.getVarBlockVAR_OUTPUT() != null){
            node.getVarBlockVAR_OUTPUT().accept(this);
        }

        if (node.getVarBlockVAR_INPUT_OUTPUT() != null){
            node.getVarBlockVAR_INPUT_OUTPUT().accept(this);
        }

        if (node.getVarBlockVAR_TEMP() != null){
            node.getVarBlockVAR_TEMP().accept(this);
        }

        MyPrint.levelOne.print("current scope : " + node.getName() );
        MyPrint.levelOne.print(symTable.currentScope);
        symTable.currentScope = symTable.currentScope.getEnclosingScope(); // pop scope

        return null;
    }

    @Override
    public Void visitIrFunctionBlockDecl(IrFunctionBlockDecl node) {
        symTable.currentScope = new LocalScope(symTable.currentScope);
        symTable.saveScope(node, symTable.currentScope);

        if (node.getVarBlockVAR() != null){
            node.getVarBlockVAR().accept(this);
        }

        if (node.getVarBlockVAR_INPUT() != null){
            node.getVarBlockVAR_INPUT().accept(this);
        }

        if (node.getVarBlockVAR_OUTPUT() != null){
            node.getVarBlockVAR_OUTPUT().accept(this);
        }

        if (node.getVarBlockVAR_INPUT_OUTPUT() != null){
            node.getVarBlockVAR_INPUT_OUTPUT().accept(this);
        }

        if (node.getVarBlockVAR_TEMP() != null){
            node.getVarBlockVAR_TEMP().accept(this);
        }
        MyPrint.levelOne.print("current scope : " + node.getName() );
        MyPrint.levelOne.print(symTable.currentScope);
        symTable.currentScope = symTable.currentScope.getEnclosingScope(); // pop scope

        return null;
    }

    /**
     * 进入 PROGRAM， 生成一个local scope
     * 再进入 varDeclaration 和 codeBlock
     */
    @Override
    public Void visitIrProgramDecl(IrProgramDecl node) {
        symTable.currentScope = new LocalScope(symTable.currentScope);
        symTable.saveScope(node, symTable.currentScope);

        if (node.getVarBlockVAR() != null){
            node.getVarBlockVAR().accept(this);
        }

        if (node.getVarBlockVAR_INPUT() != null){
            node.getVarBlockVAR_INPUT().accept(this);
        }

        if (node.getVarBlockVAR_OUTPUT() != null){
            node.getVarBlockVAR_OUTPUT().accept(this);
        }

        if (node.getVarBlockVAR_INPUT_OUTPUT() != null){
            node.getVarBlockVAR_INPUT_OUTPUT().accept(this);
        }

        if (node.getVarBlockVAR_TEMP() != null){
            node.getVarBlockVAR_TEMP().accept(this);
        }
        MyPrint.levelOne.print("current scope : " + node.getName() );
        MyPrint.levelOne.print(symTable.currentScope);
        symTable.currentScope = symTable.currentScope.getEnclosingScope(); // pop scope

        return null;
    }


    /**
     * POUS， 产生global scope，
     * 应该在此处检查：
     * 1。 POU的名字只被定义一次
     * 2。PROGRAM 只能有一个
     * 然后进入下面不同对子节点寻找符号对定义
     */
    @Override
    public Void visitIrPousDecl(IrPousDecl node) {
        /*
        进入该节点
         */
        symTable.globals = new GlobalScope(null);
        symTable.currentScope =  symTable.globals;

        // check it has not been declare, then visit it's child nodes
        for (IrFunctionDecl functionDecl : node.getFunctionDeclArrayList()){
            if ( symTable.currentScope.resolve(functionDecl.getName()) != null){
                errorMessage.append("the symbol has already been declared: ").append(functionDecl.getName())
                        .append(" line: ").append(functionDecl.getLineNumber()).append(" col: ").append(functionDecl.getColNumber());
            }
            else {
                symTable.currentScope.define(functionDecl.getName(), functionDecl);
                functionDecl.accept(this);
            }
        }

        // check it has not been declare, then visit it's child nodes
        for (IrFunctionBlockDecl functionBlockDecl : node.getFunctionBlockDeclsArrayList()){
            if ( symTable.currentScope.resolve(functionBlockDecl.getName()) != null){
                errorMessage.append("the symbol has already been declared: ").append(functionBlockDecl.getName())
                        .append(" line: ").append(functionBlockDecl.getLineNumber()).append(" col: ").append(functionBlockDecl.getColNumber());
            }
            else {
                symTable.currentScope.define(functionBlockDecl.getName(), functionBlockDecl);
                functionBlockDecl.accept(this);
            }
        }

        // check there is noly one PROGRAM
        // check PROGRAM's name has not been declared
        if (node.getProgramDeclsArrayList().size() > 1){
            errorMessage.append("too many PROGRAM POU ")
                    .append(" line: ").append(node.getLineNumber()).append(" col: ").append(node.getColNumber());
        }
        else if (node.getProgramDeclsArrayList().size() == 0){
            errorMessage.append("there is no PROGRAM POU ")
                    .append(" line: ").append(node.getLineNumber()).append(" col: ").append(node.getColNumber());
        }
        else {
            IrProgramDecl programDecl = node.getProgramDeclsArrayList().get(0);
            if ( symTable.currentScope.resolve(programDecl.getName()) == null) {
                symTable.currentScope.define(programDecl.getName(), programDecl);
                programDecl.accept(this);
            }
            else {
                errorMessage.append("the symbol has already been declared: ").append(programDecl.getName())
                        .append(" line: ").append(programDecl.getLineNumber()).append(" col: ").append(programDecl.getColNumber());

            }

        }

        // 出 该节点
        MyPrint.levelOne.print("current scope : " + symTable.currentScope.getScopeName() ); // 应该输出locals

        MyPrint.levelOne.print("global scope : " +symTable.currentScope.getScopeName()); // 应该输出 globals
        MyPrint.levelOne.print(symTable.globals);

        return null;

    }

    @Override
    public Void visitIrIdent(IrIdent irIdent) {
        return null;
    }
}
