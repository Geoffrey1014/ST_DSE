package simulation;

import cfg.BasicBlock;
import cfg.CFG;
import ll.location.LlLocation;
import tools.Tuple2;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

/**
 * 在CFG上模拟执行
 * 需要模拟内存状态，使用map
 * 处理分枝
 * 调用每个LLIR 的exe 函数
 */
public class BranchTest extends CoverageTest{


    private SymbolExecutor symbolExecutor;
    private ConcreteExecutor concreteExecutor;
    private StateManager stateManager;
    private BranchManager branchManager;
    public PtestManager ptestManager;
    private int ptestCounter;
    protected boolean inreaseInitInputs = false;

    public StateManager getStateManager() {
        return stateManager;
    }

    public BranchTest(CFG cfg) {
        super(cfg);
        this.concreteExecutor = new ConcreteExecutor(cfg);
        this.symbolExecutor = new SymbolExecutor(cfg);
        this.stateManager = new StateManager();
        this.branchManager = new BranchManager(cfg, this.concreteExecutor.branchBlocks);
        this.ptestManager = new PtestManager();
        this.ptestCounter = 0;
    }

    /**
     * Input: CIG: coverage instruction graph, CP: the candidate path pool,
     * CPP: Circle Path Pool, v: the input vector, s: the PLC state, statePool: PLC state set
     * Output: TS: test case set, CR: coverage rate
     * C P ← ∅; v ← v0; statePool ← s0;
     * repeat
         * s ← SelectNextState(statePool);
         * repeat
             * repeat
                 * P ←DSE_EXE(v,s,CIG);
                 * statePool ← AddStatePool();
                 * CIG←UpdateCIG();
                 * candidate_path_set ← CollectPathCandidates(P);
                 * CPP ←SaveCirclePath(candidate_path_set,s);
                 * CP ← PrunePath(CIG,CP,candidate_path_set,s);
                 * candidate_path ← SelectNextCandidate(C P);
                 * v ← GenerateTestCase(candidate_path, s);
                 * T S ← T S + {v};
             * until CP =∅
         * until statePool=∅
         * s,circle_path ← SelectOneCirclePath(C P P);
         * v ← GenerateTestCase(candidate_path);
         * P ←DSE_EXE(v,s,CIG);
         * statePool ← AddStatePool();
         * CIG←UpdateCIG();
         * candidate_path_set ← CollectPathCandidates(P);
         * C P P ← SaveCirclePath(candidate_path_set);
     * until C P P = ∅ ∥ C R ≥ β
     * @param fileName
     */
    public String branchTest(String fileName, boolean inreaseInitInputs) {
        this.inreaseInitInputs = inreaseInitInputs;
        long startTime = System.currentTimeMillis(); //程序开始记录时间
        ptestCounter = 0;
        HashMap<String,Double> oldBranchTestData = new OldBranchTestData().data;
        // create createInitMemory
        ConMemory oldConMenory = createInitMemory();
        stateManager.add(oldConMenory);
        int counter = 0;
        float branchCoverage = 0;
        while (stateManager.candidatesSize() > 0) {
            counter++;
//            System.out.println("------- circle -----------" + counter++);
            oldConMenory = stateManager.popLeft();
            oneCircleTest(oldConMenory, this.concreteExecutor.createRandomInputs());
            branchCoverage = branchManager.coverageRate();
            if (branchCoverage > 0.99 || counter > 1000) break;
//            genGraphViz("");
        }
        Double oldData = 0.0D;
        if(oldBranchTestData.containsKey(fileName)) oldData= oldBranchTestData.get(fileName);
        long endTime = System.currentTimeMillis(); //程序结束记录时间
        long consumedTime = endTime -startTime;
        String result = "consumed time(ms): " + consumedTime + "\n";
        result += fileName + " branch coverage: " + branchCoverage;
        System.out.println(result);
        return result;


    }

    public void branchTest() {
        // create createInitMemory
        ConMemory oldConMenory = createInitMemory();
        stateManager.add(oldConMenory);
        int counter = 0;
        float branchCoverage;
        while (stateManager.candidatesSize() > 0) {
            counter++;
//            System.out.println("------- circle -----------" + counter++);
            oldConMenory = stateManager.popLeft();
            oneCircleTest(oldConMenory, this.concreteExecutor.createRandomInputs());
            branchCoverage = branchManager.coverageRate();
            if (branchCoverage > 0.99 || counter > 1000) break;
//            genGraphViz("");
        }

    }

