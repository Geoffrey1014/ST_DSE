import grammar.gen.STParser;
import grammar.gen.STParserBaseListener;
import ir.*;
import ir.Arg.IrArg;
import ir.Arg.IrArgAssign;
import ir.Arg.IrArgInputAssign;
import ir.CtrlFlow.*;
import ir.Location.IrFbStLocation;
import ir.Location.IrLocation;
import ir.Location.IrLocationArray;
import ir.Location.IrLocationVar;
import ir.Operation.*;
import ir.POUDecl.IrFunctionBlockDecl;
import ir.POUDecl.IrFunctionDecl;
import ir.POUDecl.IrProgramDecl;
import ir.VARBlockDecl.*;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeProperty;
import org.antlr.v4.runtime.tree.TerminalNode;


import java.util.ArrayList;

public class STListener extends STParserBaseListener {
    MyPrint myPrint = new MyPrint(0);

    public IrPousDecl pous;
    ParseTreeProperty<Ir> ASTNodes = new ParseTreeProperty<>();

    @Override public void exitPous(STParser.PousContext ctx) {
        STListener.ProgramLocation l = new ProgramLocation(ctx);

        ArrayList<IrProgramDecl> programDeclsArrayList = new ArrayList<>();
        ArrayList<IrFunctionBlockDecl> functionBlockDeclsArrayList = new ArrayList<>();
        ArrayList<IrFunctionDecl> functionDeclArrayList = new ArrayList<>();
        /**
         * 需要在其他监听的动作中完成 其他AST中间结构的构建
         * 可以用stack来传递ASTNode，或者可以将这些数据结构挂在具体语法树上
         * */
        for(ParseTree node :ctx.children){
            Ir AstNode = ASTNodes.get(node);
           if ( AstNode instanceof IrProgramDecl){
               programDeclsArrayList.add((IrProgramDecl) AstNode);
           }
           else if (AstNode instanceof IrFunctionBlockDecl){
               functionBlockDeclsArrayList.add((IrFunctionBlockDecl) AstNode);
           }
           else if (AstNode instanceof IrFunctionDecl){
               functionDeclArrayList.add((IrFunctionDecl) AstNode);
           }

        }
         this.pous = new IrPousDecl(programDeclsArrayList, functionBlockDeclsArrayList, functionDeclArrayList, l.line,l.col);
    }

    @Override public void enterPou(STParser.PouContext ctx) {}

    @Override public void exitPou(STParser.PouContext ctx) {
        /** 把子节点的ASTcopy一下就行了*/
        Ir pou = getASTNode(ctx.getChild(0));
        setASTNode(ctx, pou);
//         = getASTNode(ctx.program());
//        if (pou != null) {setASTNode(ctx, pou);}
//        pou = getASTNode(ctx.function_block());
//        if (pou != null) {setASTNode(ctx, pou);}
//        pou = getASTNode(ctx.function());
//        if (pou != null) {setASTNode(ctx, pou);}

    }

    @Override public void enterProgram(STParser.ProgramContext ctx) { }

    @Override public void exitProgram(STParser.ProgramContext ctx) {
        STListener.ProgramLocation l = new ProgramLocation(ctx);
        IrIdent name = new IrIdent(ctx.ID().getText(), l.line, l.col);
//        System.out.println(ctx.name);
        System.err.println(ctx.ID().getSymbol());

        IrVARBlockDecl varBlockVAR = null;
        IrVARBlockDecl varBlockVAR_INPUT = null;
        IrVARBlockDecl varBlockVAR_OUTPUT = null;
        IrVARBlockDecl varBlockVAR_INPUT_OUTPUT =null;
        IrVARBlockDecl varBlockVAR_TEMP= null;
        IrCodeBlock codeBlock = (IrCodeBlock) getASTNode(ctx.stat_list());

        for (ParseTree node :ctx.var_blocks){
            IrVARBlockDecl varBlockDecl = (IrVARBlockDecl) getASTNode(node);
            VarAccessTypeEnum type = varBlockDecl.getAccessType();
            switch (type){
                case VAR:
                    varBlockVAR = varBlockDecl;
                    break;
                case VAR_INPUT:
                    varBlockVAR_INPUT = varBlockDecl;
                    break;
                case VAR_OUTPUT:
                    varBlockVAR_OUTPUT = varBlockDecl;
                    break;
                case VAR_INPUT_OUTPUT:
                    varBlockVAR_INPUT_OUTPUT = varBlockDecl;
                    break;
                case RES_VAR_TEMP:
                    varBlockVAR_TEMP = varBlockDecl;
                    break;
                default:
                    System.err.println("there is no such var access type" + type.getTypeName());

            }
        }
        IrProgramDecl program = new IrProgramDecl(name, varBlockVAR, varBlockVAR_INPUT, varBlockVAR_OUTPUT,
                varBlockVAR_INPUT_OUTPUT, varBlockVAR_TEMP,codeBlock);
        setASTNode(ctx, program);
    }

