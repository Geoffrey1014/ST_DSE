import grammar.gen.STParser;
import grammar.gen.STParserBaseListener;
import ir.IrPousDecl;
import ir.POUDecl.IrFunctionBlockDecl;
import ir.POUDecl.IrFunctionDecl;
import ir.POUDecl.IrProgramDecl;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.ArrayList;

public class STListener extends STParserBaseListener {
    @Override public void exitPous(STParser.PousContext ctx) {
        STListener.ProgramLocation l = this.new ProgramLocation(ctx);

        ArrayList<IrProgramDecl> programDeclsArrayList = new ArrayList<>();
        ArrayList<IrFunctionBlockDecl> functionBlockDeclsArrayList = new ArrayList<>();
        ArrayList<IrFunctionDecl> functionDeclArrayList = new ArrayList<>();
        /**
         * 需要在其他监听的动作中完成 其他AST中间结构的构建
         * 可以用stack来传递ASTNode，或者可以将这些数据结构挂在具体语法树上
         * */

        IrPousDecl pous = new IrPousDecl(programDeclsArrayList, functionBlockDeclsArrayList, functionDeclArrayList, l.line,l.col);
    }

    class ProgramLocation {
        public final int line;
        public final int col;

        public ProgramLocation(ParserRuleContext ctx) {
            this.line = ctx.getStart().getLine();
            this.col = ctx.getStart().getCharPositionInLine();
        }
    }
}
