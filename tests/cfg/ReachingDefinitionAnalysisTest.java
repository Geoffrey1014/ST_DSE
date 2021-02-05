package cfg;

import helper.LlBuilder;
import helper.LlSymbolTable;
import ll.assignStmt.LlAssignStmtBinaryOp;
import ll.assignStmt.LlAssignStmtRegular;
import ll.jump.LlJumpConditional;
import ll.jump.LlJumpUnconditional;
import ll.literal.LlLiteralInt;
import ll.location.LlLocationVar;
import org.junit.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ReachingDefinitionAnalysisTest {
    LlBuilder llBuilder;

    @Test
    public void testPrintDefsReachINAndOUT(){
        createLlBuilder();
        CFG cfg = new CFG(this.llBuilder, new LlSymbolTable("test"));
        genGraphViz("1",cfg,"tests/");
    }
    public static void genGraphViz(String cfgCounter, CFG cfg,String outPutDir )  {
        String graphVizFilename = outPutDir + "Graph_"+cfgCounter+".dot";
        File writename = new File(graphVizFilename); // 相对路径，如果没有则要建立一个新的output。txt文件
        try{
            writename.createNewFile();

            BufferedWriter out = new BufferedWriter(new FileWriter(writename));
            out.write(cfg.toGraphviz()); // \r\n即为换行
            out.flush(); // 把缓存区内容压入文件
            out.close(); // 最后记得关闭文件
            Runtime.getRuntime().exec("dot"+" "+graphVizFilename+" -Tpdf"+" -o"+" "+outPutDir+"Graph_"+cfgCounter+".pdf").waitFor();
        }
        catch (IOException | InterruptedException e) {
            System.err.println("There was an error:\n" + e);
        }


        System.out.println("to Graphviz finish!");
    }

    public void createLlBuilder(){
        this.llBuilder = new LlBuilder("test");
        llBuilder.appendStatement("Body",createLlAssignStmtRegular("s", 0) );
        llBuilder.appendStatement(createLlAssignStmtRegular("a", 4));
        llBuilder.appendStatement(createLlAssignStmtRegular("i", 0));
        llBuilder.appendStatement(createLlAssignStmtBinaryOp("t1", "k", "==", "0" ));
        llBuilder.appendStatement(createLlJumpConditional("IF", new LlLocationVar("t1")));
        llBuilder.appendStatement(createLlJumpUnconditional("ELSE"));

        llBuilder.appendStatement("IF", createLlAssignStmtRegular("b", 1));
        llBuilder.appendStatement(createLlJumpUnconditional("END_IF"));
        llBuilder.appendStatement("ELSE",createLlAssignStmtRegular("b", 2));

        llBuilder.appendStatement("END_IF", createLlAssignStmtBinaryOp("t2","i","<", "n"));
        llBuilder.appendStatement(createLlJumpConditional("WHILE", new LlLocationVar("t2")));
        llBuilder.appendStatement(createLlJumpUnconditional("END_WHILE"));


        llBuilder.appendStatement("WHILE", createLlAssignStmtBinaryOp("t3", "a","*","b"));
        llBuilder.appendStatement(createLlAssignStmtBinaryOp("s","s","+","t3"));
        llBuilder.appendStatement(createLlAssignStmtBinaryOp("i", "i","+", 1));
        llBuilder.appendStatement(createLlJumpUnconditional("END_IF"));

        llBuilder.appendStatement("END_WHILE",createLlAssignStmtRegular("s", 100) );


    }

    public LlAssignStmtRegular createLlAssignStmtRegular(String loc, Integer v){
        return new LlAssignStmtRegular(new LlLocationVar(loc),new LlLiteralInt(v));
    }
    public LlAssignStmtBinaryOp createLlAssignStmtBinaryOp(String loc, String op1, String operation,String op2){
        return new LlAssignStmtBinaryOp(new LlLocationVar(loc), new LlLocationVar(op1),operation, new LlLocationVar(op2) );
    }
    public LlAssignStmtBinaryOp createLlAssignStmtBinaryOp(String loc, String op1, String operation,Integer op2){
        return new LlAssignStmtBinaryOp(new LlLocationVar(loc), new LlLocationVar(op1),operation, new LlLiteralInt(op2) );
    }
    public LlJumpConditional createLlJumpConditional(String jumpLabel, LlLocationVar conditon){
        return new LlJumpConditional(jumpLabel, conditon);
    }
    public LlJumpUnconditional createLlJumpUnconditional(String jumpLabel){
        return new LlJumpUnconditional(jumpLabel);
    }

}