    @Override public void enterFunction_block(STParser.Function_blockContext ctx) {
    }

    @Override public void exitFunction_block(STParser.Function_blockContext ctx) {
        STListener.ProgramLocation l = new ProgramLocation(ctx);
        IrIdent name = new IrIdent(ctx.ID().getText(), l.line, l.col);
        IrVARBlockDecl varBlockVAR = null;
        IrVARBlockDecl varBlockVAR_INPUT = null;
        IrVARBlockDecl varBlockVAR_OUTPUT = null;
        IrVARBlockDecl varBlockVAR_INPUT_OUTPUT =null;
        IrVARBlockDecl varBlockVAR_TEMP= null;
        IrCodeBlock codeBlock = (IrCodeBlock) getASTNode(ctx.stat_list());

        for (ParseTree node :ctx.var_blocks){
            IrVARBlockDecl varBlockDecl = (IrVARBlockDecl) getASTNode(node);
            VarAccessTypeEnum type = varBlockDecl.getAccessType();
            switch (type){
                case VAR:
                    varBlockVAR = varBlockDecl;
                    break;
                case VAR_INPUT:
                    varBlockVAR_INPUT = varBlockDecl;
                    break;
                case VAR_OUTPUT:
                    varBlockVAR_OUTPUT = varBlockDecl;
                    break;
                case VAR_INPUT_OUTPUT:
                    varBlockVAR_INPUT_OUTPUT = varBlockDecl;
                    break;
                case RES_VAR_TEMP:
                    varBlockVAR_TEMP = varBlockDecl;
                    break;
                default:
                    System.err.println("there is no such var access type" + type.getTypeName());

            }
        }

        IrFunctionBlockDecl functionBlock = new IrFunctionBlockDecl(name, varBlockVAR, varBlockVAR_INPUT, varBlockVAR_OUTPUT,
                varBlockVAR_INPUT_OUTPUT, varBlockVAR_TEMP,codeBlock);
        setASTNode(ctx, functionBlock);
    }

    @Override public void enterFunction(STParser.FunctionContext ctx) { }

