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
    private HashSet<BasicBlock> coveredBlocks;
    private HashSet<BasicBlock> branchBlocks;
    private HashSet<BasicBlock> stmtBlocks;
    private HashSet<LlLocation> inputVars;
    private SymbolExecutor symbolExecutor;

    public Simulator(CFG cfg) {
        this.cfg = cfg;
        this.conMemory = new ConMemory();
        this.llStatementExeutor = new LlStatementExeutor();
        this.coveredBlocks = new HashSet<>();
        this.branchBlocks = new HashSet<>();
        this.stmtBlocks = new HashSet<>();
        this.inputVars = cfg.getInputVars();
        this.symbolExecutor = new SymbolExecutor(cfg);
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

    }


    public HashMap<LlLocation, ValueOfDiffType> initDefaultInputs() {
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
        Random random = new Random(19);
        Hashtable<LlComponent, LlLiteral> inputVarsInit = this.cfg.getLlSymbolTable().varInput;
        for (LlComponent llComponent : inputVarsInit.keySet()) {
            LlLiteral llLiteral = inputVarsInit.get(llComponent);
            if (llLiteral instanceof LlLiteralBool)
                inputs.put((LlLocation) llComponent, new ValueOfDiffType(random.nextBoolean()));
            else if (llLiteral instanceof LlLiteralInt)
                inputs.put((LlLocation) llComponent, new ValueOfDiffType((long) random.nextInt(5)* (random.nextBoolean() ? -1 : 1)));
            else if (llLiteral instanceof LlLiteralReal) {
                inputs.put((LlLocation) llComponent, new ValueOfDiffType(((double) random.nextFloat()) * (random.nextBoolean() ? -1 : 1)));
            } else if (llLiteral instanceof LlLiteralString)
                inputs.put((LlLocation) llComponent, new ValueOfDiffType("it is not dealt with")); // easy to do this
            else System.out.println("wrong type!");
        }
        return inputs;
    }


    /**
     * use Visitor mode
     *
     * @return
     */
    public void execute() {

        Tuple2<List<String>, List<Tuple2<Integer, Boolean>>> result = conExeFromInit(2);
        List<String> route = result.a1;
        List<Tuple2<Integer, Boolean>> branches = result.a2;
        System.out.println(route);
        System.out.println(branches);

        LinkedList<HashMap<LlLocation, ValueOfDiffType>> calculatedInputs = this.symbolExecutor.symExe(route, branches);

        while (calculatedInputs.size() > 0) {
            result = conExeFromRead(calculatedInputs.pop());
        }

    }

    /**
     * execute from initBlock and circle the loop for n times
     *
     * @return
     */
    public Tuple2<List<String>, List<Tuple2<Integer, Boolean>>> conExeFromInit(int circleNumLimit) {
        putNonInputVarInitToMemory(); // initialize NonInput vars
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

            if (leaderLabel.equals("End")) {
                circleCounter += 1;
            }

            Tuple2<BasicBlock, Boolean> nextBB;
            if (leaderLabel.equals("Read") && circleCounter == 0) {
                putInputVarInitToMemory(); // initialize input vars
                nextBB = new Tuple2<>(curBlock.getDefaultBranch(), false);
            } else {
                nextBB = executeBasicBlock(curBlock);
            }

            if (this.branchBlocks.contains(curBlock)) branches.add(new Tuple2<>(bbConuter, nextBB.a2));
            if (this.stmtBlocks.contains(curBlock)) this.coveredBlocks.add(curBlock);
            bbConuter += 1;
            curBlock = nextBB.a1;
            System.out.println("stmt coverage: " + this.coveredBlocks.size() / (float) this.stmtBlocks.size());
        }
        return new Tuple2<>(route, branches);

    }

    public void setInputIntoMemory(HashMap<LlLocation, ValueOfDiffType> inputs) {
        for (LlLocation location : inputs.keySet()) {
            conMemory.put(location, inputs.get(location));
        }
    }

    /**
     * execute from readBlock with given inputs
     *
     * @param inputs
     * @return
     */
    public Tuple2<List<String>, List<Tuple2<Integer, Boolean>>> conExeFromRead(HashMap<LlLocation, ValueOfDiffType> inputs) {
        setInputIntoMemory(inputs);
        List<String> route = new ArrayList<>();
        route.add("Read");

        BasicBlock curBlock = this.cfg.leadersToBBMap.get("Body");
        List<Tuple2<Integer, Boolean>> branches = new ArrayList<>();
        int conuter = 1;
        while (curBlock != null) {
            String leaderLabel = this.cfg.blockLabels.get(curBlock);
            System.out.println("\n" + leaderLabel);

            route.add(leaderLabel);
            if (leaderLabel.equals("Read")) break;

            Tuple2<BasicBlock, Boolean> nextBB = executeBasicBlock(curBlock);
            if (this.branchBlocks.contains(curBlock)) branches.add(new Tuple2<>(conuter, nextBB.a2));
            if (this.stmtBlocks.contains(curBlock)) this.coveredBlocks.add(curBlock);
            conuter += 1;
            curBlock = nextBB.a1;
            System.out.println("coverage: " + this.coveredBlocks.size() / (float) this.stmtBlocks.size());
        }
        return new Tuple2<>(route, branches);
    }


    /**
     * exe a basicBlock and get the next BB according to the condition if it is a branch BB otherwise the default BB
     *
     * @param currentBolock
     * @return
     */
    public Tuple2<BasicBlock, Boolean> executeBasicBlock(BasicBlock currentBolock) {
        BasicBlock nextBlock = null;
        Boolean nextBlockChoice = false;
        if (currentBolock.name.equals("Read")) {
            HashMap<LlLocation, ValueOfDiffType> inputs = createRandomInputs();
            setInputIntoMemory(inputs);
        } else {
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
        }

        if (nextBlock == null) nextBlock = currentBolock.getDefaultBranch();
        return new Tuple2<>(nextBlock, nextBlockChoice);
    }


    public void putInputVarInitToMemory() {
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

    public void putNonInputVarInitToMemory() {
        Hashtable<LlComponent, LlLiteral> varNonInput = this.cfg.getLlSymbolTable().varNonInput;
        for (LlComponent llComponent : varNonInput.keySet()) {
            LlLiteral llLiteral = varNonInput.get(llComponent);
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
