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

public interface BaseVisitor<T> {
    public abstract T visitIrArgExpr(IrArgExpr node);
    public abstract T visitIrArgInputAssign(IrArgInputAssign node);
    public abstract T IrArgOutputAssign(IrArgOutputAssign node);

    public abstract T visitIrCtrlFlowIf(IrCtrlFlowIf node);
    public abstract T visitIrCtrlFlowElsif(IrCtrlFlowElsif node);
    public abstract T visitIrCtrlFlowIfElse(IrCtrlFlowIfElse node);
    public abstract T visitIrCtrlFlowIfElsifElse(IrCtrlFlowIfElsifElse node);
    public abstract T visitIrCtrlFlowIfElsif(IrCtrlFlowIfElsif node);

    public abstract T visitIrCtrlFlowFor(IrCtrlFlowFor node);
    public abstract T visitIrCtrlFlowForRange(IrCtrlFlowForRange node);
    public abstract T visitIrCtrlFlowWhile(IrCtrlFlowWhile node);

    public abstract T visitIrBoolLiteral(IrBoolLiteral node);
    public abstract T visitIrFloatLiteral(IrFloatLiteral node);
    public abstract T visitIrIntLiteral(IrIntLiteral node);
    public abstract T visitIrStringLiteral(IrStringLiteral node);

    public abstract T visitIrFbStLocation(IrFbStLocation node);
    public abstract T visitIrLocationArray(IrLocationArray node);
    public abstract T visitIrLocationVar(IrLocationVar node);

    public abstract T visitIrOperBinaryEq(IrOperBinaryEq node);
    public abstract T visitIrOperBinaryArith(IrOperBinaryArith node);
    public abstract T visitIrOperUnaryNeg(IrOperUnaryNeg node);
    public abstract T visitIrOperUnaryNot(IrOperUnaryNot node);
    public abstract T visitIrOperBinaryRel(IrOperBinaryRel node);
    public abstract T visitIrOperBinaryLogic(IrOperBinaryLogic node);

    public abstract T visitIrFunctionDecl(IrFunctionDecl node);
    public abstract T visitIrFunctionBlockDecl(IrFunctionBlockDecl node);
    public abstract T visitIrProgramDecl(IrProgramDecl node);

    public abstract T visitIrTypeArray(IrTypeArray node);
    public abstract T visitIrTypeSimple(IrTypeSimple node);
    public abstract T visitIrValueArray(IrValueArray node);
    public abstract T visitIrValueSimple(IrValueSimple node);
    public abstract T visitIrVARBlockDecl(IrVARBlockDecl node);
    public abstract T visitIrVarsDecl(IrVarsDecl node);
    public abstract T visitIrVarDecl(IrVarDecl node);

    public abstract T visitIrAssignStmtEq(IrAssignStmtEq node);
    public abstract T visitIrCodeBlock(IrCodeBlock node);
    public abstract T visitIrFunctionCallExpr(IrFunctionCallExpr node);
    public abstract T visitIrFunctionCallStmt(IrFunctionCallStmt node);

    public abstract T visitIrPousDecl(IrPousDecl node);

    public abstract T visitIrIdent(IrIdent irIdent);
//
//    public abstract T visit( node);
//    public abstract T visit( node);
//    public abstract T visit( node);
//    public abstract T visit( node);
}
