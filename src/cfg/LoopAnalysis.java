package cfg;

import tools.Node;

import java.util.*;

public class LoopAnalysis {
    public HashMap<BasicBlock, HashSet<BasicBlock>> dominatorsMap = null;
    public CFG cfg;
    private int id;
    private HashMap<String, Node> label2Node;

    public LoopAnalysis(CFG cfg){
        this.cfg = cfg;
    }

    // returns a map where there is a key for each node in the CFG
    // and the corresponding value is the set of all nodes that
    // dominate the key node
    public HashMap<BasicBlock, HashSet<BasicBlock>> getDominatorsMap() {
        HashSet<BasicBlock> allNodesMinusRoot = new HashSet<>(cfg.getBasicBlocks());
        dominatorsMap = new HashMap<>();

        // D[root] = {root}
//        BasicBlock root = cfg.getRootBasicBlock();
        BasicBlock root = cfg.leadersToBBMap.get("Entry");
        HashSet<BasicBlock> rootDominatorSet = new HashSet<>();
        rootDominatorSet.add(root);
        dominatorsMap.put(root, rootDominatorSet);

        // initialize D[n] = N
        for (BasicBlock bb : cfg.getBasicBlocks()) {
            if(bb != root){
                dominatorsMap.put(bb, new HashSet<>(allNodesMinusRoot));
            }

        }
        HashSet<BasicBlock> allNodes = new HashSet<>(allNodesMinusRoot);

        // N = N - {root}
        allNodesMinusRoot.remove(root);

        // while D changes do
        HashMap<BasicBlock, HashSet<BasicBlock>> oldMap;
        do {
            // cache the old HashMap to check if it changes later
            oldMap = new HashMap<>(dominatorsMap);

            // loop until convergence
            for (BasicBlock bb : allNodesMinusRoot) {

                // Intersection ( D[p] for each p in predecessors[n] )
                HashSet<BasicBlock> dominators = new HashSet<>(allNodes);
                for (BasicBlock pred : bb.getPredecessors()) {
                    dominators.retainAll(dominatorsMap.get(pred));
                }

                // D[n] = {n} Union ( Intersection ( D[p] for each p in predecessors[n] ) )
                dominators.add(bb);
                dominatorsMap.put(bb, dominators);
            }
        } while (!dominatorsMap.equals(oldMap));

        return dominatorsMap;
    }

    // returns a map where there is a key for each node in the CFG
    // and the corresponding value is the set of all nodes that
    // STRICTLY dominate the key node (i.e. the set doesn't
    // contain the key node)
    public HashMap<BasicBlock, HashSet<BasicBlock>> getStrictDominatorsMap() {
        // loop through the dominators map and remove the
        // BasicBlock from its dominators set
        HashMap<BasicBlock, HashSet<BasicBlock>> dominatorsMapCopy = new HashMap<>(dominatorsMap);
        for (BasicBlock bb : dominatorsMapCopy.keySet()) {
            HashSet<BasicBlock> domsCopy = new HashSet<>(dominatorsMap.get(bb));
            domsCopy.remove(bb);
            dominatorsMapCopy.put(bb, domsCopy);
        }

        return dominatorsMapCopy;
    }

