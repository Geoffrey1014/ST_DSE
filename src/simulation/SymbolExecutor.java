package simulation;

import cfg.BasicBlock;
import cfg.CFG;
import com.microsoft.z3.*;
import ll.LlComponent;
import ll.LlStatement;
import ll.jump.LlJumpConditional;
import ll.literal.*;
import ll.location.LlLocation;

import java.util.*;

public class SymbolExecutor {
    private final CFG cfg;
    private Solver solver;
    private Context ctx;
    private SymLlStatementExeutor symLlStatementExeutor;
    private HashSet<LlLocation> inputVars;
    private HashSet<LlLocation> nonInputVars;

    public SymbolExecutor(CFG cfg) {
        this.cfg = cfg;
        HashMap<String, String> config = new HashMap<String, String>();
        config.put("model", "true");
        this.ctx = new Context(config);
        this.solver = this.ctx.mkSolver();
        this.symLlStatementExeutor = new SymLlStatementExeutor(this.ctx); // executor 和 ctx 要保持一直，所以这里其实可以设置成类成员变量
        this.inputVars = cfg.getInputVars();         // get input var
        this.nonInputVars = cfg.getNonInputVars();
    }

    public SymMemory createInitSymMemory() {
        SymMemory symMemory = new SymMemory();
        putNonInputVarInitToMemory(symMemory);
        return symMemory;
    }

    public SymMemory createSymMemory(ConMemory conMemory) {
        SymMemory symMemory = new SymMemory();
        copyNonInputVarToSymMemory(conMemory, symMemory);
        return symMemory;
    }


    public LinkedList<HashMap<LlLocation, ValueOfDiffType>> symExeFromRead(
            SymMemory startSymMemory, List<BasicBlock> route, LinkedHashMap<BasicBlock, Boolean> branchNodes,
            List<BasicBlock> flippedBranches) {
        SymMemory symMemory = new SymMemory(startSymMemory);
        LinkedList<HashMap<LlLocation, ValueOfDiffType>> calculatedInputs = new LinkedList<>();
        HashSet<BasicBlock> flippedBranchesNode = new HashSet<>(flippedBranches);

        solver.push();
        for (BasicBlock bb : route) {
            if (bb.name.equals("Read")) continue;
            if (branchNodes.containsKey(bb)) {
                if (flippedBranchesNode.contains(bb)) {
                    HashMap<LlLocation, ValueOfDiffType> result = symExecuteBasicBlock(bb, symMemory, branchNodes.get(bb), true);
                    if (result != null) calculatedInputs.add(result);
                } else {
                    symExecuteBasicBlock(bb, symMemory, branchNodes.get(bb), false);
                }
            }
            else {
                symExecuteBasicBlock(bb, symMemory, false, false);
            }
        }
        solver.pop();
        return calculatedInputs;
    }

    /**
     * symExecute a BasicBlock
     * if it is branch node and the branch is flipped, calculate new inputs.
     * then symExecute the branch chosen by the concrete execution
     *
     * @param currentBolock
     * @param symMemory
     * @param conExeBranchChoice
     * @param flip
     */
    public HashMap<LlLocation, ValueOfDiffType> symExecuteBasicBlock(BasicBlock currentBolock, SymMemory symMemory,
                                                                     Boolean conExeBranchChoice, Boolean flip) {
        HashMap<LlLocation, ValueOfDiffType> result = null;
        for (LlStatement llStatement : currentBolock.getStmtsList()) {
            if (llStatement instanceof LlJumpConditional) {
                LlComponent condition = ((LlJumpConditional) llStatement).getCondition();
                Expr conditionValue;
                if (condition instanceof LlLiteral) {
                    conditionValue = symLlStatementExeutor.getLlLiteralValue((LlLiteral) condition);
                } else {
                    conditionValue = symMemory.get(condition);
                }
                // if the branch is flipped, calculate new inputs.
                if (flip) {
                    result = calNewInputs(conditionValue, !conExeBranchChoice, symMemory);
                }

                solver.add(ctx.mkEq(conditionValue, ctx.mkBool(conExeBranchChoice)).simplify());

            }
            llStatement.accept(symLlStatementExeutor, symMemory);
        }
        return result;
    }

