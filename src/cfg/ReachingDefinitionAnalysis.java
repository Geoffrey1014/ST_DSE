package cfg;

import ll.LlComponent;
import ll.LlMethodCallStmt;
import ll.LlStatement;
import ll.assignStmt.LlAssignStmt;
import ll.assignStmt.LlAssignStmtBinaryOp;
import ll.assignStmt.LlAssignStmtRegular;
import ll.assignStmt.LlAssignStmtUnaryOp;
import ll.location.LlLocation;
import ll.location.LlLocationVar;

import java.util.*;

public class ReachingDefinitionAnalysis {

    /**
     * •  Each basic block has
     * –  IN - set of definitions that reach beginning of block
     * –  OUT - set of definitions that reach end of block
     * –  GEN - set of definitions generated in block
     * –  KILL - set of definitions killed in block
     * <p>
     * •  GEN[s = s + a*b; i = i + 1;] = 0000011
     * •  KILL[s = s + a*b; i = i + 1;] = 1010000
     * •  Compiler scans each basic block to derive GEN and KILL sets
     * <p>
     * Algorithm:
     * for all nodes n in N
     * OUT[n] = emptyset; // OUT[n] = GEN[n];
     * IN[Entry] = emptyset;
     * OUT[Entry] = GEN[Entry];
     * Changed = N - { Entry }; // N = all nodes in graph
     * <p>
     * while (Changed != emptyset)
     * choose a node n in Changed;
     * Changed = Changed - { n };
     * <p>
     * IN[n] = emptyset;
     * for all nodes p in predecessors(n)
     * IN[n] = IN[n] U OUT[p];
     * <p>
     * OUT[n] = GEN[n] U (IN[n] - KILL[n]);
     * <p>
     * if (OUT[n] changed)
     * for all nodes s in successors(n)
     * Changed = Changed U { s };
     */
    private final HashMap<BasicBlock, HashSet<Def>> defsReachIN = new HashMap<>();
    private final HashMap<BasicBlock, HashSet<Def>> defsReachOUT = new HashMap<>();
    private final HashMap<BasicBlock, HashSet<Def>> bb2Gens = new HashMap<>();
    private final HashMap<BasicBlock, HashSet<Def>> bb2Kills = new HashMap<>();
    HashMap<Use, HashSet<Def>> useDefsChains;
    private HashMap<LlLocation, HashSet<Def>> universalMap;
    private CFG cfg;

    public void printUseDefsChains(){
        for(Use use: useDefsChains.keySet()){
            System.out.println("\nuse: "+use.locationUsage.toString() + " @ " + use.stmtLabel);
            System.out.println("defs:");
            HashSet<Def> defs = useDefsChains.get(use);
            for(Def def : defs){
                System.out.println(def.statement.toString() + " @ " + def.stmtLabel);
            }
        }
    }

    public ReachingDefinitionAnalysis(CFG cfg) {
        this.cfg = cfg;
        ArrayList<BasicBlock> bbList = cfg.getBasicBlocks();

        // 1) initial allExpressions with the union of all EVAL(bb)'s
        HashSet<Def> universalSet = new HashSet<>();
        for (BasicBlock bb : bbList) {
            universalSet.addAll(GEN(bb));
        }

//        for(Def def: this.universalSet){
//            if(this.universalMap.get(def.location) == null){
//                HashSet<Def> set = new HashSet<>();
//                set.add(def);
//                this.universalMap.put(def.location, set );
//            }
//            else{
//                this.universalMap.get(def.location).add(def);
//            }
//        }
        this.universalMap = defSet2MapOfVar2Defs(universalSet);


        // 2) perform the worklist algorithm
        //for all nodes n in N
        //     OUT[n] = emptyset; // OUT[n] = GEN[n];
        for (BasicBlock bb : bbList) {
            this.defsReachOUT.put(bb, new HashSet<>());
        }

        ArrayList<BasicBlock> activeNodes = new ArrayList<>(bbList);
        BasicBlock entry = activeNodes.get(0);

        // IN[Entry] = emptyset;
        this.defsReachIN.put(entry, new HashSet<>());

        // OUT[Entry] = GEN[Entry];
        this.defsReachOUT.put(entry, GEN(entry));

        // Changed = N - {Entry};
        activeNodes.remove(0); // remove the entry since it was accounted for

        while (activeNodes.size() > 0) {

            // get a node and remove it from active nodes
            BasicBlock node = activeNodes.get(0);
            activeNodes.remove(0);
            HashSet<Def> oldOUT = this.defsReachOUT.get(node);

            HashSet<Def> IN = new HashSet<>(); // IN[n] = empty
            // IN[n] = IN[n] intersect OUT[p] for all p in predecessors
            for (BasicBlock pred : node.getPredecessors()) {
                IN.addAll(this.defsReachOUT.get(pred));
            }

            this.defsReachIN.put(node, IN);

            // KILL[n]
            HashSet<Def> kills = KILL(node, new HashSet<>(universalSet));

            // (IN[n]-KILL[n])
            HashSet<Def> INminusKILL = new HashSet<>(this.defsReachIN.get(node)); // make separate copy of IN
            INminusKILL.removeAll(kills);

            // OUT[n] = GEN[n] U (IN[n]-KILL[n])
            HashSet<Def> GENplusINminusKILL = GEN(node);
            GENplusINminusKILL.addAll(INminusKILL);
            this.defsReachOUT.put(node, GENplusINminusKILL);

            // if OUT[n] changed, add its successors to activeNodes
            if (!this.defsReachOUT.get(node).equals(oldOUT)) {
                if (node.getDefaultBranch() != null) {
                    activeNodes.add(node.getDefaultBranch());
                }
                if (node.getAlternativeBranch() != null) {
                    activeNodes.add(node.getAlternativeBranch());
                }
            }
        }
    }

