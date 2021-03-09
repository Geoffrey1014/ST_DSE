package tools;

import cfg.BasicBlock;

import java.util.HashSet;


public class Node {
    private BasicBlock basicBlock;
    private String name;
    private HashSet<Node> nextNodes;
    private int id;

    public Node(BasicBlock basicBlock){
        this.basicBlock = basicBlock;
        this.name = basicBlock.name;
    }
    public void addNextNode(Node node){this.nextNodes.add(node);}
    public void setId(int id){this.id = id;}

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