    // returns a map where there is a key for each node in the CFG
    // and the corresponding value is the set of all nodes that
    // IMMEDIATELY dominate the key node (i.e. the set contains
    // exactly 1 element)
    public HashMap<BasicBlock, BasicBlock> getImmediateDominatorsMap() {
        HashMap<BasicBlock, HashSet<BasicBlock>> strictDomMap = getStrictDominatorsMap();

        HashSet<BasicBlock> oldBlocks = new HashSet<>(cfg.getBasicBlocks());
        HashSet<BasicBlock> blocks = new HashSet<>(oldBlocks);

        // find node A with only one dominator nodes i.e. idom node
        while (true){
            HashSet<BasicBlock> candidates = new HashSet<>();
            for(BasicBlock bb : oldBlocks){
                HashSet<BasicBlock> doms = strictDomMap.get(bb);
                if(doms.size() <= 1){
                    candidates.add(bb);
                    blocks.remove(bb);
                }
            }
            if(candidates.size() == 0) break;
            oldBlocks = new HashSet<>(blocks);
            // remove A's idom from other nodes' dominator nodes
            for(BasicBlock bb : candidates){
                if(strictDomMap.get(bb).size() == 1){
                    BasicBlock sdomNode = strictDomMap.get(bb).iterator().next();
                    for(BasicBlock b : oldBlocks){
                        strictDomMap.get(b).remove(sdomNode);
                    }
                }
            }
        }

        // each set should be a singleton except for the root node which is null
        HashMap<BasicBlock, BasicBlock> iDomMap = new HashMap<>();
        for (BasicBlock bb : strictDomMap.keySet()) {
            for (BasicBlock iDomNode : strictDomMap.get(bb)) {
                iDomMap.put(bb, iDomNode);
            }
        }

        return iDomMap;
    }

    // returns a dictionary where each node points to the set of
    // nodes that are its children in the dominator tree
    public HashMap<BasicBlock, HashSet<BasicBlock>> getDominatingTreeMap() {
        HashMap<BasicBlock, BasicBlock> iDomMap = getImmediateDominatorsMap();

        // initialize each set to be empty
        HashMap<BasicBlock, HashSet<BasicBlock>> nodeToDominatingSet = new HashMap<>();
        for (BasicBlock bb : cfg.getBasicBlocks()) {
            nodeToDominatingSet.put(bb, new HashSet<>());
        }

        // add the nodes that bb immediately dominates to its set
        for (BasicBlock bb : iDomMap.keySet()) {
            BasicBlock iDom = iDomMap.get(bb);
            nodeToDominatingSet.get(iDom).add(bb);
        }

        return nodeToDominatingSet;
    }

    public Node createDominatorTree(HashMap<BasicBlock, HashSet<BasicBlock>> dominatorsMap){
        label2Node = new HashMap<>();
        for(BasicBlock bb: dominatorsMap.keySet()){
            Node curNode = new Node(bb);
            label2Node.put(bb.name, curNode );
        }
        for(BasicBlock bb: dominatorsMap.keySet()){
            Node curNode = label2Node.get(bb.name);
            for(BasicBlock b: dominatorsMap.get(bb)){
                Node dmNode = label2Node.get(b.name);
                curNode.addchild(dmNode);
            }
        }
        Node root = label2Node.get("Entry");
        this.id = 0;
        dfs(root);
        return root;
    }
    public void dfs(Node root){
        if(root == null) return;
        root.setId(this.id);
        this.id += 1;
        for(Node node : root.getChildrenNodes()){
            dfs(node);
        }
    }

    public String toGraphviz(){
        GraphViz graphViz = new GraphViz();
        for (Node node : this.label2Node.values()) {
            String srcNode = node.toString();
            graphViz.nodes.put(srcNode,false);
            for(Node child : node.getChildrenNodes()){
                graphViz.edges.map(srcNode,child.toString());
            }
        }
        return graphViz.toDOT2();
    }

    // returns the set of all the back edges in the CFG
    public HashSet<BackEdge> getBackEdges() {
        HashMap<BasicBlock, HashSet<BasicBlock>> dominatingMap = getDominatingTreeMap();
        HashSet<BackEdge> backEdges = new HashSet<>();

        // find each back edge that exists in the CFG
        for (BasicBlock bb : dominatingMap.keySet()) {
            for (BasicBlock succ : dominatingMap.get(bb)) {

                // since bb dominates succ, then if bb is the successor of succ, (succ --> bb) is a back edge
                if ((succ.getAlternativeBranch() != null && succ.getAlternativeBranch().equals(bb))
                        || (succ.getDefaultBranch() != null && succ.getDefaultBranch().equals(bb))) {
                    backEdges.add(new BackEdge(bb, succ));
                }
            }
        }
        return backEdges;
    }

