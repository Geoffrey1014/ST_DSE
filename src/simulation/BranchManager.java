package simulation;

import cfg.BasicBlock;
import cfg.CFG;
import tools.Tuple2;

import java.util.*;

/**
 * manage branches
 * record which branches have been covered
 */
public class BranchManager {
    private ArrayList<BasicBlock> sortedBranchBlocks;
    private HashMap<BasicBlock, List<Boolean>> branchRecords;
    private int coveredBranchesNum;
    private int totalBranchesNum;
    private CFG cfg;

    public BranchManager(CFG cfg, HashSet<BasicBlock> branchBlocks) {
        this.cfg = cfg;
        totalBranchesNum = branchBlocks.size() * 2;
        coveredBranchesNum = 0;
        this.sortedBranchBlocks = new ArrayList<>(branchBlocks);
        this.sortedBranchBlocks.sort(Comparator.comparingInt(BasicBlock::getDomTreeLevel));
        this.branchRecords = new HashMap<>();
        for (BasicBlock bb : sortedBranchBlocks) {
            branchRecords.put(bb, Arrays.asList(false, false));
        }
    }

    public List<BasicBlock> flipBranches(LinkedHashMap<BasicBlock,Boolean> executedBranch) {
        // pruning covered Branches

        List<BasicBlock> flipBranches = new ArrayList<>();
        for (BasicBlock branchNode : executedBranch.keySet()) {
            boolean branchChoice = executedBranch.get(branchNode);
            if (branchChoice == true) {
                if (branchRecords.get(branchNode).get(0) == false){
                    flipBranches.add(branchNode);
                }
            } else {
                if (branchRecords.get(branchNode).get(1) == false){
                    flipBranches.add(branchNode);
                }
            }
        }
        return flipBranches;
    }

    public void addRoute(List<BasicBlock> route, List<Tuple2<Integer, Boolean>> branches) {
        for (Tuple2<Integer, Boolean> branch : branches) {
            BasicBlock bb = route.get(branch.a1);
            if (branch.a2) {
                if (!branchRecords.get(bb).get(1)) {
                    coveredBranchesNum += 1;
                    branchRecords.get(bb).set(1, true);
                }

            } else {
                if (!branchRecords.get(bb).get(0)) {
                    coveredBranchesNum += 1;
                    branchRecords.get(bb).set(0, true);
                }
            }
        }
    }


    public boolean isCovered(BasicBlock bb, BranchEdge edge) {
        if (edge == BranchEdge.DEFAULT) {
            return branchRecords.get(bb).get(0);
        } else {
            return branchRecords.get(bb).get(1);
        }
    }

    public float coverageRate() {
        return (float) coveredBranchesNum / totalBranchesNum;
    }

}
