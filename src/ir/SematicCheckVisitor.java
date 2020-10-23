package ir;


import SymbolTable.Scope;
import SymbolTable.SymTable;
import ir.Arg.IrArgExpr;
import ir.Arg.IrArgInputAssign;
import ir.Arg.IrArgOutputAssign;
import ir.CtrlFlow.*;
import ir.Literal.*;
import ir.Location.IrFbStLocation;
import ir.Location.IrLocationArray;
import ir.Location.IrLocationVar;
import ir.Operation.*;
import ir.POUDecl.IrFunctionBlockDecl;
import ir.POUDecl.IrFunctionDecl;
import ir.POUDecl.IrProgramDecl;
import ir.VARBlockDecl.*;
import tools.MyPrint;

import java.util.ArrayList;

public class SematicCheckVisitor implements BaseVisitor<Void> {
    SymTable symTable = null;
    StringBuilder errorMessage = new StringBuilder();

    public  SematicCheckVisitor(SymTable symTable){
        this.symTable = symTable;
    }

    /**
     * arguments
     */
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


    /**
     * control flow
     */
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


    /**
     *Literal
     */
    @Override
    public Void visitIrBoolLiteral(IrBoolLiteral node) {
//        errorMessage += "";
        return null;
    }

    @Override
    public Void visitIrFloatLiteral(IrFloatLiteral node) {
//        errorMessage += "";
        return null;
    }

    @Override
    public Void visitIrIntLiteral(IrIntLiteral node) {
//        errorMessage += "";
        return null;
    }

    @Override
    public Void visitIrStringLiteral(IrStringLiteral node) {
//        errorMessage += "";
        return null;
    }

    /**
     * Location
     */
    @Override
    public Void visitIrFbStLocation(IrFbStLocation node) {
        // 1) make sure the FirstLocationName variable has been declared already  TODO:  FbStLocation 会比较麻烦
        if (symTable.checkIfSymbolExistsAtAnyScope(node.getFirstLocationName().getValue())) {
            Ir pou = symTable.getSymbol(node.getFirstLocationName().getValue());

            // 2）  make sure the LastLocationName variable has been declared already
            MyPrint.levelTwo.print(pou.scope.getScopeName()); // 应该打印出 locals
            Scope local = symTable.treeProperty.get(pou);
            if ( local.resolve(node.getLastLocationName().getValue()) != null){
                IrVarDecl var = (IrVarDecl) local.resolve(node.getLastLocationName().getValue());

                // 这里假定 只能作为右值
                if ((var.accessType != VarAccessTypeEnum.VAR_OUTPUT)  && (var.accessType != VarAccessTypeEnum.VAR_INPUT_OUTPUT) ){
                    errorMessage.append("VarAccessTypeEnum of lastLocationName is not right" + " line: ")
                            .append(node.getFirstLocationName().getLineNumber()).append(" col: ")
                            .append(node.getFirstLocationName().getColNumber()).append("\n");
                }

                // IMPORTANT: set the TypeEnum of the IrLocation
                node.setLocationType(var.getType().getTypeEnum());

                // IMPORTANT: set the IrDecl of the IrLocation  TODO 要不要把 POU 的 IrDecl 也加上？
                node.setIrDecl(pou ,var);

            }
            else {
                errorMessage.append("Invalid lastLocationName" + " line: ")
                        .append(node.getFirstLocationName().getLineNumber())
                        .append(" col: ").append(node.getFirstLocationName().getColNumber()).append("\n");
            }
        }
        else {
            errorMessage.append("Invalid firstLocationName" + " line: ")
                    .append(node.getFirstLocationName().getLineNumber())
                    .append(" col: ").append(node.getFirstLocationName().getColNumber()).append("\n");
        }
        return null;
    }