    // returns a map where the key is a loop header and the value
    // is the merged loop of all the natural loops that stem from
    // that header
    public HashMap<BasicBlock, Loop> getHeadersToLoopMap(CFG cfg) {
        HashSet<BackEdge> backEdges = getBackEdges();
        HashMap<BasicBlock, HashSet<Loop>> headersToLoopsSet = new HashMap<>();

        // Initialization: header --> empty set
        for (BackEdge edge : backEdges) {
            headersToLoopsSet.put(edge.getHeaderNode(), new HashSet<>());
        }

        // add all of the loops to the set corresponding to the header
        for (BackEdge edge : backEdges) {
            Loop loop = Loop.loopForBackEdge(edge);
            headersToLoopsSet.get(edge.getHeaderNode()).add(loop);
        }

        // merge all of the natural loops so each
        // header only corresponds to 1 super loop
        HashMap<BasicBlock, Loop> loopsMap = new HashMap<>();
        for (BasicBlock header : headersToLoopsSet.keySet()) {
            Loop mergedLoop = Loop.mergeLoops(header, headersToLoopsSet.get(header));
            loopsMap.put(header, mergedLoop);
        }

        return loopsMap;
    }

    // returns a map where the key is a loop header and the value is
    // the set of headers that the key immediately dominates
    public static HashMap<BasicBlock, HashSet<BasicBlock>> getHeadersImmediateDominatorsGraph(HashMap<BasicBlock, Loop> headersToLoops) {
        Set<BasicBlock> headersSet = headersToLoops.keySet();

        // find the set of loop headers that each header immediately dominates
        HashMap<BasicBlock, HashSet<BasicBlock>> headersImmediateDomMap = new HashMap<>();
        for (BasicBlock header : headersToLoops.keySet()) {
            Loop headerLoop = headersToLoops.get(header);

            // we only care about the nodes that are nested headers
            HashSet<BasicBlock> nestedHeaders = new HashSet<>(headerLoop.getLoopBlocksSet());
            nestedHeaders.retainAll(headersSet);

            // header does not immediately dominate x if there exists a
            // y such that header dominates y but both header and y both dominate x
            HashSet<BasicBlock> immediateNestedHeaders = new HashSet<>(nestedHeaders);
            for (BasicBlock x : nestedHeaders) {

                // for each y in {nestedHeaders} - {x}
                HashSet<BasicBlock> headersMINUSx = new HashSet<>(nestedHeaders);
                headersMINUSx.remove(x);
                for (BasicBlock y : headersMINUSx) {

                    // this is a y that header dominates but this y also
                    // dominates x so header does not immediately dominate x
                    if (headersToLoops.get(y).getLoopBlocksSet().contains(x)) {
                        immediateNestedHeaders.remove(x);
                    }
                }
            }

            headersImmediateDomMap.put(header, immediateNestedHeaders);
        }
        return headersImmediateDomMap;
    }


