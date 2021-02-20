import cfg.*;
import grammar.gen.STParser;
import grammar.gen.STScanner;
import helper.LlBuilder;
import helper.LlBuildersList;
import helper.LlSymbolTable;
import ll.location.LlLocation;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import tools.CLI;
import tools.CLI.Action;
import tools.MyPrint;
import visitor.DefPhaseVisitor;
import visitor.SemanticCheckVisitor;

import java.io.*;
import java.util.*;

public class Main {
    public static MyPrint myprint = new MyPrint(3);


    public static void run(String[] args) {
        try {
            CLI.parse(args, new String[0]);
            InputStream inputStream = args.length == 0 ?
                    System.in : new java.io.FileInputStream(CLI.infile);
            PrintStream outputStream = CLI.outfile == null ? System.out : new java.io.PrintStream(new java.io.FileOutputStream(CLI.outfile));
            if (CLI.target == Action.SCAN) {
                STScanner scanner =
                        new STScanner(new ANTLRInputStream(inputStream));
//        scanner.setTrace(CLI.debug);
                Token token;
                boolean done = false;
                while (!done) {
                    try {
                        for (token = scanner.nextToken(); token.getType() != STParser.EOF; token = scanner.nextToken()) {
                            String type = "";
                            String text = token.getText();

//              System.out.println("\n\n"+token.getType());
                            switch (token.getType()) {
                                // TODO: add strings for the other types here...
                                case STScanner.AS_OP:
                                    type = " AS_OP";
                                    break;
                                case STScanner.FromTo:
                                    type = " FromTo";
                                    break;
                                case STScanner.Decimal_literal:
                                    type = " Decimal_literal";
                                    break;
                                case STScanner.Static_string_literal:
                                    type = " STRINGLITERAL";
                                    break;
                                case STScanner.BOOL:
                                    type = " BOOLEANLITERAL";
                                    break;
                                case STScanner.ID:
                                    type = " IDENTIFIER";
                                    break;
                            }
                            outputStream.println(token.getLine() + type + " " + text);
                        }
                        done = true;
                    } catch (Exception e) {
                        // print the error:
                        System.err.println(CLI.infile + " " + e);
//            scanner.consume();
                        scanner.skip(); // replaces
                    }
                }
            } else if (CLI.target == Action.PARSE ||
                    CLI.target == Action.DEFAULT) {
                STScanner scanner =
                        new STScanner(new ANTLRInputStream(inputStream));
                CommonTokenStream tokenStream = new CommonTokenStream(scanner); // added for Antlr4
                STParser parser = new STParser(tokenStream);
                parser.setTrace(CLI.debug);
                parser.pous();
//        if(parser.getError()) {
                if (parser.getNumberOfSyntaxErrors() > 0) {
                    System.exit(1);
                }
            }
        } catch (Exception e) {
            // print the error:
            System.err.println(CLI.infile + " " + e);
        }

    }

