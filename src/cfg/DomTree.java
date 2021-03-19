package cfg;

import tools.GraphViz;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;

public class DomTree {
    private HashMap<String, DomNode> label2Node;
    private int id;
    public DomTree(){
        this.label2Node = new HashMap<>();
    }
    public DomNode getRoot(){
        return this.label2Node.get("Entry");
    }
    public void addNode(DomNode curDomNode){
        label2Node.put(curDomNode.getName(), curDomNode);
    }

    public void addEdge(BasicBlock from, BasicBlock to){
        DomNode fromDomNode = label2Node.get(from.name);
        fromDomNode.addchild(label2Node.get(to.name));
    }
    public DomNode get(String name){
        return label2Node.get(name);
    }
    public void dfsSetNum(int startNum){
        this.id = startNum;
        dfsHelper(getRoot());
    }
    public void dfsHelper(DomNode root){
        if(root == null) return;
        root.setId(this.id);
        this.id += 1;
        for(DomNode domNode : root.getChildrenDomNodes()){
            dfsHelper(domNode);
        }
    }
    public void bfs(int startNum){
        this.id = startNum;
        DomNode root = getRoot();
        Deque<DomNode> deque = new ArrayDeque<>();
        deque.add(root);
        while (deque.size() >0){
            DomNode node = deque.pollFirst();
            node.setId(this.id++);
            for(DomNode domNode : node.getChildrenDomNodes()){
                deque.addLast(domNode);
            }
        }
    }
    public BasicBlock searchNextCut(HashSet<BasicBlock> targetCuts, DomNode root){
        Deque<DomNode> deque = new ArrayDeque<>();
        for(DomNode domNode : root.getChildrenDomNodes()){
            deque.addLast(domNode);
        }

        while (deque.size() >0){
            DomNode node = deque.pollFirst();
            if(targetCuts.contains(node.basicBlock)) return node.basicBlock;
            for(DomNode domNode : node.getChildrenDomNodes()){
                deque.addLast(domNode);
            }


        }
        return null;
    }

    public String toGraphviz(){
        GraphViz graphViz = new GraphViz();
        for (DomNode domNode : this.label2Node.values()) {
            String srcNode = domNode.toString();
            graphViz.nodes.put(srcNode,false);
            for(DomNode child : domNode.getChildrenDomNodes()){
                graphViz.edges.map(srcNode,child.toString());
            }
        }
        return graphViz.toDOT2();
    }
}
