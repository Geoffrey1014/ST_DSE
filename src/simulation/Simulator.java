package simulation;

import cfg.BasicBlock;
import cfg.CFG;
import cfg.VarAndStmt;
import com.microsoft.z3.Context;
import com.microsoft.z3.Expr;
import com.microsoft.z3.Solver;
import com.microsoft.z3.Status;
import ll.LlComponent;
import ll.LlEmptyStmt;
import ll.LlMethodCallStmt;
import ll.LlStatement;
import ll.assignStmt.LlAssignStmt;
import ll.jump.LlJumpConditional;
import ll.jump.LlJumpUnconditional;
import ll.literal.*;
import ll.location.LlLocation;
import tools.Tuple2;

import java.util.*;

/**
 * 在CFG上模拟执行
 * 需要模拟内存状态，使用map
 * 处理分枝
 * 调用每个LLIR 的exe 函数
 */
public class Simulator {
    private final CFG cfg;
    private final LlStatementExeutor llStatementExeutor;
    private ConMemory conMemory;
    private SymMemory symMemory;
    private HashSet<BasicBlock> coveredBlocks;
    private HashSet<BasicBlock> branchBlocks;
    private HashSet<BasicBlock> stmtBlocks;
    private Solver solver;
    private Context ctx;
    private HashSet<LlComponent> inputs;
    HashMap<VarAndStmt, HashSet<Tuple2<VarAndStmt,HashSet<BasicBlock>>>> duChianWithDmt;

    public Simulator(CFG cfg, HashMap<VarAndStmt, HashSet<Tuple2<VarAndStmt,HashSet<BasicBlock>>>> duChianWithDmt) {
        this.cfg = cfg;
        this.conMemory = new ConMemory();
        this.symMemory = new SymMemory();
        this.llStatementExeutor = new LlStatementExeutor();
        this.coveredBlocks = new HashSet<>();
        this.branchBlocks = new HashSet<>();
        this.stmtBlocks = new HashSet<>();
        this.duChianWithDmt = duChianWithDmt;

        HashMap<String, String> config = new HashMap<String, String>();
        config.put("model", "true");
        this.ctx = new Context(config);
        this.solver = this.ctx.mkSolver();

        // get branch block and blocks which has executable stmts (not empty stmt)
        ArrayList<BasicBlock> basicBlockArrayList = cfg.getBasicBlocks();
        for (BasicBlock bb : basicBlockArrayList) {
            LlStatement llStatement = bb.getStmtsList().get(bb.getStmtsList().size() - 1);
            if (llStatement instanceof LlJumpConditional) {
                this.branchBlocks.add(bb);
            }
            if (bb.getStmtsList().size() > 1) {
                this.stmtBlocks.add(bb);
            }
        }

        // get input var
        this.inputs = new HashSet<>();
        BasicBlock readBB = this.cfg.leadersToBBMap.get("Read");
        for(LlStatement llStatement: readBB.getStmtsList()){
            if(llStatement instanceof LlMethodCallStmt){
                LlMethodCallStmt llMethodCallStmt = (LlMethodCallStmt) llStatement;
                this.inputs.add(llMethodCallStmt.getReturnLocation());
            }

        }

    }
    public void dataFlowTesting(HashMap<VarAndStmt, HashSet<Tuple2<VarAndStmt,HashSet<BasicBlock>>>> udChianWithDmt){
        for(VarAndStmt use : udChianWithDmt.keySet()){
            HashSet<Tuple2<VarAndStmt,HashSet<BasicBlock>>> defsAndCuts = udChianWithDmt.get(use);
            for(Tuple2<VarAndStmt,HashSet<BasicBlock>> defAndItsCuts : defsAndCuts){
                VarAndStmt def = defAndItsCuts.a1;
                HashSet<BasicBlock> cuts = defAndItsCuts.a2;
                dfTestaDuPair(def,use,cuts);
            }
        }

    }

