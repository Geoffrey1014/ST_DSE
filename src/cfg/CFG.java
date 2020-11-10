package cfg;

import helper.LlBuilder;
import ll.LlEmptyStmt;
import ll.LlStatement;
import ll.jump.LlJump;
import ll.jump.LlJumpUnconditional;

import java.util.*;

public class CFG {
    private final LlBuilder builder;
    private final ArrayList<BasicBlock> basicBlocks;
    LinkedHashMap<String, BasicBlock> leadersToBBMap;
    ArrayList<String> orderedLeadersList;
    public CFG(LlBuilder builder) {

        this.builder = builder;

        System.err.println("LlBuilder statement list:");
        for (String s : this.builder.getStatementTable().keySet()){
            System.out.println(s + " : " + this.builder.getStatementTable().get(s));
        }
        System.err.println("LlBuilder statement list  end");

        // cache the Labels => Stmts map and extract the labels list
        LinkedHashMap<String, LlStatement> labelStmtsMap = new LinkedHashMap<>(builder.getStatementTable());
        ArrayList<String> labelsList = new ArrayList<>(labelStmtsMap.keySet());

        if (labelsList.size() == 0) {
            this.basicBlocks = new ArrayList<BasicBlock>();
        }
        else {
            // 1) determine the leaders in the LLIR
            HashSet<String> leadersSet = new HashSet<>();

            // the first instruction in the LLIR is a leader
            leadersSet.add(labelsList.get(0));

            for (int i = 1; i < labelsList.size(); i++) {
                String label = labelsList.get(i);
                LlStatement stmt = labelStmtsMap.get(label);

                if (stmt instanceof LlJump) {

                    // the TARGET of the jumpStmt is a leader
                    String jmpTolabel = ((LlJump) stmt).getJumpToLabel();
                    leadersSet.add(jmpTolabel);

                    // the stmt FOLLOWING the jumpStmt is a leader
                    String nextStmtLabel = labelsList.get(i + 1);
                    leadersSet.add(nextStmtLabel);
                }
            }

            // 2) create basic blocks from LlStatements
            leadersToBBMap = new LinkedHashMap<>();
            HashSet<String> tempLeadersSet = new HashSet<>(leadersSet);
            LinkedList<String> labelsQueue = new LinkedList<>(labelsList);
            orderedLeadersList = new ArrayList<>();
            do {
                LinkedHashMap<String, LlStatement> bbLabelsToStmtsMap = new LinkedHashMap<>();

                // basic blocks start with the leader
                String leaderLabel = labelsQueue.pop();
                LlStatement leaderStmt = labelStmtsMap.get(leaderLabel);
                bbLabelsToStmtsMap.put(leaderLabel, leaderStmt);

                // remove this leader from the leadersSet and add it
                // to the leadersList
                tempLeadersSet.remove(leaderLabel);
                orderedLeadersList.add(leaderLabel);

                // keep adding LlStatments until you get to the next leader
                while (labelsQueue.size() > 0 && !tempLeadersSet.contains(labelsQueue.peek())) {

                    // keep adding stmts to the currentBBStmtList
                    String label = labelsQueue.pop();
                    LlStatement stmt = labelStmtsMap.get(label);
                    bbLabelsToStmtsMap.put(label, stmt);
                }

                // create the actual BasicBlock and it to the LinkedHashMap
                BasicBlock bb = new BasicBlock(bbLabelsToStmtsMap, builder);
                leadersToBBMap.put(leaderLabel, bb);

            } while (labelsQueue.size() > 0);

            // 3) appropriately connect the basic blocks
            for (int i = 0; i < orderedLeadersList.size(); i++) { // loop through the leaders in the order of the linear order of the basic blocks
                String leaderLabel = orderedLeadersList.get(i);
                BasicBlock bb = leadersToBBMap.get(leaderLabel);

                List<LlStatement> bbStmtsList = bb.getStmtsList();
                LlStatement lastStmtOfCurrentBB = bbStmtsList.get(bbStmtsList.size() - 1);

                // connect if there is a jump from the end of B to the beginning of C
                if (lastStmtOfCurrentBB instanceof LlJump) {
                    // set forward edge B --> C
                    String targetLabel = ((LlJump) lastStmtOfCurrentBB).getJumpToLabel();
                    BasicBlock targetBB = this.leadersToBBMap.get(targetLabel);
                    bb.setAlternativeBranch(targetBB);

                    // set reverse edge B <-- C
                    targetBB.addPredecessorNode(bb);
                }

                // C immediately follows B and B does not end in an unconditional jump
                // (this only holds if B is not the last block))
                if (!(lastStmtOfCurrentBB instanceof LlJumpUnconditional) && (i < orderedLeadersList.size() - 1)) {

                    // set forward edge B --> C
                    String nextBBLeaderLabel = this.orderedLeadersList.get(i + 1);
                    BasicBlock nextBB = this.leadersToBBMap.get(nextBBLeaderLabel);
                    bb.setDefaultBranch(nextBB);

                    // set reverse edge B --> C
                    nextBB.addPredecessorNode(bb);
                }
            }

            // add an empty BasicBlock as the entry node and
            // connect it and the orignal first BB to each other
            String trueFirstBBLabel = this.orderedLeadersList.get(0);
            BasicBlock trueFirstBB = this.leadersToBBMap.get(trueFirstBBLabel);

            String entryBBLabel = "entry";
            this.orderedLeadersList.add(0, entryBBLabel);
            LinkedHashMap<String, LlStatement> entryBBStmtsList = new LinkedHashMap<>();
            entryBBStmtsList.put(entryBBLabel, new LlEmptyStmt());
            BasicBlock entryBB = new BasicBlock(entryBBStmtsList, builder);
            this.leadersToBBMap.put(entryBBLabel, entryBB);

            entryBB.setDefaultBranch(trueFirstBB);
            trueFirstBB.addPredecessorNode(entryBB);

            // add an empty BasicBlock as the exit node and
            // connect it and the orignal last BB to each other
            String trueLastBBLabel = this.orderedLeadersList.get(this.orderedLeadersList.size() - 1);
            BasicBlock trueLastBB = this.leadersToBBMap.get(trueLastBBLabel);

            String exitBBLabel = "exit";
            this.orderedLeadersList.add(exitBBLabel);
            LinkedHashMap<String, LlStatement> exitBBStmtsList = new LinkedHashMap<>();
            exitBBStmtsList.put(exitBBLabel, new LlEmptyStmt());
            BasicBlock exitBB = new BasicBlock(exitBBStmtsList, builder);
            this.leadersToBBMap.put(exitBBLabel, exitBB);

            trueLastBB.setDefaultBranch(exitBB);
            exitBB.addPredecessorNode(trueLastBB);


            // 4) assign the list of basic blocks as a field of THIS object
            ArrayList<BasicBlock> basicBlocks = new ArrayList<>();
            for (String leaderLabel : orderedLeadersList) {
                basicBlocks.add(leadersToBBMap.get(leaderLabel));
            }

            this.basicBlocks = basicBlocks;
        }
    }

