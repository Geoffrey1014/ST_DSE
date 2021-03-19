package cfg;

import helper.LlBuilder;
import ll.LlStatement;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;

public class BasicBlock {
    public String name; //the first stmt's label
    private int domTreeLevel;
    private LinkedHashMap<String, LlStatement> labelsToStmtsMap;
    private BasicBlock defaultBranch;
    private BasicBlock alternativeBranch;
    private final LlBuilder builder;
    private final HashSet<BasicBlock> predecessors;

    private Edge defaultEdge = new Edge();
    private Edge alterName = new Edge();

    public BasicBlock(LinkedHashMap<String, LlStatement> labelsToStmtsMap, LlBuilder builder) {
        this.labelsToStmtsMap = new LinkedHashMap<>(labelsToStmtsMap);
        this.builder = builder;
        this.predecessors = new HashSet<>();
        this.domTreeLevel = -1;
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
        if(this.domTreeLevel >= 0){
            str = str + this.domTreeLevel + " ";
        }
        str += this.name + "\n";
        return str;
    }

    public String stmtsString() {
        StringBuilder str = new StringBuilder();
        for(String label : this.labelsToStmtsMap.keySet()){
            str.append(String.format("%1$15s :  ", label));
            str.append(this.labelsToStmtsMap.get(label)).append("\n");

        }
        return str.toString();
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
    protected void rmPredecessorNode(BasicBlock parent){
        if(this.predecessors.contains(parent)){
            this.predecessors.remove(parent);
        }
    }
    public void setLabelsToStmtsMap(LinkedHashMap<String, LlStatement> labelsToStmtsMap) {
        this.labelsToStmtsMap = labelsToStmtsMap;
    }

    public Edge getDefaultEdge() {
        return this.defaultEdge;
    }

    public Edge getAlterName() {
        return this.alterName;
    }
    public void setDomTreeLevel(int domTreeLevel){this.domTreeLevel = domTreeLevel;}
    public int getDomTreeLevel(){return this.domTreeLevel;}



}