    @Override public void exitFunction(STParser.FunctionContext ctx) {
        STListener.ProgramLocation l = new ProgramLocation(ctx);
        IrType type = (IrType) getASTNode(ctx.type_rule());

        IrIdent name = new IrIdent(ctx.ID().getText(), l.line, l.col);
        IrVARBlockDecl varBlockVAR = null;
        IrVARBlockDecl varBlockVAR_INPUT = null;
        IrVARBlockDecl varBlockVAR_OUTPUT = null;
        IrVARBlockDecl varBlockVAR_INPUT_OUTPUT =null;
        IrVARBlockDecl varBlockVAR_TEMP= null;
        IrCodeBlock codeBlock = (IrCodeBlock) getASTNode(ctx.stat_list());

        for (ParseTree node :ctx.var_blocks){
            IrVARBlockDecl varBlockDecl = (IrVARBlockDecl) getASTNode(node);
            VarAccessTypeEnum accessType = varBlockDecl.getAccessType();
            switch (accessType){
                case VAR:
                    varBlockVAR = varBlockDecl;
                    break;
                case VAR_INPUT:
                    varBlockVAR_INPUT = varBlockDecl;
                    break;
                case VAR_OUTPUT:
                    varBlockVAR_OUTPUT = varBlockDecl;
                    break;
                case VAR_INPUT_OUTPUT:
                    varBlockVAR_INPUT_OUTPUT = varBlockDecl;
                    break;
                case RES_VAR_TEMP:
                    varBlockVAR_TEMP = varBlockDecl;
                    break;
                default:
                    System.err.println("there is no such var access type" + accessType.getTypeName());

            }
        }
        IrFunctionDecl function = new IrFunctionDecl(name,type,
                varBlockVAR, varBlockVAR_INPUT, varBlockVAR_OUTPUT,
                varBlockVAR_INPUT_OUTPUT, varBlockVAR_TEMP,codeBlock);
        setASTNode(ctx,function);

    }
    @Override public void enterStat_list(STParser.Stat_listContext ctx) {}

    @Override public void exitStat_list(STParser.Stat_listContext ctx) {
        STListener.ProgramLocation l = new ProgramLocation(ctx);

        ArrayList<IrStmt> stmts = new ArrayList<>();
        for(ParseTree node :ctx.children){
            Ir AstNode = ASTNodes.get(node);
            stmts.add((IrStmt) AstNode);
        }
        IrCodeBlock codeBlock = new IrCodeBlock(l.line,l.col,stmts);
        setASTNode(ctx, codeBlock);
    }

    @Override public void enterStat(STParser.StatContext ctx) { }

    @Override public void exitStat(STParser.StatContext ctx) {
        Ir pou = getASTNode(ctx.getChild(0));
        setASTNode(ctx, pou);
    }

    @Override public void enterAssign_stat(STParser.Assign_statContext ctx) { }

    @Override public void exitAssign_stat(STParser.Assign_statContext ctx) {
        STListener.ProgramLocation l = new ProgramLocation(ctx);
        IrLocation location = (IrLocation) getASTNode(ctx.location());
        IrExpr expr = (IrExpr) getASTNode(ctx.expression());

        IrAssignStmtEq stmtEq = new IrAssignStmtEq(location, expr, l.line,l.col);
        setASTNode(ctx, stmtEq);

    }

    @Override public void enterIf_stat(STParser.If_statContext ctx) { }

    @Override public void exitIf_stat(STParser.If_statContext ctx) {
        IrCtrlFlowIf ctrlFlowIf = (IrCtrlFlowIf) getASTNode(ctx.if_stmt());
        setASTNode(ctx, ctrlFlowIf);

    }
    @Override public void enterIf_else_stat(STParser.If_else_statContext ctx) { }

    @Override public void exitIf_else_stat(STParser.If_else_statContext ctx) {
        IrCtrlFlowIf ctrlFlowIf = (IrCtrlFlowIf) getASTNode(ctx.if_stmt());
        IrCodeBlock elseStmt = (IrCodeBlock) getASTNode(ctx.else_stmt());
        IrCtrlFlowIfElse ctrlFlowIfElse = new IrCtrlFlowIfElse(ctrlFlowIf.getCondExpr(), ctrlFlowIf.getStmtBody(),elseStmt);
        setASTNode(ctx, ctrlFlowIfElse);
    }

    @Override public void enterIf_elsif_stat(STParser.If_elsif_statContext ctx) { }

    @Override public void exitIf_elsif_stat(STParser.If_elsif_statContext ctx) {
        IrCtrlFlowIf ctrlFlowIf = (IrCtrlFlowIf) getASTNode(ctx.if_stmt());
        ArrayList<IrCtrlFlowElsif> ctrlFlowElsifArrayList = new ArrayList<>();
        for ( ParseTree node :ctx.elsif_stmt()){
            IrCtrlFlowElsif AstNode = (IrCtrlFlowElsif) getASTNode(node);
            ctrlFlowElsifArrayList.add(AstNode);
        }
        IrCtrlFlowIfElsif ctrlFlowElsif = new IrCtrlFlowIfElsif(ctrlFlowIf.getCondExpr(), ctrlFlowIf.getStmtBody(),ctrlFlowElsifArrayList);
        setASTNode(ctx,ctrlFlowElsif);
    }

