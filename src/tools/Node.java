package tools;

import cfg.BasicBlock;

import java.util.HashSet;


public class Node {
    private BasicBlock basicBlock;
    private String name;
    private HashSet<Node> childrenNodes;
    private int id;

    public Node(BasicBlock basicBlock) {
        this.basicBlock = basicBlock;
        this.name = basicBlock.name;
        this.childrenNodes = new HashSet<>();
    }

    public HashSet<Node> getChildrenNodes() {
        return this.childrenNodes;
    }

    public void addchild(Node node) {
        this.childrenNodes.add(node);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
        this.basicBlock.setId(id);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString(){
        return this.id +" "+ this.name;
    }
}
