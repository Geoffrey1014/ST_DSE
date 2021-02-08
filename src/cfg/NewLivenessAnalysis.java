package cfg;

import ll.LlComponent;
import ll.LlMethodCallStmt;
import ll.LlStatement;
import ll.assignStmt.LlAssignStmtBinaryOp;
import ll.assignStmt.LlAssignStmtRegular;
import ll.assignStmt.LlAssignStmtUnaryOp;
import ll.location.LlLocation;
import ll.location.LlLocationVar;

import java.util.*;

public class NewLivenessAnalysis {
    /**
     * Each basic block has
     * –  IN - set of variables live at start of block
     * –  OUT - set of variables live at end of block
     * –  USE - set of variables with upwards exposed uses in block
     * –  DEF - set of variables defined in block
     * •  USE[x=z;x=x+1;]={z}(xnotinUSE)
     * •  DEF[x=z;x=x+1;y=1;]={x,y}
     * •  Compiler scans each basic block to derive USE and DEF sets
     */

    private final CFG cfg;
    public final HashMap<BasicBlock, HashSet<LlLocation>> livenessIN = new HashMap<>();
    public final HashMap<BasicBlock, HashSet<LlLocation>> livenessOUT = new HashMap<>();
    private final HashMap<BasicBlock, HashMap<LlLocation, HashSet<UseAndStmt>>> bb2Uses = new HashMap<>();
    private final HashMap<BasicBlock, HashMap<LlLocation, HashSet<DefAndStmt>>> bb2Defs = new HashMap<>();