    /**
     *
     * @param def 目标定义 -使用对 d
     * @param use 目标定义 -使用对 d
     * @param cuts du 的一系列割点
     * @return 满足 du 的程序输入 t，如果找不到对应的 t，返回 nil
     *
     * 1 let W be a worklist of branching nodes (initialized as empty)
     * 2 let t be an initial test input
     * 3 repeat
     * 4    let p be the execution path triggered by t
     * 5    if p covers du then return t
     * 6    W ← W ∪ {branching nodes on p}
     *      // the redefinition pruning heuristic
     * 7    if variable x (in du) is redeﬁned after l d on p then
     * 8        let X denote the set of branching nodes after the redeﬁnition location
     * 9        W ← W \ X
     * 10   end
     * 11 let t = guided_search(W)
     * 12 until t == nil
     * 13 return nil
     */
    public HashMap<LlLocation,ValueOfDiffType> dfTestaDuPair(VarAndStmt def, VarAndStmt use, HashSet<BasicBlock> cuts){
        HashSet<Tuple2<BasicBlock,Boolean>> W = new HashSet<>();
        HashMap<LlLocation,ValueOfDiffType> inputs = initInputs();
        do{
            Tuple2<List<String> ,List<Tuple2<Integer,Boolean>>> result = conExe(inputs);
            List<Tuple2<BasicBlock,Boolean>> branchNodes = getBranchNodes(result);

            if(pathCoverDu(result.a1,def,use)) return inputs;
            W.addAll(branchNodes);
            redefinitionPruning();
            inputs = guidedSearch(W);


        } while (inputs == null);

        return null;

    }

    public void redefinitionPruning(){

    }

    public HashMap<LlLocation,ValueOfDiffType> guidedSearch(HashSet<Tuple2<BasicBlock,Boolean>> workList ){
        if(workList.isEmpty()) return null;
        int distance = 0; //先放弃 distance这个因素
        int index = 0;
        for(Tuple2<BasicBlock,Boolean> branchNode : workList){

        }
        return null;

    }

    public List<Tuple2<BasicBlock,Boolean>> getBranchNodes(Tuple2<List<String> ,List<Tuple2<Integer,Boolean>>> result){
        List<Tuple2<BasicBlock,Boolean>> branchNodes = new ArrayList<>();
        for(Tuple2<Integer, Boolean> b: result.a2){
            branchNodes.add(new Tuple2<>(this.cfg.leadersToBBMap.get(result.a1.get(b.a1)), b.a2));
        }
        return branchNodes;
    }
    public HashMap<LlLocation,ValueOfDiffType> initInputs(){
        HashMap<LlLocation,ValueOfDiffType> inputs = new HashMap<>();
        Hashtable<LlComponent, LlLiteral> inputVarsInit = this.cfg.getLlSymbolTable().varInput;
        for (LlComponent llComponent : inputVarsInit.keySet()) {
            LlLiteral llLiteral = inputVarsInit.get(llComponent);
            if (llLiteral instanceof LlLiteralBool)
                inputs.put((LlLocation) llComponent, new ValueOfDiffType(((LlLiteralBool) llLiteral).getBoolValue()));
            else if (llLiteral instanceof LlLiteralInt)
                inputs.put((LlLocation) llComponent, new ValueOfDiffType(((LlLiteralInt) llLiteral).getIntValue()));
            else if (llLiteral instanceof LlLiteralReal)
                inputs.put((LlLocation) llComponent, new ValueOfDiffType(((LlLiteralReal) llLiteral).getRealValue()));
            else if (llLiteral instanceof LlLiteralString)
                inputs.put((LlLocation) llComponent, new ValueOfDiffType(((LlLiteralString) llLiteral).getStringValue()));
            else System.out.println("wrong type!");
        }
        return inputs;
    }

    public boolean pathCoverDu(List<String> path, VarAndStmt def, VarAndStmt use){
        boolean defFlag = false;
        boolean useFlag = false;

        for(String bbLabel: path){
            if(bbLabel.equals(def.stmtLabel)) defFlag = true;
            if(bbLabel.equals(use.stmtLabel)) useFlag = true;

        }
        return defFlag && useFlag;
    }

