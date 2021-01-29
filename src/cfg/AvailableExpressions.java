package cfg;


import ll.LlStatement;
import ll.assignStmt.LlAssignStmt;
import ll.assignStmt.LlAssignStmtBinaryOp;
import ll.assignStmt.LlAssignStmtUnaryOp;
import ll.location.LlLocation;
import ll.location.LlLocationVar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;

/**
 * •  Each basic block has
 * –  IN - set of expressions available at start of block
 * –  OUT - set of expressions available at end of block
 * –  GEN - set of expressions computed in block
 * –  KILL - set of expressions killed in in block
 * •  GEN[x=z;b=x+y]=1000
 * •  KILL[x=z;b=x+y]=1001
 * •  Compiler scans each basic block to derive GEN and KILL sets
 */
public class AvailableExpressions {
    HashSet<Computation> universalSet;
    private final HashMap<BasicBlock, HashSet<Computation>> availExprIN = new HashMap<>();
    private final HashMap<BasicBlock, HashSet<Computation>> availExprOUT = new HashMap<>();

    private AvailableExpressions(CFG cfg, HashSet<LlLocation> globalVariables) {
        ArrayList<BasicBlock> bbList = cfg.getBasicBlocks();

        // 1) initial allExpressions with the union of all EVAL(bb)'s
        this.universalSet = new HashSet<>();
        for (BasicBlock bb : bbList) {
            universalSet.addAll(EVAL(bb));
        }

        // 2) perform the worklist algorithm
        // OUT[n] = E for all nodes
        for (BasicBlock bb : bbList) {
            this.availExprOUT.put(
                    bb, new HashSet<>(universalSet) // prevent mutability issues
            );
        }

        ArrayList<BasicBlock> activeNodes = new ArrayList<>(bbList);
        BasicBlock entry = activeNodes.get(0);

        // IN[Entry] = emptyset; 
        this.availExprIN.put(entry, new HashSet<>());

        // OUT[Entry] = EVAL[Entry]; 
        this.availExprOUT.put(entry, EVAL(entry));

        // Changed = N - {Entry};
        activeNodes.remove(0); // remove the entry since it was accounted for

        while (activeNodes.size() > 0) {

            // get a node and remove it from active nodes
            BasicBlock node = activeNodes.get(0);
            activeNodes.remove(0);
            HashSet<Computation> oldOUT = this.availExprOUT.get(node);

            // IN[n] = IN[n] intersect OUT[p] for all p in predecessors
            HashSet<Computation> IN = new HashSet<>(universalSet); // IN[n] = E
            for (BasicBlock pred : node.getPredecessors()) {
                IN.retainAll(this.availExprOUT.get(pred));
            }
            if (node.getPredecessors().size() == 0) {
                // IN should be there empty set if there are no predecessors
                IN = new HashSet<>();
            }
            this.availExprIN.put(node, IN);

            // KILL[n]
            HashSet<Computation> KILL = KILL(node, new HashSet<>(universalSet));

            // (IN[n]-KILL[n])
            HashSet<Computation> INminusKILL = new HashSet<>(this.availExprIN.get(node)); // make separate copy of IN
            INminusKILL.removeAll(KILL);

            // OUT[n] = EVAL[n] U (IN[n]-KILL[n])
            HashSet<Computation> EVALplusINminusKILL = EVAL(node);
            EVALplusINminusKILL.addAll(INminusKILL);
            this.availExprOUT.put(node, EVALplusINminusKILL);

            // if OUT[n] changed, add its successors to activeNodes
            if (!this.availExprOUT.get(node).equals(oldOUT)) {
                if (node.getDefaultBranch() != null) {
                    activeNodes.add(node.getDefaultBranch());
                }
                if (node.getAlternativeBranch() != null) {
                    activeNodes.add(node.getAlternativeBranch());
                }
            }
        }
    }

    public static HashMap<BasicBlock, HashSet<Computation>> getAvailableExpressionsForCFG(CFG cfg, HashSet<LlLocation> globalVariables) {
        AvailableExpressions ae = new AvailableExpressions(cfg, globalVariables);

        // get rid of all the computations that involve global variables
        for (BasicBlock bb : ae.availExprIN.keySet()) {
            for (Computation comp : new HashSet<>(ae.availExprIN.get(bb))) {
                for (LlLocation globalVar : globalVariables) {
                    if (comp.contains(globalVar)) {
                        ae.availExprIN.remove(comp);
                    }
                }
            }
        }

        // return the purified HashMap of computations
        return ae.availExprIN;
    }

    // returns the set of expressions that are generated
    // in the BasicBlock AND are still available at the
    // end of the BasicBlock
    private HashSet<Computation> EVAL(BasicBlock bb) {
        HashSet<Computation> generatedExpressions = new HashSet<>();
        LinkedHashMap<String, LlStatement> labelsToStmtsMap = bb.getLabelsToStmtsMap();

        for (String label : labelsToStmtsMap.keySet()) {
            LlStatement stmt = labelsToStmtsMap.get(label);

            if (stmt instanceof LlAssignStmtUnaryOp) {
                LlAssignStmtUnaryOp unaryOp = (LlAssignStmtUnaryOp) stmt;

                // add all new computations to generatedExpressions
                if (unaryOp.getOperand() instanceof LlLocationVar) {
                    Computation uniComp = Computation.createUnaryComputation(
                            unaryOp.getOperator(),
                            unaryOp.getOperand()
                    );
                    generatedExpressions.add(uniComp);
                }
            } else if (stmt instanceof LlAssignStmtBinaryOp) {
                LlAssignStmtBinaryOp binaryOp = (LlAssignStmtBinaryOp) stmt;

                // add all new computations to generatedExpressions
                if (binaryOp.getLeftOperand() instanceof LlLocationVar
                        && binaryOp.getRightOperand() instanceof LlLocationVar) {
                    Computation binComp = Computation.createBinaryComputation(
                            binaryOp.getLeftOperand(),
                            binaryOp.getOperation(),
                            binaryOp.getRightOperand()
                    );
                    generatedExpressions.add(binComp);
                }
            }

            // remove all expressions that contain the storeLocation
            // variable from generatedExpressions because those
            // computations are no longer valid
            if (stmt instanceof LlAssignStmt) {
                LlAssignStmt assignStmt = (LlAssignStmt) stmt;
                for (Computation comp : new HashSet<>(generatedExpressions)) {
                    if (comp.contains(assignStmt.getStoreLocation())) {
                        generatedExpressions.remove(comp);
                    }
                }
            }

        }
        return generatedExpressions;
    }

    // returns the sub-set of passed set of expressions that are killed
    // by this BasicBlock
    private HashSet<Computation> KILL(BasicBlock bb, HashSet<Computation> superSet) {
        HashSet<Computation> killedExpressions = new HashSet<>();
        LinkedHashMap<String, LlStatement> labelsToStmtsMap = bb.getLabelsToStmtsMap();

        for (String label : labelsToStmtsMap.keySet()) {
            LlStatement stmt = labelsToStmtsMap.get(label);

            // add all the computations that contain operands
            // that get re-assigned by the current stmt to
            // killedExpressions
            if (stmt instanceof LlAssignStmt) {
                LlAssignStmt assignStmt = (LlAssignStmt) stmt;
                for (Computation comp : superSet) {
                    if (comp.contains(assignStmt.getStoreLocation())) {
                        killedExpressions.add(comp);
                    }
                }
            }
        }
        return killedExpressions;
    }
}
