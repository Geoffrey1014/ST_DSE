package ir;


import SymbolTable.Scope;
import SymbolTable.SymTable;
import ir.Arg.IrArg;
import ir.Arg.IrArgExpr;
import ir.Arg.IrArgInputAssign;
import ir.Arg.IrArgOutputAssign;
import ir.CtrlFlow.*;
import ir.Literal.*;
import ir.Location.IrFbStLocation;
import ir.Location.IrLocation;
import ir.Location.IrLocationArray;
import ir.Location.IrLocationVar;
import ir.Operation.*;
import ir.POUDecl.IrFunctionBlockDecl;
import ir.POUDecl.IrFunctionDecl;
import ir.POUDecl.IrPouDecl;
import ir.POUDecl.IrProgramDecl;
import ir.VARBlockDecl.*;
import tools.MyPrint;

import java.util.ArrayList;
import java.util.List;

public class SemanticCheckVisitor implements BaseVisitor<Void> {
    SymTable symTable = null;
    public StringBuilder errorMessage = new StringBuilder();

    public SemanticCheckVisitor(SymTable symTable){
        this.symTable = symTable;
    }

    @Override
    public Void visitIrIdent(IrIdent irIdent) {
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

    /***
     * 1. check whether the function has be declared
     * 2. check whether it is not functionBlock
     * 3. check the the number of arguments
     * 3.1 if the arguments are IrArgExpr type:
     *      check that the IrArgExpr is semantically correct
     *      check that each argument and param types match
     *      check that the argument is not an array_location
     *
     * 3.2 if the arguments are IrArgInputAssin type.
     *      check the child node
     * 4 we allow that funtions have output_var
     * @param node
     * @return
     */
    @Override
    public Void visitIrFunctionCallExpr(IrFunctionCallExpr node) {
        // 1) check that the function has already been declared
        if (symTable.checkIfSymbolExistsAtAnyScope(node.functionName.getValue())) {
            Ir pouDecl = symTable.getSymbol(node.functionName.getValue());

            // for normal POU_Decl
            if (pouDecl instanceof IrFunctionDecl) {
                IrFunctionDecl functionDecl = (IrFunctionDecl) pouDecl;

                // 2) check for same number of params
                List<IrVarDecl> varInputs = functionDecl.VarBlockVAR_INPUT.VarList;
                if (varInputs.size() == node.argsList.size()) {

                    // 分 IrArgExpr 和 IrArgInputAssign 两种
                    if (varInputs.size() != 0  && node.argsList.get(0) instanceof IrArgExpr){

                        for (int i = 0; i < varInputs.size(); i++) {
                            IrVarDecl param = varInputs.get(i);
                            Object unsafeArg = node.argsList.get(i);

                            if (unsafeArg instanceof IrArgExpr) {
                                IrArgExpr safeArg = (IrArgExpr) unsafeArg;

                                // check that the IrArgExpr is semantically correct
                                safeArg.accept(this);

                                // 3) check that each argument and param types match
                                if (param.getType().getTypeEnum() !=  safeArg.getArgumentType() ) {

                                    errorMessage.append("Argument and parameter types don't match" + " line: ").
                                            append(safeArg.getLineNumber()).append(" col: ").append(safeArg.getColNumber()).append("\n");

                                }

                                // 4) check that the argument is not an array_location
                                if (safeArg.getArgValue() instanceof IrLocation) {
                                    IrLocation locArg = (IrLocation) safeArg.getArgValue();
                                    IrVarDecl possibleArray = (IrVarDecl) symTable.getSymbol(locArg.getLocationName().getValue());

                                    if (possibleArray.getType() instanceof IrTypeArray) {
                                        errorMessage.append("Argument cannot be an array location " + " line: ")
                                                .append(safeArg.getLineNumber()).append(" col: ").append(safeArg.getColNumber()).append("\n");
                                    }
                                }
                            }
                            else {
                                errorMessage.append("Arguments are not all ArgExpr type " + " line: ").append(node.getLineNumber()).append(" col: ")
                                        .append(node.getColNumber()).append("\n");
                            }
                        }
                    }
                    else if (varInputs.size() != 0  && node.argsList.get(0) instanceof IrArgInputAssign){
                        ArrayList<IrArg> argInputAssignList = (ArrayList<IrArg>) node.argsList;
                        for (IrArg argInputAssign : argInputAssignList){

                            // 将 当前的 functionDecl 赋值给 argInputAssign.irDeclPou  再检查子节点
                            ((IrArgInputAssign) argInputAssign).irDeclPou = functionDecl;
                            argInputAssign.accept(this);
                        }

                    }


                } else {
                    errorMessage.append("Wrong number of arguments passed to function" + " line: ")
                            .append(node.getLineNumber()).append(" col: ").append(node.getColNumber()).append("\n");
                }

                if (node.assignOutputList != null){
                    errorMessage.append("can not call function block  in expression. ")
                            .append(node.getLineNumber()).append(" col: ").append(node.getColNumber()).append("\n");
                }

                // IMPORTANT: set the IrType of the IrFunctionCallExpr
                node.functionType = functionDecl.getReturnType();

            }
            // for an extern method_decl TODO 应该内置 ADD  SUB ABS 等基础函数
//
            else {
                errorMessage.append("Non-Function identifier being called as a method" + " line: ")
                        .append(node.getLineNumber()).append(" col: ").append(node.getColNumber()).append("\n");
            }
        } else {
            errorMessage.append("Function called before declared" + " line: ")
                    .append(node.getLineNumber()).append(" col: ").append(node.getColNumber()).append("\n");
        }

        return null;
    }

    /**
     * 把每个函数的功能写清楚
     * 1。FB要被声明过， 且是 IrFunctionBlockDecl 类型
     * 2。VAR_INPUT and Arg 要对得上  分为 ArgExpr  和 ArgInputAssign
     * 2。1 ArgExpr : TypeEnum 要一致
     * 2。2 ArgInputAssign 和 FB 的 VAR_INPUT 名字 以及 TypeEnum 匹配
     * 3。 ArgOutputAssign  要和 Var_OUTPUT  匹配
     * 3.1  ArgOutputAssign.fbOutput 对应一个 IrFbStLocation。 functionBlockName 就是 IrFbStLocation.varNameFirst， IrFbStLocation 检查
     * 3.2  ArgOutputAssign.acceptLocation 是个 IrLocationVar 类型，IrLocationVar.irDeclObject 应该 被赋值 IrFbStLocation， IrLocationVar 检查
     * 3.3  acceptLocation 的类型和 IrFbStLocation 是否一致
     *
     * @param node
     * @return
     */
    @Override
    public Void visitIrFunctionCallStmt(IrFunctionCallStmt node) {
        // 1) check that the method has already been declared
        if (symTable.checkIfSymbolExistsAtAnyScope(node.functionBlockName.getValue())) {
            Ir object = symTable.getSymbol(node.functionBlockName.getValue());

            // for normal POU_Decl
            if (object instanceof IrFunctionBlockDecl) {
                IrFunctionBlockDecl functionBlockDecl = (IrFunctionBlockDecl) object;

                // 2) check for same number of params
                List<IrVarDecl> varInputs = functionBlockDecl.VarBlockVAR_INPUT.VarList;
                if (varInputs.size() == node.argsList.size()) {

                    // 分 IrArgExpr 和 IrArgInputAssign 两种
                    if (varInputs.size() != 0  && node.argsList.get(0) instanceof IrArgExpr){

                        for (int i = 0; i < varInputs.size(); i++) {
                            IrVarDecl param = varInputs.get(i);
                            Object unsafeArg = node.argsList.get(i); // i.e. a INT

                            if (unsafeArg instanceof IrArgExpr) {
                                IrArgExpr safeArg = (IrArgExpr) unsafeArg;

                                // check that the IrArgExpr is semantically correct
                                safeArg.accept(this);

                                // 3) check that each argument and param types match
                                if (param.getType().getTypeEnum() !=  safeArg.getArgumentType() ) {

                                    errorMessage.append("Argument and parameter types don't match" + " line: ").
                                            append(safeArg.getLineNumber()).append(" col: ").append(safeArg.getColNumber()).append("\n");

                                }

                                // 4) check that the argument is not an array_location
                                if (safeArg.getArgValue() instanceof IrLocation) {
                                    IrLocation locArg = (IrLocation) safeArg.getArgValue();
                                    IrVarDecl possibleArray = (IrVarDecl) symTable.getSymbol(locArg.getLocationName().getValue());

                                    if (possibleArray.getType() instanceof IrTypeArray) {
                                        errorMessage.append("Argument cannot be an array location " + " line: ")
                                                .append(safeArg.getLineNumber()).append(" col: ").append(safeArg.getColNumber()).append("\n");
                                    }
                                }
                            }
                            else {
                                errorMessage.append("Arguments are not all ArgExpr type " + " line: ").append(node.getLineNumber()).append(" col: ")
                                        .append(node.getColNumber()).append("\n");
                            }
                        }
                    }
                    else if (varInputs.size() != 0  && node.argsList.get(0) instanceof IrArgInputAssign){
                        ArrayList<IrArg> argInputAssignList = (ArrayList<IrArg>) node.argsList;
                        for (IrArg argInputAssign : argInputAssignList){

                            // 将 当前的 functionDecl 赋值给 argInputAssign.irDeclPou  再检查子节点
                            ((IrArgInputAssign) argInputAssign).irDeclPou = functionBlockDecl;
                            argInputAssign.accept(this);
                        }

                    }
                } else {
                    errorMessage.append("Wrong number of arguments passed to function" + " line: ")
                            .append(node.getLineNumber()).append(" col: ").append(node.getColNumber()).append("\n");
                }

                // 2) 检查 output   是否正确, 支持只输出输出部分 VAR_OUTPUT
                List<IrArgOutputAssign>  argOutputAssigns = node.assignOutputList;
                for (IrArgOutputAssign argOutputAssign : argOutputAssigns){
                    argOutputAssign.irDeclPou = (IrPouDecl) object;
                    argOutputAssign.accept(this);
                }

                // IMPORTANT: IrFunctionCallStmt  是没有 type  的
//                node.functionType = functionDecl.getType();

            }
            // for an extern method_decl TODO 应该内置一些基础的 FB
            // for non-method identifiers
            else {
                errorMessage.append("Non-FunctionBlock identifier being called as a FunctionBlock" + " line: ")
                        .append(node.getLineNumber()).append(" col: ").append(node.getColNumber()).append("\n");
            }
        } else {
            errorMessage.append("FunctionBlock called before declared" + " line: ")
                    .append(node.getLineNumber()).append(" col: ").append(node.getColNumber()).append("\n");
        }

        return null;
    }


    /**
     * arguments
     */
    @Override
    public Void visitIrArgExpr(IrArgExpr node) {

        node.getArgValue().accept(this);
        return null;
    }

    /**
     * IrArgInputAssign 中包含 IrPouDecl irDeclPou;
     * 检查 irDeclPou 生成的 scope 中是否有 storeLocationName 的定义， 如果存在定义，则赋值给 irDeclObject
     * 判断 irDeclObject 的类型是否和 IrExpr argValue 一致
     *
     * @param node
     * @return
     */
    @Override
    public Void visitIrArgInputAssign(IrArgInputAssign node) {
        Scope locals = symTable.treeProperty.get(node.irDeclPou);
        if (locals.resolve(node.storeLocationName.getValue()) != null){
            node.irDeclVar = (IrVarDecl) locals.resolve(node.storeLocationName.getValue());

            node.argValue.accept(this); // 检查子节点 argValue

            //判断 irDeclObject 的类型是否和 IrExpr argValue 一致
            if (node.irDeclVar.getType().getTypeEnum() == node.argValue.getExpressionType()){

                // check that the argument is not an array_location
                if (node.irDeclVar.type  instanceof IrTypeArray) {
                    errorMessage.append("Argument cannot be an array location " + " line: ")
                            .append(node.getLineNumber()).append(" col: ").append(node.getColNumber()).append("\n");
                }

            }
            else {
                errorMessage.append(" the argValue's type does not match parameter's : ").append(node.argValue.getExpressionType())
                        .append(" compared with ").append(node.irDeclVar.type.getTypeEnum())
                        .append( " line: ").append(node.getLineNumber()).append(" col: ")
                        .append(node.getColNumber()).append("\n");
            }

        }
        else {
            errorMessage.append(" Can not find such name parameter : ").append(node.storeLocationName.getValue()).append(" In ")
                    .append(node.irDeclPou.getIdentName().getValue())
                    .append( " line: ").append(node.getLineNumber()).append(" col: ")
                    .append(node.getColNumber()).append("\n");
        }

        return null;
    }

    /**
     *如果是在这里进行语意检查的话，
     *  *     * 3。 ArgOutputAssign  要和 Var_OUTPUT  匹配
     *  *      * 3.1  ArgOutputAssign.fbOutput 对应一个 IrFbStLocation。 functionBlockName 就是 IrFbStLocation.varNameFirst， IrFbStLocation 检查
     *  *      * 3.2  ArgOutputAssign.acceptLocation 是个 IrLocationVar 类型，IrLocationVar.irDeclObject 应该 被赋值 IrFbStLocation， IrLocationVar 检查
     *  *      * 3.3  acceptLocation 的类型和 IrFbStLocation 是否一致
     *  *      *
     *  * IrArgOutputAssign 中需要 携带 被 call function 的 信息
     * @param node
     * @return
     */
    @Override
    public Void IrArgOutputAssign(IrArgOutputAssign node) {
        Scope locals = symTable.treeProperty.get(node.irDeclPou);

        if (locals.resolve(node.fbOutput.getValue()) != null){
            node.irDeclVar = (IrVarDecl) locals.resolve(node.fbOutput.getValue());

            node.acceptLocation.accept(this); // 检查子节点 acceptLocation

            //判断 acceptLocation 的 TypeEnum 类型是否和  IrVarDecl irDeclVar一致
            if (node.irDeclVar.getType().getTypeEnum() == node.acceptLocation.getExpressionType()){

                // 判断是否都是 array 或者 simple 类型
                boolean bothSimple = (node.irDeclVar.type instanceof IrTypeSimple  && node.acceptLocation.irDeclObject.type instanceof IrTypeSimple);
                boolean bothArray = (node.irDeclVar.type instanceof IrTypeArray  && node.acceptLocation.irDeclObject.type instanceof IrTypeArray);

                if ( !bothArray && ! bothSimple ) {
                    errorMessage.append("fbOutput and acceptLocation are not both array ot simple  " + " line: ")
                            .append(node.getLineNumber()).append(" col: ").append(node.getColNumber()).append("\n");
                }

            }
            else {
                errorMessage.append(" the acceptLocation's type does not match Output_VAR's : ").append(node.acceptLocation.getExpressionType())
                        .append(" compared with ").append(node.irDeclVar.type.getTypeEnum())
                        .append( " line: ").append(node.getLineNumber()).append(" col: ")
                        .append(node.getColNumber()).append("\n");
            }

        }
        else {
            errorMessage.append(" Can not find such name parameter : ").append(node.fbOutput.getValue()).append(" In ")
                    .append(node.irDeclPou.getIdentName().getValue())
                    .append( " line: ").append(node.getLineNumber()).append(" col: ")
                    .append(node.getColNumber()).append("\n");
        }

        return null;
    }


    /**
     * control flow
     */
    /** 1. 检查子节点 condition Expr
     *  2. 判断 condition 是否 是bool 型
     *  3. 检查子节点 getStmtBody()
     * @param node
     * @return
     */
    @Override
    public Void visitIrCtrlFlowIf(IrCtrlFlowIf node) {
        node.getCondExpr().accept(this);

        if (node.getCondExpr().getExpressionType() != VarTypeEnum.RES_BOOL){
            errorMessage.append("Condition for if-statement must be a boolean" + " line: ")
                    .append(node.getLineNumber()).append(" col: ").append(node.getColNumber()).append("\n");
        }

        node.getStmtBody().accept(this);
        return null;
    }

    /**
     *  it is the same as the above one visitIrCtrlFlowIf
     * @param node
     * @return
     */
    @Override
    public Void visitIrCtrlFlowElsif(IrCtrlFlowElsif node) {
        node.getCondExpr().accept(this);

        if (node.getCondExpr().getExpressionType() != VarTypeEnum.RES_BOOL){
            errorMessage.append("Condition for if-statement must be a boolean" + " line: ")
                    .append(node.getLineNumber()).append(" col: ").append(node.getColNumber()).append("\n");
        }

        node.getStmtBody().accept(this);

        return null;
    }

    /**
     *  1. 检查子节点 condition Expr
     *  2. 判断 condition 是否 是bool 型
     *  3. 检查子节点 getStmtBody()
     *  4. 检查子节点 getElseBlock（）
     * @param node
     * @return
     */
    @Override
    public Void visitIrCtrlFlowIfElse(IrCtrlFlowIfElse node) {
        node.getCondExpr().accept(this);

        if (node.getCondExpr().getExpressionType() != VarTypeEnum.RES_BOOL){
            errorMessage.append("Condition for if-statement must be a boolean" + " line: ")
                    .append(node.getLineNumber()).append(" col: ").append(node.getColNumber()).append("\n");
        }

        node.getStmtBody().accept(this);
        node.getElseBlock().accept(this);
        return null;
    }

    /**
     * 1. 检查子节点 condition Expr
     * 2. 判断 condition 是否 是bool 型
     * 3. 检查子节点 list elsifArrayList
     * 4。 检查子节点 getElseBlock（）
     * @param node
     * @return
     */
    @Override
    public Void visitIrCtrlFlowIfElsifElse(IrCtrlFlowIfElsifElse node) {
        node.getCondExpr().accept(this);

        if (node.getCondExpr().getExpressionType() != VarTypeEnum.RES_BOOL){
            errorMessage.append("Condition for if-statement must be a boolean" + " line: ")
                    .append(node.getLineNumber()).append(" col: ").append(node.getColNumber()).append("\n");
        }

        node.getStmtBody().accept(this);

        for ( IrCtrlFlowElsif ctrlFlowElsif: node.getElsifArrayList()){
            ctrlFlowElsif.accept(this);
        }

        node.getElseBlock().accept(this);
        return null;
    }

    @Override
    public Void visitIrCtrlFlowIfElsif(IrCtrlFlowIfElsif node) {
        node.getCondExpr().accept(this);

        if (node.getCondExpr().getExpressionType() != VarTypeEnum.RES_BOOL){
            errorMessage.append("Condition for if-statement must be a boolean" + " line: ")
                    .append(node.getLineNumber()).append(" col: ").append(node.getColNumber()).append("\n");
        }

        node.getStmtBody().accept(this);

        for ( IrCtrlFlowElsif ctrlFlowElsif: node.getElsifArrayList()){
            ctrlFlowElsif.accept(this);
        }

        return null;
    }

    /**
     * 1. visit child node counter
     * 2. make sure counter be int and not array
     * 3. visit child node Range
     * 4. visit child node CodeBlock
     * @param node
     * @return
     */
    @Override
    public Void visitIrCtrlFlowFor(IrCtrlFlowFor node) {
        node.getCounter().accept(this);
        if (node.getCounter().getExpressionType() == VarTypeEnum.RES_INT){
            if (node.getCounter().getIrDecl() instanceof  IrTypeArray){
                errorMessage.append("counter shoud not be array-type location " + " line: ")
                        .append(node.getLineNumber()).append(" col: ").append(node.getColNumber()).append("\n");
            }
        }
        else {
            errorMessage.append("counter shoud be int" + " line: ")
                    .append(node.getLineNumber()).append(" col: ").append(node.getColNumber()).append("\n");
        }
        node.getRange().accept(this);
        node.getCodeBlock().accept(this);
        return null;
    }

    /**
     * make sure the low bound, high bound and step be int
     * @param node
     * @return
     */
    @Override
    public Void visitIrCtrlFlowForRange(IrCtrlFlowForRange node) {
        if (node.getLow().getExpressionType() != VarTypeEnum.RES_INT){
            errorMessage.append(" the  low boundary shoud be int ")
                    .append(node.getLineNumber()).append(" col: ").append(node.getColNumber()).append("\n");
        }
        if (node.getHigh().getExpressionType() != VarTypeEnum.RES_INT){
            errorMessage.append(" the  high boundary shoud be int ")
                    .append(node.getLineNumber()).append(" col: ").append(node.getColNumber()).append("\n");
        }
        if (node.getStep() == null){
            node.stepNum = 1;
        }
        else if (node.getStep().getExpressionType() != VarTypeEnum.RES_INT){
            errorMessage.append(" the  low boundary shoud be int ")
                    .append(node.getLineNumber()).append(" col: ").append(node.getColNumber()).append("\n");

        }

        return null;
    }

    @Override
    public Void visitIrCtrlFlowWhile(IrCtrlFlowWhile node) {
        node.getCondExpr().accept(this);

        if (node.getCondExpr().getExpressionType() != VarTypeEnum.RES_BOOL){
            errorMessage.append("Condition for if-statement must be a boolean" + " line: ")
                    .append(node.getLineNumber()).append(" col: ").append(node.getColNumber()).append("\n");
        }

        node.getStmtBody().accept(this);
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

                // IMPORTANT: set the IrDecl of the IrFbStLocation
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
        node.getElementIndex().accept(this);

        // 2) make sure the array has been declared already
        if (symTable.checkIfSymbolExistsAtAnyScope(node.getLocationName().getValue())) {
            IrVarDecl varDecl = (IrVarDecl) symTable.getSymbol(node.getLocationName().getValue());
            // 3) make sure that var is an array (and not a method or non-array)

            if (! (varDecl.type instanceof IrTypeArray) ) {
                errorMessage.append("Non-array variable be accessed as an array" + " line: ")
                        .append(node.getElementIndex().getLineNumber()).append(" col: ")
                        .append(node.getElementIndex().getColNumber()).append("\n");
            }
            else {
//                IrTypeArray array = (IrTypeArray) object;

                // IMPORTANT: set the TypeEnum of the IrLocationArray
                // IMPORTANT: set the IrDecl of the IrLocationVar
                node.setIrDecl(varDecl);
                node.setLocationType(varDecl.type.getTypeEnum());
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
            if (object instanceof IrVarDecl) {
                IrVarDecl var = (IrVarDecl) object;

                if (var.getType() instanceof IrTypeSimple){
                    // IMPORTANT: set the typeEnum of the IrLocationVar
                    node.setLocationType(var.getType().getTypeEnum());

                    // TODO : IMPORTANT: set the IrDecl of the IrLocationVar
                    node.setIrDecl(var);
                }
                else if (var.getType() instanceof IrTypeArray){
                    // IMPORTANT: set the TypeEnum of the IrLocationVar
                    node.setLocationType(var.getType().getTypeEnum());

                    // IMPORTANT: set the IrDecl of the IrLocationVar
                    node.setIrDecl(var);

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
        node.leftOperand.accept(this);
        node.rightOperand.accept(this);

        // 2) verify that both lhs and rhs are either bool, int or real
        if (node.leftOperand.getExpressionType() != node.rightOperand.getExpressionType()){
            errorMessage
                    .append("The lhs and rhs of an equator operation must the same type of bool , int ,or real " + " line: ")
                    .append(node.getLineNumber()).
                    append(" col: ").append(node.getColNumber()).append("\n")
                    .append("leftOperand type : ").append(node.leftOperand.getExpressionType())
                    .append("rightOperand type : ").append(node.rightOperand.getExpressionType()).append("\n\n");
        }

        return null;
    }

    @Override
    public Void visitIrOperBinaryArith(IrOperBinaryArith node) {
        // 1) check that rhs and lhs are valid
        node.leftOperand.accept(this);
        node.rightOperand.accept(this);

        if (node.operation == OperKeyWordEnum.POWER_OP){
            if (node.rightOperand.getExpressionType() != VarTypeEnum.RES_INT){
                errorMessage.append("In the power operation, the rhs of an arithmetic expression must be of type int" + " line: ")
                        .append(node.getLineNumber()).append(" col: ")
                        .append(node.getColNumber()).append("\n");
            }
            node.setExpressionType(node.leftOperand.getExpressionType());
        }
//        else if (node.operation == OperKeyWordEnum.DIV_OP){
            // TODO : verify divisor can not be zero
//            if (this.rightOperand){
//                errorMessage += "In the power operation, the rhs of an arithmetic expression must be of type int" +
//                        " line: " + this.getLineNumber() + " col: " + this.getColNumber() + "\n";
//            }

//        }
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
        node.operand.accept(this);

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
        node.operand.accept(this);

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
        node.leftOperand.accept(this);
        node.rightOperand.accept(this);

        // 2) verify that both lhs and rhs are int or real
        boolean bothAreReals = (node.rightOperand.getExpressionType() == VarTypeEnum.RES_REAL)
                && (node.leftOperand.getExpressionType() == VarTypeEnum.RES_REAL );
        boolean bothAreInts = (node.rightOperand.getExpressionType()  == VarTypeEnum.RES_INT)
                && (node.leftOperand.getExpressionType() == VarTypeEnum.RES_INT );

        if (! bothAreInts  && !bothAreReals) {
            errorMessage
                    .append("The lhs and rhs of a relational expression must be of type int or real" + " line: ")
                    .append(node.getLineNumber()).append(" col: ").append(node.getColNumber())
                    .append("\n\tleftOperand type : ").append(node.leftOperand.getExpressionType())
                    .append("  rightOperand type : "+node.rightOperand.getExpressionType()).append("\n")
                    ;
        }

        return null;
    }

    @Override
    public Void visitIrOperBinaryLogic(IrOperBinaryLogic node) {
        // 1) check that rhs and lhs are valid
        node.leftOperand.accept(this);
        node.rightOperand.accept(this);

        // 2) verify that both lhs and rhs are  boo;
        if (!((node.rightOperand.getExpressionType() == VarTypeEnum.RES_BOOL)
                && (node.leftOperand.getExpressionType()  == VarTypeEnum.RES_BOOL))) {
            errorMessage.append("The lhs and rhs of an arithmetic expression must be of type bool" + " line: ")
                    .append(node.getLineNumber()).append(" col: ").append(node.getColNumber()).append("\n");
        }

        return null;
    }


    /**
     * Type
     */
    @Override
    public Void visitIrTypeArray(IrTypeArray node) {
        node.size = (int) (node.high.getValue() - node.low.getValue()) + 1;
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
     * value
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
        // 1) check that no identifiers declared twice in same scope  has been done in  DefPhase
        for (IrVarDecl varDecl : node.VarList) {
//            if (symTable.checkIfSymbolExistsAtAnyScope(varDecl.getName())) {
//                errorMessage.append("Duplicate declaration in same scope __filename__" + " line: ")
//                        .append(varDecl.getLineNumber()).append(" col: ").append(varDecl.getColNumber()).append("\n");
//            }
//            symTable.addObjectToCurrentScope(varDecl.getName(), varDecl);

            // make sure each vardDecl is correct
            varDecl.accept(this);
        }
        return null;
    }

    @Override
    public Void visitIrVarsDecl(IrVarsDecl node) {
        return null;
    }

    @Override
    public Void visitIrVarDecl(IrVarDecl node) {

        node.type.accept(this);

        // 1)  make sure the type of Irvalue and Irtype are the same
        // 2） if this is a array declaration, the size of nameArrayList is the same with it's values'.
        if (node.value != null){
            node.value.accept(this);

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
        // 1) verify that the storeLocation is semantically correct
        node.getStoreLocation().accept(this);

        if (node.getStoreLocation() instanceof IrLocationVar) {

//        2) check to make sure the var isn't a lone array var
            String name = node.getStoreLocation().getLocationName().getValue();
            if (symTable.checkIfSymbolExistsAtAnyScope(name)) {
                IrVarDecl object = (IrVarDecl) symTable.getSymbol(node.getStoreLocation().getLocationName().getValue());

                if (object.getType() instanceof IrTypeArray) {
                    errorMessage.append("Can't re-assign an array to an expression" + " line: ")
                            .append(node.getLineNumber()).append(" col: ").append(node.getColNumber()).append("\n\n");
                }
            }
        }


        // 3) verify that the expr is semantically correct
        node.getExpr().accept(this);

        // 4) make sure that the IrExpr and IrLocation are the same VarTypeEnum
        if(node.getExpr().getExpressionType() != node.getStoreLocation().getExpressionType()){

            errorMessage.append("The variable to be assigned and the expression must both be of type int, real,  or of type bool" + " line: ")
                    .append(node.getLineNumber()).append(" col: ").append(node.getColNumber())
                    .append("\n\tExpr type : ").append(node.getExpr().getExpressionType())
                    .append("  StoreLocation type : ").append(node.getStoreLocation().getExpressionType()).append("\n\n")
            ;
        }
//        boolean bothAreInts = (node.getExpr().getExpressionType() == VarTypeEnum.RES_INT)
//                && (node.getStoreLocation().getExpressionType() == VarTypeEnum.RES_INT);
//        boolean bothAreBools = (node.getExpr().getExpressionType()  == VarTypeEnum.RES_BOOL )
//                && (node.getStoreLocation().getExpressionType()  == VarTypeEnum.RES_BOOL);
//        boolean bothAreReals = (node.getExpr().getExpressionType()  == VarTypeEnum.RES_REAL )
//                && (node.getStoreLocation().getExpressionType()  == VarTypeEnum.RES_REAL);
//        if (!bothAreBools && !bothAreInts && !bothAreReals) {
//            errorMessage.append("The variable to be assigned and the expression must both be of type int, real,  or of type bool" + " line: ")
//                    .append(node.getLineNumber()).append(" col: ").append(node.getColNumber()).append("\n");
//        }
        return null;
    }


    @Override
    public Void visitIrCodeBlock(IrCodeBlock node) {
        if (node.stmtsList.size() == 0 ){
            errorMessage.append("CodeBlock is empty : " + " line: ")
                    .append(node.getLineNumber()).append(" col: ").append(node.getColNumber()).append("\n");
        }
        else {
            // check that each statement is valid
            for (IrStmt stmt : node.stmtsList) {

                stmt.accept(this);
            }
        }


        return null;
    }


    /**
     * POU
     */
    @Override
    public Void visitIrFunctionDecl(IrFunctionDecl node) {
        // change to the accordding scope
        symTable.currentScope = symTable.treeProperty.get(node);

        if (node.getVarBlockVAR() != null){
            node.getVarBlockVAR().accept(this);
        }

        if (node.getVarBlockVAR_INPUT() != null){
            node.getVarBlockVAR_INPUT().accept(this);
        }
        else {
            errorMessage.append("there is no VAR_INPUT in this POU : " + " line: ")
                    .append(node.getLineNumber()).append(" col: ").append(node.getColNumber()).append("\n");
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
        if (node.getCodeBlock() != null){
            node.getCodeBlock().accept(this);
        }
        else {
            errorMessage.append("there is no code block in this POU : " + " line: ")
                    .append(node.getLineNumber()).append(" col: ").append(node.getColNumber()).append("\n");
        }

        symTable.currentScope = symTable.currentScope.getEnclosingScope();
        return null;
    }

    @Override
    public Void visitIrFunctionBlockDecl(IrFunctionBlockDecl node) {
        // change to the accordding scope
        symTable.currentScope = symTable.treeProperty.get(node);

        if (node.getVarBlockVAR() != null){
            node.getVarBlockVAR().accept(this);
        }

        if (node.getVarBlockVAR_INPUT() != null){
            node.getVarBlockVAR_INPUT().accept(this);
        }
        else {
            errorMessage.append("there is no VAR_INPUT in this POU : " + " line: ")
                    .append(node.getLineNumber()).append(" col: ").append(node.getColNumber()).append("\n");
        }

        if (node.getVarBlockVAR_OUTPUT() != null){
            node.getVarBlockVAR_OUTPUT().accept(this);
        }
        else {
            errorMessage.append("there is no VAR_OUTPUT in this POU : " + " line: ")
                    .append(node.getLineNumber()).append(" col: ").append(node.getColNumber()).append("\n");
        }

        if (node.getVarBlockVAR_INPUT_OUTPUT() != null){
            node.getVarBlockVAR_INPUT_OUTPUT().accept(this);
        }

        if (node.getVarBlockVAR_TEMP() != null){
            node.getVarBlockVAR_TEMP().accept(this);
        }
        if (node.getCodeBlock() != null){
            node.getCodeBlock().accept(this);
        }
        else {
            errorMessage.append("there is no code block in this POU : " + " line: ")
                    .append(node.getLineNumber()).append(" col: ").append(node.getColNumber()).append("\n");
        }
        symTable.currentScope = symTable.currentScope.getEnclosingScope();
        return null;
    }

    @Override
    public Void visitIrProgramDecl(IrProgramDecl node) {
        // change to the accordding scope
        symTable.currentScope = symTable.treeProperty.get(node);

        if (node.getVarBlockVAR() != null){
            node.getVarBlockVAR().accept(this);
        }

        if (node.getVarBlockVAR_INPUT() != null){
            node.getVarBlockVAR_INPUT().accept(this);
        }
        else {
            errorMessage.append("there is no VAR_INPUT in this POU : " + " line: ")
                    .append(node.getLineNumber()).append(" col: ").append(node.getColNumber()).append("\n");
        }

        if (node.getVarBlockVAR_OUTPUT() != null){
            node.getVarBlockVAR_OUTPUT().accept(this);
        }
        else {
            errorMessage.append("there is no VAR_OUTPUT in this POU : " + " line: ")
                    .append(node.getLineNumber()).append(" col: ").append(node.getColNumber()).append("\n");
        }

        if (node.getVarBlockVAR_INPUT_OUTPUT() != null){
            node.getVarBlockVAR_INPUT_OUTPUT().accept(this);
        }

        if (node.getVarBlockVAR_TEMP() != null){
            node.getVarBlockVAR_TEMP().accept(this);
        }
        if (node.getCodeBlock() != null){
            node.getCodeBlock().accept(this);
        }
        else {
            errorMessage.append("there is no code block in this POU : " + " line: ")
                    .append(node.getLineNumber()).append(" col: ").append(node.getColNumber()).append("\n");
        }
        symTable.currentScope = symTable.currentScope.getEnclosingScope();
        return null;
    }

    /**1. check it have one and only one ProgramDecl (done in defPhase)
     * 2. check there is no pou has been declared twice (done in defPhase)
     * 3. check child nodes
     * @param node
     * @return
     */
    @Override
    public Void visitIrPousDecl(IrPousDecl node) {
        symTable.currentScope = symTable.globals;

        for (IrProgramDecl programDecl: node.getProgramDeclsArrayList()){
            programDecl.accept(this);
        }

        for (IrFunctionBlockDecl functionBlockDecl : node.getFunctionBlockDeclsArrayList()){
            functionBlockDecl.accept(this);
        }

        for (IrFunctionDecl functionDecl : node.getFunctionDeclArrayList()){
            functionDecl.accept(this);
        }

        return null;
    }


}