    /**
     * 使用访问者模式
     *
     * @return
     */
    public void execute() {
        putInputVarInitToMemo(); // seems unnecessary

        Tuple2<List<String> ,List<Tuple2<Integer,Boolean>>> result = firstConExe();
        List<String> route = result.a1;
        List<Tuple2<Integer,Boolean>> branches = result.a2;
        System.out.println(route);
        System.out.println(branches);

        LinkedList<HashMap<LlLocation,ValueOfDiffType>> calculatedInputs = symExe(route,branches);

        while (calculatedInputs.size() >0){
            result = conExe(calculatedInputs.pop());
        }

    }
    public Tuple2<List<String> ,List<Tuple2<Integer,Boolean>>> firstConExe(){
        List<String> route = new ArrayList<>();
        List<Tuple2<Integer,Boolean>> branches = new ArrayList<>();
        BasicBlock entryNode = this.cfg.getBasicBlocks().get(0);
        BasicBlock curBlock = entryNode.getDefaultBranch();
        int conuter = 0;
        while (curBlock != null) {
            String leaderLabel = this.cfg.blockLabels.get(curBlock);
            System.out.println("\n" + leaderLabel);

            route.add(leaderLabel);
            if (leaderLabel.equals("Read") && conuter > 1) break;

            Tuple2<BasicBlock,Boolean> nextBB = executeBasicBlock(curBlock);
            if (this.branchBlocks.contains(curBlock)) branches.add(new Tuple2<>(conuter,nextBB.a2));
            if (this.stmtBlocks.contains(curBlock)) this.coveredBlocks.add(curBlock);
            conuter += 1;
            curBlock = nextBB.a1;
            System.out.println("stmt coverage: " + this.coveredBlocks.size() / (float) this.stmtBlocks.size());
        }
        return new Tuple2<>(route,branches);

    }
    public Tuple2<List<String> ,List<Tuple2<Integer,Boolean>>> conExe(HashMap<LlLocation,ValueOfDiffType> inputs){
        for (LlLocation location :inputs.keySet()){
            conMemory.put(location,inputs.get(location));
        }
        BasicBlock curBlock = this.cfg.leadersToBBMap.get("Body");
        List<String> route = new ArrayList<>();
        List<Tuple2<Integer,Boolean>> branches = new ArrayList<>();
        int conuter = 0;
        while (curBlock != null) {
            String leaderLabel = this.cfg.blockLabels.get(curBlock);
            System.out.println("\n" + leaderLabel);

            route.add(leaderLabel);
            if (leaderLabel.equals("Read")) break;

            Tuple2<BasicBlock,Boolean> nextBB = executeBasicBlock(curBlock);
            if (this.branchBlocks.contains(curBlock)) branches.add(new Tuple2<>(conuter,nextBB.a2));
            if (this.stmtBlocks.contains(curBlock)) this.coveredBlocks.add(curBlock);
            conuter += 1;
            curBlock = nextBB.a1;
            System.out.println("coverage: " + this.coveredBlocks.size() / (float) this.stmtBlocks.size());
        }
        return new Tuple2<>(route,branches);
    }

    public LinkedList<HashMap<LlLocation,ValueOfDiffType>> symExe(List<String> route, List<Tuple2<Integer,Boolean>> branches){
        // TODO  需要过滤算法，去除route中不必要的分支
        // TODo 把函数整理一下
        LinkedList<HashMap<LlLocation,ValueOfDiffType>> calculatedInputs = new LinkedList<>();
        mkInputSymbolic(ctx);
        SymLlStatementExeutor symLlStatementExeutor = new SymLlStatementExeutor(this.ctx);
        int left = 0;
        solver.push();
        for(Tuple2<Integer,Boolean> branch : branches){
            for(String bbLabel: route.subList(left,branch.a1+1)){
                if (bbLabel.equals("Read")) continue;
                symExecuteBasicBlock(this.cfg.leadersToBBMap.get(bbLabel),symLlStatementExeutor,
                        symMemory,!branch.a2, calculatedInputs);
            }
            left = branch.a1+1;
        }
        solver.pop();
        return calculatedInputs;
    }


