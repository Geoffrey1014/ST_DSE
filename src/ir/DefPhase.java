package ir;

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

public class DefPhase implements BaseVisitor<Void> {
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
    public Void visitIrFunctionDecl(IrFunctionDecl node) {
        return null;
    }

    @Override
    public Void visitIrFunctionBlockDecl(IrFunctionBlockDecl node) {
        return null;
    }

    @Override
    public Void visitIrProgramDecl(IrProgramDecl node) {
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

    @Override
    public Void visitIrVARBlockDecl(IrVARBlockDecl node) {
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
        return null;
    }

    @Override
    public Void visitIrFunctionCallExpr(IrFunctionCallExpr node) {
        return null;
    }

    @Override
    public Void visitIrPousDecl(IrPousDecl node) {
        return null;
    }
}
