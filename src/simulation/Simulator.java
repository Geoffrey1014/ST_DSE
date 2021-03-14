package simulation;

import cfg.BasicBlock;
import cfg.CFG;
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

import java.text.DecimalFormat;
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
    private HashSet<BasicBlock> coveredBlocks;
    private HashSet<BasicBlock> branchBlocks;
    private HashSet<BasicBlock> stmtBlocks;
    private HashSet<LlLocation> inputVars;
    private SymbolExecutor symbolExecutor;
    private Random random = new Random(19);
    private StateManager stateManager;
    private BranchManager branchManager;

    public Simulator(CFG cfg) {
        this.cfg = cfg;
        this.llStatementExeutor = new LlStatementExeutor();
        this.coveredBlocks = new HashSet<>();
        this.branchBlocks = new HashSet<>();
        this.stmtBlocks = new HashSet<>();
        this.inputVars = cfg.getInputVars();
        this.symbolExecutor = new SymbolExecutor(cfg);
        this.stateManager = new StateManager();

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
        this.branchManager = new BranchManager(cfg,this.branchBlocks);

    }


    /**
     * use Visitor mode
     *
     * @return
     */
    public void execute() {
        //TODO：只考虑单周期。多周期由单周期组合而成，需要stateManager

        // create createInitMemory
        ConMemory conMemory = createInitMemory();
        ConMemory oldConMenory = new ConMemory(conMemory);
        //createSymMemory according to conMemory and mkInputSymbolic
        SymMemory symMemory = symbolExecutor.createSymMemory(conMemory);
        symbolExecutor.mkInputSymbolic(symMemory);

        // initially concrete exe
        Tuple2<List<BasicBlock>, List<Tuple2<Integer, Boolean>>> result = conExeFromRead(createDefaultInputs(), conMemory);
        List<BasicBlock> route = result.a1;
        List<Tuple2<Integer, Boolean>> branches = result.a2;
        stateManager.add(conMemory);
        branchManager.addRoute(route,branches);

        // pruning algorithm
        List<Tuple2<Integer, Boolean>> flipBranches = branchManager.pruningBranches(route, branches);

        // SE and get calculatedInputs
        LinkedList<HashMap<LlLocation, ValueOfDiffType>> calculatedInputs = symbolExecutor.symExeFromRead(symMemory, route, flipBranches);

        // concrete exe calculatedInputs
        while (calculatedInputs.size() > 0) {
            ConMemory nextConMemory = new ConMemory(oldConMenory);
            result = conExeFromRead(calculatedInputs.pop(), nextConMemory);
            stateManager.add(nextConMemory);
        }

        while (stateManager.candidatesSize() > 0){
            result = conExeFromRead(createRandomInputs(),stateManager.popLeft());
        }
        System.out.println("branch coverage: "+branchManager.coverageRate());

    }



    /**
     * execute from initBlock and circle the loop for n times
     *
     * @return
     */
    public Tuple2<List<String>, List<Tuple2<Integer, Boolean>>> conExeFromInit(int circleNumLimit, ConMemory conMemory) {
        putNonInputVarInitToMemory(conMemory); // initialize NonInput vars
        List<String> route = new ArrayList<>();
        List<Tuple2<Integer, Boolean>> branches = new ArrayList<>();
        BasicBlock initNode = this.cfg.getBasicBlocks().get(0).getDefaultBranch(); //initNode
        BasicBlock curBlock = initNode;
        int bbConuter = 0;
        int circleCounter = 0;
        while (circleCounter < circleNumLimit) {
            String leaderLabel = this.cfg.blockLabels.get(curBlock);
            System.out.println("\n" + leaderLabel);
            route.add(leaderLabel);
            bbConuter += 1;
            if (leaderLabel.equals("End")) {
                circleCounter += 1;
            }

            Tuple2<BasicBlock, Boolean> nextBB;
            if (leaderLabel.equals("Read") && circleCounter == 0) {
                putInputVarInitToMemory(conMemory); // initialize input vars
                nextBB = new Tuple2<>(curBlock.getDefaultBranch(), false);
            } else {
                nextBB = executeBasicBlock(curBlock, conMemory);
            }

            if (this.branchBlocks.contains(curBlock)) branches.add(new Tuple2<>(bbConuter, nextBB.a2));
            if (this.stmtBlocks.contains(curBlock)) this.coveredBlocks.add(curBlock);

            curBlock = nextBB.a1;
            System.out.println("stmt coverage: " + this.coveredBlocks.size() / (float) this.stmtBlocks.size());
        }
        return new Tuple2<>(route, branches);

    }

    public void setInputIntoMemory(HashMap<LlLocation, ValueOfDiffType> inputs, ConMemory conMemory) {
        for (LlLocation location : inputs.keySet()) {
            conMemory.put(location, inputs.get(location));
        }
    }

    /**
     * execute from readBlock with given inputs for 1 circle
     *
     * @param inputs
     * @return
     */
    public Tuple2<List<BasicBlock>, List<Tuple2<Integer, Boolean>>> conExeFromRead(
            HashMap<LlLocation, ValueOfDiffType> inputs, ConMemory conMemory) {

        // equal to executing Read Block
        setInputIntoMemory(inputs, conMemory);
        List<BasicBlock> route = new ArrayList<>();
        BasicBlock readBB = this.cfg.leadersToBBMap.get("Read");
        route.add(readBB);

        BasicBlock curBlock = this.cfg.leadersToBBMap.get("Body");
        List<Tuple2<Integer, Boolean>> branches = new ArrayList<>();
        int conuter = 1;
        while (curBlock != null) {
            String leaderLabel = this.cfg.blockLabels.get(curBlock);
            System.out.println("\n" + leaderLabel);

            if (curBlock.equals(readBB)) break;
            route.add(curBlock);
            Tuple2<BasicBlock, Boolean> nextBB = executeBasicBlock(curBlock, conMemory);
            if (this.branchBlocks.contains(curBlock)) branches.add(new Tuple2<>(conuter, nextBB.a2));
            if (this.stmtBlocks.contains(curBlock)) this.coveredBlocks.add(curBlock);
            conuter += 1;
            curBlock = nextBB.a1;
        }
        System.out.println("stmts coverage: " + this.coveredBlocks.size() / (float) this.stmtBlocks.size());
        return new Tuple2<>(route, branches);
    }


    /**
     * exe a basicBlock and get the next BB according to the condition if it is a branch BB otherwise the default BB
     *
     * @param currentBolock
     * @return
     */
    public Tuple2<BasicBlock, Boolean> executeBasicBlock(BasicBlock currentBolock, ConMemory conMemory) {
        BasicBlock nextBlock = null;
        Boolean nextBlockChoice = false;
        if (currentBolock.name.equals("Read")) {
            HashMap<LlLocation, ValueOfDiffType> inputs = createRandomInputs();
            setInputIntoMemory(inputs, conMemory);
        } else {
            for (LlStatement llStatement : currentBolock.getStmtsList()) {
                if (llStatement instanceof LlAssignStmt) {
                    llStatement.accept(llStatementExeutor, conMemory);
                } else if (llStatement instanceof LlEmptyStmt) {
                    llStatement.accept(llStatementExeutor, conMemory);
                } else if (llStatement instanceof LlJumpConditional) {
                    LlComponent condition = ((LlJumpConditional) llStatement).getCondition();
                    ValueOfDiffType conditionValue = null;
                    if (condition instanceof LlLiteral) {
                        conditionValue = this.llStatementExeutor.getLlLiteralValue((LlLiteral) condition);
                    } else {
                        conditionValue = conMemory.getLocationvalue(condition);
                    }
                    llStatement.accept(llStatementExeutor, conMemory);

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
        }

        if (nextBlock == null) nextBlock = currentBolock.getDefaultBranch();
        return new Tuple2<>(nextBlock, nextBlockChoice);
    }

    public ConMemory createInitMemory() {
        ConMemory conMemory = new ConMemory();
        putNonInputVarInitToMemory(conMemory);
        return conMemory;
    }

    public HashMap<LlLocation, ValueOfDiffType> createDefaultInputs() {
        HashMap<LlLocation, ValueOfDiffType> inputs = new HashMap<>();
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

    public HashMap<LlLocation, ValueOfDiffType> createRandomInputs() {
        HashMap<LlLocation, ValueOfDiffType> inputs = new HashMap<>();
        Hashtable<LlComponent, LlLiteral> inputVarsInit = this.cfg.getLlSymbolTable().varInput;
        for (LlComponent llComponent : inputVarsInit.keySet()) {
            LlLiteral llLiteral = inputVarsInit.get(llComponent);
            if (llLiteral instanceof LlLiteralBool)
                inputs.put((LlLocation) llComponent, new ValueOfDiffType(random.nextBoolean()));
            else if (llLiteral instanceof LlLiteralInt)
                inputs.put((LlLocation) llComponent, new ValueOfDiffType((long) random.nextInt(5) * (random.nextBoolean() ? -1 : 1)));
            else if (llLiteral instanceof LlLiteralReal) {
                float leftLimit = -5F;
                float rightLimit = 5F;
                DecimalFormat df = new DecimalFormat("0.0");
                String s = df.format(leftLimit + random.nextDouble() * (rightLimit - leftLimit));
                inputs.put((LlLocation) llComponent, new ValueOfDiffType(Double.parseDouble(s)));
            } else if (llLiteral instanceof LlLiteralString)
                inputs.put((LlLocation) llComponent, new ValueOfDiffType("it is not dealt with")); // easy to do this
            else System.out.println("wrong type!");
        }
        return inputs;
    }

    public void putInputVarInitToMemory(ConMemory conMemory) {
        Hashtable<LlComponent, LlLiteral> inputVarsInit = this.cfg.getLlSymbolTable().varInput;
        for (LlComponent llComponent : inputVarsInit.keySet()) {
            LlLiteral llLiteral = inputVarsInit.get(llComponent);
            if (llLiteral instanceof LlLiteralBool)
                conMemory.put(llComponent, new ValueOfDiffType(((LlLiteralBool) llLiteral).getBoolValue()));
            else if (llLiteral instanceof LlLiteralInt)
                conMemory.put(llComponent, new ValueOfDiffType(((LlLiteralInt) llLiteral).getIntValue()));
            else if (llLiteral instanceof LlLiteralReal)
                conMemory.put(llComponent, new ValueOfDiffType(((LlLiteralReal) llLiteral).getRealValue()));
            else if (llLiteral instanceof LlLiteralString)
                conMemory.put(llComponent, new ValueOfDiffType(((LlLiteralString) llLiteral).getStringValue()));
            else System.out.println("wrong type!");
        }
    }

    public void putNonInputVarInitToMemory(ConMemory conMemory) {
        Hashtable<LlComponent, LlLiteral> varNonInput = this.cfg.getLlSymbolTable().varNonInput;
        for (LlComponent llComponent : varNonInput.keySet()) {
            LlLiteral llLiteral = varNonInput.get(llComponent);
            if (llLiteral instanceof LlLiteralBool)
                conMemory.put(llComponent, new ValueOfDiffType(((LlLiteralBool) llLiteral).getBoolValue()));
            else if (llLiteral instanceof LlLiteralInt)
                conMemory.put(llComponent, new ValueOfDiffType(((LlLiteralInt) llLiteral).getIntValue()));
            else if (llLiteral instanceof LlLiteralReal)
                conMemory.put(llComponent, new ValueOfDiffType(((LlLiteralReal) llLiteral).getRealValue()));
            else if (llLiteral instanceof LlLiteralString)
                conMemory.put(llComponent, new ValueOfDiffType(((LlLiteralString) llLiteral).getStringValue()));
            else System.out.println("wrong type!");
        }
    }
}