    public void symExecuteBasicBlock(BasicBlock currentBolock, SymLlStatementExeutor symLlStatementExeutor,
                                     SymMemory symMemory, Boolean symBranchChoice,
    LinkedList<HashMap<LlLocation,ValueOfDiffType>> calculatedInputs) {

        for (LlStatement llStatement : currentBolock.getStmtsList()) {
            if (llStatement instanceof LlAssignStmt) {
                llStatement.accept(symLlStatementExeutor, symMemory);
            } else if (llStatement instanceof LlEmptyStmt) {
                llStatement.accept(symLlStatementExeutor, symMemory);
            } else if (llStatement instanceof LlJumpConditional) {
                LlComponent condition = ((LlJumpConditional) llStatement).getCondition();
                Expr conditionValue;
                if (condition instanceof LlLiteral) {
                    conditionValue = symLlStatementExeutor.getLlLiteralValue((LlLiteral) condition);
                } else {
                    conditionValue = symMemory.get(condition);
                }
                HashMap<LlLocation,ValueOfDiffType> results = calNewInputs(conditionValue, symBranchChoice);
                if(results != null)  calculatedInputs.add(results);

                solver.add(ctx.mkEq(conditionValue,ctx.mkBool(!symBranchChoice)).simplify());

                llStatement.accept(symLlStatementExeutor,symMemory);
                //  the next bb according to the condition is decided by concrete execution

            } else if (llStatement instanceof LlJumpUnconditional) {
//                nextBlock = currentBolock.getAlternativeBranch();
            } else if (llStatement instanceof LlMethodCallStmt) {
                llStatement.accept(symLlStatementExeutor, symMemory);
            } else {
                System.err.println("not handled stmt: " + llStatement);
            }

        }
    }

    public HashMap<LlLocation,ValueOfDiffType> calNewInputs(Expr conditionValue, Boolean branchChoice) {
        HashMap<LlLocation,ValueOfDiffType> resluts = new HashMap<>();
        this.solver.push();
        this.solver.add(ctx.mkEq(conditionValue,ctx.mkBool(branchChoice)));

        if(this.solver.check().equals(Status.SATISFIABLE)) {
            for (LlComponent llComponent : this.inputs) {
                LlLocation location = (LlLocation) llComponent;
                Expr locationExpr = symMemory.get(location);
                String locationValue = this.solver.getModel().evaluate(locationExpr, true).toString();
                putValues(locationValue,location,resluts);
                System.out.println(locationExpr.toString() + ": " + locationValue);
            }
            this.solver.pop();
            return resluts;

        }
        else{
            System.out.println("\n"+Status.UNSATISFIABLE);
            this.solver.pop();
            return null;
        }

    }
    public void putValues(String inPut, LlLocation location,HashMap<LlLocation,ValueOfDiffType> resluts){
        BasicTypeEnum type = conMemory.getLocationvalue(location).getType();

        switch (type) {
            case BOOLEAN:
                resluts.put(location, new ValueOfDiffType(Boolean.parseBoolean(inPut)));
                break;
            case INTEGER:
                resluts.put(location, new ValueOfDiffType(Long.parseLong(inPut)));
                break;
            case FLOAT:
                resluts.put(location, new ValueOfDiffType(Double.parseDouble(inPut)));
                break;
            case STRING:
                resluts.put(location, new ValueOfDiffType(inPut));
                break;
            default:
                System.err.println("wrong type!");
        }

    }
    public Tuple2<BasicBlock,Boolean> executeBasicBlock(BasicBlock currentBolock) {
        BasicBlock nextBlock = null;
        Boolean nextBlockChoice = false;
        for (LlStatement llStatement : currentBolock.getStmtsList()) {
            if (llStatement instanceof LlAssignStmt) {
                llStatement.accept(llStatementExeutor, this.conMemory);
            } else if (llStatement instanceof LlEmptyStmt) {
                llStatement.accept(llStatementExeutor, this.conMemory);
            } else if (llStatement instanceof LlJumpConditional) {
                LlComponent condition = ((LlJumpConditional) llStatement).getCondition();
                ValueOfDiffType conditionValue = null;
                if (condition instanceof LlLiteral) {
                    conditionValue = this.llStatementExeutor.getLlLiteralValue((LlLiteral) condition);
                } else {
                    conditionValue = this.conMemory.getLocationvalue(condition);
                }
                llStatement.accept(llStatementExeutor, this.conMemory);

                // get the next bb according to the condition
                if (conditionValue.getvBoolean()) {
                    nextBlock = currentBolock.getAlternativeBranch();
                    nextBlockChoice = true;
                } else {
                    nextBlock = currentBolock.getDefaultBranch();
                }
            } else if (llStatement instanceof LlJumpUnconditional) {
                nextBlock = currentBolock.getAlternativeBranch();
                nextBlockChoice = true;
            } else if (llStatement instanceof LlMethodCallStmt) {
                llStatement.accept(llStatementExeutor, conMemory);
            } else {
                System.err.println("not handled stmt: " + llStatement);
            }

        }
        if (nextBlock == null) nextBlock = currentBolock.getDefaultBranch();
        return new Tuple2<>(nextBlock, nextBlockChoice);
    }

