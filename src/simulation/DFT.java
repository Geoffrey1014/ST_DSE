package simulation;

import cfg.BasicBlock;
import cfg.CFG;
import cfg.VarAndStmt;
import ll.location.LlLocation;
import tools.Tuple2;

import java.util.*;

public class DFT {
    private final CFG cfg;
    private HashMap<VarAndStmt, HashSet<Tuple2<VarAndStmt, HashSet<BasicBlock>>>> udChianWithDmt;
    private Simulator simulator;

    public DFT(CFG cfg, HashMap<VarAndStmt, HashSet<Tuple2<VarAndStmt, HashSet<BasicBlock>>>> udChianWithDmt){
        this.cfg = cfg;
        this.udChianWithDmt = udChianWithDmt;
        this.simulator = new Simulator(cfg);
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
     */
    public HashMap<LlLocation,ValueOfDiffType> dfTestaDuPair(VarAndStmt def, VarAndStmt use, HashSet<BasicBlock> cuts){
        HashSet<Tuple2<BasicBlock,Boolean>> W = new HashSet<>();
        ArrayList<BasicBlock> sortedCuts = new ArrayList<>(cuts);
        sortedCuts.sort(Comparator.comparingInt(BasicBlock::getId));
        ConMemory conMemory = simulator.createInitMemory();

        HashMap<LlLocation,ValueOfDiffType> inputs = simulator.createRandomInputs();
        do{
            Tuple2<List<String>,List<Tuple2<Integer,Boolean>>> result = simulator.conExeFromRead(inputs,conMemory);

            List<Tuple2<BasicBlock,Boolean>> branchNodes = getBranchNodes(result);


            if(pathCoverDu(result.a1,def,use)) return inputs;
            W.addAll(branchNodes);

            redefinitionPruning();
            inputs = guidedSearch(W,sortedCuts);


        } while (inputs == null);

        return null;

    }

    public void redefinitionPruning(){

    }

    public HashMap<LlLocation,ValueOfDiffType> guidedSearch(HashSet<Tuple2<BasicBlock,Boolean>> workList,ArrayList<BasicBlock> sortedCuts ){
        if(workList.isEmpty()) return null;
        int distance = 0; //先放弃 distance这个因素
        int index = 0;
        BasicBlock nextTarget = null;

        for (Tuple2<BasicBlock, Boolean> branchNode : workList) {
            int branchId = branchNode.a1.getId();
            BasicBlock nextTargetCut = null;
            // can use binary search to optimize
            for (BasicBlock cutBB : sortedCuts) {
                if (cutBB.getId() > branchId) {
                    nextTargetCut = cutBB;
                    break;
                }
                // if(cutBB.getId() <= branchId) case is not needed to considered.
            }
            if (nextTargetCut.getId() > nextTarget.getId()) {
                nextTarget = nextTargetCut;

            }

        }
        workList.remove(nextTarget);


        return null;

    }


    public List<Tuple2<BasicBlock,Boolean>> getBranchNodes(Tuple2<List<String> ,List<Tuple2<Integer,Boolean>>> result){
        List<Tuple2<BasicBlock,Boolean>> branchNodes = new ArrayList<>();
        for(Tuple2<Integer, Boolean> b: result.a2){
            branchNodes.add(new Tuple2<>(this.cfg.leadersToBBMap.get(result.a1.get(b.a1)), b.a2));
        }
        return branchNodes;
    }


    public boolean pathCoverDu(List<String> path, VarAndStmt def, VarAndStmt use){
        boolean defFlag = false;
        boolean useFlag = false;

        for(String bbLabel: path){
            if(bbLabel.equals(def.block.name)) defFlag = true;
            if(bbLabel.equals(use.block.name)) useFlag = true;

        }
        return defFlag && useFlag;
    }

}
