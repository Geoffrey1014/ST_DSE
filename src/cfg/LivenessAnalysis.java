package cfg;


import ll.LlStatement;
import ll.assignStmt.LlAssignStmt;
import ll.assignStmt.LlAssignStmtBinaryOp;
import ll.assignStmt.LlAssignStmtRegular;
import ll.assignStmt.LlAssignStmtUnaryOp;
import ll.location.LlLocationVar;

import java.util.*;


public class LivenessAnalysis {
    private final HashSet<BlockLabelPair> universalRequiredDefs = new HashSet<>();
    private final HashMap<BasicBlock, HashSet<BlockLabelPair>> requiredDefsIN = new HashMap<>();
    private final HashMap<BasicBlock, HashSet<BlockLabelPair>> requiredDefsOUT = new HashMap<>();
    private final HashMap<CFG.SymbolDef, HashSet<BlockLabelPair>> defsForUses;
    private final CFG cfg;

    /**
     * 和书上的 活跃变量分析的算法思路整个是反过来的。不采用书上的方法的原因可能是，对变量进行分析可能比对语句更复杂，需要对变量编码
     * USE[n] ： Bb 中定值后会被使用的语句
     * DEF[n] : Bb 中所有定值语句
     * OUT[n] : 出口处所需要的定值语句的集合  requiredDefsOUT （变量名已经暗示了）
     * IN[n] ： 入口处所需要的定值语句的集合 requiredDefsIN
     * OUT[n] = OUT[n] intersect IN[s] for all s in successors
     * IN[n] = USE[n] U (OUT[n]-DEF[n])
     * @param cfg
     */
    public LivenessAnalysis(CFG cfg) {
        this.cfg = cfg;
        this.defsForUses = this.cfg.getDefsForUseAsBlockLabelPairs(); //获得 use ： def 对

        for (CFG.SymbolDef defForUse : this.defsForUses.keySet()){
            System.out.println("\nuse:\t"+ defForUse);
            HashSet<BlockLabelPair> defs = this.defsForUses.get(defForUse);
            Iterator iterator = defs.iterator();
            System.out.println("defs:");
            while (iterator.hasNext()){
                BlockLabelPair blockLabelPair = (BlockLabelPair) iterator.next();
                if (blockLabelPair.getLabel().equals("NO_DEF_1010")){
                    System.out.println(" there is no defs, must be some problems.");
                }
                else{
                    System.out.println(blockLabelPair);
                }
            }
        }

        System.out.println("-----end of defsForUses-------");

        ArrayList<BasicBlock> bbList = this.cfg.getBasicBlocks();

        // IN[n] = EmptySet for all nodes
        for (BasicBlock bb : bbList) {
            this.requiredDefsIN.put(bb, new HashSet<>());
        }

        // Changed = N - {Exit};
        LinkedList<BasicBlock> activeNodes = new LinkedList<>(bbList);
        BasicBlock exit = activeNodes.removeLast();

        // OUT[Exit] = emptyset
        this.requiredDefsOUT.put(exit, new HashSet<>());

        // IN[Exit] = USE[Exit];
        this.requiredDefsIN.put(exit, USE(exit));

        while (activeNodes.size() > 0) {

            // get a node and remove it from active nodes
            BasicBlock node = activeNodes.removeLast();
            HashSet<BlockLabelPair> oldIN = this.requiredDefsIN.get(node);

            // OUT[n] = OUT[n] U IN[s] for all s in successors

            HashSet<BlockLabelPair> OUT = new HashSet<>(); // OUT[n] = EmptySet
            if (node.getAlternativeBranch() != null) {
                OUT.addAll(this.requiredDefsIN.get(node.getAlternativeBranch()));
            }
            if (node.getDefaultBranch() != null) {
                OUT.addAll(this.requiredDefsIN.get(node.getDefaultBranch()));
            }
            if (node.getDefaultBranch() == null && node.getAlternativeBranch() == null) {
                this.requiredDefsOUT.put(node, new HashSet<>());
            }
            this.requiredDefsOUT.put(node, OUT);

            // DEF[n]
            HashSet<BlockLabelPair> DEF = DEF(node);

            // (OUT[n]-DEF[n])
            HashSet<BlockLabelPair> OUTminusDEF = new HashSet<>(this.requiredDefsOUT.get(node)); // make separate copy of OUT
            OUTminusDEF.removeAll(DEF);

            // IN[n] = USE[n] U (OUT[n]-DEF[n])
            HashSet<BlockLabelPair> USEplusOUTminusDEF = USE(node);
            USEplusOUTminusDEF.addAll(OUTminusDEF);
            this.requiredDefsIN.put(node, USEplusOUTminusDEF);

            // if IN[n] changed, add its predecessors to activeNodes
            if (!this.requiredDefsIN.get(node).equals(oldIN)) {
                for (BasicBlock pred : node.getPredecessors()) {
                    activeNodes.addFirst(pred);
                }
            }
        }
    }

