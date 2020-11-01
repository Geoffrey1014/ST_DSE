package visitor;

import ir.Arg.IrArgExpr;
import ir.Arg.IrArgInputAssign;
import ir.Arg.IrArgOutputAssign;
import ir.CtrlFlow.*;
import ir.*;
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

public class LLIRGenerator implements BaseVisitor {
    @Override
    public Object visitIrIdent(IrIdent irIdent) {

        return null;
    }

    @Override
    public Object visitIrBoolLiteral(IrBoolLiteral node) {
        return null;
    }

    @Override
    public Object visitIrFloatLiteral(IrFloatLiteral node) {
        return null;
    }

    @Override
    public Object visitIrIntLiteral(IrIntLiteral node) {
        return null;
    }

    @Override
    public Object visitIrStringLiteral(IrStringLiteral node) {
        return null;
    }

    @Override
    public Object visitIrArgExpr(IrArgExpr node) {
        return null;
    }

    @Override
    public Object visitIrArgInputAssign(IrArgInputAssign node) {
        return null;
    }

    @Override
    public Object IrArgOutputAssign(IrArgOutputAssign node) {
        return null;
    }

    @Override
    public Object visitIrFbStLocation(IrFbStLocation node) {
        return null;
    }

    @Override
    public Object visitIrLocationArray(IrLocationArray node) {
        return null;
    }

    @Override
    public Object visitIrLocationVar(IrLocationVar node) {
        return null;
    }

    @Override
    public Object visitIrOperBinaryEq(IrOperBinaryEq node) {
        return null;
    }

    @Override
    public Object visitIrOperBinaryArith(IrOperBinaryArith node) {
        return null;
    }

    @Override
    public Object visitIrOperUnaryNeg(IrOperUnaryNeg node) {
        return null;
    }

    @Override
    public Object visitIrOperUnaryNot(IrOperUnaryNot node) {
        return null;
    }

    @Override
    public Object visitIrOperBinaryRel(IrOperBinaryRel node) {
        return null;
    }

    @Override
    public Object visitIrOperBinaryLogic(IrOperBinaryLogic node) {
        return null;
    }

    @Override
    public Object visitIrFunctionCallExpr(IrFunctionCallExpr node) {
        return null;
    }

    @Override
    public Object visitIrFunctionCallStmt(IrFunctionCallStmt node) {
        return null;
    }

    @Override
    public Object visitIrAssignStmtEq(IrAssignStmtEq node) {
        return null;
    }

    @Override
    public Object visitIrCtrlFlowIf(IrCtrlFlowIf node) {
        return null;
    }

    @Override
    public Object visitIrCtrlFlowElsif(IrCtrlFlowElsif node) {
        return null;
    }

    @Override
    public Object visitIrCtrlFlowIfElse(IrCtrlFlowIfElse node) {
        return null;
    }

    @Override
    public Object visitIrCtrlFlowIfElsifElse(IrCtrlFlowIfElsifElse node) {
        return null;
    }

    @Override
    public Object visitIrCtrlFlowIfElsif(IrCtrlFlowIfElsif node) {
        return null;
    }

    @Override
    public Object visitIrCtrlFlowFor(IrCtrlFlowFor node) {
        return null;
    }

    @Override
    public Object visitIrCtrlFlowForRange(IrCtrlFlowForRange node) {
        return null;
    }

    @Override
    public Object visitIrCtrlFlowWhile(IrCtrlFlowWhile node) {
        return null;
    }

    @Override
    public Object visitIrStmtExit(IrStmtExit node) {
        return null;
    }

    @Override
    public Object visitIrStmtReturn(IrStmtReturn node) {
        return null;
    }

    @Override
    public Object visitIrCodeBlock(IrCodeBlock node) {
        return null;
    }

    @Override
    public Object visitIrTypeArray(IrTypeArray node) {
        return null;
    }

    @Override
    public Object visitIrTypeSimple(IrTypeSimple node) {
        return null;
    }

    @Override
    public Object visitIrValueArray(IrValueArray node) {
        return null;
    }

    @Override
    public Object visitIrValueSimple(IrValueSimple node) {
        return null;
    }

    @Override
    public Object visitIrVARBlockDecl(IrVARBlockDecl node) {
        return null;
    }

    @Override
    public Object visitIrVarsDecl(IrVarsDecl node) {
        return null;
    }

    @Override
    public Object visitIrVarDecl(IrVarDecl node) {
        return null;
    }

    @Override
    public Object visitIrFunctionDecl(IrFunctionDecl node) {
        return null;
    }

    @Override
    public Object visitIrFunctionBlockDecl(IrFunctionBlockDecl node) {
        return null;
    }

    @Override
    public Object visitIrProgramDecl(IrProgramDecl node) {
        return null;
    }

    @Override
    public Object visitIrPousDecl(IrPousDecl node) {
        return null;
    }
}