    public static void walkTree(String filePath) {
        String[] pathSegments = filePath.split("/");
        String prefix = pathSegments[0] +"/"+ pathSegments[1]+"/";
        String inputFileName = pathSegments[3];

        String inputFile = prefix + pathSegments[2] + "/" + inputFileName;
        String outPutDir = prefix +   inputFileName.substring(0,7)+  "_output/" ;
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

            System.err.println("\n semantic check error message:");
            System.err.println(semanticCheckVisitor.errorMessage);

            System.out.println("\n pretty print:\n");
            writeFile(listener.pous.prettyPrint(""),outPutDir+ "prettyPrint" + ".txt");

            int cfgCounter = 0;
            System.out.println("\n low level IR\n");

            LlBuildersList llBuilderList = listener.pous.getBuilderList();
            ArrayList<LlSymbolTable> llSymbolTables = llBuilderList.getSymbolTables();
            ArrayList<LlBuilder> llBuilders = llBuilderList.getBuilders();
            Iterator<LlSymbolTable> llSymbolTableIterator = llSymbolTables.iterator();

            for (Iterator<LlBuilder> llBuilderIterator = llBuilders.iterator(); llBuilderIterator.hasNext();) {
                CFG cfg = new CFG(llBuilderIterator.next(), llSymbolTableIterator.next(),true);
//                System.out.println(cfg.toString());
//                System.out.println(cfg.toGraphviz());

                genGraphViz( "origin_" + cfgCounter,cfg,outPutDir);


                System.out.println("_______________________ ");
                writeFile(cfg.toString(), outPutDir + "origin_" + cfgCounter + ".txt");

                printDominatorMap(cfg);

//                printDUchain(cfg, 1);

                HashSet<LlLocation> globalVArs = new HashSet<>();
                GlobalCSE.performGlobalCommonSubexpressionEliminationOnCFG(cfg, globalVArs);
                writeFile(cfg.toString(), outPutDir + "new_" + "CSE_" + cfgCounter + ".txt");
                System.out.println("\nafterCSE---------------------\n");
                genGraphViz( "CSE_"+cfgCounter,cfg,outPutDir);
//                printDUchain(cfg, 2);

//                GlobalCP.performGlobalCP(cfg, globalVArs);
//                GlobalDCE.performGlobalDeadCodeElimination(cfg);

                GlobalCP.performGlobalCP(cfg, globalVArs);
                writeFile(cfg.toString(), outPutDir + "new_" + "CP_" + cfgCounter + ".txt");
                System.out.println("\nafterCP---------------------\n");

//                printDUchain(cfg, 3);
                genGraphViz( "CP_"+cfgCounter,cfg,outPutDir);

                GlobalDCE.performGlobalDeadCodeElimination(cfg);
                writeFile(cfg.toString(), outPutDir + "new_" + "DSE_" + cfgCounter + ".txt");
                System.out.println("\nafterDSE---------------------\n");
                genGraphViz( "DSE_"+cfgCounter,cfg,outPutDir);
//                printDUchain(cfg, 4);


//                System.out.println("simulator.execute();------------");
//                Simulator simulator = new Simulator(cfg,new Memory(),new LlStatementExeutor());
//                simulator.execute();

                GlobalCF.performGlobalCodeFolding(cfg);
                writeFile(cfg.toString(), outPutDir + "new_" + "CF_" + cfgCounter + ".txt");

                GlobalURE.performGlobalURE(cfg);
                writeFile(cfg.toString(), outPutDir + "new_" + "URE_" + cfgCounter + ".txt");

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
//        String file = "04_test.txt";
//        walkTree(inputDir+file);
        // 判断结果是否正确（感觉这个比较困难，看看别人是怎么做都）

        // 打开一个文件夹，把所有文件都执行一边，把结果输出
        runDirFiles(inputDir);

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

    public static void printDUchain(CFG cfg, int counter){
        cfg.buildDefUseChains();
        System.out.println("defUseChain--------------------"+ String.valueOf(counter));
        for (CFG.SymbolDef symbolDef : cfg.defUseChain.keySet()) {
            System.out.println(symbolDef + " : " + cfg.defUseChain.get(symbolDef));
        }
        System.out.println("defUseChain--------------------"+ String.valueOf(counter));
    }

    public static void printDominatorMap(CFG cfg){
        HashMap<BasicBlock, HashSet<BasicBlock>> dominatorsMap = LoopAnalysis.getStrictDominatorsMap(cfg);
        System.out.println("dominatorsMap------------");
        for (BasicBlock bb : dominatorsMap.keySet()) {
            System.out.println(bb.getLabelsToStmtsMap().entrySet().iterator().next() + "--------------dominators:");
            for (BasicBlock b : dominatorsMap.get(bb)) {
                System.out.println(b.getLabelsToStmtsMap().entrySet().iterator().next());
            }
        }
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
