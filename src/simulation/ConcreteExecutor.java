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
import tools.GraphViz;
import tools.Tuple2;

import java.text.DecimalFormat;
import java.util.*;

public class ConcreteExecutor {
    private final CFG cfg;
    private final LlStatementExeutor llStatementExeutor;
    private Random random = new Random(19);

    HashSet<BasicBlock> branchBlocks;
    HashSet<BasicBlock> stmtBlocks;
    HashSet<BasicBlock> coveredBlocks;
    HashSet<BasicBlock> coveredBlocks2 = new HashSet<>();
    public ConcreteExecutor(CFG cfg) {
        this.cfg = cfg;
        this.llStatementExeutor = new LlStatementExeutor();
        this.coveredBlocks = new HashSet<>();
        this.branchBlocks = new HashSet<>();
        this.stmtBlocks = new HashSet<>();
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
        return new Tuple2<>(new ExecutedRoute(route, branches), endConMemory);
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
    public void setInputIntoMemory(HashMap<LlLocation, ValueOfDiffType> inputs, ConMemory conMemory) {
        for (LlLocation location : inputs.keySet()) {
            conMemory.put(location, inputs.get(location));
        }
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
}
