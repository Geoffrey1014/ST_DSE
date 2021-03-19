package cfg;

import java.util.HashSet;


public class DomNode {
    public BasicBlock basicBlock;
    private String name;
    private HashSet<DomNode> childrenDomNodes;
    private int id;

    public DomNode(BasicBlock basicBlock) {
        this.basicBlock = basicBlock;
        this.name = basicBlock.name;
        this.childrenDomNodes = new HashSet<>();
    }

    public HashSet<DomNode> getChildrenDomNodes() {
        return this.childrenDomNodes;
    }

    public void addchild(DomNode domNode) {
        this.childrenDomNodes.add(domNode);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
        this.basicBlock.setDomTreeLevel(id);
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
