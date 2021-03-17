package simulation;

import cfg.BasicBlock;
import cfg.CFG;
import cfg.VarAndStmt;
import ll.location.LlLocation;
import tools.Tuple2;

import java.util.*;

public class DataFlowTest extends CoverageTest{
    private HashMap<VarAndStmt, HashSet<Tuple2<VarAndStmt, HashSet<BasicBlock>>>> udChianWithDmt;
    private ConcreteExecutor concreteExecutor;
    private SymbolExecutor symbolExecutor;
    private StateManager stateManager;

    public DataFlowTest(CFG cfg, HashMap<VarAndStmt, HashSet<Tuple2<VarAndStmt, HashSet<BasicBlock>>>> udChianWithDmt){
        super(cfg);

        this.udChianWithDmt = udChianWithDmt;
        this.concreteExecutor = new ConcreteExecutor(cfg);
        this.symbolExecutor = new SymbolExecutor(cfg);
        this.stateManager = new StateManager();
    }

    public void dataFlowTesting(){
        for(VarAndStmt use : udChianWithDmt.keySet()){
            HashSet<Tuple2<VarAndStmt,HashSet<BasicBlock>>> defsAndCuts = udChianWithDmt.get(use);
            for(Tuple2<VarAndStmt,HashSet<BasicBlock>> defAndItsCuts : defsAndCuts){
                VarAndStmt def = defAndItsCuts.a1;
                HashSet<BasicBlock> cuts = defAndItsCuts.a2;
                dfTestaDuPair(def,use,cuts);
            }
        }

    }

    /**
     *
     * @param def 目标定义 -使用对 d
     * @param use 目标定义 -使用对 d
     * @param cuts du 的一系列割点
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
    public HashMap<LlLocation,ValueOfDiffType> dfTestaDuPair(VarAndStmt def, VarAndStmt use, HashSet<BasicBlock> cuts){
        ArrayList<BasicBlock> sortedCuts = new ArrayList<>(cuts);
        sortedCuts.sort(Comparator.comparingInt(BasicBlock::getId));

        ConMemory conMemory = createInitMemory();
        stateManager.add(conMemory);
        HashMap<LlLocation,ValueOfDiffType> inputs = null;
        while(inputs == null && stateManager.candidatesSize()>0){
            inputs = stateDuPairTest(def,use,sortedCuts,stateManager.popLeft());
        }

        return null;

    }


    public HashMap<LlLocation,ValueOfDiffType> stateDuPairTest(VarAndStmt def, VarAndStmt use, ArrayList<BasicBlock> sortedCuts,ConMemory startConMenory){
        HashMap<BasicBlock,Boolean> W = new HashMap<>();

        SymMemory startSymMemory = symbolExecutor.createSymMemory(startConMenory);
        symbolExecutor.mkInputSymbolic(startSymMemory);

        HashMap<LlLocation,ValueOfDiffType> inputs = concreteExecutor.createRandomInputs();
        do{
            Tuple2<ExecutedRoute,ConMemory> exeResult = concreteExecutor.conExeFromRead(inputs,startConMenory);
            ExecutedRoute executedRoute = exeResult.a1;
            ConMemory endConMemory = exeResult.a2;
            List<BasicBlock> route = executedRoute.route;
            LinkedHashMap<BasicBlock,Boolean> branchNodes = executedRoute.executedBranches;

            this.stateManager.add(endConMemory);
            if(pathCoverDu(route,def,use)) return inputs;
            W.putAll(branchNodes);

            redefinitionPruning();
            inputs = guidedSearch(route,W,sortedCuts,startSymMemory,branchNodes);

        } while (inputs != null);

        return null;
    }



    public HashMap<LlLocation,ValueOfDiffType> guidedSearch(List<BasicBlock> route,
                                                            HashMap<BasicBlock,Boolean> workList,
                                                            ArrayList<BasicBlock> sortedCuts,
                                                            SymMemory symMemory,
                                                            LinkedHashMap<BasicBlock,Boolean> branchNodes){
        SymMemory oldSymMemory = new SymMemory(symMemory);
        if(workList.isEmpty()) return null;
        int distance = 0; //先放弃 distance这个因素
        BasicBlock curTargetCut = sortedCuts.get(0);
        BasicBlock flippedBB = null;

        for (BasicBlock branchNode : workList.keySet()) {
            int branchId = branchNode.getId();
            BasicBlock nextTargetCut = null;
            // look for next target cut TODO: can use binary search to optimize
            for (BasicBlock cutBB : sortedCuts) {
                if (cutBB.getId() > branchId) {
                    nextTargetCut = cutBB;
                    break;
                }
                // if(cutBB.getId() <= branchId) case is not needed to considered.
            }

            if (nextTargetCut != null && nextTargetCut.getId() > curTargetCut.getId()) {
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


    public boolean pathCoverDu(List<BasicBlock> path, VarAndStmt def, VarAndStmt use){
        boolean defFlag = false;
        boolean useFlag = false;

        for(BasicBlock bbLabel: path){
            if(bbLabel.equals(def.block)) defFlag = true;
            if(bbLabel.equals(use.block)) useFlag = true;

        }
        return defFlag && useFlag;
    }
    public void redefinitionPruning(){

    }
}