    @Override
    public Void visitIrLocationArray(IrLocationArray node) {
        // 1) verify that the IrExpr is semantically correct
        node.getElementIndex().visit(this);

        // 2) make sure the array has been declared already
        if (symTable.checkIfSymbolExistsAtAnyScope(node.getLocationName().getValue())) {
            Ir object = symTable.getSymbol(node.getLocationName().getValue());
            // 3) make sure that var is an array (and not a method or non-array)

            if (! (object instanceof IrTypeArray) ) {
                errorMessage.append("Non-array variable be accessed as an array" + " line: ")
                        .append(node.getElementIndex().getLineNumber()).append(" col: ")
                        .append(node.getElementIndex().getColNumber()).append("\n");
            }
            else {
                IrTypeArray array = (IrTypeArray) object;

                // IMPORTANT: set the TypeEnum of the IrLocationArray
                // IMPORTANT: set the IrDecl of the IrLocationVar
                node.setIrDecl(object);
                node.setLocationType(array.getTypeEnum());
            }
        } else {
            errorMessage.append("Array variable used before declared" + " line: ").
                    append(node.getElementIndex().getLineNumber()).append(" col: ").
                    append(node.getElementIndex().getColNumber()).append("\n");
        }

        // 4) make sure that the IrExpr offset is an IrTypeInt
        if (!(node.getElementIndex().getExpressionType() == VarTypeEnum.RES_INT)) {
            errorMessage.append("Element offset must be of type int" + " line: ")
                    .append(node.getElementIndex().getLineNumber()).append(" col: ")
                    .append(node.getElementIndex().getColNumber()).append("\n");
        }

        return null;
    }

    @Override
    public Void visitIrLocationVar(IrLocationVar node) {
        // 1) make sure the variable has been declared already  TODO: 其实只分成global 和 local scope 两个
        if (symTable.checkIfSymbolExistsAtAnyScope(node.getLocationName().getValue())) {
            Ir object = symTable.getSymbol(node.getLocationName().getValue());

            // make sure that the identifier is a var, not a method or array
            if (object instanceof IrVarsDecl) {
                IrVarsDecl var = (IrVarsDecl) object;

                if (var.getType() instanceof IrTypeSimple){
                    // IMPORTANT: set the typeEnum of the IrLocationVar
                    node.setLocationType(var.getType().getTypeEnum());

                    // TODO : IMPORTANT: set the IrDecl of the IrLocationVar
                    node.setIrDecl(object);
                }
                else if (var.getType() instanceof IrTypeArray){
                    // IMPORTANT: set the TypeEnum of the IrLocationVar
                    node.setLocationType(var.getType().getTypeEnum());

                    // IMPORTANT: set the IrDecl of the IrLocationVar
                    node.setIrDecl(object);

                    errorMessage.append("Invalid array assignment" + " line: ")
                            .append(node.getLocationName().getLineNumber())
                            .append(" col: ").append(node.getLocationName().getColNumber()).append("\n");
                }
                else{
                    // this branch should be a POU declaration
                    errorMessage.append("Invalid assignment to a POU" + " line: ")
                            .append(node.getLocationName().getLineNumber()).append(" col: ")
                            .append(node.getLocationName().getColNumber()).append("\n");
                }

            }

        }
        else {
            errorMessage.append("Variable used before declared" + " line: ")
                    .append(node.getLocationName().getLineNumber())
                    .append(" col: ").append(node.getLocationName().getColNumber()).append("\n");
        }
        return null;
    }


    /**
     *Operation
     */
    @Override
    public Void visitIrOperBinaryEq(IrOperBinaryEq node) {
        // 1) check that rhs and lhs are valid
        node.leftOperand.visit(this);
        node.rightOperand.visit(this);

        // 2) verify that both lhs and rhs are either bool, int or real
        boolean bothAreBools = (node.rightOperand.getExpressionType() == VarTypeEnum.RES_BOOL)
                && (node.leftOperand.getExpressionType() == VarTypeEnum.RES_BOOL );
        boolean bothAreInts = (node.rightOperand.getExpressionType()  == VarTypeEnum.RES_INT)
                && (node.leftOperand.getExpressionType() == VarTypeEnum.RES_INT );
        boolean bothAreReals = (node.rightOperand.getExpressionType() == VarTypeEnum.RES_REAL)
                && (node.leftOperand.getExpressionType() == VarTypeEnum.RES_REAL );

        if (!bothAreBools && !bothAreInts && !bothAreReals) {
            errorMessage.append("The lhs and rhs of an equator operation must the same type of bool , int ,or real " + " line: ")
                    .append(node.getLineNumber()).
                    append(" col: ").append(node.getColNumber()).append("\n");
        }

        return null;
    }

