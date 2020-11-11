package cfg;

import helper.LlBuilder;
import ll.LlStatement;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;

public class BasicBlock {
    private LinkedHashMap<String, LlStatement> labelsToStmtsMap;
    private BasicBlock defaultBranch;
    private BasicBlock alternativeBranch;
    private final LlBuilder builder;
    private final HashSet<BasicBlock> predecessors;

    private Edge left = new Edge();
    private Edge right = new Edge();

    public BasicBlock(LinkedHashMap<String, LlStatement> labelsToStmtsMap, LlBuilder builder) {
        this.labelsToStmtsMap = new LinkedHashMap<>(labelsToStmtsMap);
        this.builder = builder;
        this.predecessors = new HashSet<>();
    }

    public LinkedHashMap<String, LlStatement> getLabelsToStmtsMap() {
        return this.labelsToStmtsMap;
    }

    public ArrayList<LlStatement> getStmtsList() {
        ArrayList<LlStatement> stmtsList = new ArrayList<>();
        for (String label : this.labelsToStmtsMap.keySet()) {
            LlStatement stmt = this.labelsToStmtsMap.get(label);
            stmtsList.add(stmt);
        }
        return stmtsList;
    }

    public ArrayList<String> getLabelsList() {
        return new ArrayList<String>(this.labelsToStmtsMap.keySet());
    }

    protected void setDefaultBranch(BasicBlock defaultBranch) {
        this.defaultBranch = defaultBranch;
    }

    protected void setAlternativeBranch(BasicBlock alternativeBranch) {
        this.alternativeBranch = alternativeBranch;
    }

    public BasicBlock getDefaultBranch() {
        return defaultBranch;
    }

    public BasicBlock getAlternativeBranch() {
        return alternativeBranch;
    }

    @Override
    public String toString() {
        String str = "";
//        str += "++++++ new block +++++++\n";
        for(String label : this.labelsToStmtsMap.keySet()){
            str += String.format("%1$15s :  ", label);
            str += this.labelsToStmtsMap.get(label) + "\n";

        }
        return str;
    }

    public LlBuilder getBuilder() {
        return builder;
    }

    public HashSet<BasicBlock> getPredecessors() {
        return predecessors;
    }

    protected void addPredecessorNode(BasicBlock parent) {
        this.predecessors.add(parent);
    }
    public void setLabelsToStmtsMap(LinkedHashMap<String, LlStatement> labelsToStmtsMap) {
        this.labelsToStmtsMap = labelsToStmtsMap;
    }

    public Edge getLeft() {
        return this.left;
    }

    public Edge getRight() {
        return this.right;
    }

}
