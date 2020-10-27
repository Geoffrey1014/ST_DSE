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
    SymTable symTable = new SymTable();


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
     * when meet the array type, visit this child node
     * @param node
     * @return
     */
    @Override
    public Void visitIrVARBlockDecl(IrVARBlockDecl node) {
        for (IrVarDecl varDecl : node.getVarList()){
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
        symTable.saveScope(node, symTable.currentScope);

        if (node.getVarBlockVAR() != null){
            node.getVarBlockVAR().visit(this);
        }

        if (node.getVarBlockVAR_INPUT() != null){
            node.getVarBlockVAR_INPUT().visit(this);
        }

        if (node.getVarBlockVAR_OUTPUT() != null){
            node.getVarBlockVAR_OUTPUT().visit(this);
        }

        if (node.getVarBlockVAR_INPUT_OUTPUT() != null){
            node.getVarBlockVAR_INPUT_OUTPUT().visit(this);
        }

        if (node.getVarBlockVAR_TEMP() != null){
            node.getVarBlockVAR_TEMP().visit(this);
        }

        MyPrint.levelTwo.print("current scope : " + node.getName() );
        MyPrint.levelTwo.print(symTable.currentScope);
        symTable.currentScope = symTable.currentScope.getEnclosingScope(); // pop scope

        return null;
    }

    @Override
    public Void visitIrFunctionBlockDecl(IrFunctionBlockDecl node) {
        symTable.saveScope(node, symTable.currentScope);

        if (node.getVarBlockVAR() != null){
            node.getVarBlockVAR().visit(this);
        }

        if (node.getVarBlockVAR_INPUT() != null){
            node.getVarBlockVAR_INPUT().visit(this);
        }

        if (node.getVarBlockVAR_OUTPUT() != null){
            node.getVarBlockVAR_OUTPUT().visit(this);
        }

        if (node.getVarBlockVAR_INPUT_OUTPUT() != null){
            node.getVarBlockVAR_INPUT_OUTPUT().visit(this);
        }

        if (node.getVarBlockVAR_TEMP() != null){
            node.getVarBlockVAR_TEMP().visit(this);
        }
        MyPrint.levelTwo.print("current scope : " + node.getName() );
        MyPrint.levelTwo.print(symTable.currentScope);
        symTable.currentScope = symTable.currentScope.getEnclosingScope(); // pop scope

        return null;
    }

    /**
     * 进入 program， 生成一个local scope
     * 再进入 varDecalration 和 vodeBlock
     */
    @Override
    public Void visitIrProgramDecl(IrProgramDecl node) {
        symTable.currentScope = new LocalScope(symTable.currentScope);
        symTable.saveScope(node, symTable.currentScope);

        if (node.getVarBlockVAR() != null){
            node.getVarBlockVAR().visit(this);
        }

        if (node.getVarBlockVAR_INPUT() != null){
            node.getVarBlockVAR_INPUT().visit(this);
        }

        if (node.getVarBlockVAR_OUTPUT() != null){
            node.getVarBlockVAR_OUTPUT().visit(this);
        }

        if (node.getVarBlockVAR_INPUT_OUTPUT() != null){
            node.getVarBlockVAR_INPUT_OUTPUT().visit(this);
        }

        if (node.getVarBlockVAR_TEMP() != null){
            node.getVarBlockVAR_TEMP().visit(this);
        }
        MyPrint.levelTwo.print("current scope : " + node.getName() );
        MyPrint.levelTwo.print(symTable.currentScope);
        symTable.currentScope = symTable.currentScope.getEnclosingScope(); // pop scope

        return null;
    }


    /**
     * POUS， 产生global scope，
     * 然后进入下面不同对子节点寻找符号对定义
     */
    @Override
    public Void visitIrPousDecl(IrPousDecl node) {
        /*
        进入该节点
         */
        symTable.globals = new GlobalScope(null);
        symTable.currentScope =  symTable.globals;


        for (IrFunctionDecl functionDecl : node.getFunctionDeclArrayList()){
            symTable.currentScope.define(functionDecl.getName(), functionDecl);
            functionDecl.visit(this);
        }
        for (IrFunctionBlockDecl functionBlockDecl : node.getFunctionBlockDeclsArrayList()){
            symTable.currentScope.define(functionBlockDecl.getName(), functionBlockDecl);
            functionBlockDecl.visit(this);
        }
        for (IrProgramDecl programDecl : node.getProgramDeclsArrayList()){
            symTable.currentScope.define(programDecl.getName(), programDecl);
            programDecl.visit(this);
        }

        // 出 该即诶单
        MyPrint.levelTwo.print("global scope : " );
        MyPrint.levelTwo.print(symTable.globals);

        return null;

    }

    @Override
    public Void visitIrIdent(IrIdent irIdent) {
        return null;
    }
}