    // IMPORTANT: this optimization should not be called more than once (most likely)
//    public static void performLoopInvariantCodeMotionOnCFG(CFG cfg) {
//        HashMap<BasicBlock, Loop> headersToLoopMap = getHeadersToLoopMap(cfg);
//        HashMap<BasicBlock, HashSet<BasicBlock>> headersDominatorGraph = getHeadersImmediateDominatorsGraph(headersToLoopMap);
//
//        // find the outermost root nodes in the graph (i.e. the entry points)
//        HashSet<BasicBlock> rootNodes = new HashSet<>(headersDominatorGraph.keySet());
//        for (BasicBlock header : headersDominatorGraph.keySet()) {
//
//            // consider all the other headers when deciding if header is an entry point
//            HashSet<BasicBlock> otherHeaders = new HashSet<>(headersDominatorGraph.keySet());
//            otherHeaders.remove(header);
//            for (BasicBlock otherHeader : otherHeaders) {
//
//                // if another header dominates the current header, then this is not a root node
//                if (headersDominatorGraph.get(otherHeader).contains(header)) {
//                    rootNodes.remove(header);
//                }
//            }
//        }
//
//        // 2) preform DFS on this nested hierarchy to obtain the
//        // correct order to perform code hoisting on
//        Stack<BasicBlock> masterOrderStack = new Stack<>();
//        for (BasicBlock rootLoop : rootNodes) {
//            masterOrderStack.push(rootLoop);
//
//            // initialize DFS
//            Stack<BasicBlock> localStack = new Stack<>();
//            localStack.push(rootLoop);
//            HashSet<BasicBlock> visited = new HashSet<>();
//
//            // run DFS
//            while (localStack.size() > 0) {
//                BasicBlock loop = localStack.pop();
//
//                if (!visited.contains(loop)) {
//                    visited.add(loop);
//
//                    for (BasicBlock nestedLoop : headersDominatorGraph.get(loop)) {
//                        localStack.push(nestedLoop);
//
//                        // add to master stack to ensure the correct code-hoisting order
//                        masterOrderStack.push(nestedLoop);
//                    }
//                }
//            }
//        }
//
//        // 3) perform loop invariant code motion in
//        // the order that the nodes get popped from the stack
//        while (masterOrderStack.size() > 0) {
//            BasicBlock loopHeader = masterOrderStack.pop();
//            Loop loop = headersToLoopMap.get(loopHeader);
//            Loop.performCodeHoistingOnLoop(loop, cfg);
//        }
//    }


    public static class BackEdge {
        private final BasicBlock headerNode;
        private final BasicBlock exitNode;

        public BackEdge(BasicBlock headerNode, BasicBlock exitNode) {
            this.headerNode = headerNode;
            this.exitNode = exitNode;
        }

        public BasicBlock getHeaderNode() {
            return this.headerNode;
        }

        public BasicBlock getExitNode() {
            return this.exitNode;
        }
    }

    public static class Loop {
        private final HashSet<BasicBlock> loopBlocksSet;
        private final ArrayList<BasicBlock> loopBlocksArray;
        private final BasicBlock header;

        private Loop(HashSet<BasicBlock> loopBlocks, BasicBlock header) {
            this.loopBlocksSet = loopBlocks;
            this.header = header;
            this.loopBlocksArray = new ArrayList<>();

            // run DFS to initialize loopBlocksArray to contain
            // the ordered loop nodes (starting from the header)
            LinkedList<BasicBlock> queue = new LinkedList();
            queue.push(this.header);
            HashSet<BasicBlock> visited = new HashSet<>();
            while (queue.size() > 0) {
                BasicBlock node = queue.pop();

                // only look at nodes we haven't visited yet
                if (!visited.contains(node)) {
                    visited.add(node);
                    this.loopBlocksArray.add(node);

                    // if a node has children that are in the loop, add them to the queue
                    if (node.getDefaultBranch() != null
                            && this.loopBlocksSet.contains(node.getDefaultBranch())) {
                        queue.push(node.getDefaultBranch());
                    }
                    if (node.getAlternativeBranch() != null
                            && this.loopBlocksSet.contains(node.getAlternativeBranch())) {
                        queue.push(node.getAlternativeBranch());
                    }
                }
            }
        }

        public HashSet<BasicBlock> getLoopBlocksSet() {
            return this.loopBlocksSet;
        }

        public static Loop loopForBackEdge(BackEdge backEdge) {

            // initialize the set of all nodes in the loop to contain the header node
            HashSet<BasicBlock> loopNodes = new HashSet<>();
            loopNodes.add(backEdge.getHeaderNode());

            // determine the remaining nodes in the loop
            //TODO: 这里有问题
            Stack<BasicBlock> stack = new Stack<>();
            //添加
            stack.push(backEdge.getHeaderNode());
            while (stack.size() > 0) {

                // consider all of the predecessors of each node
                BasicBlock node = stack.pop();
                for (BasicBlock pred : node.getPredecessors()) {

                    // only consider a node once
                    if (!loopNodes.contains(pred)) {
                        loopNodes.add(pred);
//                        stack.push(node); //原来是
                        stack.push(pred);
                    }
                }
            }
            return new Loop(loopNodes, backEdge.getHeaderNode());
        }