    @Override
    public Void visitIrOperBinaryArith(IrOperBinaryArith node) {
        // 1) check that rhs and lhs are valid
        node.leftOperand.visit(this);
        node.rightOperand.visit(this);

        if (node.operation == OperKeyWordEnum.POWER_OP){
            if (node.rightOperand.getExpressionType() != VarTypeEnum.RES_INT){
                errorMessage.append("In the power operation, the rhs of an arithmetic expression must be of type int" + " line: ")
                        .append(node.getLineNumber()).append(" col: ")
                        .append(node.getColNumber()).append("\n");
            }
        }
        else if (node.operation == OperKeyWordEnum.DIV_OP){
            // TODO : verify divisor can not be zero
//            if (this.rightOperand){
//                errorMessage += "In the power operation, the rhs of an arithmetic expression must be of type int" +
//                        " line: " + this.getLineNumber() + " col: " + this.getColNumber() + "\n";
//            }

        }
        else{
            // 3) verify that both lhs and rhs are  int or real
            boolean bothAreReals = (node.rightOperand.getExpressionType() == VarTypeEnum.RES_REAL)
                    && (node.leftOperand.getExpressionType() == VarTypeEnum.RES_REAL );
            boolean bothAreInts = (node.rightOperand.getExpressionType()  == VarTypeEnum.RES_INT)
                    && (node.leftOperand.getExpressionType() == VarTypeEnum.RES_INT );
            if (bothAreInts){
                node.setExpressionType(VarTypeEnum.RES_INT);
            }
            else if (bothAreReals){
                node.setExpressionType(VarTypeEnum.RES_REAL);

            }
            else {
                errorMessage.append("The lhs and rhs of an arithmetic expression must be of type int or real" + " line: ")
                        .append(node.getLineNumber()).append(" col: ").append(node.getColNumber()).append("\n");
            }
        }
        return null;
    }

    @Override
    public Void visitIrOperUnaryNeg(IrOperUnaryNeg node) {
        // 1) check that the operand is valid
        node.operand.visit(this);

        // 2) verify that the operand is int or real
        if (!(node.operand.getExpressionType() == VarTypeEnum.RES_INT) && !(node.operand.getExpressionType() == VarTypeEnum.RES_REAL)) {
            errorMessage.append("The negation '-' operand must be used on an int or real" + " line: ")
                    .append(node.getLineNumber()).append(" col: ").append(node.getColNumber()).append("\n");
        }

        return null;
    }

    @Override
    public Void visitIrOperUnaryNot(IrOperUnaryNot node) {
        // 1) check that the operand is valid
        node.operand.visit(this);

        // 2) verify that the operand is int or real
        if (!(node.operand.getExpressionType() == VarTypeEnum.RES_BOOL)) {
            errorMessage.append("The not 'NOT' operand must be used on an bool" + " line: ")
                    .append(node.getLineNumber()).append(" col: ").append(node.getColNumber()).append("\n");
        }
        return null;
    }

    @Override
    public Void visitIrOperBinaryRel(IrOperBinaryRel node) {
        // 1) check that rhs and lhs are valid
        node.leftOperand.visit(this);
        node.rightOperand.visit(this);

        // 2) verify that both lhs and rhs are int or real
        boolean bothAreReals = (node.rightOperand.getExpressionType() == VarTypeEnum.RES_REAL)
                && (node.leftOperand.getExpressionType() == VarTypeEnum.RES_REAL );
        boolean bothAreInts = (node.rightOperand.getExpressionType()  == VarTypeEnum.RES_INT)
                && (node.leftOperand.getExpressionType() == VarTypeEnum.RES_INT );

        if (! bothAreInts  && !bothAreReals) {
            errorMessage.append("The lhs and rhs of a relational expression must be of type int or real" + " line: ")
                    .append(node.getLineNumber()).append(" col: ").append(node.getColNumber()).append("\n");
        }

        return null;
    }

