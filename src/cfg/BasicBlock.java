package cfg;

import helper.LlBuilder;
import ll.LlStatement;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;

public class BasicBlock {

    public Edge getLeft() {
        return this.left;
    }

    public Edge getRight() {
        return this.right;
    }

    private Edge left = new Edge();
    private Edge right = new Edge();

    private LinkedHashMap<String, LlStatement> labelsToStmtsMap;
    private final HashSet<BasicBlock> predecessors;
    private final LlBuilder builder;
    private BasicBlock defaultBranch;
    private BasicBlock alternativeBranch;

    public BasicBlock(LinkedHashMap<String, LlStatement> labelsToStmtsMap, LlBuilder builder) {
        this.labelsToStmtsMap = new LinkedHashMap<>(labelsToStmtsMap);
        this.predecessors = new HashSet<>();
        this.builder = builder;
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

    public LlBuilder getBuilder() {
        return this.builder;
    }

    public ArrayList<String> getLabelsList() {
        return new ArrayList<String>(this.labelsToStmtsMap.keySet());
    }

    public BasicBlock getDefaultBranch() {
        return defaultBranch;
    }

    public BasicBlock getAlternativeBranch() {
        return alternativeBranch;
    }

    public void setLabelsToStmtsMap(LinkedHashMap<String, LlStatement> labelsToStmtsMap) {
        this.labelsToStmtsMap = labelsToStmtsMap;
    }

    protected void setDefaultBranch(BasicBlock defaultBranch) {
        this.defaultBranch = defaultBranch;
    }

    protected void setAlternativeBranch(BasicBlock alternativeBranch) {
        this.alternativeBranch = alternativeBranch;
    }

    protected void addPredecessorNode(BasicBlock parent) {
        this.predecessors.add(parent);
    }

    protected HashSet<BasicBlock> getPredecessors() {
        return new HashSet<>(this.predecessors);
    }

    @Override
    public String toString() {
        String str = "";
        for (String label : this.labelsToStmtsMap.keySet()) {
            str += String.format("%1$15s :  ", label);
            str += this.labelsToStmtsMap.get(label) + "\n";

        }
        return str;
    }

    @Override
    public boolean equals(Object obj) {
        return obj == this;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
