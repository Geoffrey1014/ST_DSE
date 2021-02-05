package cfg;

import ll.LlMethodCallStmt;
import ll.LlStatement;
import ll.assignStmt.LlAssignStmt;
import ll.location.LlLocation;
import ll.location.LlLocationVar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;

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
    private HashSet<Def> universalSet = new HashSet<>();

    public ReachingDefinitionAnalysis(CFG cfg) {
        ArrayList<BasicBlock> bbList = cfg.getBasicBlocks();

        // 1) initial allExpressions with the union of all EVAL(bb)'s
        for (BasicBlock bb : bbList) {
            this.universalSet.addAll(Gen(bb));
        }

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
        this.defsReachOUT.put(entry, Gen(entry));

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
            HashSet<Def> kills = KILL(node, new HashSet<>(this.universalSet));

            // (IN[n]-KILL[n])
            HashSet<Def> INminusKILL = new HashSet<>(this.defsReachIN.get(node)); // make separate copy of IN
            INminusKILL.removeAll(kills);

            // OUT[n] = GEN[n] U (IN[n]-KILL[n])
            HashSet<Def> GENplusINminusKILL = Gen(node);
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
    public void printDefsReachINAndOUT(){
        System.out.println("IN");
        for(BasicBlock bb : this.defsReachIN.keySet()){
            String leaderLabel = CFG.getblockLeaderLabel(bb);
            System.out.println(leaderLabel + ":");
            for(Def def: this.defsReachIN.get(bb)){
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
    private HashSet<Def> Gen(BasicBlock bb) {
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
        LinkedHashMap<String, LlStatement> labelsToStmtsMap = bb.getLabelsToStmtsMap();
        HashSet<Def> defCandidates = new HashSet<>();

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
                if(((LlMethodCallStmt) stmt).getReturnLocation() != null){
                    location = ((LlMethodCallStmt) stmt).getReturnLocation();
                }

            }
            for (Def def : superSet) { // 可以用哈希表进行优化
                if (def.location.equals(location) &&
                        !def.stmtLabel.equals(label)) {
                    defCandidates.add(def);
                }
            }

        }
        return defCandidates;
    }

    public class Def {
        // the quadruple is of the form
        // (locationVar, statement, i, pos) which represents LlLocationVar  and LlStatement; @ instruction pos in block i
        private final LlLocation location;
        private final LlStatement statement;
        private final BasicBlock block;
        private final String stmtLabel;

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
            return  this.stmtLabel + ", " + this.statement.toString() ;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Def) {
                Def that = (Def) obj;

                // two quadruples will be equal if they have equivalent u and v's
                return this.location.equals(that.location) && this.statement.equals(that.statement);
            }
            return false;
        }

        @Override
        public int hashCode() {
            return this.location.hashCode() * this.statement.hashCode();
        }
    }

}