    /**
     * for all nodes n in N - { Exit }
     * IN[n] = emptyset;
     * OUT[Exit] = emptyset;
     * IN[Exit] = use[Exit];
     * Changed = N - { Exit };
     * while (Changed != emptyset)
     * choose a node n in Changed;
     * Changed = Changed - { n };
     * OUT[n] = emptyset;
     * for all nodes s in successors(n)
     * OUT[n] = OUT[n] U IN[p];
     * IN[n] = use[n] U (out[n] - def[n]);
     * if (IN[n] changed)
     * for all nodes p in predecessors(n)
     * Changed = Changed U { p };
     *
     * @param cfg
     */
    public NewLivenessAnalysis(CFG cfg) {
        this.cfg = cfg;
        ArrayList<BasicBlock> bbList = this.cfg.getBasicBlocks();

        for(BasicBlock bb: bbList){
            HashMap<LlLocation, HashSet<DefAndStmt>> varDef2DefAndStmt = new HashMap<>();
            HashMap<LlLocation, HashSet<UseAndStmt>> varUse2UseAndStmt = new HashMap<>();
            calculateDEFAndUSE(bb,varDef2DefAndStmt,varUse2UseAndStmt);
            this.bb2Defs.put(bb,varDef2DefAndStmt);
            this.bb2Uses.put(bb,varUse2UseAndStmt);

        }

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
        this.livenessIN.put(exit,USE(exit));

        while (activeNodes.size() > 0) {
            // get a node and remove it from active nodes
            BasicBlock node = activeNodes.removeLast();
            HashSet<LlLocation> oldIN = this.livenessIN.get(node);

            // OUT[n] = EmptySet
            HashSet<LlLocation> OUT = new HashSet<>();
            // OUT[n] = OUT[n] union IN[s] for all s in successors
            if (node.getAlternativeBranch() != null) {
                OUT.addAll(this.livenessIN.get(node.getAlternativeBranch()));
            }
            if (node.getDefaultBranch() != null) {
                OUT.addAll(this.livenessIN.get(node.getDefaultBranch()));
            }
            this.livenessOUT.put(node, OUT);

            // DEF[n]
            HashSet<LlLocation> DEFs = DEF(node);

            // (OUT[n]-DEF[n])
            HashSet<LlLocation> OUTminusDEF = new HashSet<>(this.livenessOUT.get(node)); // make separate copy of OUT
            OUTminusDEF.removeAll(DEFs);

            // IN[n] = USE[n] U (OUT[n]-DEF[n])
            HashSet<LlLocation> USEplusOUTminusDEF = USE(node);
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
    // USE - set of variables with upwards exposed uses in block
    // USE[x = z; x = x+1;] = { z } (x not in USE)
    private HashSet<LlLocation> USE(BasicBlock bb) {
        HashMap<LlLocation, HashSet<UseAndStmt>> uses = this.bb2Uses.get(bb);
        return new HashSet<>(uses.keySet());
    }

    // DEF - set of variables defined in block
    // DEF[x = z; x = x+1;y = 1;] = {x, y}
    private HashSet<LlLocation> DEF(BasicBlock bb) {
        HashMap<LlLocation, HashSet<DefAndStmt>> defs = this.bb2Defs.get(bb);
        return new HashSet<>(defs.keySet());
    }



    private void calculateDEFAndUSE(BasicBlock bb,HashMap<LlLocation, HashSet<DefAndStmt>> varDef2DefAndStmt,
                                    HashMap<LlLocation, HashSet<UseAndStmt>> varUse2UseAndStmt) {
        LinkedHashMap<String, LlStatement> labelsToStmtsMap = bb.getLabelsToStmtsMap();


        for (String label : labelsToStmtsMap.keySet()) {
            LlStatement stmt = labelsToStmtsMap.get(label);
            DefAndStmt curStmtDefAndStmt;
            LlLocation locationDef = null;

            // AssignStmtsRegular
            if (stmt instanceof LlAssignStmtRegular) {
                locationDef = ((LlAssignStmtRegular) stmt).getStoreLocation();
                LlComponent operand = ((LlAssignStmtRegular) stmt).getOperand();

                if (operand instanceof LlLocation) {
                    LlLocation locationUsage = (LlLocation) operand;
                    // put operand in the USE if it is not in the DEF
                    if (varDef2DefAndStmt.get(locationUsage) == null) {
                        UseAndStmt useAndStmt= new UseAndStmt(locationUsage, stmt, bb, label);
                        putVarInUSE(varUse2UseAndStmt,locationUsage,useAndStmt);
                    }
                }

            }
            // AssignStmtsUnary
            else if (stmt instanceof LlAssignStmtUnaryOp) {
                locationDef = ((LlAssignStmtUnaryOp) stmt).getStoreLocation();
                LlComponent operand = ((LlAssignStmtUnaryOp) stmt).getOperand();

                if (operand instanceof LlLocation) {
                    LlLocation locationUsage = (LlLocation) operand;
                    // put operand in the USE if it is not in the DEF
                    if (varDef2DefAndStmt.get(locationUsage) == null) {
                        UseAndStmt useAndStmt= new UseAndStmt(locationUsage, stmt, bb, label);
                        putVarInUSE(varUse2UseAndStmt,locationUsage,useAndStmt);
                    }
                }


            }
            // AssignStmtsBinary
            else if (stmt instanceof LlAssignStmtBinaryOp) {
                locationDef = ((LlAssignStmtBinaryOp) stmt).getStoreLocation();
                LlComponent left = ((LlAssignStmtBinaryOp) stmt).getLeftOperand();
                LlComponent right = ((LlAssignStmtBinaryOp) stmt).getRightOperand();

                if (left instanceof LlLocation) {
                    LlLocation locationUsage = (LlLocation) left;
                    // put operand in the USE if it is not in the DEF
                    if (varDef2DefAndStmt.get(locationUsage) == null) {
                        UseAndStmt useAndStmt= new UseAndStmt(locationUsage, stmt, bb, label);
                        putVarInUSE(varUse2UseAndStmt,locationUsage,useAndStmt);
                    }
                }
                if (right instanceof LlLocation) {
                    LlLocation locationUsage = (LlLocation) right;
                    // put operand in the USE if it is not in the DEF
                    if (varDef2DefAndStmt.get(locationUsage) == null) {
                        UseAndStmt useAndStmt= new UseAndStmt(locationUsage, stmt, bb, label);
                        putVarInUSE(varUse2UseAndStmt,locationUsage,useAndStmt);
                    }
                }


            }
            //LlMethodCallStmt
            else if (stmt instanceof LlMethodCallStmt) {
                locationDef = ((LlMethodCallStmt) stmt).getReturnLocation();

                List<LlComponent> args = ((LlMethodCallStmt) stmt).getArgsList();
                for (LlComponent arg : args) {
                    if (arg instanceof LlLocation) {
                        LlLocation locationUsage = (LlLocation) arg;
                        // put operand in the USE if it is not in the DEF
                        if (varDef2DefAndStmt.get(locationUsage) == null) {
                            UseAndStmt useAndStmt= new UseAndStmt(locationUsage, stmt, bb, label);
                            putVarInUSE(varUse2UseAndStmt,locationUsage,useAndStmt);
                        }
                    }
                }
            }
            //  add def
            DefAndStmt defAndStmt = new DefAndStmt(locationDef,stmt,bb,label);
            putVarInDEF(varDef2DefAndStmt,locationDef,defAndStmt);
        }
    }


    private void putVarInUSE(HashMap<LlLocation, HashSet<UseAndStmt>> varUse2UseAndStmt, LlLocation locationUsage,UseAndStmt useAndStmt ){
        if (varUse2UseAndStmt.get(locationUsage) == null) {
            HashSet<UseAndStmt> useAndStmts = new HashSet<>();
            useAndStmts.add(useAndStmt);
            varUse2UseAndStmt.put(locationUsage, useAndStmts);
        } else {
            varUse2UseAndStmt.get(locationUsage).add(useAndStmt);
        }
    }
    private void putVarInDEF(HashMap<LlLocation, HashSet<DefAndStmt>> varDef2DefAndStmt,LlLocation curStmtLeftValue,DefAndStmt defAndStmt){
        if(varDef2DefAndStmt.get(curStmtLeftValue) == null){
            HashSet<DefAndStmt> defAndStmts = new HashSet<>();
            defAndStmts.add(defAndStmt );
            varDef2DefAndStmt.put(curStmtLeftValue,defAndStmts);
        }
        else{
            varDef2DefAndStmt.get(curStmtLeftValue).add(defAndStmt);
        }
    }


    private class UseAndStmt {
        public final String stmtLabel;
        // the quadruple is of the form
        // (locationVar, statement, i, pos) which represents LlLocationVar  and LlStatement; @ instruction pos in block i
        private final LlLocation locationUsage;
        private final LlStatement statement;
        private final BasicBlock block;

        public UseAndStmt(LlLocation locationUsage, LlStatement statement, BasicBlock block, String stmtLabel) {
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
            if (obj instanceof DefAndStmt) {
                DefAndStmt that = (DefAndStmt) obj;
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

    private class DefAndStmt implements Comparable<DefAndStmt> {
        public final String stmtLabel;
        // the def is of the form
        // (locationVar, statement, i, pos) which represents LlLocationVar  and LlStatement; @ instruction pos in block i
        private final LlLocation location;
        private final LlStatement statement;
        private final BasicBlock block;

        public DefAndStmt(LlLocation location, LlStatement statement, BasicBlock block, String stmtLabel) {
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
            if (obj instanceof DefAndStmt) {
                DefAndStmt that = (DefAndStmt) obj;
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

        public int compareTo(DefAndStmt o) {
            return Integer.parseInt(this.stmtLabel.substring(1)) - Integer.parseInt(o.stmtLabel.substring(1));
        }
    }

}
