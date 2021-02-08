package cfg;

import helper.LlBuilder;
import helper.LlSymbolTable;
import ll.LlEmptyStmt;
import ll.assignStmt.LlAssignStmtBinaryOp;
import ll.assignStmt.LlAssignStmtRegular;
import ll.jump.LlJumpConditional;
import ll.jump.LlJumpUnconditional;
import ll.literal.LlLiteralInt;
import ll.location.LlLocationVar;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class testHelper {
    public static void createLlBuilderAndLlSymbolTable2(LlBuilder llBuilder,LlSymbolTable llSymbolTable){
        // MIT reaching definition example

        llSymbolTable.varInput.put(new LlLocationVar("i"), new LlLiteralInt(1));
        llSymbolTable.varNonInput.put(new LlLocationVar("a"), new LlLiteralInt(1));

        llBuilder.appendStatement("Body", new LlEmptyStmt());
        llBuilder.appendStatement(createLlAssignStmtBinaryOp("i","m","-", 1));
        llBuilder.appendStatement(createLlAssignStmtRegular("j", "n") );
        llBuilder.appendStatement(createLlAssignStmtRegular("a", "u1") );

        llBuilder.appendStatement("LOOP", new LlEmptyStmt());
        llBuilder.appendStatement(createLlAssignStmtBinaryOp("i", "i","+", 1));
        llBuilder.appendStatement(createLlAssignStmtBinaryOp("j","j","-", 1));
        llBuilder.appendStatement(createLlJumpConditional("IF", new LlLocationVar("j")));
        llBuilder.appendStatement(createLlJumpUnconditional("END_IF"));

        llBuilder.appendStatement("IF", new LlEmptyStmt());
        llBuilder.appendStatement(createLlAssignStmtRegular("a", "u2") );

        llBuilder.appendStatement("END_IF", new LlEmptyStmt());
        llBuilder.appendStatement(createLlAssignStmtRegular("i", "u3") );
        llBuilder.appendStatement(createLlJumpUnconditional("LOOP"));


    }

    public static void createLlBuilderAndLlSymbolTable1(LlBuilder llBuilder,LlSymbolTable llSymbolTable){
        // HIT example

        llSymbolTable.varInput.put(new LlLocationVar("i"), new LlLiteralInt(1));
        llSymbolTable.varNonInput.put(new LlLocationVar("a"), new LlLiteralInt(1));

        llBuilder.appendStatement("Body", new LlEmptyStmt());
        llBuilder.appendStatement(createLlAssignStmtRegular("s", 0) );
        llBuilder.appendStatement(createLlAssignStmtRegular("a", 4));
        llBuilder.appendStatement(createLlAssignStmtRegular("i", 0));
        llBuilder.appendStatement(createLlAssignStmtBinaryOp("t1", "k", "==", "0" ));
        llBuilder.appendStatement(createLlJumpConditional("IF", new LlLocationVar("t1")));
        llBuilder.appendStatement(createLlJumpUnconditional("ELSE"));

        llBuilder.appendStatement("IF", new LlEmptyStmt());
        llBuilder.appendStatement(createLlAssignStmtRegular("b", 1));
        llBuilder.appendStatement(createLlJumpUnconditional("END_IF"));
        llBuilder.appendStatement("ELSE", new LlEmptyStmt());
        llBuilder.appendStatement(createLlAssignStmtRegular("b", 2));

        llBuilder.appendStatement("END_IF", new LlEmptyStmt());
        llBuilder.appendStatement(createLlAssignStmtBinaryOp("t2","i","<", "n"));
        llBuilder.appendStatement(createLlJumpConditional("WHILE", new LlLocationVar("t2")));
        llBuilder.appendStatement(createLlJumpUnconditional("END_WHILE"));

        llBuilder.appendStatement("WHILE", new LlEmptyStmt());
        llBuilder.appendStatement( createLlAssignStmtBinaryOp("t3", "a","*","b"));
        llBuilder.appendStatement(createLlAssignStmtBinaryOp("s","s","+","t3"));
        llBuilder.appendStatement(createLlAssignStmtBinaryOp("i", "i","+", 1));
        llBuilder.appendStatement(createLlJumpUnconditional("END_IF"));
        llBuilder.appendStatement("END_WHILE", new LlEmptyStmt());
        llBuilder.appendStatement(createLlAssignStmtRegular("s", 100) );

    }

    public static void createLlBuilderAndLlSymbolTable3(LlBuilder llBuilder,LlSymbolTable llSymbolTable){
        //MIT livness example

        llSymbolTable.varNonInput.put(new LlLocationVar("a"), new LlLiteralInt(1));
        llSymbolTable.varNonInput.put(new LlLocationVar("b"), new LlLiteralInt(1));
        llSymbolTable.varNonInput.put(new LlLocationVar("c"), new LlLiteralInt(1));

        llBuilder.appendStatement("Body", new LlEmptyStmt());
        llBuilder.appendStatement(createLlAssignStmtBinaryOp("a","x","+","y"));

        llBuilder.appendStatement(createLlAssignStmtRegular("t", "a") );
        llBuilder.appendStatement(createLlAssignStmtBinaryOp("c","a","+","x"));

        llBuilder.appendStatement(createLlAssignStmtBinaryOp("t1", "x", "==", 0 ));
        llBuilder.appendStatement(createLlJumpConditional("IF", new LlLocationVar("t1")));
        llBuilder.appendStatement(createLlJumpUnconditional("END_IF"));

        llBuilder.appendStatement("IF", new LlEmptyStmt());
        llBuilder.appendStatement(createLlAssignStmtBinaryOp("b","t","+","z"));
        llBuilder.appendStatement(createLlJumpUnconditional("END_IF"));

        llBuilder.appendStatement("END_IF", new LlEmptyStmt());
        llBuilder.appendStatement(createLlAssignStmtBinaryOp("c", "y","+", 1));

    }

    public static LlAssignStmtRegular createLlAssignStmtRegular(String loc, Integer v){
        return new LlAssignStmtRegular(new LlLocationVar(loc),new LlLiteralInt(v));
    }
    public static LlAssignStmtRegular createLlAssignStmtRegular(String loc, String v){
        return new LlAssignStmtRegular(new LlLocationVar(loc),new LlLocationVar(v));
    }
    public static LlAssignStmtBinaryOp createLlAssignStmtBinaryOp(String loc, String op1, String operation, String op2){
        return new LlAssignStmtBinaryOp(new LlLocationVar(loc), new LlLocationVar(op1),operation, new LlLocationVar(op2) );
    }
    public static LlAssignStmtBinaryOp createLlAssignStmtBinaryOp(String loc, String op1, String operation,Integer op2){
        return new LlAssignStmtBinaryOp(new LlLocationVar(loc), new LlLocationVar(op1),operation, new LlLiteralInt(op2) );
    }
    public static LlJumpConditional createLlJumpConditional(String jumpLabel, LlLocationVar conditon){
        return new LlJumpConditional(jumpLabel, conditon);
    }
    public static LlJumpUnconditional createLlJumpUnconditional(String jumpLabel){
        return new LlJumpUnconditional(jumpLabel);
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

}
