package cfg;

import ll.LlStatement;
import ll.assignStmt.LlAssignStmt;

import java.util.*;

public class NewLivrnessAnalysis {
    /**
     * Each basic block has
     * –  IN - set of variables live at start of block
     * –  OUT - set of variables live at end of block
     * –  USE - set of variables with upwards exposed uses in block –  DEF - set of variables defined in block
     * •  USE[x=z;x=x+1;]={z}(xnotinUSE)
     * •  DEF[x=z;x=x+1;y=1;]={x,y}
     * •  Compiler scans each basic block to derive USE and DEF sets
     */
    private final HashMap<CFG.SymbolDef, HashSet<BlockLabelPair>> use2defs;
    private final CFG cfg;
    private final HashMap<BasicBlock, HashSet<BlockLabelPair>> livenessIN = new HashMap<>();
    private final HashMap<BasicBlock, HashSet<BlockLabelPair>> livenessOUT = new HashMap<>();

    /**
     * for all nodes n in N - { Exit }
     *      IN[n] = emptyset;
     * OUT[Exit] = emptyset;
     * IN[Exit] = use[Exit];
     * Changed = N - { Exit };
     * while (Changed != emptyset)
     *      choose a node n in Changed;
     *      Changed = Changed - { n };
     *      OUT[n] = emptyset;
     *      for all nodes s in successors(n)
     *          OUT[n] = OUT[n] U IN[p];
     *      IN[n] = use[n] U (out[n] - def[n]);
     * if (IN[n] changed)
     *      for all nodes p in predecessors(n)
     *          Changed = Changed U { p };
     * @param cfg
     */
    private NewLivrnessAnalysis(CFG cfg) {
        this.cfg = cfg;
        this.use2defs = this.cfg.getDefsForUseAsBlockLabelPairs(); //获得 use ： def 对

        ArrayList<BasicBlock> bbList = this.cfg.getBasicBlocks();

        // IN[n] = EmptySet for all nodes
        for (BasicBlock bb : bbList) {
            this.livenessIN.put(bb, new HashSet<>());
        }
        // Changed = N - {Exit};
        LinkedList<BasicBlock> activeNodes = new LinkedList<>(bbList);
        BasicBlock exit = activeNodes.removeLast();
        // OUT[Exit] = emptyset
        this.livenessOUT.put(exit, new HashSet<>());

        // IN[Exit] = USE[Exit];
        this.livenessIN.put(exit, USE(exit));

        while (activeNodes.size() > 0) {
            // get a node and remove it from active nodes
            BasicBlock node = activeNodes.removeLast();
            HashSet<BlockLabelPair> oldIN = this.livenessIN.get(node);

            // OUT[n] = EmptySet
            HashSet<BlockLabelPair> OUT = new HashSet<>();
            // OUT[n] = OUT[n] union IN[s] for all s in successors
            if (node.getAlternativeBranch() != null) {
                OUT.addAll(this.livenessIN.get(node.getAlternativeBranch()));
            }
            if (node.getDefaultBranch() != null) {
                OUT.addAll(this.livenessIN.get(node.getDefaultBranch()));
            }
//            if (node.getDefaultBranch() == null && node.getAlternativeBranch() == null) {
//                this.livenessOUT.put(node, new HashSet<>());
//            }
            this.livenessOUT.put(node, OUT);

            // DEF[n]
            HashSet<BlockLabelPair> DEF = DEF(node);

            // (OUT[n]-DEF[n])
            HashSet<BlockLabelPair> OUTminusDEF = new HashSet<>(this.livenessOUT.get(node)); // make separate copy of OUT
            OUTminusDEF.removeAll(DEF);

            // IN[n] = USE[n] U (OUT[n]-DEF[n])
            HashSet<BlockLabelPair> USEplusOUTminusDEF = USE(node);
            USEplusOUTminusDEF.addAll(OUTminusDEF);
            this.livenessIN.put(node, USEplusOUTminusDEF);
            // if IN[n] changed, add its predecessors to activeNodes
            if (!this.livenessIN.get(node).equals(oldIN)) {
                for (BasicBlock pred : node.getPredecessors()) {
                    activeNodes.addFirst(pred);
                }
            }
        }

    }

    private HashSet<BlockLabelPair> DEF(BasicBlock bb) {
        HashSet<BlockLabelPair> setOfNewDefs = new HashSet<BlockLabelPair>();
        LinkedHashMap<String, LlStatement> labelsToStmtsMap = bb.getLabelsToStmtsMap();
        for (String label : labelsToStmtsMap.keySet()) {
            LlStatement stmt = labelsToStmtsMap.get(label);

            if (stmt instanceof LlAssignStmt) {
                BlockLabelPair newDef = new BlockLabelPair(bb, label);
                setOfNewDefs.add(newDef);
            }
        }
        return setOfNewDefs;
    }
    private HashSet<BlockLabelPair> USE(BasicBlock bb) {
        HashSet<BlockLabelPair> setOfNeededDefs = new HashSet<BlockLabelPair>();
        return setOfNeededDefs;

    }
}
