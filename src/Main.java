import cfg.*;
import grammar.gen.STParser;
import grammar.gen.STScanner;
import helper.LlBuilder;
import helper.LlBuildersList;
import helper.LlSymbolTable;
import ll.location.LlLocation;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import simulation.Simulator;
import tools.MyPrint;
import tools.Tuple2;
import visitor.DefPhaseVisitor;
import visitor.SemanticCheckVisitor;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Main {
    public static MyPrint myprint = new MyPrint(3);

    public static void walkTree(String filePath) {
        String[] pathSegments = filePath.split("/");
        String prefix = pathSegments[0] +"/"+ pathSegments[1]+"/";
        String inputFileName = pathSegments[3];

        String inputFile = prefix + pathSegments[2] + "/" + inputFileName;
        String outPutDir = prefix +   inputFileName.split("\\.")[0]+  "_output/" ;
        File dir = new File(outPutDir);
        if (!dir.exists()) {// 判断目录是否存在
            dir.mkdir();
        }

        try {
            CharStream stream = CharStreams.fromFileName(inputFile);
            STScanner lexer = new STScanner(stream);
            TokenStream tokens = new CommonTokenStream(lexer);
            STParser parser = new STParser(tokens);
            ParseTree tree = parser.pous();
//            Trees.inspect(tree, parser);

            ParseTreeWalker walker = new ParseTreeWalker();
            STListener listener = new STListener();
            walker.walk(listener, tree);

            DefPhaseVisitor defPhaseVisitor = new DefPhaseVisitor();
            listener.pous.accept(defPhaseVisitor);
//            System.err.println("DefPhase check error message:");
//            System.err.println(defPhaseVisitor.errorMessage);

            SemanticCheckVisitor semanticCheckVisitor = new SemanticCheckVisitor(defPhaseVisitor.symTable);
            listener.pous.accept(semanticCheckVisitor);

            System.out.println("\n semantic check error message:");
            System.err.println(semanticCheckVisitor.errorMessage);

            System.out.println("\n pretty print:\n");
            writeFile(listener.pous.prettyPrint(""),outPutDir+ "prettyPrint" + ".txt");

            int cfgCounter = 0;
            System.out.println("\n low level IR\n");

            LlBuildersList llBuilderList = listener.pous.getBuilderList();
            ArrayList<LlSymbolTable> llSymbolTables = llBuilderList.getSymbolTables();
            ArrayList<LlBuilder> llBuilders = llBuilderList.getBuilders();
            Iterator<LlSymbolTable> llSymbolTableIterator = llSymbolTables.iterator();

            // control of updating pictures
            Boolean updateFig = true;

            for (LlBuilder llBuilder : llBuilders) {
                CFG cfg = new CFG(llBuilder, llSymbolTableIterator.next(), true);
                System.out.println("_______________________ ");
                writeFile(cfg.toString(), outPutDir + "origin_" + cfgCounter + ".txt");
                if (updateFig) genGraphViz("origin_" + cfgCounter, cfg, outPutDir);


                HashSet<LlLocation> globalVArs = new HashSet<>();
                GlobalCSE.performGlobalCommonSubexpressionEliminationOnCFG(cfg, globalVArs);
                writeFile(cfg.toString(), outPutDir + "new_" + "CSE_" + cfgCounter + ".txt");
                System.out.println("\nafterCSE---------------------\n");
                if (updateFig) genGraphViz("CSE_" + cfgCounter, cfg, outPutDir);


                GlobalCP.performGlobalCP(cfg, globalVArs);
                writeFile(cfg.toString(), outPutDir + "new_" + "CP_" + cfgCounter + ".txt");
                System.out.println("\nafterCP---------------------\n");
                if (updateFig) genGraphViz("CP_" + cfgCounter, cfg, outPutDir);

                GlobalDCE globalDCE = new GlobalDCE(cfg);
                globalDCE.performGlobalDeadCodeElimination();
                System.out.println("\nafterDSE---------------------\n");
                if (updateFig) genGraphViz("DSE_" + cfgCounter, cfg, outPutDir);
                writeFile(cfg.toString(), outPutDir + "new_" + "DSE_" + cfgCounter + ".txt");

                // calculateDefinitionUseChain
                NewLivenessAnalysis liveAnalysis = new NewLivenessAnalysis(cfg);
                liveAnalysis.livenessAnalysis();
                liveAnalysis.calculateDefinitionUseChain();
                liveAnalysis.writeDefUseChainToFile(outPutDir + "DUChain_0" + cfgCounter + ".txt");

                // Dominator writeFile
                HashMap<BasicBlock, HashSet<BasicBlock>> dominatorsMap = LoopAnalysis.getStrictDominatorsMap(cfg);
                writeFile(dominatorMapToString(cfg,dominatorsMap), outPutDir + "Dominator" + cfgCounter + ".txt");

                //calCutNodes
                HashMap<VarAndStmt,HashSet<VarAndStmt>> definitionUseChain = liveAnalysis.definitionUseChain;
                HashMap<VarAndStmt, HashSet<Tuple2<VarAndStmt,HashSet<BasicBlock>>>> duChianWithDmt = new HashMap<>();
                calCutNodes(dominatorsMap,definitionUseChain,duChianWithDmt);

                System.out.println("done!");

//                NewLivenessAnalysis livenessAnalysis = new NewLivenessAnalysis(cfg);
//                livenessAnalysis.livenessAnalysis2();
//                livenessAnalysis.calculateDefinitionUseChain();
//                livenessAnalysis.writeDefUseChainToFile(outPutDir + "DUChain" + cfgCounter + ".txt");
                System.out.println("simulator.execute();------------");
                Simulator simulator = new Simulator(cfg,duChianWithDmt);
                simulator.execute();

                System.out.println("CF------------------------");
                GlobalCF.performGlobalCodeFolding(cfg);
                writeFile(cfg.toString(), outPutDir + "new_" + "CF_" + cfgCounter + ".txt");

                System.out.println("URE------------------------");
                GlobalURE.performGlobalURE(cfg);
                writeFile(cfg.toString(), outPutDir + "new_" + "URE_" + cfgCounter + ".txt");

                System.out.println("AS------------------------");
                AlgebraicSimplifications.performAlgebraicSimplifications(cfg);
                writeFile(cfg.toString(), outPutDir + "new_" + "AS_" + cfgCounter + ".txt");

                cfgCounter++;
            }


        } catch (IOException e) {
            System.err.println("There was an error:\n" + e);
        }
    }

    public static void main(String[] args) {
        MyPrint.levelZero.print(System.getProperty("user.home"));
        String inputDir = "tests_programs/dataflow/input/";		//要遍历的路径
        inputDir = "tests_programs/paper1_tests/input/";
        String file = "counter.txt";
        walkTree(inputDir+file);
        // 判断结果是否正确（感觉这个比较困难，看看别人是怎么做都）

        // 打开一个文件夹，把所有文件都执行一边，把结果输出
//        runDirFiles(inputDir);

    }
    public static void runDirFiles(String path){

        File file = new File(path);		//获取其file对象
        File[] fs = file.listFiles();	//遍历path下的文件和目录，放在File数组中
        for(File f:fs) {                    //遍历File[]数组
            if (!f.isDirectory())        //若非目录(即文件)，则打印
                System.out.println(Arrays.toString(f.toString().split("/")));
                walkTree(f.toString());

        }
    }

    public static void writeFile(String content, String pathName) {

        try {

            File file = new File(pathName);
            file.createNewFile();
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(content);
            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void calCutNodes(HashMap<BasicBlock, HashSet<BasicBlock>> dominatorsMap,
                                   HashMap<VarAndStmt,HashSet<VarAndStmt>>definitionUseChain,
                                   HashMap<VarAndStmt, HashSet<Tuple2<VarAndStmt,HashSet<BasicBlock>>>> duChianWithDmt){
        for (VarAndStmt def : definitionUseChain.keySet()) {
            duChianWithDmt.put(def, new HashSet<>());
            for (VarAndStmt use : definitionUseChain.get(def)) {
                HashSet<BasicBlock> defDmt = new HashSet<>(dominatorsMap.get(def.getBlock()));
                HashSet<BasicBlock> useDmt = new HashSet<>(dominatorsMap.get(use.getBlock()));
                useDmt.removeAll(defDmt);
                duChianWithDmt.get(def).add(new Tuple2<>(use, useDmt));
            }
        }
    }

    public static String dominatorMapToString(CFG cfg,HashMap<BasicBlock, HashSet<BasicBlock>> dominatorsMap){
        System.out.println("dominatorsMap writing------------");
        StringBuilder stringBuilder = new StringBuilder();
        for (BasicBlock bb : cfg.getBasicBlocks()) {
            stringBuilder.append("Node: "+bb.name).append(" :\n");
            for (BasicBlock b : dominatorsMap.get(bb)) {
                stringBuilder.append(b.name).append(", ");
            }
            stringBuilder.append("\n\n");

        }
        return stringBuilder.toString();
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