    public void genUseDefinitionChains() {
        // how to store the result? Map use --> defs
        this.useDefsChains = new HashMap<>();

        ArrayList<BasicBlock> bbList = cfg.getBasicBlocks();
        for (BasicBlock bb : bbList) {

            //the last definition of certain var in a block
            HashMap<LlLocation, Def> lastDefInBlock = new HashMap<>();

            HashSet<Def> curDefsReachIN = this.defsReachIN.get(bb);
            HashMap<LlLocation, HashSet<Def>> curDefsReachINMap = defSet2MapOfVar2Defs(curDefsReachIN);
            LinkedHashMap<String, LlStatement> labelsToStmtsMap = bb.getLabelsToStmtsMap();

            // loop through each stmt in the BasicBlock
            for (String label : labelsToStmtsMap.keySet()) {
                LlStatement stmt = labelsToStmtsMap.get(label);
                Def curStmtDef;
                LlLocation curStmtLeftValue = null;

                // AssignStmtsRegular
                if (stmt instanceof LlAssignStmtRegular) {
                    curStmtLeftValue = ((LlAssignStmtRegular) stmt).getStoreLocation();

                    LlComponent operand = ((LlAssignStmtRegular) stmt).getOperand();
                    createUseDefChainsForLocationUsage(operand, stmt, bb, label, lastDefInBlock, curDefsReachINMap);
                }
                // AssignStmtsUnary
                else if (stmt instanceof LlAssignStmtUnaryOp) {
                    curStmtLeftValue = ((LlAssignStmtUnaryOp) stmt).getStoreLocation();

                    LlComponent operand = ((LlAssignStmtUnaryOp) stmt).getOperand();
                    createUseDefChainsForLocationUsage(operand, stmt, bb, label, lastDefInBlock, curDefsReachINMap);

                }
                // AssignStmtsBinary
                else if (stmt instanceof LlAssignStmtBinaryOp) {
                    curStmtLeftValue = ((LlAssignStmtBinaryOp) stmt).getStoreLocation();


                    LlComponent left = ((LlAssignStmtBinaryOp) stmt).getLeftOperand();
                    LlComponent right = ((LlAssignStmtBinaryOp) stmt).getRightOperand();

                    createUseDefChainsForLocationUsage(left, stmt, bb, label, lastDefInBlock, curDefsReachINMap);
                    createUseDefChainsForLocationUsage(right, stmt, bb, label, lastDefInBlock, curDefsReachINMap);
                }
                //LlMethodCallStmt
                else if (stmt instanceof LlMethodCallStmt) {
                    curStmtLeftValue = ((LlMethodCallStmt) stmt).getReturnLocation();

                    List<LlComponent> args = ((LlMethodCallStmt) stmt).getArgsList();
                    for (LlComponent arg : args) {
                        createUseDefChainsForLocationUsage(arg, stmt, bb, label, lastDefInBlock, curDefsReachINMap);
                    }
                }
                curStmtDef = new Def(curStmtLeftValue,stmt,bb,label);

                // 3) define the left value
                lastDefInBlock.put(curStmtLeftValue,curStmtDef);
            }
        }
    }



