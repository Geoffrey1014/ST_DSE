package simulation;

import cfg.*;
import ll.location.LlLocation;
import tools.Tuple2;

import java.util.*;

public class DataFlowTest extends CoverageTest{

    private ConcreteExecutor concreteExecutor;
    private SymbolExecutor symbolExecutor;
    private StateManager stateManager;
    private BranchTest branchTestor;
    private DomTree domTree;
    private UdChainsAndDoms udChainsAndDoms;
    private int successTestedDu =0 ;
    public DataFlowTest(CFG cfg, DomTree domTree, UdChainsAndDoms udChainsAndDoms){
        super(cfg);

        this.concreteExecutor = new ConcreteExecutor(cfg);
        this.symbolExecutor = new SymbolExecutor(cfg);
        this.stateManager = new StateManager();
        this.domTree = domTree;
        this.branchTestor = new BranchTest(cfg);
        this.udChainsAndDoms = udChainsAndDoms;
    }

    public String dataFlowTesting(){

        int countInit = 0;
        branchTestor.branchTest();
        HashSet<ConMemory> states = branchTestor.getStateManager().statesAppeared;

        List<DuPairAndDoms> duPairAndDomsList = udChainsAndDoms.duPairAndDomsList;

        long startTime = System.currentTimeMillis(); //程序开始记录时间
        long totalTime = 0;
        int countedDu = 0;
        for(DuPairAndDoms duPairAndDoms : duPairAndDomsList){
            countedDu += 1;
            totalTime = System.currentTimeMillis() - startTime;       //总消耗时间
            if(totalTime > 180000){
                break;
            }

            this.stateManager = new StateManager(states);
            VarAndStmt def = duPairAndDoms.def;
            VarAndStmt use = duPairAndDoms.use;
//            System.out.println("def: " + def.toStringSimple());
//            System.out.println("use: "+ use.toStringSimple()+"\n");

            if(def.block.name.equals("Init")) {
                this.successTestedDu += 1;
                countInit += 1;
                continue;
            }

            dfTestaDuPair(def,use,duPairAndDoms);
        }

        return printResult(states.size(),totalTime,countInit,countedDu,successTestedDu,duPairAndDomsList.size());
    }
    public String printResult(int statesSize, long totalTime, int countInit, int countedDu,int successTestedDu,
                            int duPairAndDomsListSize){
        String resultString = "";
        System.out.println("branch states: "+ statesSize);
        resultString += "branch states: "+ statesSize +"\n";

        System.out.println("consumed time: " + totalTime);
        resultString += "consumed time: " + totalTime + "\n";

        System.out.println("countInit: " + countInit);
        resultString += "countInit: " + countInit + "\n";

        System.out.println("succTestedDu: "+ successTestedDu);
        resultString += "succTestedDu: "+ successTestedDu + "\n";

        System.out.println("countedDu: " + countedDu);
        resultString += "countedDu: " + countedDu + "\n";

        System.out.println("totalDu: "+ duPairAndDomsListSize);
        resultString += "totalDu: "+ duPairAndDomsListSize + "\n";

        System.out.println("dft: "+(float) successTestedDu /duPairAndDomsListSize);
        resultString += "dft: "+(float) successTestedDu /duPairAndDomsListSize + "\n";

        return resultString;
    }

    /**
     *
     * @param def 目标定义 -使用对 d
     * @param use 目标定义 -使用对 d
     * @return 满足 du 的程序输入 t，如果找不到对应的 t，返回 nil
     *
     * 1 let W be a worklist of branching nodes (initialized as empty)
     * 2 let t be an initial test input
     * 3 repeat
     * 4    let p be the execution path triggered by t
     * 5    if p covers du then return t
     * 6    W ← W ∪ {branching nodes on p}
     *      // the redefinition pruning heuristic
     * 7    if variable x (in du) is redeﬁned after l d on p then
     * 8        let X denote the set of branching nodes after the redeﬁnition location
     * 9        W ← W \ X
     * 10   end
     * 11 let t = guided_search(W)
     * 12 until t == nil
     * 13 return nil
     *
     * 这是在一个PLC state 下进行的测试，如果不行，则需要换一个新的PLC state。
     * 我觉得算法应该是先想办法到达 def, 再从def 到 use
     */
    public HashMap<LlLocation,ValueOfDiffType> dfTestaDuPair(VarAndStmt def, VarAndStmt use, DuPairAndDoms duPairAndDoms){


        long startTime = System.currentTimeMillis(); //程序开始记录时间

        HashMap<LlLocation,ValueOfDiffType> inputs;
        while( stateManager.candidatesSize()>0){
            long endTime   = System.currentTimeMillis(); //程序结束记录时间
            long totalTime = endTime - startTime;       //总消耗时间
            if(totalTime > 500){
                break;
            }
            inputs = stateDuPairTest(def,use,duPairAndDoms,stateManager.popLeft());
            if(inputs != null) {
//                System.err.println("get the du test!");
                this.successTestedDu += 1;
                return inputs;
            }
        }
//        System.err.println("def: " + def.toStringSimple());
//        System.err.println("use: "+ use.toStringSimple()+"\n");

        return null;

    }