    public BasicBlock getRootBasicBlock() {
        return this.basicBlocks.get(0);
    }

    @Override
    public String toString() {
        String str = "CFG:\n";
        for (BasicBlock bb : this.basicBlocks) {
            str += bb.toString() + "\n";
        }
        str = str.substring(0, str.length() - 1);
        return str;
    }

    public ArrayList<BasicBlock> getBasicBlocks() {
        return this.basicBlocks;
    }

    /**
     *  每当一种 OPT 完成后， 调用此函数，更新 basic block
     * @return 返回 此 CFG 的 LlBuilder
     */
    public LlBuilder getBuilder() {

        // in case any optimizations have occurred, make sure to update
        // the LlBuilder's statementsTable in case some optimizations have occurred
        LinkedHashMap<String, LlStatement> updatedMap = new LinkedHashMap<>();

        // loop through the BasicBlocks in their linear order, and for
        // each LlStatement, add it to the updatedMap
        for (BasicBlock bb : this.basicBlocks) {
            for (String label : bb.getLabelsToStmtsMap().keySet()) {
                LlStatement stmt = bb.getLabelsToStmtsMap().get(label);
                updatedMap.put(label, stmt);
            }
        }
        this.builder.setStatementTable(updatedMap);

        return this.builder;
    }
}