    public HashMap<LlLocation, ValueOfDiffType> calNewInputs(Expr conditionValue, Boolean branchChoice, SymMemory symMemory) {
        HashMap<LlLocation, ValueOfDiffType> resluts = new HashMap<>();
        this.solver.push();
        this.solver.add(ctx.mkEq(conditionValue, ctx.mkBool(branchChoice)));

        if (this.solver.check().equals(Status.SATISFIABLE)) {
//            System.out.println("\n" + Status.SATISFIABLE);
            for (LlLocation location : this.inputVars) {
                Expr locationExpr = symMemory.get(location);
                String locationValue = this.solver.getModel().evaluate(locationExpr, true).toString();
                putValues(symMemory, locationValue, location, resluts);
//                System.out.println(locationExpr.toString() + ": " + locationValue);
            }
            this.solver.pop();
            return resluts;

        } else {
//            System.out.println("\n" + Status.UNSATISFIABLE);
            this.solver.pop();
            return null;
        }

    }

    public void putValues(SymMemory symMemory, String inPut, LlLocation location, HashMap<LlLocation, ValueOfDiffType> resluts) {
        Sort type = symMemory.get(location).getSort();
        if (type instanceof BoolSort) {
            resluts.put(location, new ValueOfDiffType(Boolean.parseBoolean(inPut)));
        } else if (type instanceof IntSort) {
            resluts.put(location, new ValueOfDiffType(Long.parseLong(inPut)));
        } else if (type instanceof RealSort) {
            resluts.put(location, new ValueOfDiffType(Double.parseDouble(inPut)));
        } else {
            System.err.println("wrong type!");
        }

    }

    public void mkInputSymbolic(SymMemory symMemory) {
        Hashtable<LlComponent, LlLiteral> inputVarsInit = this.cfg.getLlSymbolTable().varInput;
        for (LlComponent location : inputVarsInit.keySet()) {
            Expr varSym = null;
            String varName = ((LlLocation) location).getVarName();
            LlLiteral llLiteral = inputVarsInit.get(location);
            if (llLiteral instanceof LlLiteralBool)
                varSym = ctx.mkBoolConst(varName);
            else if (llLiteral instanceof LlLiteralInt)
                varSym = ctx.mkIntConst(varName);
            else if (llLiteral instanceof LlLiteralReal)
                varSym = ctx.mkRealConst(varName);
            else if (llLiteral instanceof LlLiteralString)
                //TODO: string needs to been handled
                System.err.println("string needs to been handled");
            else System.err.println("wrong type!");
            symMemory.put(location, varSym);
        }

    }

    public void putNonInputVarInitToMemory(SymMemory symMemory) {
        Hashtable<LlComponent, LlLiteral> varNonInput = this.cfg.getLlSymbolTable().varNonInput;
        for (LlComponent llComponent : varNonInput.keySet()) {
            LlLiteral llLiteral = varNonInput.get(llComponent);
            if (llLiteral instanceof LlLiteralBool)
                symMemory.put(llComponent, ctx.mkBool(((LlLiteralBool) llLiteral).getBoolValue()));
            else if (llLiteral instanceof LlLiteralInt)
                symMemory.put(llComponent, ctx.mkInt(((LlLiteralInt) llLiteral).getIntValue()));
            else if (llLiteral instanceof LlLiteralReal)
                symMemory.put(llComponent, ctx.mkReal(String.valueOf(((LlLiteralReal) llLiteral).getRealValue())));
            else if (llLiteral instanceof LlLiteralString)
                System.err.println("string is not supported!");
            else System.out.println("wrong type!");
        }
    }

    public void copyNonInputVarToSymMemory(ConMemory conMemory, SymMemory symMemory) {

        for (LlComponent llComponent : nonInputVars) {
            ValueOfDiffType value = conMemory.getLocationvalue(llComponent);
            BasicTypeEnum type = value.getType();
            switch (type) {
                case STRING:
                    System.err.println("string is not dealt with");
                    break;
                case FLOAT:
                    symMemory.put(llComponent, ctx.mkReal(value.getvDouble().toString()));
                    break;
                case INTEGER:
                    symMemory.put(llComponent, ctx.mkInt(value.getvLong()));
                    break;
                case BOOLEAN:
                    symMemory.put(llComponent, ctx.mkBool(value.getvBoolean()));
                    break;
                default:
                    System.out.println("wrong type!");
            }
        }
    }
}
