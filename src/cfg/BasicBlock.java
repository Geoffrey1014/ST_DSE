package cfg;

import helper.LlBuilder;
import ll.LlStatement;

import java.util.*;

public class BasicBlock {
    public String name; //the first stmt's label
    private int domTreeLevel;
    private LinkedHashMap<String, LlStatement> labelsToStmtsMap;
    private BasicBlock defaultBranch;
    private BasicBlock alternativeBranch;
    private final LlBuilder builder;
    private final HashSet<BasicBlock> predecessors;
    public HashSet<VarAndStmt> defs;

    private Edge defaultEdge = new Edge();
    private Edge alterName = new Edge();

    public BasicBlock(LinkedHashMap<String, LlStatement> labelsToStmtsMap, LlBuilder builder) {
        this.labelsToStmtsMap = new LinkedHashMap<>(labelsToStmtsMap);
        this.builder = builder;
        this.predecessors = new HashSet<>();
        this.domTreeLevel = -1;
        this.defs = new HashSet<>();
    }
    public int calDistance(BasicBlock target){
        int distance = -1;
        // 去掉 visitedVertex 的判断 不会变快？
        List<BasicBlock> visitedVertex = new LinkedList<>();
        Queue<BasicBlock> queue=new LinkedList<>();
        queue.offer(this);
        while(!queue.isEmpty()) {

            int currentLevelSize = queue.size();
            distance += 1;

            for (int i = 1; i <= currentLevelSize; ++i) {
                BasicBlock node = queue.poll();
                if(node.equals(target)){
                    return distance;
                }
                visitedVertex.add(node);
                if (node.defaultBranch != null && !visitedVertex.contains(node.defaultBranch)) {
                    queue.offer(node.defaultBranch);
                }
                if (node.alternativeBranch != null && !visitedVertex.contains(node.alternativeBranch)) {
                    queue.offer(node.alternativeBranch);
                }
            }

        }
        return -2;

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