    public void oneCircleTest(ConMemory startConMenory, HashMap<LlLocation, ValueOfDiffType> inputs) {
        SymMemory startSymMemory = symbolExecutor.createSymMemory(startConMenory);
        symbolExecutor.mkInputSymbolic(startSymMemory);

        LinkedList<HashMap<LlLocation, ValueOfDiffType>> inputsWorkList = new LinkedList<>();
        inputsWorkList.add(inputs);
        if (this.inreaseInitInputs) createRandomInputs(inputsWorkList);
        int counter = 0;
        while (inputsWorkList.size() > 0) {
//            System.out.println("--inputs: " + counter++);
            HashMap<LlLocation, ValueOfDiffType> ptest = inputsWorkList.pollFirst();
            float oldBranchCoverage = branchManager.coverageRate();
            PtestWithState ptestWithState = new PtestWithState(startConMenory,ptest, ptestCounter++);

            Tuple2<ExecutedRoute,ConMemory> exeResult= this.concreteExecutor.conExeFromRead(ptest, startConMenory);
            ExecutedRoute executedRoute = exeResult.a1;
            stateManager.add(exeResult.a2);
            branchManager.addRoute(executedRoute.route, executedRoute.branches);
            if(hasLoop(executedRoute)){
                useCPP(startConMenory);
            }
            ptestWithState.addPLCEndState(exeResult.a2);

            float branchCoverage = branchManager.coverageRate();

            if(oldBranchCoverage < branchCoverage){
                ptestWithState.improveCoverage = true;
            }


//            System.out.println(ptestWithState);
            ptestManager.addPtestWithStates(ptestWithState);
            if (branchCoverage > 0.99) break;

            // flipBranches
            List<BasicBlock> flipBranches = branchManager.flipBranches(executedRoute.executedBranches);
            // SE and get calculatedInputs
            LinkedList<HashMap<LlLocation, ValueOfDiffType>> calculatedInputs = symbolExecutor.symExeFromRead(startSymMemory, executedRoute.route, executedRoute.executedBranches, flipBranches);
//            if (calculatedInputs.size() > 0){
//                System.out.println("\ncalculatedInputs:\n"+calculatedInputs);
//            }
            inputsWorkList.addAll(calculatedInputs);
//            genGraphViz("");

        }

    }

    private void createRandomInputs(LinkedList<HashMap<LlLocation, ValueOfDiffType>> inputsWorkList){
        for(int i=0 ; i < 10; i++){
            HashMap<LlLocation, ValueOfDiffType> input = this.concreteExecutor.createRandomInputs();
            inputsWorkList.add(input);
        }
    }
    private void useCPP(ConMemory startConMenory){
        for(int i=0 ; i < 5; i++){
            HashMap<LlLocation, ValueOfDiffType> input = this.concreteExecutor.createRandomInputs();
            Tuple2<ExecutedRoute,ConMemory> exeResult= this.concreteExecutor.conExeFromRead(input, startConMenory);
            stateManager.add(exeResult.a2);
        }

    }
    private boolean hasLoop(ExecutedRoute executedRoute){
        HashSet<BasicBlock> basicBlockHashSet = new HashSet<>();
        for (BasicBlock bb: executedRoute.route){
            if(basicBlockHashSet.contains(bb)){
                return true;
            }
            else {
                basicBlockHashSet.add(bb);
            }
        }
        return false;
    }


    public void genGraphViz(String outPutDir) {
        String graphVizFilename = outPutDir + "Graph_debug" + ".dot";
        File writename = new File(graphVizFilename); // 相对路径，如果没有则要建立一个新的output.txt文件
        try {
            writename.createNewFile();

            BufferedWriter out = new BufferedWriter(new FileWriter(writename));
            out.write(this.concreteExecutor.toGraphviz()); // \r\n即为换行
            out.flush(); // 把缓存区内容压入文件
            out.close(); // 最后记得关闭文件
            Runtime.getRuntime().exec("dot" + " " + graphVizFilename + " -Tpdf" + " -o" + " " + outPutDir + "Graph_debug" + ".pdf").waitFor();
        } catch (IOException | InterruptedException e) {
            System.err.println("There was an error:\n" + e);
        }

        System.out.println("to Graphviz finish!");
    }


}