    @Override public void enterIf_elsif_else_stat(STParser.If_elsif_else_statContext ctx) { }

    @Override public void exitIf_elsif_else_stat(STParser.If_elsif_else_statContext ctx) {
        IrCtrlFlowIf ctrlFlowIf = (IrCtrlFlowIf) getASTNode(ctx.if_stmt());
        ArrayList<IrCtrlFlowElsif> ctrlFlowElsifArrayList = new ArrayList<>();
        for ( ParseTree node :ctx.elsif_stmt()){
            IrCtrlFlowElsif AstNode = (IrCtrlFlowElsif) getASTNode(node);
            ctrlFlowElsifArrayList.add(AstNode);
        }
        IrCodeBlock elseStmt = (IrCodeBlock) getASTNode(ctx.else_stmt());

        IrCtrlFlowIfElsifElse ctrlFlowIfElsifElse = new IrCtrlFlowIfElsifElse(ctrlFlowIf.getCondExpr(),ctrlFlowIf.getStmtBody(),
                ctrlFlowElsifArrayList, elseStmt);
        setASTNode(ctx,ctrlFlowIfElsifElse);
    }

    @Override public void enterIf_stmt(STParser.If_stmtContext ctx) { }

    @Override public void exitIf_stmt(STParser.If_stmtContext ctx) {
        IrExpr expr = (IrExpr) getASTNode(ctx.expression());
        IrCodeBlock codeBlock = (IrCodeBlock) getASTNode(ctx.stat_list());
        IrCtrlFlowIf ctrlFlowIf = new IrCtrlFlowIf(expr, codeBlock);
        setASTNode(ctx, ctrlFlowIf);
    }

    @Override public void enterElsif_stmt(STParser.Elsif_stmtContext ctx) { }

    @Override public void exitElsif_stmt(STParser.Elsif_stmtContext ctx) {
        IrExpr expr = (IrExpr) getASTNode(ctx.expression());
        IrCodeBlock codeBlock = (IrCodeBlock) getASTNode(ctx.stat_list());
        IrCtrlFlowElsif ctrlFlowElsif = new IrCtrlFlowElsif(expr, codeBlock);
        setASTNode(ctx, ctrlFlowElsif);
    }

    @Override public void enterElse_stmt(STParser.Else_stmtContext ctx) { }

    @Override public void exitElse_stmt(STParser.Else_stmtContext ctx) {
        IrCodeBlock codeBlock = (IrCodeBlock) getASTNode(ctx.stat_list());
        setASTNode(ctx, codeBlock);
    }

    @Override public void enterFor_stat(STParser.For_statContext ctx) { }

    @Override public void exitFor_stat(STParser.For_statContext ctx) {
        IrIdent ident = new IrIdent(ctx.ID().getText(), ctx.control_variable.getLine(), ctx.control_variable.getCharPositionInLine());
        IrLocationVar locationVar = new IrLocationVar(ident);
        IrCtrlFlowForRange ctrlFlowForRange = (IrCtrlFlowForRange) getASTNode(ctx.for_range());
        IrCodeBlock codeBlock = (IrCodeBlock) getASTNode(ctx.stat_list());

        IrCtrlFlowFor ctrlFlowFor = new IrCtrlFlowFor(locationVar, ctrlFlowForRange,codeBlock);
        setASTNode(ctx, ctrlFlowFor);
    }
    @Override public void enterFor_range(STParser.For_rangeContext ctx) { }

