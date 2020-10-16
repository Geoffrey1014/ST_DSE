import grammar.gen.STParser;
import grammar.gen.STParserBaseListener;
import ir.*;
import ir.POUDecl.IrFunctionBlockDecl;
import ir.POUDecl.IrFunctionDecl;
import ir.POUDecl.IrProgramDecl;
import ir.VARBlockDecl.IrVARBlockDecl;
import ir.VARBlockDecl.IrVARBlockDeclInput;
import ir.VARBlockDecl.IrVARBlockDeclOutput;
import ir.VARBlockDecl.IrVARBlockDeclVar;
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
        IrIdent programName = new IrIdent(ctx.ID().getText(), l.line, l.col);
//        System.out.println(ctx.name);
        System.err.println(ctx.ID().getSymbol());

        IrVARBlockDecl varBlockVAR = null;
        IrVARBlockDecl varBlockVAR_INPUT = null;
        IrVARBlockDecl varBlockVAR_OUTPUT = null;
        IrCodeBlock codeBlock = (IrCodeBlock) getASTNode(ctx.stat_list());

        for (ParseTree node :ctx.var_blocks){
            Ir AstNode = ASTNodes.get(node);

            if (AstNode instanceof IrVARBlockDeclInput){
                varBlockVAR = (IrVARBlockDecl) AstNode;
            }
            else if (AstNode instanceof IrVARBlockDeclOutput){
                varBlockVAR_INPUT = (IrVARBlockDecl) AstNode;
            }
            else if (AstNode instanceof IrVARBlockDeclVar){
                varBlockVAR_OUTPUT = (IrVARBlockDecl) AstNode;
            }
        }
        IrProgramDecl program = new IrProgramDecl(programName, varBlockVAR, varBlockVAR_INPUT, varBlockVAR_OUTPUT, codeBlock);
        setASTNode(ctx, program);
    }

    @Override public void enterFunction_block(STParser.Function_blockContext ctx) {
    }

    @Override public void exitFunction_block(STParser.Function_blockContext ctx) {
        STListener.ProgramLocation l = new ProgramLocation(ctx);
        IrIdent fuctionBlockName = new IrIdent(ctx.ID().getText(), l.line, l.col);

        IrCodeBlock codeBlock = (IrCodeBlock) getASTNode(ctx.stat_list());
        IrVARBlockDecl varBlockVAR = null;
        IrVARBlockDecl varBlockVAR_INPUT = null;
        IrVARBlockDecl varBlockVAR_OUTPUT = null;

        for (ParseTree node :ctx.var_blocks){
            Ir AstNode = ASTNodes.get(node);

            if (AstNode instanceof IrVARBlockDeclInput){
                varBlockVAR = (IrVARBlockDecl) AstNode;
            }
            else if (AstNode instanceof IrVARBlockDeclOutput){
                varBlockVAR_INPUT = (IrVARBlockDecl) AstNode;
            }
            else if (AstNode instanceof IrVARBlockDeclVar){
                 varBlockVAR_OUTPUT = (IrVARBlockDecl) AstNode;
            }
        }

        IrFunctionBlockDecl functionBlock = new IrFunctionBlockDecl(fuctionBlockName,
                varBlockVAR, varBlockVAR_INPUT, varBlockVAR_OUTPUT, codeBlock);
        setASTNode(ctx, functionBlock);
    }

    @Override public void enterFunction(STParser.FunctionContext ctx) { }

    @Override public void exitFunction(STParser.FunctionContext ctx) {
        STListener.ProgramLocation l = new ProgramLocation(ctx);
        IrIdent fuctionName = new IrIdent(ctx.ID().getText(), l.line, l.col);
        IrType type = (IrType) getASTNode(ctx.type_rule());

        IrCodeBlock codeBlock = (IrCodeBlock) getASTNode(ctx.stat_list());
        IrVARBlockDecl varBlockVAR = null;
        IrVARBlockDecl varBlockVAR_INPUT = null;
        IrVARBlockDecl varBlockVAR_OUTPUT = null;

        for (ParseTree node :ctx.var_blocks){
            Ir AstNode = ASTNodes.get(node);

            if (AstNode instanceof IrVARBlockDeclInput){
                varBlockVAR = (IrVARBlockDecl) AstNode;
            }
            else if (AstNode instanceof IrVARBlockDeclOutput){
                varBlockVAR_INPUT = (IrVARBlockDecl) AstNode;
            }
            else if (AstNode instanceof IrVARBlockDeclVar){
                varBlockVAR_OUTPUT = (IrVARBlockDecl) AstNode;
            }
        }
        IrFunctionDecl function = new IrFunctionDecl(fuctionName,type,varBlockVAR,varBlockVAR_INPUT, varBlockVAR_OUTPUT, codeBlock);
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






    @Override public void enterRange(STParser.RangeContext ctx) { }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
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
