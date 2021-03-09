package cfg;

import ll.LlComponent;
import ll.LlMethodCallStmt;
import ll.LlStatement;
import ll.assignStmt.LlAssignStmt;
import ll.assignStmt.LlAssignStmtBinaryOp;
import ll.assignStmt.LlAssignStmtRegular;
import ll.assignStmt.LlAssignStmtUnaryOp;
import ll.jump.LlJumpConditional;
import ll.location.LlLocation;

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
    private final HashMap<BasicBlock, HashSet<cfg.VarAndStmt>> defsReachIN = new HashMap<>();
    private final HashMap<BasicBlock, HashSet<cfg.VarAndStmt>> defsReachOUT = new HashMap<>();
    private final HashMap<BasicBlock, HashSet<cfg.VarAndStmt>> bb2Gens = new HashMap<>();
    private final HashMap<BasicBlock, HashSet<cfg.VarAndStmt>> bb2Kills = new HashMap<>();
    public HashMap<VarAndStmt, HashSet<cfg.VarAndStmt>> useDefsChains;
    private HashMap<LlLocation, HashSet<cfg.VarAndStmt>> universalMap;
    private CFG cfg;

    public String printUseDefsChains(){
        StringBuilder stringBuilder = new StringBuilder();
        for(VarAndStmt useAndStmt : useDefsChains.keySet()){
            stringBuilder.append("use: ").append(useAndStmt.location.toString()).append(" @ ")
                    .append(useAndStmt.stmtLabel).append(" @ ").append(useAndStmt.block.name);
            stringBuilder.append("\ndefs: ");
            HashSet<cfg.VarAndStmt> defStmts = useDefsChains.get(useAndStmt);
            for(cfg.VarAndStmt defStmt : defStmts){
                stringBuilder.append(defStmt.stmtLabel + " @ " + defStmt.block.name + ", ");
            }
            stringBuilder.append("\n\n");
        }
        return stringBuilder.toString();
    }

    public ReachingDefinitionAnalysis(CFG cfg) {
        this.cfg = cfg;
        ArrayList<BasicBlock> bbList = cfg.getBasicBlocks();

        // 1) initial allExpressions with the union of all EVAL(bb)'s
        HashSet<cfg.VarAndStmt> universalSet = new HashSet<>();
        for (BasicBlock bb : bbList) {
            universalSet.addAll(GEN(bb));
        }

        this.universalMap = defSet2MapOfVar2Defs(universalSet);

        // 2) perform the worklist algorithm
        //for all nodes n in N
        //     OUT[n] = emptyset; // OUT[n] = GEN[n];
        for (BasicBlock bb : bbList) {
            this.defsReachOUT.put(bb, new HashSet<>());
        }

        ArrayList<BasicBlock> activeNodes = new ArrayList<>(bbList);
        BasicBlock entry = activeNodes.get(activeNodes.size()-4);

        // IN[Entry] = emptyset;
        this.defsReachIN.put(entry, new HashSet<>());

        // OUT[Entry] = GEN[Entry];
        this.defsReachOUT.put(entry, GEN(entry));

        // Changed = N - {Entry};
        activeNodes.remove(activeNodes.size()-4); // remove the entry since it was accounted for

        while (activeNodes.size() > 0) {

            // get a node and remove it from active nodes
            BasicBlock node = activeNodes.get(0);
            activeNodes.remove(0);
            HashSet<cfg.VarAndStmt> oldOUT = new HashSet<>(this.defsReachOUT.get(node));

            HashSet<cfg.VarAndStmt> IN = new HashSet<>(); // IN[n] = empty
            // IN[n] = IN[n] intersect OUT[p] for all p in predecessors
            for (BasicBlock pred : node.getPredecessors()) {
                IN.addAll(this.defsReachOUT.get(pred));
            }

            this.defsReachIN.put(node, IN);

            // KILL[n]
            HashSet<cfg.VarAndStmt> kills = KILL(node);

            // (IN[n]-KILL[n])
            HashSet<cfg.VarAndStmt> INminusKILL = new HashSet<>(this.defsReachIN.get(node)); // make separate copy of IN
            INminusKILL.removeAll(kills);

            // OUT[n] = GEN[n] U (IN[n]-KILL[n])
            HashSet<cfg.VarAndStmt> GENplusINminusKILL = GEN(node);
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
            HashMap<LlLocation, cfg.VarAndStmt> lastDefInBlock = new HashMap<>();

            HashSet<cfg.VarAndStmt> curDefsReachIN = this.defsReachIN.get(bb);
            HashMap<LlLocation, HashSet<cfg.VarAndStmt>> curDefsReachINMap = defSet2MapOfVar2Defs(curDefsReachIN);
            LinkedHashMap<String, LlStatement> labelsToStmtsMap = bb.getLabelsToStmtsMap();

            // loop through each stmt in the BasicBlock
            for (String label : labelsToStmtsMap.keySet()) {
                LlStatement stmt = labelsToStmtsMap.get(label);
                cfg.VarAndStmt curStmtDef;
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
                // LlJumpConditional
                else if(stmt instanceof LlJumpConditional){
                    LlComponent condition = ((LlJumpConditional) stmt).getCondition();
                    createUseDefChainsForLocationUsage(condition, stmt, bb, label, lastDefInBlock, curDefsReachINMap);
                }
                //LlMethodCallStmt
                else if (stmt instanceof LlMethodCallStmt) {
                    curStmtLeftValue = ((LlMethodCallStmt) stmt).getReturnLocation();

                    List<LlComponent> args = ((LlMethodCallStmt) stmt).getArgsList();
                    for (LlComponent arg : args) {
                        createUseDefChainsForLocationUsage(arg, stmt, bb, label, lastDefInBlock, curDefsReachINMap);
                    }
                }
                if(curStmtLeftValue == null){
//                    System.out.println("----no curStmtLeftValue----");
                }
                else{
                    curStmtDef = new cfg.VarAndStmt(curStmtLeftValue,stmt,bb,label);
                    // 3) define the left value
                    lastDefInBlock.put(curStmtLeftValue,curStmtDef);
                }

            }
        }
    }



    private void createUseDefChainsForLocationUsage(LlComponent operand, LlStatement stmt, BasicBlock bb, String label,
                                                    HashMap<LlLocation, cfg.VarAndStmt> lastDefInBlock, HashMap<LlLocation, HashSet<cfg.VarAndStmt>> curDefsReachINMap) {
        if (operand instanceof LlLocation) {
            LlLocation locationUsage = (LlLocation) operand;
            VarAndStmt useAndStmt = new VarAndStmt(locationUsage, stmt, bb, label);


            if (lastDefInBlock.get(locationUsage) != null) {
                // 1) the used var is defined in front of this BB, which is not needed in our case
//                addUseDefToUseDefChains(useAndStmt, lastDefInBlock.get(locationUsage));
            } else {
                // 2) the used var is not defined in front of this BB
                HashSet<cfg.VarAndStmt> defStmts = curDefsReachINMap.get(locationUsage);
                if(defStmts == null){
                    System.err.println("no definition for " + locationUsage.toString() + " @ " + label);
                }
                else{
                    for (cfg.VarAndStmt defStmt : defStmts){
                        addUseDefToUseDefChains(useAndStmt, defStmt);
                    }
                }


            }
        }
    }

    private void addUseDefToUseDefChains(VarAndStmt useAndStmt, cfg.VarAndStmt defStmt) {

        if (useDefsChains.get(useAndStmt) == null) {
            HashSet<cfg.VarAndStmt> defsForUse = new HashSet<>();
            defsForUse.add(defStmt);
            useDefsChains.put(useAndStmt, defsForUse);
        } else {
            useDefsChains.get(useAndStmt).add(defStmt);
        }
    }

    private HashMap<LlLocation, HashSet<cfg.VarAndStmt>> defSet2MapOfVar2Defs(HashSet<cfg.VarAndStmt> defStmtSet) {
        HashMap<LlLocation, HashSet<cfg.VarAndStmt>> mapOfVar2Defs = new HashMap<>();
        for (cfg.VarAndStmt defStmt : defStmtSet) {
            if (mapOfVar2Defs.get(defStmt.location) == null) {
                HashSet<cfg.VarAndStmt> set = new HashSet<>();
                set.add(defStmt);
                mapOfVar2Defs.put(defStmt.location, set);
            } else {
                mapOfVar2Defs.get(defStmt.location).add(defStmt);
            }
        }
        return mapOfVar2Defs;
    }

    public void printDefsReachINAndOUT() {
        System.out.println("IN");
        for (BasicBlock bb : this.defsReachIN.keySet()) {
            String leaderLabel = CFG.getblockLeaderLabel(bb);
            System.out.println("\n\n" + leaderLabel + ":");
            ArrayList<cfg.VarAndStmt> defStmts = new ArrayList<cfg.VarAndStmt>(this.defsReachIN.get(bb));
            Collections.sort(defStmts);
            for (cfg.VarAndStmt defStmt : defStmts) {
                System.out.println(defStmt);
            }
        }

        System.out.println("\n\nOUT");
        for (BasicBlock bb : this.defsReachOUT.keySet()) {
            String leaderLabel = CFG.getblockLeaderLabel(bb);
            System.out.println("\n\n" + leaderLabel + ":");
            ArrayList<cfg.VarAndStmt> defStmts = new ArrayList<cfg.VarAndStmt>(this.defsReachOUT.get(bb));
            Collections.sort(defStmts);
            for (cfg.VarAndStmt defStmt : defStmts) {
                System.out.println(defStmt);
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
    private HashSet<cfg.VarAndStmt> GEN(BasicBlock bb) {
        if (this.bb2Gens.get(bb) != null) {
            return this.bb2Gens.get(bb);
        }
        LinkedHashMap<String, LlStatement> labelsToStmtsMap = bb.getLabelsToStmtsMap();
        HashSet<cfg.VarAndStmt> defStmtCandidates = new HashSet<>();

        // loop through each stmt in the BasicBlock
        for (String label : labelsToStmtsMap.keySet()) {
            LlStatement stmt = labelsToStmtsMap.get(label);
            // 看看是否有赋值语句
            // AssignStmts
            if (stmt instanceof LlAssignStmt) {
                LlLocation llLocation = ((LlAssignStmt) stmt).getStoreLocation();
                defStmtCandidates.add(new cfg.VarAndStmt(llLocation, stmt, bb, label));
            }
            //LlMethodCallStmt
            else if (stmt instanceof LlMethodCallStmt) {
                if (((LlMethodCallStmt) stmt).getReturnLocation() != null) {
                    LlLocation llLocation = ((LlMethodCallStmt) stmt).getReturnLocation();
                    defStmtCandidates.add(new cfg.VarAndStmt(llLocation, stmt, bb, label));
                }

            }

        }
        this.bb2Gens.put(bb, defStmtCandidates);
        return defStmtCandidates;
    }

    /**
     * –  GEN - set of definitions generated in block
     * –  KILL - set of definitions killed in block
     * <p>
     * •  GEN[s = s + a*b; i = i + 1;] = 0000011
     * •  KILL[s = s + a*b; i = i + 1;] = 1010000
     * •  Compiler scans each basic block to derive GEN and KILL sets
     */
    private HashSet<cfg.VarAndStmt> KILL(BasicBlock bb) {
        if (this.bb2Kills.get(bb) != null) {
            return this.bb2Kills.get(bb);
        }
        LinkedHashMap<String, LlStatement> labelsToStmtsMap = bb.getLabelsToStmtsMap();
        HashSet<cfg.VarAndStmt> killedDefStmtCandidates = new HashSet<>();

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

            if (location != null) {
                HashSet<cfg.VarAndStmt> defStmtSet = this.universalMap.get(location);
//                HashSet<DefStmt> killedDefStmtSet = (HashSet<DefStmt>) defStmtSet.clone();
                HashSet<cfg.VarAndStmt> killedDefStmtSet = new HashSet<>(defStmtSet);
                killedDefStmtSet.remove(new cfg.VarAndStmt(location, stmt, bb, label));
                killedDefStmtCandidates.addAll(killedDefStmtSet);
            }


        }
        this.bb2Kills.put(bb, killedDefStmtCandidates);
        return killedDefStmtCandidates;
    }




}