    @Override public void exitFor_range(STParser.For_rangeContext ctx) {
        IrExpr low = (IrExpr) getASTNode(ctx.expression(0));
        IrExpr high = (IrExpr) getASTNode(ctx.expression(1));
        myPrint.LevelOne.print("=====");
        Integer step = null;
        if (ctx.step != null){
            step = Integer.valueOf(ctx.step.getText());
        }

        myPrint.LevelOne.print("=====");
        IrCtrlFlowForRange range = new IrCtrlFlowForRange(low, high, step);
        setASTNode(ctx, range);
    }


    @Override public void enterWhile_stat(STParser.While_statContext ctx) { }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
    @Override public void exitWhile_stat(STParser.While_statContext ctx) { }

    @Override public void enterInvoc_stat(STParser.Invoc_statContext ctx) { }

    @Override public void exitInvoc_stat(STParser.Invoc_statContext ctx) {
        STListener.ProgramLocation l = new ProgramLocation(ctx);
        IrIdent fbName = new IrIdent(ctx.ID().getText(), l.line, l.col);
        ArrayList<IrArg> argArrayList = new ArrayList<>();
        for (ParseTree node : ctx.param_assignment()){
            argArrayList.add((IrArg) getASTNode(node));
        }
        setASTNode(ctx, new IrFunctionCallExpr(fbName, argArrayList));

    }

    @Override public void enterExternArg(STParser.ExternArgContext ctx) { }

    @Override public void exitExternArg(STParser.ExternArgContext ctx) {
        STListener.ProgramLocation l = new ProgramLocation(ctx);
        setASTNode(ctx, new IrArgAssign( getASTNode(ctx.expression()) , l.line, l.col ) );
    }

    @Override public void enterAssignParam(STParser.AssignParamContext ctx) { }

