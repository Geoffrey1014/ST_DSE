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
    T visitIrIdent(IrIdent irIdent);
    T visitIrBoolLiteral(IrBoolLiteral node);
    T visitIrFloatLiteral(IrFloatLiteral node);
    T visitIrIntLiteral(IrIntLiteral node);
    T visitIrStringLiteral(IrStringLiteral node);

    T visitIrArgExpr(IrArgExpr node);
    T visitIrArgInputAssign(IrArgInputAssign node);
    T IrArgOutputAssign(IrArgOutputAssign node);

    T visitIrFbStLocation(IrFbStLocation node);
    T visitIrLocationArray(IrLocationArray node);
    T visitIrLocationVar(IrLocationVar node);

    T visitIrOperBinaryEq(IrOperBinaryEq node);
    T visitIrOperBinaryArith(IrOperBinaryArith node);
    T visitIrOperUnaryNeg(IrOperUnaryNeg node);
    T visitIrOperUnaryNot(IrOperUnaryNot node);
    T visitIrOperBinaryRel(IrOperBinaryRel node);
    T visitIrOperBinaryLogic(IrOperBinaryLogic node);


    T visitIrFunctionCallExpr(IrFunctionCallExpr node);

    T visitIrFunctionCallStmt(IrFunctionCallStmt node);
    T visitIrAssignStmtEq(IrAssignStmtEq node);

    T visitIrCtrlFlowIf(IrCtrlFlowIf node);
    T visitIrCtrlFlowElsif(IrCtrlFlowElsif node);
    T visitIrCtrlFlowIfElse(IrCtrlFlowIfElse node);
    T visitIrCtrlFlowIfElsifElse(IrCtrlFlowIfElsifElse node);
    T visitIrCtrlFlowIfElsif(IrCtrlFlowIfElsif node);

    T visitIrCtrlFlowFor(IrCtrlFlowFor node);
    T visitIrCtrlFlowForRange(IrCtrlFlowForRange node);
    T visitIrCtrlFlowWhile(IrCtrlFlowWhile node);

    T visitIrStmtExit(IrStmtExit node);
    T visitIrStmtReturn(IrStmtReturn node);

    T visitIrCodeBlock(IrCodeBlock node);

    T visitIrTypeArray(IrTypeArray node);
    T visitIrTypeSimple(IrTypeSimple node);
    T visitIrValueArray(IrValueArray node);
    T visitIrValueSimple(IrValueSimple node);
    T visitIrVARBlockDecl(IrVARBlockDecl node);
    T visitIrVarsDecl(IrVarsDecl node);
    T visitIrVarDecl(IrVarDecl node);

    T visitIrFunctionDecl(IrFunctionDecl node);
    T visitIrFunctionBlockDecl(IrFunctionBlockDecl node);
    T visitIrProgramDecl(IrProgramDecl node);
    T visitIrPousDecl(IrPousDecl node);

//    public abstract T visit( node);
//    public abstract T visit( node);
//    public abstract T visit( node);
//    public abstract T visit( node);
}