    public HashMap<LlLocation,ValueOfDiffType> stateDuPairTest(VarAndStmt def, VarAndStmt use,  DuPairAndDoms duPairAndDomss,ConMemory startConMenory){
        HashMap<BasicBlock,Boolean> W = new HashMap<>();

        SymMemory startSymMemory = symbolExecutor.createSymMemory(startConMenory);
        symbolExecutor.mkInputSymbolic(startSymMemory);

        HashMap<LlLocation,ValueOfDiffType> inputs = concreteExecutor.createRandomInputs();
        int counter = 0;
        do{
            counter += 1;
            if(counter >20) {
                break;
            }
            Tuple2<ExecutedRoute,ConMemory> exeResult = concreteExecutor.conExeFromRead(inputs,startConMenory);
            ExecutedRoute executedRoute = exeResult.a1;
            ConMemory endConMemory = exeResult.a2;
            List<BasicBlock> route = executedRoute.route;
            LinkedHashMap<BasicBlock,Boolean> branchNodes = executedRoute.executedBranches;

//            this.stateManager.add(endConMemory);
            int coverResult = pathCoverDu(route,def,use);
            if(coverResult == 2) return inputs;
            if(coverResult == 1){  //cover def

//                LinkedHashMap<BasicBlock,Boolean> prunedBr = redefinitionPruning(def,branchNodes,route);
                W.putAll(branchNodes);
                inputs = guidedSearch(route,W,duPairAndDomss.sortedDomNodeFromDefToUSe,startSymMemory,branchNodes);
            }
            else{
                // not cover def
                W.putAll(branchNodes);
                inputs = guidedSearch(route,W,duPairAndDomss.sortedDomNodeFromEntryToDef,startSymMemory,branchNodes);
            }


        } while (inputs != null);

        return null;
    }

    public LinkedHashMap<BasicBlock,Boolean>  redefinitionPruning(VarAndStmt def,LinkedHashMap<BasicBlock,Boolean> branchNodes,
                                    List<BasicBlock> route  ){
        int defCounter = 0;
        int i = 0;
        LinkedHashMap<BasicBlock,Boolean> branchNodesNew = new LinkedHashMap<>();
        for(; i< route.size(); i++){
            BasicBlock bb = route.get(i);
            if(bb.defs.contains(def)){
                defCounter += 1;
            }
            if(defCounter > 1){
                break;
            }
            if(branchNodes.containsKey(bb)){
                branchNodesNew.put(bb,branchNodes.get(bb));
            }
        }
        return branchNodesNew;

    }

    /**
     * 15 Procedure guided_search(reference worklist W) ′
     * 16 let b denote the branch to be ﬂipped
     * 17 if W is empty then
     * 18 return nil
     * 19 end // j is the index of a cut point, d is the distance variable
     *
     * 20 j ← 0, d ← 0
     *
     * 21 forall the branching node b ∈ W do
     *      // l_b is the program location of b
     * 22   let pp be the path preﬁx of b, i.e. l 1 , l 2 , . . . , lb
     *      // c 1 , . . . , c i−1 are sequentially covered, while c i not yet
     * 23   i ← index of the uncovered cut point c i on pp
     *      // ¯b is the opposite branch of b
     * 24   if i > j ∨ (i == j ∧ distance(¯b, c i ) < d) then ′
     * 25        b ← b, j ← i, d ← distance(¯b, c i )
     * 26   end
     * 27 end
     * 28 W ← W \ {b },
     * // ¯l b ′ is the opposite branch direction of the original b at l_b
     * 29 if ∃ input t driving the program through l 1 , l 2 , . . . , ¯l b ′ then
     * 30   return t
     * 31 end
     * 32 else
     * 33   return guided_search(W)
     * 34 end
     * @param route
     * @param workList
     * @param sortedCuts
     * @param symMemory
     * @param branchNodes
     * @return
     */
    public HashMap<LlLocation,ValueOfDiffType> guidedSearch(List<BasicBlock> route,
                                                            HashMap<BasicBlock,Boolean> workList,
                                                            ArrayList<BasicBlock> sortedCuts,
                                                            SymMemory symMemory,
                                                            LinkedHashMap<BasicBlock,Boolean> branchNodes){
        SymMemory oldSymMemory = new SymMemory(symMemory);
        if(workList.isEmpty()) return null;
        int distance = 0; //先放弃 distance这个因素
        if(sortedCuts.size() ==0) return null; // 先不考虑def use 在同一个block的情况

        BasicBlock curTargetCut = sortedCuts.get(0);
        BasicBlock flippedBB = null;

        for (BasicBlock branchNode : workList.keySet()) {

            BasicBlock nextTargetCut = lookForNextCut(sortedCuts,branchNode);
            // look for next target cut

            if (nextTargetCut != null && nextTargetCut.getDomTreeLevel() > curTargetCut.getDomTreeLevel()) {
                curTargetCut = nextTargetCut;
                flippedBB = branchNode;
            }

        }
        if(flippedBB == null) {
            workList.clear();
            return null;
        }
        workList.remove(flippedBB);
        LinkedList<HashMap<LlLocation, ValueOfDiffType>> calculatedInputs = symbolExecutor.symExeFromRead(symMemory,route,branchNodes, Collections.singletonList(flippedBB));
        if(calculatedInputs.size() > 0) return calculatedInputs.pollFirst();
        else{
            return guidedSearch(route,workList,sortedCuts,oldSymMemory,branchNodes);
        }
    }

    public BasicBlock lookForNextCut(ArrayList<BasicBlock> sortedCuts, BasicBlock branchNode){
        DomNode root = domTree.get(branchNode.name);
        return domTree.searchNextCut( new HashSet<>(sortedCuts),root);
    }

    /**
     *  2: cover both def and use
     *  1: cover def
     *  0: other
     * @param path
     * @param def
     * @param use
     * @return
     */
    public int pathCoverDu(List<BasicBlock> path, VarAndStmt def, VarAndStmt use){
        boolean defFlag = false;
        boolean useFlag = false;

        for(BasicBlock bbLabel: path){
            if(bbLabel.equals(def.block)) defFlag = true;
            if(bbLabel.equals(use.block)) useFlag = true;

        }
        if(defFlag && useFlag){
            return 2;
        }
        else if( defFlag){
            return 1;
        }
        else {
            return 0;
        }
    }


}
