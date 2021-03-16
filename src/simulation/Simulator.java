package simulation;

import cfg.BasicBlock;
import cfg.CFG;
import cfg.GraphViz;
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

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
    private SymbolExecutor symbolExecutor;
    private Random random = new Random(19);
    private StateManager stateManager;
    private BranchManager branchManager;
    private HashSet<BasicBlock> coveredBlocks2 = new HashSet<>();

    public Simulator(CFG cfg) {
        this.cfg = cfg;
        this.llStatementExeutor = new LlStatementExeutor();
        this.coveredBlocks = new HashSet<>();
        this.branchBlocks = new HashSet<>();
        this.stmtBlocks = new HashSet<>();
        this.symbolExecutor = new SymbolExecutor(cfg);
        this.stateManager = new StateManager();

        // get branch block and blocks which has executable stmts (not empty stmt)
        ArrayList<BasicBlock> basicBlockArrayList = cfg.getBasicBlocks();
        for (BasicBlock bb : basicBlockArrayList) {
            LlStatement llStatement = bb.getStmtsList().get(bb.getStmtsList().size() - 1);
            if (llStatement instanceof LlJumpConditional) {
                this.branchBlocks.add(bb);
            }
            if (bb.getStmtsList().size() > 1 && !bb.name.equals("Init")) {
                this.stmtBlocks.add(bb);
            }
        }
        this.branchManager = new BranchManager(cfg, this.branchBlocks);

    }

    public String toGraphviz() {
        GraphViz graphViz = new GraphViz();
        for (BasicBlock bb : cfg.getBasicBlocks()) {
            String label = bb.toString();
            if (coveredBlocks2.contains(bb)) {
                graphViz.nodes.put(label, true);
            } else {
                graphViz.nodes.put(label, false);
            }

            if (bb.getDefaultBranch() != null) {
                BasicBlock b = bb.getDefaultBranch();
                graphViz.edges.map(label, b.toString() + "---default");
            }
            if (bb.getAlternativeBranch() != null) {
                BasicBlock b = bb.getAlternativeBranch();
                graphViz.edges.map(label, b.toString() + "---alter");
            }
        }
        return graphViz.toDOT3();
    }

    public void genGraphViz(String outPutDir) {
        String graphVizFilename = outPutDir + "Graph_debug" + ".dot";
        File writename = new File(graphVizFilename); // 相对路径，如果没有则要建立一个新的output。txt文件
        try {
            writename.createNewFile();

            BufferedWriter out = new BufferedWriter(new FileWriter(writename));
            out.write(toGraphviz()); // \r\n即为换行
            out.flush(); // 把缓存区内容压入文件
            out.close(); // 最后记得关闭文件
            Runtime.getRuntime().exec("dot" + " " + graphVizFilename + " -Tpdf" + " -o" + " " + outPutDir + "Graph_debug" + ".pdf").waitFor();
        } catch (IOException | InterruptedException e) {
            System.err.println("There was an error:\n" + e);
        }

        System.out.println("to Graphviz finish!");
    }

    public void oneCircleTest(ConMemory startConMenory, HashMap<LlLocation, ValueOfDiffType> inputs) {
        SymMemory startSymMemory = symbolExecutor.createSymMemory(startConMenory);
        symbolExecutor.mkInputSymbolic(startSymMemory);

        LinkedList<HashMap<LlLocation, ValueOfDiffType>> inputsWorkList = new LinkedList<>();
        inputsWorkList.add(inputs);
        int counter = 0;
        while (inputsWorkList.size() > 0) {
//            System.out.println("--inputs: " + counter++);
            Tuple2<ExecutedRoute,ConMemory> exeResult= conExeFromRead(inputsWorkList.pollFirst(), startConMenory);
            ExecutedRoute executedRoute = exeResult.a1;
            stateManager.add(exeResult.a2);
            branchManager.addRoute(executedRoute.route, executedRoute.branches);

            float branchCoverage = branchManager.coverageRate();
            if (branchCoverage > 0.99) break;

            // flipBranches
            List<BasicBlock> flipBranches = branchManager.flipBranches(executedRoute.executedBranches);
            // SE and get calculatedInputs
            LinkedList<HashMap<LlLocation, ValueOfDiffType>> calculatedInputs = symbolExecutor.symExeFromRead(startSymMemory, executedRoute.route, executedRoute.executedBranches, flipBranches);
            inputsWorkList.addAll(calculatedInputs);
//            genGraphViz("");

        }

    }

    public void branchTest(String fileName) {
        HashMap<String,Double> oldBranchTestData = new OldBranchTestData().data;
        // create createInitMemory
        ConMemory oldConMenory = createInitMemory();
        stateManager.add(oldConMenory);
        int counter = 0;
        float branchCoverage = 0;
        while (stateManager.candidatesSize() > 0) {
            counter++;
//            System.out.println("------- circle -----------" + counter++);
            oldConMenory = stateManager.popLeft();
            oneCircleTest(oldConMenory, createRandomInputs());
            branchCoverage = branchManager.coverageRate();
            if (branchCoverage > 0.99 || counter > 40) break;
//            genGraphViz("");
        }
        Double oldData = 0.0D;
        if(oldBranchTestData.containsKey(fileName)) oldData= oldBranchTestData.get(fileName);

        System.err.println(fileName + " branch coverage: " + branchCoverage+", "+oldData);


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
    public Tuple2<ExecutedRoute,ConMemory> conExeFromRead(
            HashMap<LlLocation, ValueOfDiffType> inputs, ConMemory startConMemory) {
        ConMemory endConMemory = new ConMemory(startConMemory);
        // equal to executing Read Block
        setInputIntoMemory(inputs, endConMemory);
        List<BasicBlock> route = new ArrayList<>();
        BasicBlock readBB = this.cfg.leadersToBBMap.get("Read");
        this.coveredBlocks.add(readBB);
        this.coveredBlocks2.add(readBB);
        route.add(readBB);

        BasicBlock curBlock = this.cfg.leadersToBBMap.get("Body");
        List<Tuple2<Integer, Boolean>> branches = new ArrayList<>();
        int conuter = 1;
        while (curBlock != null) {
//            System.out.println("\n" + curBlock.name);

            if (curBlock.equals(readBB)) break;
            route.add(curBlock);
            Tuple2<BasicBlock, Boolean> nextBB = executeBasicBlock(curBlock, endConMemory);
            if (this.branchBlocks.contains(curBlock)) branches.add(new Tuple2<>(conuter, nextBB.a2));
            if (this.stmtBlocks.contains(curBlock)) this.coveredBlocks.add(curBlock);
            this.coveredBlocks2.add(curBlock);
            conuter += 1;
            curBlock = nextBB.a1;
        }
        return new Tuple2<ExecutedRoute,ConMemory>(new ExecutedRoute(route, branches), endConMemory);
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
