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

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class LiveVarAnalysis {

    /**
     * Each basic block has
     * –  IN - set of variables live at start of block
     * –  OUT - set of variables live at end of block
     * –  USE - set of variables with upwards exposed uses in block
     * –  DEF - set of variables defined in block
     * •  USE[x=z;x=x+1;]={z}(xnotinUSE)
     * •  DEF[x=z;x=x+1;y=1;]={x,y}
     * •  Compiler scans each basic block to derive USE and DEF sets
     * <p>
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
     * @param
     */
    private final CFG cfg;
    public  HashMap<BasicBlock, HashSet<LlLocation>> livenessIN ;
    public  HashMap<BasicBlock, HashSet<LlLocation>> livenessOUT ;
    public  HashMap<BasicBlock, HashMap<LlLocation, HashSet<VarAndStmt>>> livenessIN2 ;
    public  HashMap<BasicBlock, HashMap<LlLocation, HashSet<VarAndStmt>>> livenessOUT2 ;

    private  HashMap<BasicBlock, HashMap<LlLocation, HashSet<VarAndStmt>>> usesInBB;
    private  HashMap<BasicBlock, HashMap<LlLocation, HashSet<VarAndStmt>>> defsInBB;
    public HashMap<VarAndStmt,HashSet<VarAndStmt>> definitionUseChain;

    public LiveVarAnalysis(CFG cfg) {
        this.cfg = cfg;
    }

    public void livenessAnalysis2(){
        livenessIN2 = new HashMap<>();
        livenessOUT2 = new HashMap<>();
        usesInBB = new HashMap<>();
        defsInBB = new HashMap<>();
        ArrayList<BasicBlock> bbList = this.cfg.getBasicBlocks();
        for (BasicBlock bb : bbList) {
            HashMap<LlLocation, HashSet<VarAndStmt>> varDef2DefAndStmt = new HashMap<>();
            HashMap<LlLocation, HashSet<VarAndStmt>> varUse2UseAndStmt = new HashMap<>();
            calculateDEFAndUSE(bb, varDef2DefAndStmt, varUse2UseAndStmt);
            this.defsInBB.put(bb, varDef2DefAndStmt);
            this.usesInBB.put(bb, varUse2UseAndStmt);

        }

        // IN[n] = EmptySet for all nodes
        for (BasicBlock bb : bbList) {
            this.livenessIN2.put(bb, new HashMap<>());
        }
        // Changed = N - {Exit};
        LinkedList<BasicBlock> activeNodes = new LinkedList<>(bbList);
        BasicBlock exit = activeNodes.removeLast();
        // OUT[Exit] = emptyset
        this.livenessOUT2.put(exit, new HashMap<>());


        // IN[Exit] = USE[Exit];
        this.livenessIN2.put(exit, USE2(exit));


        while (activeNodes.size() > 0) {
            // get a node and remove it from active nodes
            BasicBlock node = activeNodes.removeLast();

            HashSet<LlLocation> oldIN2Keys = new HashSet(this.livenessIN2.get(node).keySet());
            HashSet<HashSet<VarAndStmt>> oldIN2Values =  new HashSet<>();
            for(HashSet<VarAndStmt> values :this.livenessIN2.get(node).values()){
                oldIN2Values.add(new HashSet<>(values));
            }

            // OUT[n] = EmptySet
            HashMap<LlLocation, HashSet<VarAndStmt>> OUT2 = new HashMap<>();
            // OUT[n] = OUT[n] union IN[s] for all s in successors
            if (node.getAlternativeBranch() != null) {

                HashMap<LlLocation, HashSet<VarAndStmt>> uses = this.livenessIN2.get(node.getAlternativeBranch());
                for (LlLocation location : uses.keySet()) {
                    OUT2.computeIfAbsent(location, k -> new HashSet<>());
                    OUT2.get(location).addAll(uses.get(location));
                }
            }
            if (node.getDefaultBranch() != null) {

                HashMap<LlLocation, HashSet<VarAndStmt>> uses = this.livenessIN2.get(node.getDefaultBranch());
                for (LlLocation location : uses.keySet()) {
                    OUT2.computeIfAbsent(location, k -> new HashSet<>());
                    OUT2.get(location).addAll(uses.get(location));
                }
            }

            this.livenessOUT2.put(node, OUT2);

            // DEF[n]
            HashMap<LlLocation, HashSet<VarAndStmt>> DEFs2 = DEF2(node);

            // (OUT[n]-DEF[n])
            HashMap<LlLocation, HashSet<VarAndStmt>> OUTminusDEF2 = new HashMap<>(this.livenessOUT2.get(node));
            for (LlLocation var : DEFs2.keySet()) {
                if (OUTminusDEF2.get(var) != null) {
                    OUTminusDEF2.remove(var);
                }
            }

            // IN[n] = USE[n] U (OUT[n]-DEF[n])
            HashMap<LlLocation, HashSet<VarAndStmt>> USEplusOUTminusDEF2 = USE2(node);
            for(LlLocation var: OUTminusDEF2.keySet()){
                if (!USEplusOUTminusDEF2.containsKey(var)) {
                    USEplusOUTminusDEF2.put(var, new HashSet<>());
                }
                USEplusOUTminusDEF2.get(var).addAll(OUTminusDEF2.get(var));
            }

            this.livenessIN2.put(node, USEplusOUTminusDEF2);

            boolean flag = equals(this.livenessIN2.get(node).values(),oldIN2Values);
            // if IN[n] changed, add its predecessors to activeNodes
            if (!this.livenessIN2.get(node).keySet().equals(oldIN2Keys)
            || !flag) {
                for (BasicBlock pred : node.getPredecessors()) {
                    activeNodes.addFirst(pred);
                }
            }
        }
    }

    private boolean equals(Collection<HashSet<VarAndStmt>> set1, Collection<HashSet<VarAndStmt>> set2) {
        if(set1 == null || set2 ==null){//null就直接不比了
            return false;
        }
        if(set1.size()!=set2.size()){//大小不同也不用比了
            return false;
        }
        return set1.containsAll(set2);//最后比containsAll
    }


    public void livenessAnalysis(){
        livenessIN = new HashMap<>();
        livenessOUT = new HashMap<>();
        livenessIN2 = new HashMap<>();
        livenessOUT2 = new HashMap<>();
        usesInBB = new HashMap<>();
        defsInBB = new HashMap<>();
        ArrayList<BasicBlock> bbList = this.cfg.getBasicBlocks();

        for (BasicBlock bb : bbList) {
            HashMap<LlLocation, HashSet<VarAndStmt>> varDef2DefAndStmt = new HashMap<>();
            HashMap<LlLocation, HashSet<VarAndStmt>> varUse2UseAndStmt = new HashMap<>();
            calculateDEFAndUSE(bb, varDef2DefAndStmt, varUse2UseAndStmt);
            this.defsInBB.put(bb, varDef2DefAndStmt);
            this.usesInBB.put(bb, varUse2UseAndStmt);

        }

        // IN[n] = EmptySet for all nodes
        for (BasicBlock bb : bbList) {
            this.livenessIN.put(bb, new HashSet<>());
            this.livenessIN2.put(bb, new HashMap<>());
        }

        // Changed = N - {Exit};
        LinkedList<BasicBlock> activeNodes = new LinkedList<>(bbList);
        BasicBlock exit = activeNodes.removeLast();
        // OUT[Exit] = emptyset
        this.livenessOUT.put(exit, new HashSet<>());
        this.livenessOUT2.put(exit, new HashMap<>());

        // IN[Exit] = USE[Exit];
        this.livenessIN.put(exit, USE(exit));
        this.livenessIN2.put(exit, USE2(exit));

        while (activeNodes.size() > 0) {
            // get a node and remove it from active nodes
            BasicBlock node = activeNodes.removeLast();
            HashSet<LlLocation> oldIN = this.livenessIN.get(node);
//            HashMap<LlLocation, HashSet<VarAndStmt>> oldIN2 = this.livenessIN2.get(node);

            // OUT[n] = EmptySet
            HashSet<LlLocation> OUT = new HashSet<>();
            HashMap<LlLocation, HashSet<VarAndStmt>> OUT2 = new HashMap<>();
            // OUT[n] = OUT[n] union IN[s] for all s in successors
            if (node.getAlternativeBranch() != null) {
                OUT.addAll(this.livenessIN.get(node.getAlternativeBranch()));

                HashMap<LlLocation, HashSet<VarAndStmt>> uses = this.livenessIN2.get(node.getAlternativeBranch());
                for (LlLocation location : uses.keySet()) {
                    OUT2.computeIfAbsent(location, k -> new HashSet<>());
                    OUT2.get(location).addAll(uses.get(location));
                }
            }
            if (node.getDefaultBranch() != null) {
                OUT.addAll(this.livenessIN.get(node.getDefaultBranch()));

                HashMap<LlLocation, HashSet<VarAndStmt>> uses = this.livenessIN2.get(node.getDefaultBranch());
                for (LlLocation location : uses.keySet()) {
                    OUT2.computeIfAbsent(location, k -> new HashSet<>());
                    OUT2.get(location).addAll(uses.get(location));
                }
            }
            this.livenessOUT.put(node, OUT);
            this.livenessOUT2.put(node, OUT2);

            // DEF[n]
            HashSet<LlLocation> DEFs = DEF(node);
            HashMap<LlLocation, HashSet<VarAndStmt>> DEFs2 = DEF2(node);

            // (OUT[n]-DEF[n])
            HashSet<LlLocation> OUTminusDEF = new HashSet<>(this.livenessOUT.get(node)); // make separate copy of OUT
            OUTminusDEF.removeAll(DEFs);
            HashMap<LlLocation, HashSet<VarAndStmt>> OUTminusDEF2 = new HashMap<>(this.livenessOUT2.get(node));
            for (LlLocation var : DEFs2.keySet()) {
                if (OUTminusDEF2.get(var) != null) {
                    OUTminusDEF2.remove(var);
//                    OUTminusDEF2.get(var).clear();
// can not use clear, because new HashMap<> can only shallow copy the HashSet<VarAndStmt> of HashMap<LlLocation, HashSet<VarAndStmt>>
                    // In other words, only some references in he HashSet<VarAndStmt> is copied.
                }
            }

            // IN[n] = USE[n] U (OUT[n]-DEF[n])
            HashSet<LlLocation> USEplusOUTminusDEF = USE(node);
            HashMap<LlLocation, HashSet<VarAndStmt>> USEplusOUTminusDEF2 = USE2(node);
            USEplusOUTminusDEF.addAll(OUTminusDEF);

            for(LlLocation var: OUTminusDEF2.keySet()){
                if (!USEplusOUTminusDEF2.containsKey(var)) {
                    USEplusOUTminusDEF2.put(var, new HashSet<>());
                }
                USEplusOUTminusDEF2.get(var).addAll(OUTminusDEF2.get(var));
            }

            this.livenessIN.put(node, USEplusOUTminusDEF);
            this.livenessIN2.put(node, USEplusOUTminusDEF2);

            // if IN[n] changed, add its predecessors to activeNodes
            if (!this.livenessIN.get(node).equals(oldIN)) {
                for (BasicBlock pred : node.getPredecessors()) {
                    activeNodes.addFirst(pred);
                }
            }
        }
    }

    public HashMap<VarAndStmt,HashSet<VarAndStmt>> calculateDefinitionUseChain() {
        // data structure of result：BlockLabelPair(VarAndStmt) -> HashSet<VarAndStmt>
        HashMap<VarAndStmt,HashSet<VarAndStmt>> result = new HashMap<>();
        for (BasicBlock bb : this.livenessOUT2.keySet()) {
//            System.out.println("\nbasicBlock=" + bb.getLabelsToStmtsMap().keySet().iterator().next() + ":");
            HashMap<LlLocation, HashSet<VarAndStmt>> lives = this.livenessOUT2.get(bb);
            // 1） in a basicblock, a def's uses are all usage between it and the next def. （which is not needed）
            // 2） in a basicblock, the uses of the last def (no more def behind)   are usages before OUT(not needed) and liveness of OUT
            HashMap<LlLocation,VarAndStmt> lastVarDefs = new HashMap<>();
            LinkedHashMap<String, LlStatement> labelsToStmtsMap = bb.getLabelsToStmtsMap();
            for (String label : labelsToStmtsMap.keySet()) {
                LlStatement stmt = labelsToStmtsMap.get(label);
                LlLocation locationDef = null;
                // AssignStmts
                if (stmt instanceof LlAssignStmt) {
                    locationDef = ((LlAssignStmt) stmt).getStoreLocation();
                }
                //LlMethodCallStmt
                else if (stmt instanceof LlMethodCallStmt) {
                    if (((LlMethodCallStmt) stmt).getReturnLocation() != null) {
                        locationDef = ((LlMethodCallStmt) stmt).getReturnLocation();
                    }
                }
                else{
                    continue;
                }
                VarAndStmt defAndStmt = new VarAndStmt(locationDef, stmt, bb, label);
                lastVarDefs.put(locationDef,defAndStmt);
            }
            for(LlLocation location: lastVarDefs.keySet()){
                if(lives.containsKey(location)){
                    VarAndStmt def = lastVarDefs.get(location);
                    result.put(def, lives.get(location));
//                    System.out.println(def.stmtLabel +" ,"+ location.toString()+" :");
//                    System.out.println(lives.get(location));
                }
            }
        }
        this.definitionUseChain = result;
        return result;

    }

    public void writeDefUseChainToFile(String path){
        ArrayList<VarAndStmt> defs= new ArrayList<>(definitionUseChain.keySet());
        Collections.sort(defs);
        StringBuilder stringBuilder = new StringBuilder();
        for(VarAndStmt varAndStmt : defs){
            stringBuilder.append("\ndef => ").append(varAndStmt);
            stringBuilder.append("\nuse => ").append(definitionUseChain.get(varAndStmt)).append("\n");
        }
        writeFile(stringBuilder.toString(),path);
        System.out.println("finish writing DUchain");
    }

    public static void writeFile(String content, String pathName) {

        try {

            File file = new File(pathName);
            file.createNewFile();
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(content);
            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public HashMap<BasicBlock, HashSet<BlockLabelPair>> calculateDeadCode() {
        //loop through the bb, find stmt which is not active in OUT
        ArrayList<BasicBlock> bbList = this.cfg.getBasicBlocks();
        HashMap<BasicBlock, HashSet<BlockLabelPair>> deadCodeMap = new HashMap<>();
        for (BasicBlock bb : bbList) {
            LinkedHashMap<String, LlStatement> labelsToStmtsMap = bb.getLabelsToStmtsMap();
            HashSet<LlLocation> livenessVars = new HashSet<> (livenessOUT2.get(bb).keySet()) ;
            HashSet<BlockLabelPair> deadCode = new HashSet<>();

            HashSet<BlockLabelPair> bBInnerLiveCode = liveCodeInBb(bb);
            for (String label : labelsToStmtsMap.keySet()) {
                LlStatement stmt = labelsToStmtsMap.get(label);
                LlLocation locationDef = null;

                if (stmt instanceof LlAssignStmtRegular) {
                    locationDef = ((LlAssignStmtRegular) stmt).getStoreLocation();
                } else if (stmt instanceof LlAssignStmtUnaryOp) {
                    locationDef = ((LlAssignStmtUnaryOp) stmt).getStoreLocation();
                } else if (stmt instanceof LlAssignStmtBinaryOp) {
                    locationDef = ((LlAssignStmtBinaryOp) stmt).getStoreLocation();

                } else if (stmt instanceof LlMethodCallStmt) {
                    locationDef = ((LlMethodCallStmt) stmt).getReturnLocation();
                } else {
                    continue;
                }
                if (locationDef!= null && !livenessVars.contains(locationDef) ) {
                    BlockLabelPair notAliveOutsied = new BlockLabelPair(bb, label);
                    if(!bBInnerLiveCode.contains(notAliveOutsied)){
                        deadCode.add(notAliveOutsied);
                    }

                }
            }
            deadCodeMap.put(bb, deadCode);
        }
        return deadCodeMap;
    }

    public HashSet<BlockLabelPair> liveCodeInBb(BasicBlock bb){
        LinkedHashMap<String, LlStatement> labelsToStmtsMap = bb.getLabelsToStmtsMap();
        HashMap<LlLocation,BlockLabelPair> lastVar2DefStmtInBasicBlock = new HashMap<>();
        HashSet<BlockLabelPair> liveCode = new HashSet<>();
        for (String label : labelsToStmtsMap.keySet()) {
            LlStatement stmt = labelsToStmtsMap.get(label);
            LlLocation locationDef = null;

            if (stmt instanceof LlAssignStmtRegular) {
                locationDef = ((LlAssignStmtRegular) stmt).getStoreLocation();
                LlComponent operand = ((LlAssignStmtRegular) stmt).getOperand();

                if (operand instanceof LlLocation) {
                    LlLocation locationUsage = (LlLocation) operand;
                    if(lastVar2DefStmtInBasicBlock.get(locationUsage) != null){
                        liveCode.add(lastVar2DefStmtInBasicBlock.get(locationUsage));
                    }
                }
            } else if (stmt instanceof LlAssignStmtUnaryOp) {
                locationDef = ((LlAssignStmtUnaryOp) stmt).getStoreLocation();
                LlComponent operand = ((LlAssignStmtUnaryOp) stmt).getOperand();

                if (operand instanceof LlLocation) {
                    LlLocation locationUsage = (LlLocation) operand;
                    //
                    if (lastVar2DefStmtInBasicBlock.get(locationUsage) != null) {
                        liveCode.add(lastVar2DefStmtInBasicBlock.get(locationUsage));
                    }
                }

            } else if (stmt instanceof LlAssignStmtBinaryOp) {
                locationDef = ((LlAssignStmtBinaryOp) stmt).getStoreLocation();
                LlComponent left = ((LlAssignStmtBinaryOp) stmt).getLeftOperand();
                LlComponent right = ((LlAssignStmtBinaryOp) stmt).getRightOperand();

                if (left instanceof LlLocation) {
                    LlLocation locationUsage = (LlLocation) left;
                    if (lastVar2DefStmtInBasicBlock.get(locationUsage) != null) {
                        liveCode.add(lastVar2DefStmtInBasicBlock.get(locationUsage));
                    }
                }
                if (right instanceof LlLocation) {
                    LlLocation locationUsage = (LlLocation) right;
                    if (lastVar2DefStmtInBasicBlock.get(locationUsage) != null) {
                        liveCode.add(lastVar2DefStmtInBasicBlock.get(locationUsage));
                    }
                }
            }
            else if(stmt instanceof LlJumpConditional){
                LlComponent condition = ((LlJumpConditional) stmt).getCondition();
                if(condition instanceof LlLocation){
                    LlLocation locationUsage = (LlLocation) condition;
                    if (lastVar2DefStmtInBasicBlock.get(locationUsage) != null) {
                        liveCode.add(lastVar2DefStmtInBasicBlock.get(locationUsage));
                    }
                }
            }
            else if (stmt instanceof LlMethodCallStmt) {
                locationDef = ((LlMethodCallStmt) stmt).getReturnLocation();
                List<LlComponent> args = ((LlMethodCallStmt) stmt).getArgsList();
                for (LlComponent arg : args) {
                    if (arg instanceof LlLocation) {
                        LlLocation locationUsage = (LlLocation) arg;
                        if (lastVar2DefStmtInBasicBlock.get(locationUsage) != null) {
                            liveCode.add(lastVar2DefStmtInBasicBlock.get(locationUsage));
                        }
                    }
                }
            } else {
                continue;
            }
            lastVar2DefStmtInBasicBlock.put(locationDef, new BlockLabelPair(bb, label));
        }
        return liveCode;
    }
    // USE - set of variables with upwards exposed uses in block
    private HashSet<LlLocation> USE(BasicBlock bb) {
        HashMap<LlLocation, HashSet<VarAndStmt>> uses = this.usesInBB.get(bb);
        return new HashSet<>(uses.keySet());
    }

    private HashMap<LlLocation, HashSet<VarAndStmt>> USE2(BasicBlock bb) {
        return new HashMap<>(this.usesInBB.get(bb));
    }

    // DEF - set of variables defined in block
    private HashSet<LlLocation> DEF(BasicBlock bb) {
        HashMap<LlLocation, HashSet<VarAndStmt>> defs = this.defsInBB.get(bb);
        return new HashSet<>(defs.keySet());
    }

    private HashMap<LlLocation, HashSet<VarAndStmt>> DEF2(BasicBlock bb) {
        return new HashMap<>(this.defsInBB.get(bb));
    }


    /**
     * –  USE - set of variables with upwards exposed uses in block
     * –  DEF - set of variables defined in block
     * •  USE[x=z;x=x+1;]={z}(xnotinUSE)
     * •  DEF[x=z;x=x+1;y=1;]={x,y}
     * •  Compiler scans each basic block to derive USE and DEF sets
     */
    private void calculateDEFAndUSE(BasicBlock bb, HashMap<LlLocation, HashSet<VarAndStmt>> varDef2DefStmt,
                                    HashMap<LlLocation, HashSet<VarAndStmt>> varUsage2UsageStmt) {

        LinkedHashMap<String, LlStatement> labelsToStmtsMap = bb.getLabelsToStmtsMap();
        for (String label : labelsToStmtsMap.keySet()) {
            LlStatement stmt = labelsToStmtsMap.get(label);
            LlLocation locationDef = null;

            // AssignStmtsRegular
            if (stmt instanceof LlAssignStmtRegular) {
                locationDef = ((LlAssignStmtRegular) stmt).getStoreLocation();
                LlComponent operand = ((LlAssignStmtRegular) stmt).getOperand();

                if (operand instanceof LlLocation) {
                    LlLocation locationUsage = (LlLocation) operand;
                    // put operand in the USE if it is not in the DEF
                    if (varDef2DefStmt.get(locationUsage) == null) {
                        VarAndStmt useAndStmt = new VarAndStmt(locationUsage, stmt, bb, label);
                        putVarInUSE(varUsage2UsageStmt, locationUsage, useAndStmt);
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
                    if (varDef2DefStmt.get(locationUsage) == null) {
                        VarAndStmt useAndStmt = new VarAndStmt(locationUsage, stmt, bb, label);
                        putVarInUSE(varUsage2UsageStmt, locationUsage, useAndStmt);
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
                    if (varDef2DefStmt.get(locationUsage) == null) {
                        VarAndStmt useAndStmt = new VarAndStmt(locationUsage, stmt, bb, label);
                        putVarInUSE(varUsage2UsageStmt, locationUsage, useAndStmt);
                    }
                }
                if (right instanceof LlLocation) {
                    LlLocation locationUsage = (LlLocation) right;
                    // put operand in the USE if it is not in the DEF
                    if (varDef2DefStmt.get(locationUsage) == null) {
                        VarAndStmt useAndStmt = new VarAndStmt(locationUsage, stmt, bb, label);
                        putVarInUSE(varUsage2UsageStmt, locationUsage, useAndStmt);
                    }
                }


            }
            // LlJumpConditional
            else if(stmt instanceof LlJumpConditional){
                LlComponent condition = ((LlJumpConditional) stmt).getCondition();
                if(condition instanceof LlLocation){
                    LlLocation locationUsage = (LlLocation) condition;
                    if (varDef2DefStmt.get(locationUsage) == null) {
                        VarAndStmt useAndStmt = new VarAndStmt(locationUsage, stmt, bb, label);
                        putVarInUSE(varUsage2UsageStmt, locationUsage, useAndStmt);
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
                        if (varDef2DefStmt.get(locationUsage) == null) {
                            VarAndStmt useAndStmt = new VarAndStmt(locationUsage, stmt, bb, label);
                            putVarInUSE(varUsage2UsageStmt, locationUsage, useAndStmt);
                        }
                    }
                }
            } else {
                continue;
            }
            //  add def if it is not used
            if (locationDef != null && varUsage2UsageStmt.get(locationDef) == null) {
                VarAndStmt defAndStmt = new VarAndStmt(locationDef, stmt, bb, label);
                putVarInDEF(varDef2DefStmt, locationDef, defAndStmt);
            }

        }
    }


    private void putVarInUSE(HashMap<LlLocation, HashSet<VarAndStmt>> varUse2UseAndStmt, LlLocation locationUsage, VarAndStmt useAndStmt) {
        if (varUse2UseAndStmt.get(locationUsage) == null) {
            HashSet<VarAndStmt> useAndStmts = new HashSet<>();
            useAndStmts.add(useAndStmt);
            varUse2UseAndStmt.put(locationUsage, useAndStmts);
        } else {
            varUse2UseAndStmt.get(locationUsage).add(useAndStmt);
        }
    }

    private void putVarInDEF(HashMap<LlLocation, HashSet<VarAndStmt>> varDef2DefAndStmt, LlLocation curStmtLeftValue, VarAndStmt defAndStmt) {
        if (varDef2DefAndStmt.get(curStmtLeftValue) == null) {
            HashSet<VarAndStmt> defAndStmts = new HashSet<>();
            defAndStmts.add(defAndStmt);
            varDef2DefAndStmt.put(curStmtLeftValue, defAndStmts);
        } else {
            varDef2DefAndStmt.get(curStmtLeftValue).add(defAndStmt);
        }
    }


}