    public void mkInputSymbolic(Context ctx) {
        BasicBlock readBb = cfg.getLeadersToBBMap().get("Read");
        Iterator<LlStatement> llStatementIterator = readBb.getStmtsList().iterator();
        llStatementIterator.next();
        // conMemory has all input vars because of putInputVarInitToMemo()
        // accordding to conMenory, make input vars symbolic
        while (llStatementIterator.hasNext()) {
            LlMethodCallStmt llStatementRead = (LlMethodCallStmt) llStatementIterator.next();
            LlLocation location = llStatementRead.getReturnLocation();
            BasicTypeEnum type = conMemory.getLocationvalue(location).getType();
            String varName = location.getVarName();
            Expr varSym = null;
            switch (type) {
                case BOOLEAN:
                    varSym = ctx.mkBoolConst(varName);
                    break;
                case INTEGER:
                    varSym = ctx.mkIntConst(varName);
                    break;
                case FLOAT:
                    varSym = ctx.mkRealConst(varName);
                    break;
                case STRING:
                    //TODO: string needs to been handled
                    System.err.println("string needs to been handled");
                    break;
                default:
                    System.err.println("wrong type!");
            }
            this.symMemory.put(location, varSym);
        }
    }

    public void putInputVarInitToMemo() {
        Hashtable<LlComponent, LlLiteral> inputVarsInit = this.cfg.getLlSymbolTable().varInput;
        for (LlComponent llComponent : inputVarsInit.keySet()) {
            LlLiteral llLiteral = inputVarsInit.get(llComponent);
            if (llLiteral instanceof LlLiteralBool)
                this.conMemory.put(llComponent, new ValueOfDiffType(((LlLiteralBool) llLiteral).getBoolValue()));
            else if (llLiteral instanceof LlLiteralInt)
                this.conMemory.put(llComponent, new ValueOfDiffType(((LlLiteralInt) llLiteral).getIntValue()));
            else if (llLiteral instanceof LlLiteralReal)
                this.conMemory.put(llComponent, new ValueOfDiffType(((LlLiteralReal) llLiteral).getRealValue()));
            else if (llLiteral instanceof LlLiteralString)
                this.conMemory.put(llComponent, new ValueOfDiffType(((LlLiteralString) llLiteral).getStringValue()));
            else System.out.println("wrong type!");
        }
    }
}
