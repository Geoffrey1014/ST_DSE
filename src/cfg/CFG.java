package cfg;

import helper.LlBuilder;
import helper.LlSymbolTable;
import ll.*;
import ll.assignStmt.LlAssignStmt;
import ll.assignStmt.LlAssignStmtBinaryOp;
import ll.assignStmt.LlAssignStmtRegular;
import ll.assignStmt.LlAssignStmtUnaryOp;
import ll.jump.LlJump;
import ll.jump.LlJumpConditional;
import ll.jump.LlJumpUnconditional;
import ll.location.LlLocation;
import ll.location.LlLocationArray;
import ll.location.LlLocationVar;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CFG {
    private final LlBuilder builder;
    private final ArrayList<BasicBlock> basicBlocks;
    public final LinkedHashMap<String, BasicBlock> leadersToBBMap;
    public LinkedList<String> orderedLeadersList;
    public   LinkedHashMap<BasicBlock, String> blockLabels;
    private GraphViz graphViz;
    private LlSymbolTable llSymbolTable;

    public CFG(LlBuilder builder, LlSymbolTable llSymbolTable, Boolean addLoop) {
        this.builder = builder;
        this.llSymbolTable = llSymbolTable;

        LinkedHashMap<String, LlStatement> labelStmtsMap = new LinkedHashMap<>(builder.getStatementTable());
        ArrayList<String> labelsList = new ArrayList<>(labelStmtsMap.keySet());

        if (labelsList.size() == 0) {
            this.basicBlocks = new ArrayList<>();
            this.orderedLeadersList = new LinkedList<>();
            this.leadersToBBMap = new LinkedHashMap<>();
            this.blockLabels = new LinkedHashMap<>();
        } else {
            // 1) determine the leaders in the LLIR
            HashSet<String> leadersSet = new HashSet<>();

            // the first instruction in the LLIR is a leader
            leadersSet.add(labelsList.get(0));
            leadersSet.add("Init");
            leadersSet.add("Body");


            for (int i = 1; i < labelsList.size(); i++) {
                String label = labelsList.get(i);
                LlStatement stmt = labelStmtsMap.get(label);

                if (stmt instanceof LlJump) {

                    // the TARGET of the jumpStmt is a leader
                    String jmpTolabel = ((LlJump) stmt).getJumpToLabel();
                    leadersSet.add(jmpTolabel);

                    // the stmt FOLLOWING the jumpStmt is a leader
                    if(labelsList.size() > i+1){
                        String nextStmtLabel = labelsList.get(i + 1);
                        leadersSet.add(nextStmtLabel);
                    }

                }
            }

            // 2) create basic blocks from LlStatements
            this.leadersToBBMap = new LinkedHashMap<>();
            HashSet<String> tempLeadersSet = new HashSet<>(leadersSet);
            LinkedList<String> labelsQueue = new LinkedList<>(labelsList);
            this.orderedLeadersList = new LinkedList<>();
            do {
                LinkedHashMap<String, LlStatement> bbLabelsToStmtsMap = new LinkedHashMap<>();

                // basic blocks start with the leader
                String leaderLabel = labelsQueue.pop();
                LlStatement leaderStmt = labelStmtsMap.get(leaderLabel);
                bbLabelsToStmtsMap.put(leaderLabel, leaderStmt);

                // remove this leader from the leadersSet and add it
                // to the leadersList
                tempLeadersSet.remove(leaderLabel);
                this.orderedLeadersList.add(leaderLabel);

                // keep adding LlStatments until you get to the next leader
                while (labelsQueue.size() > 0 && !tempLeadersSet.contains(labelsQueue.peek())) {

                    // keep adding stmts to the currentBBStmtList
                    String label = labelsQueue.pop();
                    LlStatement stmt = labelStmtsMap.get(label);
                    bbLabelsToStmtsMap.put(label, stmt);
                }

                // create the actual BasicBlock and add it to the LinkedHashMap
                BasicBlock bb = new BasicBlock(bbLabelsToStmtsMap, builder);
                this.leadersToBBMap.put(leaderLabel, bb);

            } while (labelsQueue.size() > 0);

            // 3) appropriately connect the basic blocks
            for (int i = 0; i < this.orderedLeadersList.size(); i++) { // loop through the leaders in the order of the linear order of the basic blocks
                String leaderLabel = this.orderedLeadersList.get(i);
                BasicBlock bb = this.leadersToBBMap.get(leaderLabel);

                List<LlStatement> bbStmtsList = bb.getStmtsList();
                LlStatement lastStmtOfCurrentBB = bbStmtsList.get(bbStmtsList.size() - 1);

                // connect if there is a jump from the end of B to the beginning of C
                if (lastStmtOfCurrentBB instanceof LlJump) {

                    // set forward edge B --> C
                    String targetLabel = ((LlJump) lastStmtOfCurrentBB).getJumpToLabel();
                    BasicBlock targetBB = this.leadersToBBMap.get(targetLabel);
                    bb.setAlternativeBranch(targetBB);

                    System.out.println(targetLabel);
                    // set reverse edge B <-- C
                    targetBB.addPredecessorNode(bb);
                }

                // C immediately follows B and B does not end in an unconditional jump
                // (this only holds if B is not the last block))
                if (!(lastStmtOfCurrentBB instanceof LlJumpUnconditional) && (i < this.orderedLeadersList.size() - 1)) {

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
            String trueLastBBLabel = this.orderedLeadersList.get(this.orderedLeadersList.size() - 1);
            BasicBlock trueLastBB = this.leadersToBBMap.get(trueLastBBLabel);
            // after add Entry, End, Exit and Read block, trueLastBB is the Fifth from last

            BasicBlock entryBB = createEmptyBB("Entry");
            entryBB.setDefaultBranch(trueFirstBB);
            trueFirstBB.addPredecessorNode(entryBB);


            BasicBlock endBB = createReadOrPrintBB("End",llSymbolTable);
            trueLastBB.setDefaultBranch(endBB);
            endBB.addPredecessorNode(trueLastBB);

            if(addLoop){
                // add read input block
                String readInputBBLabel = "Read";
                BasicBlock readBB = createReadOrPrintBB(readInputBBLabel, llSymbolTable);
                endBB.setDefaultBranch(readBB);
                readBB.addPredecessorNode(endBB);
                BasicBlock body = this.leadersToBBMap.get("Body");
                readBB.setDefaultBranch(body);
                body.addPredecessorNode(readBB);

                // init-node connects to read-node
                BasicBlock initBB = this.leadersToBBMap.get("Init");
                initBB.setDefaultBranch(readBB);
                readBB.addPredecessorNode(initBB);
                body.rmPredecessorNode(initBB);

                // add an empty BasicBlock as the exit node and
                // connect it and the orignal last BB to each other
                BasicBlock exitBB = createEmptyBB("Exit");
                endBB.setAlternativeBranch(exitBB);
                exitBB.addPredecessorNode(endBB);

            }

            removeUnconditionalJumpBlocks();

            // 5) assign the list of basic blocks as a field of THIS object
            ArrayList<BasicBlock> basicBlocks = new ArrayList<>();
            for (String leaderLabel : this.orderedLeadersList) {
                basicBlocks.add(this.leadersToBBMap.get(leaderLabel));
            }
            for (String label : this.leadersToBBMap.keySet()){
                this.leadersToBBMap.get(label).name = label;
            }
            this.blockLabels = new LinkedHashMap<>(reverse(leadersToBBMap));
            this.basicBlocks = basicBlocks;
        }
    }
    public void removeUnconditionalJumpBlocks(){
        System.out.println("\nremoveGotoBlocks");

        for (Iterator<String> iterator = this.orderedLeadersList.iterator(); iterator.hasNext();) {
            String leaderLabel = iterator.next();
            if(leaderLabel.startsWith("UCJ") ){
                iterator.remove();
                BasicBlock uselessBB = this.leadersToBBMap.get(leaderLabel);
                BasicBlock predecessor = uselessBB.getPredecessors().iterator().next();
                BasicBlock successor = uselessBB.getAlternativeBranch();

                predecessor.setDefaultBranch(successor);
                successor.rmPredecessorNode(uselessBB);
                successor.addPredecessorNode(predecessor);
                this.leadersToBBMap.remove(leaderLabel);
            }
            else if(leaderLabel.startsWith("END_ELSIF")){
                BasicBlock uselessBB = this.leadersToBBMap.get(leaderLabel);
                if(uselessBB.getStmtsList().size() == 1){
                    iterator.remove();
                    BasicBlock predecessor = uselessBB.getPredecessors().iterator().next();
                    BasicBlock successor = uselessBB.getDefaultBranch();

                    predecessor.setDefaultBranch(successor);
                    successor.rmPredecessorNode(uselessBB);
                    successor.addPredecessorNode(predecessor);
                    this.leadersToBBMap.remove(leaderLabel);
                }

            }
            else if(leaderLabel.startsWith("ELSIF")){
                BasicBlock uselessBB = this.leadersToBBMap.get(leaderLabel);
                if(uselessBB.getStmtsList().size() == 1){
                    iterator.remove();
                    BasicBlock predecessor = uselessBB.getPredecessors().iterator().next();
                    BasicBlock successor = uselessBB.getDefaultBranch();

                    predecessor.setAlternativeBranch(successor);
                    successor.rmPredecessorNode(uselessBB);
                    successor.addPredecessorNode(predecessor);
                    this.leadersToBBMap.remove(leaderLabel);
                }
            }
        }
//        this.orderedLeadersList = new LinkedList<>(this.leadersToBBMap.keySet());
    }

    private BasicBlock createEmptyBB(String label){
        this.orderedLeadersList.add(label);
        LinkedHashMap<String, LlStatement> bBStmtsList = new LinkedHashMap<>();
        bBStmtsList.put(label, new LlEmptyStmt());
        BasicBlock bb = new BasicBlock(bBStmtsList, builder);
        this.leadersToBBMap.put(label, bb);
        return bb;
    }
    private BasicBlock createReadOrPrintBB(String label, LlSymbolTable llSymbolTable){
        this.orderedLeadersList.add(label);
        LinkedHashMap<String, LlStatement> bBStmtsList = new LinkedHashMap<>();
        bBStmtsList.put(label, new LlEmptyStmt());
        if(label.equals("Read")){
            for (LlComponent llComponent : llSymbolTable.varInput.keySet()) {
                bBStmtsList.put(this.builder.generateLabel(), new LlMethodCallStmt("read", new ArrayList<>(), (LlLocation) llComponent));
            }
        }
        else{
            for (LlComponent llComponent : llSymbolTable.varNonInput.keySet()) {
                ArrayList<LlComponent> args = new ArrayList<>();
                args.add(llComponent);
                bBStmtsList.put(this.builder.generateLabel(), new LlMethodCallStmt("print", args));
            }
        }
        BasicBlock bb = new BasicBlock(bBStmtsList, builder);
        this.leadersToBBMap.put(label, bb);
        return bb;
    }

    public static String getblockLeaderLabel(BasicBlock bb){
        // hacky way to get the first element from the keySet() of labels
        String blockLeader = "";
        for (String leader : bb.getLabelsToStmtsMap().keySet()) {
            blockLeader = leader;
            break;
        }
        return blockLeader;
    }

    public BasicBlock getRootBasicBlock() {
        return this.basicBlocks.get(0);
    }

    public String toGraphviz(){
        //  TODO: move this function out of CFG class
        this.graphViz  = new GraphViz(); // 这里重新new一个，会不会太浪费资源？？
        for (BasicBlock bb : this.basicBlocks) {
//            String label = getblockLeaderLabel(bb);
            String label = bb.toString();
            this.graphViz.nodes.add(label);
            if (bb.getDefaultBranch() != null){
//                this.graphViz.edges.map(label, getblockLeaderLabel(bb.getDefaultBranch()));
                this.graphViz.edges.map(label,bb.getDefaultBranch().toString() +"---default");
            }
            if(bb.getAlternativeBranch() != null){
//                this.graphViz.edges.map(label,getblockLeaderLabel(bb.getAlternativeBranch()));
                this.graphViz.edges.map(label,bb.getAlternativeBranch().toString()+ "---alter");
            }
        }
        return this.graphViz.toDOT();
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

    private static HashMap<BasicBlock, String> reverse(Map<String, BasicBlock> map) {
        HashMap<BasicBlock, String> rev = new HashMap<BasicBlock, String>();
        for (Map.Entry<String, BasicBlock> entry : map.entrySet())
            rev.put(entry.getValue(), entry.getKey());
        return rev;
    }

    public LlBuilder reorderLables() {
        int counter = 0;
        Hashtable<String, String> oldToNew = new Hashtable<>();
        LlBuilder currentBuilder = this.getBuilder();
        LlBuilder newBuilder = new LlBuilder(currentBuilder.getName());
        for (String lable : currentBuilder.getStatementTable().keySet()) {
            String newLabel = lable;
            Pattern p = Pattern.compile("[\\d]+");

            // get a matcher object
            Matcher m = p.matcher(lable);
            newLabel = m.replaceAll(Integer.toString(counter++));
            oldToNew.put(lable, newLabel);
            newBuilder.appendStatement(newLabel, currentBuilder.getStatementTable().get(lable));
        }
        for (String newLabel : newBuilder.getStatementTable().keySet()) {
            LlStatement currentStatement = newBuilder.getStatementTable().get(newLabel);
            if (currentStatement instanceof LlJumpUnconditional) {
                String newl = oldToNew.get(((LlJumpUnconditional) currentStatement).getJumpToLabel());
                newBuilder.getStatementTable().replace(newLabel, new LlJumpUnconditional(newl));

            }
            if (newBuilder.getStatementTable().get(newLabel) instanceof LlJumpConditional) {
                String newl = oldToNew.get(((LlJumpConditional) currentStatement).getJumpToLabel());
                newBuilder.getStatementTable().replace(newLabel, new LlJumpConditional(newl, ((LlJumpConditional) currentStatement).getCondition()));
            }
        }
        return newBuilder;
    }


    // ================= Tuple =================

    private final defBlockLocationTuple noDefTuple = new defBlockLocationTuple("NO_DEF_1010", "NO_DEF_1010");

    public defBlockLocationTuple getNoDefTuple() {
        return this.noDefTuple;
    }

    public LlSymbolTable getLlSymbolTable() {
        return llSymbolTable;
    }

    public class defBlockLocationTuple {
        public String blockName;

        public String label;

        public defBlockLocationTuple(String x, String y) {
            this.blockName = x;
            this.label = y;
        }

        @Override
        public boolean equals(Object o) {
            return (o instanceof defBlockLocationTuple) && (((defBlockLocationTuple) o).blockName.equals(this.blockName)) &&
                    (((defBlockLocationTuple) o).label.equals(this.label));
        }

        @Override
        public int hashCode() {
            return this.blockName.hashCode() + this.label.hashCode();
        }

        @Override
        public String toString() {
            return "(" + this.blockName + ", " + this.label + ")";
        }

    }

    public class SymbolDef {
        public LlLocation symbol;

        public defBlockLocationTuple useDef;

        public SymbolDef(LlLocation symbol, defBlockLocationTuple useDef) {
            this.useDef = useDef;
            this.symbol = symbol;
        }

        @Override
        public boolean equals(Object o) {
            return (o instanceof SymbolDef) && (((SymbolDef) o).symbol.equals(this.symbol)) &&
                    (((SymbolDef) o).useDef.equals(this.useDef));
        }

        @Override
        public int hashCode() {
            return this.symbol.hashCode() + this.useDef.hashCode();
        }

        @Override
        public String toString() {
            return this.symbol.toString() + " : " + this.useDef.toString();
        }

    }

    // ================= USE-DEF Chain =================
    public HashMap<SymbolDef, ArrayList<defBlockLocationTuple>> defUseChain = new HashMap<>();
    public HashMap<SymbolDef, ArrayList<defBlockLocationTuple>> useDefChain = new HashMap<>();
    private HashSet<Edge> isVisited = new HashSet<>();

    //Mark use of arg at currentUseDefLocation in defUseChain using recentDef
    private void addUseArg(HashMap<LlLocation, defBlockLocationTuple> recentDef, LlComponent arg, defBlockLocationTuple currentUseDefLocation) {
        if (arg instanceof LlLocation) {

            //Default value (0) being used in arg
            if (!recentDef.containsKey(arg)) {
                recentDef.put((LlLocation) arg, this.noDefTuple);
                this.defUseChain.put(new SymbolDef((LlLocation) arg, this.noDefTuple), new ArrayList<>());
            }

            //Add use to ArrayList of uses corresponding to recent def of LlLocation arg
            defBlockLocationTuple latestDef = recentDef.get(arg);
            ArrayList<defBlockLocationTuple> useList = this.defUseChain.get(new SymbolDef((LlLocation) arg, latestDef));
            useList.add(currentUseDefLocation);
        }

        if (arg instanceof LlLocationArray) {
            LlLocationArray locationArrayArg = (LlLocationArray) arg;

            if (locationArrayArg.getElementIndex() instanceof LlLocationVar) {
                LlLocationVar indexArg = (LlLocationVar) locationArrayArg.getElementIndex();
                this.addUseArg(recentDef, indexArg, currentUseDefLocation);
            }
        }
    }


    //Recursively (DFS) build defUseChains
    private void buildDefUseRecursive(BasicBlock head, HashMap<LlLocation, defBlockLocationTuple> recentDef) {
        //Add def-use chains of basic block head
        if (head == null) {
            return;
        }
        Edge left = head.getDefaultEdge();
        Edge right = head.getAlterName();
        if(isVisited.contains(left) && isVisited.contains(right)){
            return;
        }
        for (Map.Entry<String, LlStatement> statementRow : head.getLabelsToStmtsMap().entrySet()) {
            String label = statementRow.getKey();
            LlStatement statement = statementRow.getValue();

            //Tuple corresponding to location (blockName, label) of current statement
            //All uses and defs in statement happen at this location
            defBlockLocationTuple currentUseDefLocation = new defBlockLocationTuple(blockLabels.get(head), label);

            //Method call statements
            if (statement instanceof LlMethodCallStmt) {
                //Mark useDef for returnLocation
                if (((LlMethodCallStmt) statement).getReturnLocation() != null) {
                    LlLocation returnLocation = ((LlMethodCallStmt) statement).getReturnLocation();
                    SymbolDef currentSymbolDef = new SymbolDef(returnLocation, currentUseDefLocation);

                    recentDef.put(returnLocation, currentUseDefLocation);
                    this.defUseChain.put(currentSymbolDef, new ArrayList<>());

                    //Get index from array def call
                    if (returnLocation instanceof LlLocationArray) {
                        LlLocationArray returnLocationArray = (LlLocationArray) returnLocation;

                        if (returnLocationArray.getElementIndex() instanceof LlLocationVar) {
                            LlLocationVar indexArg = (LlLocationVar) returnLocationArray.getElementIndex();
                            this.addUseArg(recentDef, indexArg, currentUseDefLocation);
                        }
                    }
                }

                //Mark use for argsList values
                for (LlComponent arg : ((LlMethodCallStmt) statement).getArgsList()) {
                    this.addUseArg(recentDef, arg, currentUseDefLocation);
                }
            }

            //Conditional jump statements
            else if (statement instanceof LlJumpConditional) {
                LlComponent arg = ((LlJumpConditional) statement).getCondition();

                //Mark use of arg location
                this.addUseArg(recentDef, arg, currentUseDefLocation);
            }

            //Return statements
            else if (statement instanceof LlReturn) {
                //Mark variable use for return statement
                LlComponent arg = ((LlReturn) statement).getReturnValue();
                this.addUseArg(recentDef, arg, currentUseDefLocation);
            }

            //Assign statements and sub-class statements
            else if (statement instanceof LlAssignStmt) {

                //Mark useDef for storeLocation
                LlLocation returnLocation = ((LlAssignStmt) statement).getStoreLocation();
                SymbolDef currentSymbolDef = new SymbolDef(returnLocation, currentUseDefLocation);

                recentDef.put(returnLocation, currentUseDefLocation);
                this.defUseChain.put(currentSymbolDef, new ArrayList<>());

                //Get index from array def call
                if (returnLocation instanceof LlLocationArray) {
                    LlLocationArray returnLocationArray = (LlLocationArray) returnLocation;

                    if (returnLocationArray.getElementIndex() instanceof LlLocationVar) {
                        LlLocationVar indexArg = (LlLocationVar) returnLocationArray.getElementIndex();
                        this.addUseArg(recentDef, indexArg, currentUseDefLocation);
                    }
                }

                if (statement instanceof LlAssignStmtRegular) {
                    //Mark use of arg location
                    LlComponent arg = ((LlAssignStmtRegular) statement).getOperand();
                    this.addUseArg(recentDef, arg, currentUseDefLocation);
                } else if (statement instanceof LlAssignStmtUnaryOp) {
                    //Mark use of arg location
                    LlComponent arg = ((LlAssignStmtUnaryOp) statement).getOperand();
                    this.addUseArg(recentDef, arg, currentUseDefLocation);
                } else if (statement instanceof LlAssignStmtBinaryOp) {
                    //Mark use of leftArg and rightArg location
                    LlComponent leftArg = ((LlAssignStmtBinaryOp) statement).getLeftOperand();
                    LlComponent rightArg = ((LlAssignStmtBinaryOp) statement).getRightOperand();
                    this.addUseArg(recentDef, leftArg, currentUseDefLocation);
                    this.addUseArg(recentDef, rightArg, currentUseDefLocation);
                }
            }
        }

        //Visit default and alternate branches to continue depth first search


        if (left != null && !isVisited.contains(left)) {
            isVisited.add(head.getDefaultEdge());
            buildDefUseRecursive(head.getDefaultBranch(), new HashMap<>(recentDef));
        }

        if (right != null && !isVisited.contains(right)) {
            isVisited.add(head.getAlterName());
            buildDefUseRecursive(head.getAlternativeBranch(), new HashMap<>(recentDef));
        }
    }

    // =================== transform to BlockLabelPairs ==================

    public HashMap<SymbolDef, HashSet<BlockLabelPair>> getDefsForUseAsBlockLabelPairs() {
        HashMap<SymbolDef, ArrayList<defBlockLocationTuple>> useDefsWithTuples = this.buildUseDefChains();
        HashMap<SymbolDef, HashSet<BlockLabelPair>> useDefsAsBlockLabelPairs = new HashMap<>();

        // loop through each symbol used
        for (SymbolDef symbolDef : useDefsWithTuples.keySet()) {
            ArrayList<defBlockLocationTuple> defsArrayList = useDefsWithTuples.get(symbolDef);
            useDefsAsBlockLabelPairs.put(symbolDef, new HashSet<>());

            // add each def that corresponds that that symbol's use to that
            // the BlockLabelPair HashSet
            for (defBlockLocationTuple defBlockLocationTuple : defsArrayList) {
                BasicBlock bb = this.getLeadersToBBMap().get(defBlockLocationTuple.blockName);
                BlockLabelPair blockLabelPair = new BlockLabelPair(bb, defBlockLocationTuple.label);
                useDefsAsBlockLabelPairs.get(symbolDef).add(blockLabelPair);
            }
        }
        return useDefsAsBlockLabelPairs;
    }

    //Build def-use chains for each symbol from updated/changed LlBuilder
    public HashMap<SymbolDef, ArrayList<defBlockLocationTuple>> buildDefUseChains() {
        this.defUseChain = new HashMap<>();
        this.isVisited = new HashSet<>();
        BasicBlock head = this.basicBlocks.get(0);
        HashMap<LlLocation, defBlockLocationTuple> recentDef = new HashMap<>();
        buildDefUseRecursive(head, recentDef);

        //All uses and defs in statement happen at this location
//        Tuple firstUseDefLocation = new Tuple(blockLabels.get(head), "L0");
//
//        for (LlLocationVar paramArg : this.paramsList) {
//            SymbolDef currentSymbolDef = new SymbolDef(paramArg, firstUseDefLocation);
//            recentDef.put(paramArg, firstUseDefLocation);
//            this.defUseChain.put(currentSymbolDef, new ArrayList<>());
//        }

//        //Print statements for useDefChains

        return new HashMap<SymbolDef, ArrayList<defBlockLocationTuple>>(this.defUseChain);
    }

    //Build use-def chains for each symbol from updated/changed LlBuilder
    public HashMap<SymbolDef, ArrayList<defBlockLocationTuple>> buildUseDefChains() {
        this.buildDefUseChains();
        this.useDefChain = new HashMap<>();
        for (Map.Entry<SymbolDef, ArrayList<defBlockLocationTuple>> duChain : this.defUseChain.entrySet()) {
            ArrayList<defBlockLocationTuple> useList = duChain.getValue();
            LlLocation symbol = duChain.getKey().symbol;
            defBlockLocationTuple defLocation = duChain.getKey().useDef;

            //Add reaching defs for each corresponding use to useDefChain
            for (defBlockLocationTuple useLocation : useList) {
                SymbolDef currentUseSymbol = new SymbolDef(symbol, useLocation);
                if (!this.useDefChain.containsKey(currentUseSymbol)) {
                    ArrayList<defBlockLocationTuple> defList = new ArrayList<>();
                    defList.add(defLocation);
                    this.useDefChain.put(currentUseSymbol, defList);
                } else {
                    this.useDefChain.get(currentUseSymbol).add(defLocation);
                }
            }
        }
        return this.useDefChain;
    }

    public LinkedHashMap<String, BasicBlock> getLeadersToBBMap() {
        return new LinkedHashMap<>(leadersToBBMap);
    }



}