    @Override public void exitAssignParam(STParser.AssignParamContext ctx) {
        STListener.ProgramLocation l = new ProgramLocation(ctx);
        IrIdent argName = new IrIdent(ctx.ID().getText(), l.line, l.col);
        IrExpr value = (IrExpr) getASTNode(ctx.expression());
        IrArgInputAssign argInputAssign = new IrArgInputAssign(value,argName);
        setASTNode(ctx, argInputAssign);

    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
    @Override public void enterAssignOutput(STParser.AssignOutputContext ctx) { }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
    @Override public void exitAssignOutput(STParser.AssignOutputContext ctx) {
        STListener.ProgramLocation l = new ProgramLocation(ctx);
        IrIdent fbOutput = new IrIdent(ctx.ID(0).getText(), l.line, l.col); //TODO: 这里可以尝试一下 l.line, l.col 和 ctx.ID(1)
        TerminalNode node = ctx.ID(1);
        IrIdent acceptLocation = new IrIdent(node.getText(), node.getSymbol().getLine(), node.getSymbol().getCharPositionInLine() );
    }

    @Override public void enterNotExpr(STParser.NotExprContext ctx) { }

    @Override public void exitNotExpr(STParser.NotExprContext ctx) {
        IrOperUnaryNot operUnaryNot = new IrOperUnaryNot((IrExpr) getASTNode(ctx.expression()));
        setASTNode(ctx, operUnaryNot);
    }
    @Override public void enterNegateExpr(STParser.NegateExprContext ctx) { }

    @Override public void exitNegateExpr(STParser.NegateExprContext ctx) {
        IrOperUnaryNeg operUnaryNeg = new IrOperUnaryNeg((IrExpr) getASTNode(ctx.expression()));
        setASTNode(ctx, operUnaryNeg);
    }

    @Override public void enterArithExpr(STParser.ArithExprContext ctx) { }

    @Override public void exitArithExpr(STParser.ArithExprContext ctx) {
        IrExpr left = (IrExpr) getASTNode(ctx.expression(0));
        IrExpr right = (IrExpr) getASTNode(ctx.expression(1));
        IrOperBinaryArith operBinaryArith = null;
        myPrint.levelTwo.print(ctx.op.getText());
        String op = ctx.op.getText();
        OperKeyWordEnum type = OperKeyWordEnum.fromOperTpye(op);
        if (type == null){
            System.err.println("There is no such operation: " + op);
        }
        else {
            switch (type){
                case ADD_OP:
                    operBinaryArith = new IrOperBinaryArith(OperKeyWordEnum.ADD_OP,left,right );
                    break;
                case SUB_OP:
                    operBinaryArith = new IrOperBinaryArith(OperKeyWordEnum.SUB_OP,left,right );
                    break;
                case MUL_OP:
                    operBinaryArith = new IrOperBinaryArith(OperKeyWordEnum.MUL_OP,left,right );
                    break;
                case DIV_OP:
                    operBinaryArith = new IrOperBinaryArith(OperKeyWordEnum.DIV_OP,left,right );
                    break;
                case MOD_OP:
                    operBinaryArith = new IrOperBinaryArith(OperKeyWordEnum.MOD_OP,left,right );
                    break;
                case POWER_OP:
                    operBinaryArith = new IrOperBinaryArith(OperKeyWordEnum.POWER_OP,left,right );
                    break;
                default:

            }
            setASTNode(ctx, operBinaryArith);
        }

    }

    @Override public void enterComparison(STParser.ComparisonContext ctx) { }

    @Override public void exitComparison(STParser.ComparisonContext ctx) {
        IrExpr left = (IrExpr) getASTNode(ctx.expression(0));
        IrExpr right = (IrExpr) getASTNode(ctx.expression(1));
        IrOperBinaryCond operBinaryCond = null;
        myPrint.levelTwo.print(ctx.op.getText());
        String op = ctx.op.getText();
        OperKeyWordEnum type = OperKeyWordEnum.fromOperTpye(op);
        if (type == null){
            System.err.println("There is no such operation: " + op);
        }
        else {
            switch (type){
                case LT_OP:
                    operBinaryCond = new IrOperBinaryCond(OperKeyWordEnum.LT_OP,left,right );
                    break;
                case GT_OP:
                    operBinaryCond = new IrOperBinaryCond(OperKeyWordEnum.GT_OP,left,right );
                    break;
                case LEQ_OP:
                    operBinaryCond = new IrOperBinaryCond(OperKeyWordEnum.LEQ_OP,left,right );
                    break;
                case GEQ_OP:
                    operBinaryCond = new IrOperBinaryCond(OperKeyWordEnum.GEQ_OP,left,right );
                    break;
                case EQ_OP:
                    operBinaryCond = new IrOperBinaryCond(OperKeyWordEnum.EQ_OP,left,right );
                    break;
                case NEQ_OP:
                    operBinaryCond = new IrOperBinaryCond(OperKeyWordEnum.NEQ_OP,left,right );
                    break;
                default:
                    break;
            }
            setASTNode(ctx, operBinaryCond);
        }

    }


    @Override public void enterLogic(STParser.LogicContext ctx) { }

    @Override public void exitLogic(STParser.LogicContext ctx) {
        IrExpr left = (IrExpr) getASTNode(ctx.expression(0));
        IrExpr right = (IrExpr) getASTNode(ctx.expression(1));
        IrOperBinaryCond operBinaryCond = null;
        myPrint.levelTwo.print(ctx.op.getText());
        String op = ctx.op.getText();
        OperKeyWordEnum type = OperKeyWordEnum.fromOperTpye(op);
        if (type == null){
            System.err.println("There is no such operation: " + op);
        }
        else {
            switch (type){
                case AND_OP:
                case AND_S_OP:
                    operBinaryCond = new IrOperBinaryCond(OperKeyWordEnum.AND_OP,left,right );
                    break;
                case OR_OP:
                    operBinaryCond = new IrOperBinaryCond(OperKeyWordEnum.OR_OP,left,right );
                    break;
                case XOR_OP:
                    operBinaryCond = new IrOperBinaryCond(OperKeyWordEnum.XOR_OP,left,right );
                    break;
                default:
                    break;
            }
            setASTNode(ctx, operBinaryCond);
        }

    }




    @Override public void enterParenExper(STParser.ParenExperContext ctx) { }

    @Override public void exitParenExper(STParser.ParenExperContext ctx) {
        setASTNode(ctx, getASTNode(ctx.expression()));
    }



    @Override public void enterPrimaryExpr(STParser.PrimaryExprContext ctx) {
        setASTNode(ctx, getASTNode(ctx.primary_expression()));
    }

    @Override public void exitPrimaryExpr(STParser.PrimaryExprContext ctx) {

    }

    @Override public void enterPrimary_expression(STParser.Primary_expressionContext ctx) { }

    @Override public void exitPrimary_expression(STParser.Primary_expressionContext ctx) {
//        System.err.println(        ctx.getChild(0).getText());

        setASTNode(ctx,getASTNode(ctx.getChild(0)));
    }

    @Override public void enterVarLocation(STParser.VarLocationContext ctx) { }

    @Override public void exitVarLocation(STParser.VarLocationContext ctx) {
        STListener.ProgramLocation l = new ProgramLocation(ctx);
        IrIdent irIdent = new IrIdent(ctx.ID().getText(),l.line,l.col);
        IrLocationVar locationVar = new IrLocationVar(irIdent);
        setASTNode(ctx, locationVar);
    }

    @Override public void enterArrayLocation(STParser.ArrayLocationContext ctx) {    }

    @Override public void exitArrayLocation(STParser.ArrayLocationContext ctx) {
        STListener.ProgramLocation l = new ProgramLocation(ctx);
        IrIdent irIdent = new IrIdent(ctx.ID().getText(), l.line, l.col);
        IrExpr expr = (IrExpr) getASTNode(ctx.expression());
        IrLocationArray locationArray = new IrLocationArray(irIdent, expr);
        setASTNode(ctx, locationArray);

    }

    @Override public void enterFbLcation(STParser.FbLcationContext ctx) { }

    @Override public void exitFbLcation(STParser.FbLcationContext ctx) {
        STListener.ProgramLocation l = new ProgramLocation(ctx);
        ArrayList<String> strings = new ArrayList<>();
        String name = "";
        for (ParseTree node : ctx.ID()){
            name += node.getText();
        }
        IrIdent irIdent = new IrIdent(name,l.line, l.col);

        setASTNode(ctx,new IrFbStLocation(irIdent));
    }

    @Override public void enterVar_block(STParser.Var_blockContext ctx) { }

    @Override public void exitVar_block(STParser.Var_blockContext ctx) {


        for (ParseTree node : ctx.variable_declaration()){
            IrVarDecl varDecl = (IrVarDecl) getASTNode(node);
        }
        VarAccessTypeEnum type = VarAccessTypeEnum.fromVarAccessType(ctx.var_acc_type.getText());
        if (type == null){
            System.err.println("There is no such operation: " + ctx.getText());
        }
        else {

        }

    }



    @Override public void enterArrayType(STParser.ArrayTypeContext ctx) { }

    @Override public void exitArrayType(STParser.ArrayTypeContext ctx) { }

    @Override public void enterArray_type(STParser.Array_typeContext ctx) { }

    @Override public void exitArray_type(STParser.Array_typeContext ctx) { }


    @Override public void enterRange(STParser.RangeContext ctx) { }

    @Override public void exitRange(STParser.RangeContext ctx) {
        myPrint.LevelOne.print(ctx.lbound.getText());
        myPrint.LevelOne.print(ctx.ubound.getText());

    }



    @Override public void enterEveryRule(ParserRuleContext ctx) { }
    @Override public void exitEveryRule(ParserRuleContext ctx) { }
    @Override public void visitTerminal(TerminalNode node) { }
    @Override public void visitErrorNode(ErrorNode node) { }

    public void setASTNode(ParseTree parseTreeNode, Ir ASTNode){this.ASTNodes.put(parseTreeNode, ASTNode);}
    public Ir getASTNode(ParseTree parseTreeNode){ return this.ASTNodes.get(parseTreeNode);}

    static class ProgramLocation {
        public final int line;
        public final int col;

        public ProgramLocation(ParserRuleContext ctx) {
            this.line = ctx.getStart().getLine();
            this.col = ctx.getStart().getCharPositionInLine();
        }
    }
}