        public static Loop mergeLoops(BasicBlock header, HashSet<Loop> loopSet) {
            HashSet<BasicBlock> mergedLoopNodes = new HashSet<>();

            for (Loop loop : loopSet) {
                mergedLoopNodes.addAll(loop.getLoopBlocksSet());
            }

            return new Loop(mergedLoopNodes, header);
        }

        // assumes any nested loops have already have their code hoisted
//        public static void performCodeHoistingOnLoop(Loop loop, CFG cfg) {
//
//            // initialize all stmts to be NOT invariant at the start
//            HashMap<String, Boolean> invariantStmts = new HashMap<>();
//            for (BasicBlock bb : loop.loopBlocksArray) {
//                LinkedHashMap<String, LlStatement> labelsToStmtsMap = bb.getLabelsToStmtsMap();
//
//                for (String label : labelsToStmtsMap.keySet()) {
//                    invariantStmts.put(label, false);
//                }
//            }
//
//            // mark all stmts whose operands are all constants or whose
//            // operands have reaching definitions that exist entirely
//            // outside of the loop
//            for (BasicBlock bb : loop.loopBlocksArray) {
//                LinkedHashMap<String, LlStatement> labelsToStmtsMap = bb.getLabelsToStmtsMap();
//
//                for (String label : labelsToStmtsMap.keySet()) {
//                    LlStatement stmt = labelsToStmtsMap.get(label);
//
//                    // check unaryOps
//                    if (stmt instanceof LlAssignStmtUnaryOp) {
//                        LlAssignStmtUnaryOp unaryOp = (LlAssignStmtUnaryOp) stmt;
//
//                        // is the operand a constant
//                        if (unaryOp.getOperand() instanceof LlLiteral) {
//                            invariantStmts.put(label, true);
//                        }
//
//                        // are all def's for this use outside of the loop
//                        else if (unaryOp.getOperand() instanceof LlLocationVar) {
//                            HashSet<Tuple> defForOpUse = GET_DEFS_FOR_USE(bb,label, unaryOp.getOperand());
//
//                            // check if at least one of the Op's uses are outside the loop
//                            invariantStmts.put(label, true);
//                            for (Tuple tuple : defForOpUse) {
//                                BasicBlock defBlock = tuple.GET_BASIC_BLOCK();
//
//                                if (loop.loopBlocksSet.contains(defBlock)) {
//                                    invariantStmts.put(label, false);
//                                    break;
//                                }
//                            }
//                        }
//                    }
//
//                    // check binaryOps
//                    else if (stmt instanceof LlAssignStmtBinaryOp) {
//                        LlAssignStmtBinaryOp binaryOp = (LlAssignStmtBinaryOp) stmt;
//
//                        if ((binaryOp.getLeftOperand() instanceof LlLiteral)
//                                && (binaryOp.getRightOperand() instanceof LlLiteral)) {
//                            invariantStmts.put(label, true);
//                        }
//
//                        // are all def's for this use outside of the loop
//                        else if ((binaryOp.getLeftOperand() instanceof LlLocationVar)
//                                && (binaryOp.getRightOperand() instanceof LlLocationVar)) {
//                            HashSet<Tuple> defsForLeftOpUse = GET_DEFS_FOR_USE(bb,label, binaryOp.getLeftOperand());
//                            HashSet<Tuple> defsForRightOpUse = GET_DEFS_FOR_USE(bb,label, binaryOp.getRightOperand());
//
//                            // check if at least one of the Ops' uses are outside the loop
//                            invariantStmts.put(label, true);
//                            for (Tuple tuple : defsForLeftOpUse) {
//                                BasicBlock defBlock = tuple.GET_BASIC_BLOCK();
//
//                                if (loop.loopBlocksSet.contains(defBlock)) {
//                                    invariantStmts.put(label, false);
//                                    break;
//                                }
//                            }
//                            for (Tuple tuple : defsForRightOpUse) {
//                                BasicBlock defBlock = tuple.GET_BASIC_BLOCK();
//
//                                if (loop.loopBlocksSet.contains(defBlock)) {
//                                    invariantStmts.put(label, false);
//                                    break;
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//
//            // go through loop stmts again and check if any stmts have
//            // exactly one reaching definition that points to an invariant piece of code
//            for (BasicBlock bb : loop.loopBlocksArray) {
//                LinkedHashMap<String, LlStatement> labelsToStmtsMap = bb.getLabelsToStmtsMap();
//
//                for (String label : labelsToStmtsMap.keySet()) {
//                    LlStatement stmt = labelsToStmtsMap.get(label);
//
//                    // check unaryOps
//                    if (stmt instanceof LlAssignStmtUnaryOp) {
//                        LlAssignStmtUnaryOp unaryOp = (LlAssignStmtUnaryOp) stmt;
//
//                        if (unaryOp.getOperand() instanceof LlLocationVar) {
//                            HashSet<Tuple> defForOpUse = GET_DEFS_FOR_USE(bb,label, unaryOp.getOperand());
//
//                            // if the operand has exactly 1 reaching def and its reaching
//                            // def is invariant, then mark this stmt as invariant
//                            if (defForOpUse.size() == 1) {
//
//                                for (Tuple tuple : defForOpUse) {
//                                    String defLineLabel = tuple.GET_LABEL();
//
//                                    // exactly 1 def, and that def is invariant
//                                    if (invariantStmts.containsKey(defLineLabel) && invariantStmts.get(defLineLabel) == true) {
//                                        invariantStmts.put(label, true);
//                                    }
//                                    break;
//                                }
//                            }
//                        }
//                    }
//
//                    // check binaryOps
//                    if (stmt instanceof LlAssignStmtBinaryOp) {
//                        LlAssignStmtBinaryOp binaryOp = (LlAssignStmtBinaryOp) stmt;
//
//                        if ((binaryOp.getLeftOperand() instanceof LlLocationVar)
//                                && (binaryOp.getRightOperand() instanceof LlLocationVar)) {
//                            HashSet<Tuple> defsForLeftOpUse = GET_DEFS_FOR_USE(bb,label, binaryOp.getLeftOperand());
//                            HashSet<Tuple> defsForRightOpUse = GET_DEFS_FOR_USE(bb,label, binaryOp.getRightOperand());
//
//                            // if left operand and right operand have exactly 1 reaching definition
//                            boolean leftIsInvariant = false;
//                            boolean rightIsInvariant = false;
//                            if ((defsForLeftOpUse.size() == 1) && (defsForRightOpUse.size() == 1)) {
//
//                                // the left op must have a reaching def that is loop invariant for it to also be loop invariant
//                                for (Tuple tuple : defsForLeftOpUse) {
//                                    String defLineLabel = tuple.GET_LABEL();
//
//                                    // exactly 1 def, and that def is invariant
//                                    if (invariantStmts.containsKey(defLineLabel) && invariantStmts.get(defLineLabel) == true) {
//                                        leftIsInvariant = true;
//                                    }
//                                    break;
//                                }
//
//                                // the right op must have a reaching def that is loop invariant for it to also be loop invariant
//                                for (Tuple tuple : defsForRightOpUse) {
//                                    String defLineLabel = tuple.GET_LABEL();
//
//                                    // exactly 1 def, and that def is invariant
//                                    if (invariantStmts.containsKey(defLineLabel) && invariantStmts.get(defLineLabel) == true) {
//                                        rightIsInvariant = true;
//                                    }
//                                    break;
//                                }
//                            }
//
//                            if (leftIsInvariant && rightIsInvariant) {
//                                invariantStmts.put(label, true);
//                            }
//                        }
//                    }
//                }
//            }
//
//
//            //
//        }

    }

}

/*  Goal: Create a hierarchy of the loops such that H => {headers of immediate nested loops}

    Given:  headersToLoopMap = {Headers => Loop}
            headers = {every loop header}

            for each header in headersToLoopMap.keySet():
                Loop loop
                for x in header


    what if I keep track of all of the nodes that I have visited so far so that I don't visit them again

 */































