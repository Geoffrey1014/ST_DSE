import cfg.*;
import grammar.gen.STParser;
import grammar.gen.STScanner;
import helper.LlBuilder;
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
import java.util.HashMap;
import java.util.HashSet;

public class Mian {
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

    public static void writeFile(CFG cfg, String pathName) {

        try {

            String content = cfg.toString();

            File file = new File(pathName);

            if (file.exists()) {
                file = new File(pathName);
//                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(content);
            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void walkTree(String[] args) {
        String prefix = "tests/dataflow/";
        String inputFile = prefix + "input/07_test.txt";

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
            System.out.println(listener.pous.prettyPrint(""));

            int cfgCounter = 0;
            System.out.println("\n low level IR\n");
            for (LlBuilder builder : listener.pous.getBuilderList()) {
                CFG cfg = new CFG(builder);
//                System.out.println(cfg.toString());

                System.out.println("_______________________ ");
                writeFile(cfg, prefix + "origin_" + cfgCounter + ".txt");

                HashMap<BasicBlock, HashSet<BasicBlock>> dominatorsMap = LoopAnalysis.getStrictDominatorsMap(cfg);
                System.out.println("dominatorsMap------------");
                for (BasicBlock bb : dominatorsMap.keySet()) {
                    System.out.println(bb.getLabelsToStmtsMap().entrySet().iterator().next() + "--------------dominators:");
                    for (BasicBlock b : dominatorsMap.get(bb)) {
                        System.out.println(b.getLabelsToStmtsMap().entrySet().iterator().next());
                    }
                }

                cfg.buildDefUseChains();
                System.out.println("defUseChain1--------------------");
                for (CFG.SymbolDef symbolDef : cfg.defUseChain.keySet()) {
                    System.out.println(symbolDef + " : " + cfg.defUseChain.get(symbolDef));
                }
                System.out.println("defUseChain1--------------------");


                HashSet<LlLocation> globalVArs = new HashSet<>();
                GlobalCSE.performGlobalCommonSubexpressionEliminationOnCFG(cfg, globalVArs);
                writeFile(cfg, prefix + "new_" + "CSE_" + cfgCounter + ".txt");
                System.out.println("\nafterCSE---------------------\n");

                cfg.buildDefUseChains();
                System.out.println("defUseChain2--------------------");
                for (CFG.SymbolDef symbolDef : cfg.defUseChain.keySet()) {
                    System.out.println(symbolDef + " : " + cfg.defUseChain.get(symbolDef));
                }
                System.out.println("defUseChain2--------------------");

                GlobalCP.performGlobalCP(cfg, globalVArs);
                writeFile(cfg, prefix + "new_" + "CP_" + cfgCounter + ".txt");
                System.out.println("\nafterCP---------------------\n");

                cfg.buildDefUseChains();
                System.out.println("defUseChain3--------------------");
                for (CFG.SymbolDef symbolDef : cfg.defUseChain.keySet()) {
                    System.out.println(symbolDef + " : " + cfg.defUseChain.get(symbolDef));
                }
                System.out.println("defUseChain3--------------------");

                GlobalDCE.performGlobalDeadCodeElimination(cfg);
                writeFile(cfg, prefix + "new_" + "DSE_" + cfgCounter + ".txt");
                System.out.println("\nafterDSE---------------------\n");

                cfg.buildDefUseChains();
                System.out.println("defUseChain4--------------------");
                for (CFG.SymbolDef symbolDef : cfg.defUseChain.keySet()) {
                    System.out.println(symbolDef + " : " + cfg.defUseChain.get(symbolDef));
                }
                System.out.println("defUseChain4--------------------");

                System.out.println("simulator.execute();------------");
                Simulator simulator = new Simulator(cfg,new Memory(),new LlStatementExeutor());
                simulator.execute();

                GlobalCF.performGlobalCodeFolding(cfg);
                writeFile(cfg, prefix + "new_" + "CF_" + cfgCounter + ".txt");

                GlobalURE.performGlobalURE(cfg);
                writeFile(cfg, prefix + "new_" + "URE_" + cfgCounter + ".txt");

                AlgebraicSimplifications.performAlgebraicSimplifications(cfg);
                writeFile(cfg, prefix + "new_" + "AS_" + cfgCounter + ".txt");

                cfgCounter++;
            }


        } catch (IOException e) {
            System.err.println("There was an error:\n" + e);
        }
    }

    public static void main(String[] args) {
        MyPrint.levelZero.print(System.getProperty("user.home"));
        walkTree(args);


    }
}