    // DEF[s] - (OUT[n] union USE[n]) , 在Bb 中 的定值语句，去除 定值后被使用的语句，再去除 出口处活跃的 定值语句
    // returns the set of (block, labels) that are dead code. That is,
    // this function returns DEF[s] - (OUT[n] union USE[n])
    public static HashMap<BasicBlock, HashSet<BlockLabelPair>> getLivenessAnalysisForCFG(CFG cfg) {
        LivenessAnalysis la = new LivenessAnalysis(cfg);
        HashMap<BasicBlock, HashSet<BlockLabelPair>> bbToTuplesMap = new HashMap<>();

        // populate the map for each BasicBlock
        for (BasicBlock bb : cfg.getBasicBlocks()) {
            HashSet<BlockLabelPair> DEFminusOUTminusUSE = la.DEF(bb);
            DEFminusOUTminusUSE.removeAll(la.requiredDefsOUT.get(bb));
            DEFminusOUTminusUSE.removeAll(la.USE(bb));
            bbToTuplesMap.put(bb, DEFminusOUTminusUSE);
        }
        return bbToTuplesMap;
    }

    /**
     * 收集当前 Bb 中， 定值前未被引用的变量
     * 因为在此阶段，LLIR 中引入了大量的 零时变量，所以不存在 定值前 已经被引用的变量，
     * 所以作者采用的是取巧的方法，直接将所有的 复制语句的 location 算作 定值前未被引用的变量
     * （   上面说得不对     2020-11-14 17:48:22）
     * @param bb
     * @return 返回所有定值语句，这些定值 在后边 可能会被引用
     */
    // returns the set of (block, label) pairs that represent LlAssignStmts
    // where the storeLocation has been DEFined and may now be used below
    private HashSet<BlockLabelPair> DEF(BasicBlock bb) {
        HashSet<BlockLabelPair> setOfNewDefs = new HashSet<>();
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


    /**
     * returns the set of (block, label) pairs that represent LlAssignStmts
     * where the storeLocation has a USE somewhere in the BasicBlock
     * 重点是 ： the storeLocation has a USE somewhere in the BasicBlock
     * @param bb
     * @return 返回 一个定值语句的位置的集合，变量是在此Bb或者其他Bb被 DEF，在此Bb USE，
     */
     private HashSet<BlockLabelPair> USE(BasicBlock bb) {
        HashSet<BlockLabelPair> setOfNeededDefs = new HashSet<BlockLabelPair>();
        LinkedHashMap<String, LlStatement> labelsToStmtsMap = bb.getLabelsToStmtsMap();

        // loop through each stmt in the BasicBlock
        for (String label : labelsToStmtsMap.keySet()) {
            LlStatement stmt = labelsToStmtsMap.get(label);

            // AssignStmtsRegular
            if (stmt instanceof LlAssignStmtRegular) {
                LlAssignStmtRegular regular = (LlAssignStmtRegular) stmt;
                if (regular.getOperand() instanceof LlLocationVar) {

                    // add the set of the DEFs needed for this variable USE
                    // to the set of needed defs
                    LlLocationVar var = (LlLocationVar) regular.getOperand();
                    HashSet<BlockLabelPair> defsForUse = GET_DEFS_FOR_USE(bb, label, var);
                    if(defsForUse == null){
                        System.out.println("null");
                    }
//                    System.out.println(defsForUse);
                    setOfNeededDefs.addAll(defsForUse);
                }
            }

            // AssignStmtsUnary
            else if (stmt instanceof LlAssignStmtUnaryOp) {
                LlAssignStmtUnaryOp unaryOp = (LlAssignStmtUnaryOp) stmt;
                if (unaryOp.getOperand() instanceof LlLocationVar) {

                    // add the set of the DEFs needed for this variable USE
                    // to the set of needed defs
                    LlLocationVar var = (LlLocationVar) unaryOp.getOperand();
                    HashSet<BlockLabelPair> defsForUse = GET_DEFS_FOR_USE(bb, label, var);
                    setOfNeededDefs.addAll(defsForUse);
                }
            }

            // AssignStmtsBinary
            else if (stmt instanceof LlAssignStmtBinaryOp) {
                LlAssignStmtBinaryOp binaryOp = (LlAssignStmtBinaryOp) stmt;

                if (binaryOp.getLeftOperand() instanceof LlLocationVar) {

                    // add the set of the DEFs needed for this variable USE
                    // to the set of needed defs
                    LlLocationVar var = (LlLocationVar) binaryOp.getLeftOperand();
                    HashSet<BlockLabelPair> defsForUse = GET_DEFS_FOR_USE(bb, label, var);
                    setOfNeededDefs.addAll(defsForUse);
                }
                if (binaryOp.getRightOperand() instanceof LlLocationVar) {

                    // add the set of the DEFs needed for this variable USE
                    // to the set of needed defs
                    LlLocationVar var = (LlLocationVar) binaryOp.getRightOperand();
                    HashSet<BlockLabelPair> defsForUse = GET_DEFS_FOR_USE(bb, label, var);
                    setOfNeededDefs.addAll(defsForUse);
                }
            }
        }
        return setOfNeededDefs;
    }

    private HashSet<BlockLabelPair> GET_DEFS_FOR_USE(BasicBlock bb, String label, LlLocationVar var) {


        String blockLeader =CFG.getblockLeaderLabel(bb);
        // return the HashSet of BlockLabelPairs associated with the SymbolDef
        CFG.SymbolDef symbolDef = this.cfg.new SymbolDef(var, this.cfg.new defBlockLocationTuple(blockLeader, label));
        return this.defsForUses.get(symbolDef);
    }

}