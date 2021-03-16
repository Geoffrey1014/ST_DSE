package simulation;

import cfg.*;
import grammar.gen.STParser;
import grammar.gen.STScanner;
import helper.LlBuilder;
import helper.LlBuildersList;
import helper.LlSymbolTable;
import ll.LlComponent;
import ll.literal.*;
import ll.location.LlLocation;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.junit.Before;
import org.junit.Test;
import parser.STListener;
import tools.Tuple2;
import visitor.DefPhaseVisitor;
import visitor.SemanticCheckVisitor;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class BranchTestTest {
    BranchTest branchTest;
    CFG cfg;

    @Before
    public void startUp() {
        String inputDir = "tests_programs/paper1_tests/input/";
        String file = "power.txt";
        walkTree(inputDir + file);

        branchTest = new BranchTest(cfg);
    }

    @Test
    public void testCreateRandomInputs() {
        HashMap<LlLocation, ValueOfDiffType> inputs = createRandomInputs();
        for(LlLocation location:inputs.keySet()){
            ValueOfDiffType value = inputs.get(location);
            System.out.println(location+": "+value);
        }
    }

    public HashMap<LlLocation, ValueOfDiffType> createRandomInputs() {
        HashMap<LlLocation, ValueOfDiffType> inputs = new HashMap<>();
        Random random = new Random(19);
        Hashtable<LlComponent, LlLiteral> inputVarsInit = this.cfg.getLlSymbolTable().varInput;
        for (LlComponent llComponent : inputVarsInit.keySet()) {
            LlLiteral llLiteral = inputVarsInit.get(llComponent);
            if (llLiteral instanceof LlLiteralBool)
                inputs.put((LlLocation) llComponent, new ValueOfDiffType(random.nextBoolean()));
            else if (llLiteral instanceof LlLiteralInt)
                inputs.put((LlLocation) llComponent, new ValueOfDiffType((long) random.nextInt(5)* (random.nextBoolean() ? -1 : 1)));
            else if (llLiteral instanceof LlLiteralReal) {
                inputs.put((LlLocation) llComponent, new ValueOfDiffType(((double) random.nextFloat()) * (random.nextBoolean() ? -1 : 1)));
            } else if (llLiteral instanceof LlLiteralString)
                inputs.put((LlLocation) llComponent, new ValueOfDiffType("it is not dealt with")); // easy to do this
            else System.out.println("wrong type!");
        }
        return inputs;
    }

    private void walkTree(String filePath) {
        String[] pathSegments = filePath.split("/");
        String prefix = pathSegments[0] + "/" + pathSegments[1] + "/";
        String inputFileName = pathSegments[3];

        String inputFile = prefix + pathSegments[2] + "/" + inputFileName;
        String outPutDir = prefix + inputFileName.split("\\.")[0] + "_output/";
        File dir = new File(outPutDir);
        if (!dir.exists()) {// 判断目录是否存在
            dir.mkdir();
        }

        try {
            CharStream stream = CharStreams.fromFileName(inputFile);
            STScanner lexer = new STScanner(stream);
            TokenStream tokens = new CommonTokenStream(lexer); // 这里 tokens 为 空不知道为什么
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

            int cfgCounter = 0;
            System.out.println("\n low level IR\n");

            LlBuildersList llBuilderList = listener.pous.getBuilderList();
            ArrayList<LlSymbolTable> llSymbolTables = llBuilderList.getSymbolTables();
            ArrayList<LlBuilder> llBuilders = llBuilderList.getBuilders();
            Iterator<LlSymbolTable> llSymbolTableIterator = llSymbolTables.iterator();


            for (LlBuilder llBuilder : llBuilders) {
                cfg = new CFG(llBuilder, llSymbolTableIterator.next(), true);
                System.out.println("_______________________ ");


                HashSet<LlLocation> globalVArs = new HashSet<>();
                GlobalCSE.performGlobalCommonSubexpressionEliminationOnCFG(cfg, globalVArs);
                System.out.println("\nafterCSE---------------------\n");


                GlobalCP.performGlobalCP(cfg, globalVArs);
                System.out.println("\nafterCP---------------------\n");

                // Dominator
                LoopAnalysis loopAnalysis = new LoopAnalysis(cfg);
                HashMap<BasicBlock, HashSet<BasicBlock>> dominatorsMap = loopAnalysis.getDominatorsMap();

                // DominatorTree
                HashMap<BasicBlock, HashSet<BasicBlock>> dominatingTreeMap = loopAnalysis.getDominatingTreeMap();
                loopAnalysis.createDominatorTree(dominatingTreeMap);

                GlobalDCE globalDCE = new GlobalDCE(cfg);
                globalDCE.performGlobalDeadCodeElimination();
                System.out.println("\nafterDSE---------------------\n");


                //UDChain
                ReachingDefinitionAnalysis rDAnalysis = new ReachingDefinitionAnalysis(cfg);
                rDAnalysis.genUseDefinitionChains();


                //calCutNodes
                HashMap<VarAndStmt, HashSet<Tuple2<VarAndStmt,HashSet<BasicBlock>>>> udChianWithDmt = rDAnalysis.calCutNodes(dominatorsMap);

//                DFT dft = new DFT(cfg,udChianWithDmt);
//                System.out.println("data flow testing!----------------");
//                dft.dataFlowTesting();
//
//
//                System.out.println("simulator.execute();------------");
//                Simulator simulator = new Simulator(cfg);
//                simulator.execute();

//                System.out.println("CF------------------------");
//                GlobalCF.performGlobalCodeFolding(cfg);
//
//
//                System.out.println("URE------------------------");
//                GlobalURE.performGlobalURE(cfg);
//
//                System.out.println("AS------------------------");
//                AlgebraicSimplifications.performAlgebraicSimplifications(cfg);

                cfgCounter++;
            }


        } catch (IOException e) {
            System.err.println("There was an error:\n" + e);
        }
    }
}