    private void createUseDefChainsForLocationUsage(LlComponent operand, LlStatement stmt, BasicBlock bb, String label,
                                                    HashMap<LlLocation, Def> lastDefInBlock, HashMap<LlLocation, HashSet<Def>> curDefsReachINMap) {
        if (operand instanceof LlLocation) {
            LlLocation locationUsage = (LlLocation) operand;
            Use use = new Use(locationUsage, stmt, bb, label);


            if (lastDefInBlock.get(locationUsage) != null) {
                // 1) the used var is defined in front of this BB
                addUseDefToUseDefChains(use, lastDefInBlock.get(locationUsage));
            } else {
                // 2) the used var is not defined in front of this BB
                HashSet<Def> defs = curDefsReachINMap.get(locationUsage);
                if(defs == null){
                    System.err.println("no definition for " + locationUsage.toString() + " @ " + label);
                }
                else{
                    for (Def def : defs){
                        addUseDefToUseDefChains(use, def);
                    }
                }


            }
        }
    }

    private void addUseDefToUseDefChains(Use use, Def def) {

        if (useDefsChains.get(use) == null) {
            HashSet<Def> defsForUse = new HashSet<>();
            defsForUse.add(def);
            useDefsChains.put(use, defsForUse);
        } else {
            useDefsChains.get(use).add(def);
        }
    }

    private HashMap<LlLocation, HashSet<Def>> defSet2MapOfVar2Defs(HashSet<Def> defSet) {
        HashMap<LlLocation, HashSet<Def>> mapOfVar2Defs = new HashMap<>();
        for (Def def : defSet) {
            if (mapOfVar2Defs.get(def.location) == null) {
                HashSet<Def> set = new HashSet<>();
                set.add(def);
                mapOfVar2Defs.put(def.location, set);
            } else {
                mapOfVar2Defs.get(def.location).add(def);
            }
        }
        return mapOfVar2Defs;
    }

    public void printDefsReachINAndOUT() {
        System.out.println("IN");
        for (BasicBlock bb : this.defsReachIN.keySet()) {
            String leaderLabel = CFG.getblockLeaderLabel(bb);
            System.out.println("\n\n" + leaderLabel + ":");
            ArrayList<Def> defs = new ArrayList<Def>(this.defsReachIN.get(bb));
            Collections.sort(defs);
            for (Def def : defs) {
                System.out.println(def);
            }
        }

        System.out.println("\n\nOUT");
        for (BasicBlock bb : this.defsReachOUT.keySet()) {
            String leaderLabel = CFG.getblockLeaderLabel(bb);
            System.out.println("\n\n" + leaderLabel + ":");
            ArrayList<Def> defs = new ArrayList<Def>(this.defsReachOUT.get(bb));
            Collections.sort(defs);
            for (Def def : defs) {
                System.out.println(def);
            }
        }

    }

    /**
     * –  GEN - set of definitions generated in block
     * –  KILL - set of definitions killed in block
     * <p>
     * •  GEN[s = s + a*b; i = i + 1;] = 0000011
     * •  KILL[s = s + a*b; i = i + 1;] = 1010000
     * •  Compiler scans each basic block to derive GEN and KILL sets
     */
    private HashSet<Def> GEN(BasicBlock bb) {
        if (this.bb2Gens.get(bb) != null) {
            return this.bb2Gens.get(bb);
        }
        LinkedHashMap<String, LlStatement> labelsToStmtsMap = bb.getLabelsToStmtsMap();
        HashSet<Def> defCandidates = new HashSet<>();

        // loop through each stmt in the BasicBlock
        for (String label : labelsToStmtsMap.keySet()) {
            LlStatement stmt = labelsToStmtsMap.get(label);
            // 看看是否有赋值语句
            // AssignStmts
            if (stmt instanceof LlAssignStmt) {
                LlLocation llLocation = ((LlAssignStmt) stmt).getStoreLocation();
                defCandidates.add(new Def(llLocation, stmt, bb, label));
            }
            //LlMethodCallStmt
            else if (stmt instanceof LlMethodCallStmt) {
                if (((LlMethodCallStmt) stmt).getReturnLocation() != null) {
                    LlLocation llLocation = ((LlMethodCallStmt) stmt).getReturnLocation();
                    defCandidates.add(new Def(llLocation, stmt, bb, label));
                }

            }

        }
        this.bb2Gens.put(bb, defCandidates);
        return defCandidates;
    }

