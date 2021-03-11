package simulation;

import cfg.BasicBlock;
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
    public BranchManager(HashSet<BasicBlock> branchBlocks){
        totalBranchesNum = branchBlocks.size() *2;
        coveredBranchesNum = 0;
        this.sortedBranchBlocks = new ArrayList<>(branchBlocks);
        this.sortedBranchBlocks.sort(Comparator.comparingInt(BasicBlock::getId));
        this.branchRecords = new HashMap<>();
        for(BasicBlock bb: sortedBranchBlocks){
            branchRecords.put(bb,  Arrays.asList(false,false));
        }
    }

    public BranchManager(List<Tuple2<BasicBlock,Boolean>> branchNodes){
        totalBranchesNum = branchNodes.size() *2;
        coveredBranchesNum = 0;


        this.branchRecords = new HashMap<>();
        for(Tuple2<BasicBlock,Boolean> nodeAndchoice: branchNodes){
            if(nodeAndchoice.a2) {
                addCoveredBranch(nodeAndchoice.a1, BranchEdge.ALERT );
            } else{
                addCoveredBranch(nodeAndchoice.a1, BranchEdge.DEFAULT );
            }
        }
        this.sortedBranchBlocks = new ArrayList<>(this.branchRecords.keySet());
        this.sortedBranchBlocks.sort(Comparator.comparingInt(BasicBlock::getId));

        for(BasicBlock bb: sortedBranchBlocks){
            branchRecords.put(bb,  Arrays.asList(false,false));
        }
    }

    // add a covered branch
    public void addCoveredBranch(BasicBlock bb, BranchEdge edge){
        if(edge == BranchEdge.DEFAULT){
            branchRecords.computeIfAbsent(bb, k -> Arrays.asList(false, false));
            branchRecords.get(bb).set(0,true);
        }
        else{
            branchRecords.computeIfAbsent(bb, k -> Arrays.asList(false, false));
            branchRecords.get(bb).set(1, true);
        }
        coveredBranchesNum += 1;
    }

    public boolean isCovered(BasicBlock bb, BranchEdge edge){
        if(edge == BranchEdge.DEFAULT){
            return branchRecords.get(bb).get(0);
        }
        else{
            return branchRecords.get(bb).get(1);
        }
    }

    public float coverageRate(){
        return (float) coveredBranchesNum / totalBranchesNum;
    }

}
