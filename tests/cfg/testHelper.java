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
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class testHelper {


    public HashMap<String, HashSet<LlLocationVar>> createOutLivenessOfHIT(){
        HashMap<String, HashSet<LlLocationVar>> livenessOut = new HashMap<>();
        livenessOut.put("IF",new HashSet<>(Arrays.asList(a, j, u2, u3)));
        livenessOut.put("LOOP",new HashSet<>(Arrays.asList(a, j, u2, u3)));
        livenessOut.put("END_IF",new HashSet<>(Arrays.asList(a, i, j, u2, u3)));
        livenessOut.put("Body",new HashSet<>(Arrays.asList(a, i, j, u2, u3)));
        return livenessOut;
    }

    public HashMap<String, HashSet<LlLocationVar>> createOutLivenessOfMIT(){
        HashMap<String, HashSet<LlLocationVar>> livenessOut = new HashMap<>();
        livenessOut.put("IF",new HashSet<>(Arrays.asList(a, b, y)));
        livenessOut.put("END_IF",new HashSet<>(Arrays.asList(a, b,c)));
        livenessOut.put("Body",new HashSet<>(Arrays.asList(a, b, y, z, t)));
        return livenessOut;
    }

    public void createLlBuilderAndLlSymbolTableOfHIT(LlBuilder llBuilder, LlSymbolTable llSymbolTable){
        // HIT example (reaching definition, liveness)

        llSymbolTable.varInput.put(new LlLocationVar("i"), new LlLiteralInt(1));
        llSymbolTable.varNonInput.put(new LlLocationVar("a"), new LlLiteralInt(1));

        llBuilder.appendStatement("Body", new LlEmptyStmt());
        llBuilder.appendStatement(createLlAssignStmtBinaryOp(i,m,"-", 1));
        llBuilder.appendStatement(createLlAssignStmtRegular(j, n) );
        llBuilder.appendStatement(createLlAssignStmtRegular(a, u1) );

        llBuilder.appendStatement("LOOP", new LlEmptyStmt());
        llBuilder.appendStatement(createLlAssignStmtBinaryOp(i, i,"+", 1));
        llBuilder.appendStatement(createLlAssignStmtBinaryOp(j,j,"-", 1));
        llBuilder.appendStatement(createLlJumpConditional("IF", new LlLocationVar("j")));
        llBuilder.appendStatement(createLlJumpUnconditional("END_IF"));

        llBuilder.appendStatement("IF", new LlEmptyStmt());
        llBuilder.appendStatement(createLlAssignStmtRegular(a, u2) );

        llBuilder.appendStatement("END_IF", new LlEmptyStmt());
        llBuilder.appendStatement(createLlAssignStmtRegular(i, u3) );
        llBuilder.appendStatement(createLlJumpUnconditional("LOOP"));
    }
    public void createLlBuilderAndLlSymbolTable1(LlBuilder llBuilder, LlSymbolTable llSymbolTable){
        // MIT reaching definition example

        llSymbolTable.varInput.put(this.i, new LlLiteralInt(1));
        llSymbolTable.varNonInput.put(this.a, new LlLiteralInt(1));

        llBuilder.appendStatement("Body", new LlEmptyStmt());
        llBuilder.appendStatement(createLlAssignStmtRegular(s, 0));
        llBuilder.appendStatement(createLlAssignStmtRegular(a, 4));
        llBuilder.appendStatement(createLlAssignStmtRegular(i, 0));
        llBuilder.appendStatement(createLlAssignStmtBinaryOp(t1, k, "==", 0 ));
        llBuilder.appendStatement(createLlJumpConditional("IF", new LlLocationVar("t1")));
        llBuilder.appendStatement(createLlJumpUnconditional("ELSE"));

        llBuilder.appendStatement("IF", new LlEmptyStmt());
        llBuilder.appendStatement(createLlAssignStmtRegular(b, 1));
        llBuilder.appendStatement(createLlJumpUnconditional("END_IF"));
        llBuilder.appendStatement("ELSE", new LlEmptyStmt());
        llBuilder.appendStatement(createLlAssignStmtRegular(b, 2));

        llBuilder.appendStatement("END_IF", new LlEmptyStmt());
        llBuilder.appendStatement(createLlAssignStmtBinaryOp(t2,i,"<", n));
        llBuilder.appendStatement(createLlJumpConditional("WHILE", new LlLocationVar("t2")));
        llBuilder.appendStatement(createLlJumpUnconditional("END_WHILE"));

        llBuilder.appendStatement("WHILE", new LlEmptyStmt());
        llBuilder.appendStatement( createLlAssignStmtBinaryOp(t3, a,"*",b));
        llBuilder.appendStatement(createLlAssignStmtBinaryOp(s,s,"+",t3));
        llBuilder.appendStatement(createLlAssignStmtBinaryOp(i, i,"+", 1));
        llBuilder.appendStatement(createLlJumpUnconditional("END_IF"));
        llBuilder.appendStatement("END_WHILE", new LlEmptyStmt());
        llBuilder.appendStatement(createLlAssignStmtRegular(s, 100) );

    }

    public void createLlBuilderAndLlSymbolTableMITLiveness(LlBuilder llBuilder, LlSymbolTable llSymbolTable){
        //MIT liveness example

        llSymbolTable.varNonInput.put(a, new LlLiteralInt(1));
        llSymbolTable.varNonInput.put(b, new LlLiteralInt(1));
        llSymbolTable.varNonInput.put(c, new LlLiteralInt(1));

        llBuilder.appendStatement("Body", new LlEmptyStmt());
        llBuilder.appendStatement(createLlAssignStmtBinaryOp(a,x,"+",y));

        llBuilder.appendStatement(createLlAssignStmtRegular(t, a) );
        llBuilder.appendStatement(createLlAssignStmtBinaryOp(c,a,"+",x));

        llBuilder.appendStatement(createLlAssignStmtBinaryOp(t1, x, "==", 0 ));
        llBuilder.appendStatement(createLlJumpConditional("IF", new LlLocationVar("t1")));
        llBuilder.appendStatement(createLlJumpUnconditional("END_IF"));

        llBuilder.appendStatement("IF", new LlEmptyStmt());
        llBuilder.appendStatement(createLlAssignStmtBinaryOp(b,t,"+",z));
        llBuilder.appendStatement(createLlJumpUnconditional("END_IF"));

        llBuilder.appendStatement("END_IF", new LlEmptyStmt());
        llBuilder.appendStatement(createLlAssignStmtBinaryOp(c, y,"+", 1));

    }

    public static LlAssignStmtRegular createLlAssignStmtRegular(LlLocationVar loc, Integer v){
        return new LlAssignStmtRegular(loc,new LlLiteralInt(v));
    }
    public static LlAssignStmtRegular createLlAssignStmtRegular(LlLocationVar loc, LlLocationVar v){
        return new LlAssignStmtRegular(loc,v);
    }
    public static LlAssignStmtBinaryOp createLlAssignStmtBinaryOp(LlLocationVar loc, LlLocationVar op1, String operation, LlLocationVar op2){
        return new LlAssignStmtBinaryOp(loc, op1,operation, op2 );
    }
    public static LlAssignStmtBinaryOp createLlAssignStmtBinaryOp(LlLocationVar loc, LlLocationVar op1, String operation,Integer op2){
        return new LlAssignStmtBinaryOp(loc, op1,operation, new LlLiteralInt(op2) );
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
    LlLocationVar i = new LlLocationVar("i");
    LlLocationVar j = new LlLocationVar("j");
    LlLocationVar k = new LlLocationVar("k");
    LlLocationVar a = new LlLocationVar("a");
    LlLocationVar b = new LlLocationVar("b");
    LlLocationVar c = new LlLocationVar("c");
    LlLocationVar d = new LlLocationVar("d");
    LlLocationVar m = new LlLocationVar("m");
    LlLocationVar n = new LlLocationVar("n");
    LlLocationVar s = new LlLocationVar("s");
    LlLocationVar t = new LlLocationVar("t");
    LlLocationVar t1 = new LlLocationVar("t1");
    LlLocationVar t2 = new LlLocationVar("t2");
    LlLocationVar t3 = new LlLocationVar("t3");
    LlLocationVar x = new LlLocationVar("x");
    LlLocationVar y = new LlLocationVar("y");
    LlLocationVar z = new LlLocationVar("z");
    LlLocationVar u1 = new LlLocationVar("u1");
    LlLocationVar u2 = new LlLocationVar("u2");
    LlLocationVar u3 = new LlLocationVar("u3");
}
