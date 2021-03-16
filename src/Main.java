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
import parser.STListener;
import simulation.BranchTest;
import tools.MyPrint;
import tools.Tuple2;
import visitor.DefPhaseVisitor;
import visitor.SemanticCheckVisitor;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public class Main {
    public static MyPrint myprint = new MyPrint(3);
    // control of updating pictures
    public static Boolean updateFig = false;

    public static void walkTree(String filePath) {
        String[] pathSegments = filePath.split("/");
        String prefix = pathSegments[0] + "/" + pathSegments[1] + "/";
        String inputFileName = pathSegments[3];

        String inputFile = prefix + pathSegments[2] + "/" + inputFileName;
        String inputFileNamePrefix = inputFileName.split("\\.")[0];
        String outPutDir = prefix + inputFileNamePrefix + "_output/";
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
            System.out.println("semantic check error message:");
            System.err.println(semanticCheckVisitor.errorMessage);

            if (updateFig) {
                System.out.println("pretty print:");
                writeFile(listener.pous.prettyPrint(""), outPutDir + "prettyPrint" + ".txt");
                System.out.println("low level IR");
            }

            int cfgCounter = 0;

            LlBuildersList llBuilderList = listener.pous.getBuilderList();
            ArrayList<LlSymbolTable> llSymbolTables = llBuilderList.getSymbolTables();
            ArrayList<LlBuilder> llBuilders = llBuilderList.getBuilders();
            Iterator<LlSymbolTable> llSymbolTableIterator = llSymbolTables.iterator();

            for (LlBuilder llBuilder : llBuilders) {
                CFG cfg = new CFG(llBuilder, llSymbolTableIterator.next(), true);
                if (updateFig) {
                    System.out.println("_______________________ ");
                    writeFile(cfg.toString(), outPutDir + "origin_" + cfgCounter + ".txt");
                    genGraphViz(inputFileNamePrefix+ "_origin_" + cfgCounter, cfg, outPutDir);
                }


                HashSet<LlLocation> globalVArs = new HashSet<>();
                GlobalCSE.performGlobalCommonSubexpressionEliminationOnCFG(cfg, globalVArs);

                if (updateFig) {
                    System.out.println("afterCSE---------------------");
                    writeFile(cfg.toString(), outPutDir + "new_" + "CSE_" + cfgCounter + ".txt");
                    genGraphViz(inputFileNamePrefix+"_CSE_" + cfgCounter, cfg, outPutDir);
                }


                GlobalCP.performGlobalCP(cfg, globalVArs);
                if (updateFig) {
                    System.out.println("afterCP-----------------------");
                    writeFile(cfg.toString(), outPutDir + "new_" + "CP_" + cfgCounter + ".txt");
                    genGraphViz(inputFileNamePrefix+"_CP_" + cfgCounter, cfg, outPutDir);
                }

                // Dominator
                LoopAnalysis loopAnalysis = new LoopAnalysis(cfg);
                HashMap<BasicBlock, HashSet<BasicBlock>> dominatorsMap = loopAnalysis.getDominatorsMap();

                // DominatorTree
                HashMap<BasicBlock, HashSet<BasicBlock>> dominatingTreeMap = loopAnalysis.getDominatingTreeMap();
                loopAnalysis.createDominatorTree(dominatingTreeMap);
                if (updateFig) {
                    writeFile(dominatorMapToString(cfg, dominatorsMap), outPutDir + inputFileNamePrefix+"_Dominator" + cfgCounter + ".txt");
                    writeFile(dominatorMapToString(cfg, dominatingTreeMap), outPutDir + inputFileNamePrefix+"_DominatorTree" + cfgCounter + ".txt");
                    genGraphViz(inputFileNamePrefix+"_DomTree_" + cfgCounter, loopAnalysis, outPutDir);
                }

                GlobalDCE globalDCE = new GlobalDCE(cfg);
                globalDCE.performGlobalDeadCodeElimination();

                if (updateFig) {
                    System.out.println("afterDSE---------------------");
                    writeFile(cfg.toString(), outPutDir + inputFileNamePrefix+"_new_" + "DSE_" + cfgCounter + ".txt");
                    genGraphViz(inputFileNamePrefix+"_DSE_" + cfgCounter, cfg, outPutDir);
                }


                //UDChain
                ReachingDefinitionAnalysis rDAnalysis = new ReachingDefinitionAnalysis(cfg);
                rDAnalysis.genUseDefinitionChains();
                writeFile(rDAnalysis.printUseDefsChains(), outPutDir + inputFileNamePrefix+"_UDChain" + cfgCounter + ".txt");


                //calCutNodes
                HashMap<VarAndStmt, HashSet<Tuple2<VarAndStmt, HashSet<BasicBlock>>>> udChianWithDmt = rDAnalysis.calCutNodes(dominatorsMap);
                writeCutNodesToFile(udChianWithDmt, outPutDir +inputFileNamePrefix+ "_CutNodes" + cfgCounter + ".txt");

//                DFT dft = new DFT(cfg, udChianWithDmt);
//                System.out.println("data flow testing!----------------");
//                dft.dataFlowTesting();


                System.out.println("simulator.execute();------------");
                BranchTest branchTest = new BranchTest(cfg);
                branchTest.branchTest(inputFileNamePrefix);

//                System.out.println("CF------------------------");
//                GlobalCF.performGlobalCodeFolding(cfg);
//                writeFile(cfg.toString(), outPutDir + inputFileNamePrefix+"_new_" + "CF_" + cfgCounter + ".txt");
//
//                System.out.println("URE------------------------");
//                GlobalURE.performGlobalURE(cfg);
//                writeFile(cfg.toString(), outPutDir + inputFileNamePrefix+"_new_" + "URE_" + cfgCounter + ".txt");
//
//                System.out.println("AS------------------------");
//                AlgebraicSimplifications.performAlgebraicSimplifications(cfg);
//                writeFile(cfg.toString(), outPutDir + inputFileNamePrefix+"_new_" + "AS_" + cfgCounter + ".txt");

                cfgCounter++;
            }


        } catch (IOException e) {
            System.err.println("There was an error:\n" + e);
        }
    }

    public static void main(String[] args) {
        MyPrint.levelZero.print(System.getProperty("user.home"));
        updateFig = false;
        String inputDir = "tests_programs/dataflow/input/";        //要遍历的路径
        inputDir = "tests_programs/paper1_tests/input/";
//        String file = "power.txt";
//        String file = "counter.txt";
        String file = "example.txt";

//        String file = "FB_G4LTL13.txt";// TODO 有问题

        walkTree(inputDir + file);

        // 打开一个文件夹，把所有文件都执行一边，把结果输出
        runDirFiles(inputDir);

    }

    public static void runDirFiles(String path) {

        File file = new File(path);        //获取其file对象
        File[] fs = file.listFiles();    //遍历path下的文件和目录，放在File数组中
        for (File f : fs) {                    //遍历File[]数组
            if (!f.isDirectory()) {
                //若非目录(即文件)，则打印
//                System.out.println(Arrays.toString(f.toString().split("/")));
                if (f.toString().equals("tests_programs/paper1_tests/input/FB_G4LTL15.txt")) {
                    continue;
                } else if (f.toString().equals("tests_programs/paper1_tests/input/FB_G4LTL12.txt")) {
                    continue;
                } else if (f.toString().equals("tests_programs/paper1_tests/input/FB_G4LTL9.txt")) {
                    continue;
                } else if (f.toString().equals("tests_programs/paper1_tests/input/FB_G4LTL13.txt")) {
                    continue;
                } else if (f.toString().equals("tests_programs/paper1_tests/input/FB_G4LTL10.txt")) {
                    continue;
                }
                System.out.println("\n----- " + f + " ---------");
                walkTree(f.toString());
            }


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


    public static String dominatorMapToString(CFG cfg, HashMap<BasicBlock, HashSet<BasicBlock>> dominatorsMap) {
        StringBuilder stringBuilder = new StringBuilder();
        for (BasicBlock bb : cfg.getBasicBlocks()) {
            stringBuilder.append("Node: ").append(bb.name).append(" :\n");
            for (BasicBlock b : dominatorsMap.get(bb)) {
                stringBuilder.append(b.name).append(", ");
            }
            stringBuilder.append("\n\n");

        }
        return stringBuilder.toString();
    }

    public static void genGraphViz(String cfgCounter, CFG cfg, String outPutDir) {
        String graphVizFilename = outPutDir + "Graph_" + cfgCounter + ".dot";
        File writename = new File(graphVizFilename); // 相对路径，如果没有则要建立一个新的output。txt文件
        try {
            writename.createNewFile();

            BufferedWriter out = new BufferedWriter(new FileWriter(writename));
            out.write(cfg.toGraphviz()); // \r\n即为换行
            out.flush(); // 把缓存区内容压入文件
            out.close(); // 最后记得关闭文件
            Runtime.getRuntime().exec("dot" + " " + graphVizFilename + " -Tpdf" + " -o" + " " + outPutDir + "Graph_" + cfgCounter + ".pdf").waitFor();
        } catch (IOException | InterruptedException e) {
            System.err.println("There was an error:\n" + e);
        }


        System.out.println("to Graphviz finish!");
    }

    public static void genGraphViz(String cfgCounter, LoopAnalysis loopAyls, String outPutDir) {
        String graphVizFilename = outPutDir + "Graph_" + cfgCounter + ".dot";
        File writename = new File(graphVizFilename); // 相对路径，如果没有则要建立一个新的output。txt文件
        try {
            writename.createNewFile();

            BufferedWriter out = new BufferedWriter(new FileWriter(writename));
            String s = loopAyls.toGraphviz();
            out.write(s); // \r\n即为换行
            out.flush(); // 把缓存区内容压入文件
            out.close(); // 最后记得关闭文件
            Runtime.getRuntime().exec("dot" + " " + graphVizFilename + " -Tpdf" + " -o" + " " + outPutDir + "Graph_" + cfgCounter + ".pdf").waitFor();
        } catch (IOException | InterruptedException e) {
            System.err.println("There was an error:\n" + e);
        }


        System.out.println("to Graphviz finish!");
    }

    public static void writeCutNodesToFile(HashMap<VarAndStmt, HashSet<Tuple2<VarAndStmt, HashSet<BasicBlock>>>> udChianWithDmt, String path) {
        StringBuilder stringBuilder = new StringBuilder();
        for (VarAndStmt use : udChianWithDmt.keySet()) {
            HashSet<Tuple2<VarAndStmt, HashSet<BasicBlock>>> defAndDomsSet = udChianWithDmt.get(use);
            for (Tuple2<VarAndStmt, HashSet<BasicBlock>> defAndDoms : defAndDomsSet) {
                VarAndStmt def = defAndDoms.a1;
                HashSet<BasicBlock> doms = defAndDoms.a2;
                stringBuilder.append("def: ").append(def.location).append(" @ ").append(def.stmtLabel).append(" @ ").append(def.block.name).append("\n");
                stringBuilder.append("use: ").append(use.location).append(" @ ").append(use.stmtLabel).append(" @ ").append(use.block.name).append("\n");

                stringBuilder.append("dominators: ");
                for (BasicBlock bb : doms) {
                    stringBuilder.append(bb.name).append(" ");
                }
                stringBuilder.append("\n\n");
            }
        }
        writeFile(stringBuilder.toString(), path);
    }

}