    /**
     * –  GEN - set of definitions generated in block
     * –  KILL - set of definitions killed in block
     * <p>
     * •  GEN[s = s + a*b; i = i + 1;] = 0000011
     * •  KILL[s = s + a*b; i = i + 1;] = 1010000
     * •  Compiler scans each basic block to derive GEN and KILL sets
     */
    private HashSet<Def> KILL(BasicBlock bb, HashSet<Def> superSet) {
        if (this.bb2Kills.get(bb) != null) {
            return this.bb2Kills.get(bb);
        }
        LinkedHashMap<String, LlStatement> labelsToStmtsMap = bb.getLabelsToStmtsMap();
        HashSet<Def> killedDefCandidates = new HashSet<>();

        // loop through each stmt in the BasicBlock
        for (String label : labelsToStmtsMap.keySet()) {
            LlStatement stmt = labelsToStmtsMap.get(label);
            LlLocation location = null;
            // 根据不同类型的赋值语句，进行不同的 KILL 操作
            // AssignStmt
            if (stmt instanceof LlAssignStmt) {
                location = ((LlAssignStmt) stmt).getStoreLocation();
            }
            //LlMethodCallStmt
            else if (stmt instanceof LlMethodCallStmt) {
                if (((LlMethodCallStmt) stmt).getReturnLocation() != null) {
                    location = ((LlMethodCallStmt) stmt).getReturnLocation();
                }

            }
//            for (Def def : superSet) { // 可以用哈希表进行优化
//                if (def.location.equals(location) &&
//                        !def.stmtLabel.equals(label)) {
//                    killedDefCandidates.add(def);
//                }
//            }
            if (location != null) {
                HashSet<Def> defSet = this.universalMap.get(location);
                HashSet<Def> killedDefSet = (HashSet<Def>) defSet.clone();
                killedDefSet.remove(new Def(location, stmt, bb, label));
                killedDefCandidates.addAll(killedDefSet);
            }


        }
        this.bb2Kills.put(bb, killedDefCandidates);
        return killedDefCandidates;
    }

    public class Use {
        public final String stmtLabel;
        // the quadruple is of the form
        // (locationVar, statement, i, pos) which represents LlLocationVar  and LlStatement; @ instruction pos in block i
        private final LlLocation locationUsage;
        private final LlStatement statement;
        private final BasicBlock block;

        public Use(LlLocation locationUsage, LlStatement statement, BasicBlock block, String stmtLabel) {
            this.locationUsage = locationUsage;
            this.statement = statement;
            this.block = block;
            this.stmtLabel = stmtLabel;
        }

        public LlLocation getLocationUsage() {
            return this.locationUsage;
        }

        public LlStatement getStatement() {
            return this.statement;
        }

        public BasicBlock getBlock() {
            return block;
        }

        @Override
        public String toString() {
            return this.stmtLabel + ", " + this.statement.toString() + " use " + this.locationUsage.toString();
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Def) {
                Def that = (Def) obj;
                // two quadruples will be equal if they have equivalent locationUsage,block and stmtLabel
                return this.locationUsage.equals(that.location)
                        && this.block.equals(that.block) && that.stmtLabel.equals(that.stmtLabel);
            }
            return false;
        }

        @Override
        public int hashCode() {
            return this.locationUsage.hashCode() * this.block.hashCode() * this.stmtLabel.hashCode();
        }

    }

    public class Def implements Comparable<Def> {
        public final String stmtLabel;
        // the def is of the form
        // (locationVar, statement, i, pos) which represents LlLocationVar  and LlStatement; @ instruction pos in block i
        private final LlLocation location;
        private final LlStatement statement;
        private final BasicBlock block;

        public Def(LlLocation location, LlStatement statement, BasicBlock block, String stmtLabel) {
            this.location = location;
            this.statement = statement;
            this.block = block;
            this.stmtLabel = stmtLabel;
        }

        public LlLocation getLocation() {
            return this.location;
        }

        public LlStatement getStatement() {
            return this.statement;
        }

        public boolean containsVar(LlLocationVar var) {
            return this.location.equals(var) || this.statement.equals(var);
        }

        public BasicBlock getBlock() {
            return block;
        }

        @Override
        public String toString() {
            return this.stmtLabel + ", " + this.statement.toString();
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Def) {
                Def that = (Def) obj;
                // two defs will be equal if they have equivalent block, stmtLabel and statement
                return this.block.equals(that.block) && that.stmtLabel.equals(that.stmtLabel)
                        && this.statement.equals(that.statement);
            }
            return false;
        }

        @Override
        public int hashCode() {
            return this.block.hashCode() * this.stmtLabel.hashCode() * this.statement.hashCode();
        }

        public int compareTo(Def o) {
            return Integer.parseInt(this.stmtLabel.substring(1)) - Integer.parseInt(o.stmtLabel.substring(1));
        }
    }

}