    @Override
    public Void visitIrOperBinaryLogic(IrOperBinaryLogic node) {
        // 1) check that rhs and lhs are valid
        node.leftOperand.visit(this);
        node.rightOperand.visit(this);

        // 2) verify that both lhs and rhs are  boo;
        if (!((node.rightOperand.getExpressionType() == VarTypeEnum.RES_BOOL)
                && (node.leftOperand.getExpressionType()  == VarTypeEnum.RES_BOOL))) {
            errorMessage.append("The lhs and rhs of an arithmetic expression must be of type bool" + " line: ")
                    .append(node.getLineNumber()).append(" col: ").append(node.getColNumber()).append("\n");
        }

        return null;
    }


    /**
     * POU
     */
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


    /**
     * Type
     */
    @Override
    public Void visitIrTypeArray(IrTypeArray node) {
        node.size = (int) (node.high.getValue() - node.low.getValue());
        if (node.size <= 0) {
            errorMessage.append("Array size must be a non-zero positive integer" + " line: ")
                    .append(node.getLineNumber()).append(" col: ").append(node.getColNumber());
        }
        return null;
    }

    @Override
    public Void visitIrTypeSimple(IrTypeSimple node) {
        return null;
    }


    /**
     * Type
     */
    @Override
    public Void visitIrValueArray(IrValueArray node) {
        if (node.valueList != null){
            VarTypeEnum type = node.valueList.get(0).getExpressionType();
            node.setType(type);
            for (IrLiteral v : node.valueList){
                if (type != v.getExpressionType()){
                    errorMessage.append("the type of array value are not the same " + " line: ")
                            .append(node.getLineNumber()).append(" col: ").append(node.getColNumber()).append("\n");
                }
            }
        }
        return null;
    }

    @Override
    public Void visitIrValueSimple(IrValueSimple node) {
        node.type = node.value.getExpressionType();
        return null;
    }


    /**
     * VarDecalration
     */
    @Override
    public Void visitIrVARBlockDecl(IrVARBlockDecl node) {
        // 1) check that no identifiers declared twice in same scope
        for (IrVarDecl varDecl : node.VarList) {
            if (symTable.checkIfSymbolExistsAtAnyScope(varDecl.getName())) {
                errorMessage.append("Duplicate declaration in same scope __filename__" + " line: ")
                        .append(varDecl.getLineNumber()).append(" col: ").append(varDecl.getColNumber()).append("\n");
            }
            symTable.addObjectToCurrentScope(varDecl.getName(), varDecl);

            // make sure each vardDecl is correct
            varDecl.visit(this);
        }
        return null;
    }

    @Override
    public Void visitIrVarsDecl(IrVarsDecl node) {
        return null;
    }

    @Override
    public Void visitIrVarDecl(IrVarDecl node) {

        node.type.visit(this);

        // 1) keep the type of Irvalue and Irtype are the same
        // 2） if this is a array declaration, the size of nameArrayList is the same with it's values'.
        if (node.value != null){
            node.value.visit(this);

            if ( node.type.getTypeEnum() != node.value.getType() ){
                errorMessage.append("the type of Irvalue and Irtype should be the same " + " line: ")
                        .append(node.getLineNumber()).append(" col: ").append(node.getColNumber()).append("\n");
            }

            if (node.type instanceof IrTypeArray){
                if ( ((ArrayList) ( node.value.getValue() ) ).size() != node.type.getArraySize() ){
                    errorMessage.append("the size of nameArrayList should be the same with values " + " line: ")
                            .append(node.getLineNumber()).append(" col: ").append(node.getColNumber()).append("\n");
                }

            }


        }
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

    @Override
    public Void visitIrIdent(IrIdent irIdent) {
        return null;
    }
}
