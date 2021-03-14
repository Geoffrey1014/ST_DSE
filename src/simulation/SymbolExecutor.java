package simulation;

import cfg.BasicBlock;
import cfg.CFG;
import com.microsoft.z3.*;
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

    public SymMemory createSymMemory(ConMemory conMemory){
        SymMemory symMemory = new SymMemory();
        copyNonInputVarToSymMemory(conMemory,symMemory);
        return symMemory;
    }


    public LinkedList<HashMap<LlLocation, ValueOfDiffType>> symExeFromRead(
            SymMemory symMemory, List<BasicBlock> route, List<Tuple2<Integer, Boolean>> branches) {
        // TODO 把函数整理一下
        // TODO 需要拆分函数功能, 这个函数是有问题的。 应该只考虑一个周期的执行
        LinkedList<HashMap<LlLocation, ValueOfDiffType>> calculatedInputs = new LinkedList<>();

        int left = 0;
        solver.push();
        for (Tuple2<Integer, Boolean> branch : branches) {
            for (BasicBlock bb : route.subList(left, branch.a1 + 1)) { //这里有问题
                if (bb.name.equals("Read")) continue;
                symExecuteBasicBlock(bb, symMemory, branch.a2, calculatedInputs);
            }
            left = branch.a1 + 1;
        }
        solver.pop();
        return calculatedInputs;
    }

    public void symExecuteBasicBlock(BasicBlock currentBolock, SymMemory symMemory, Boolean symBranchChoice,
                                     LinkedList<HashMap<LlLocation, ValueOfDiffType>> calculatedInputs) {

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

                HashMap<LlLocation, ValueOfDiffType> results = calNewInputs(conditionValue, symBranchChoice, symMemory);
                if (results != null) calculatedInputs.add(results);

                // 这里直接取反，是简化过的，需要把这功能分出去, 计算不出来的时候可以采用具体值
                solver.add(ctx.mkEq(conditionValue, ctx.mkBool(!symBranchChoice)).simplify());

                llStatement.accept(symLlStatementExeutor, symMemory);
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

    public HashMap<LlLocation, ValueOfDiffType> calNewInputs(Expr conditionValue, Boolean branchChoice, SymMemory symMemory) {
        HashMap<LlLocation, ValueOfDiffType> resluts = new HashMap<>();
        this.solver.push();
        this.solver.add(ctx.mkEq(conditionValue, ctx.mkBool(branchChoice)));

        if (this.solver.check().equals(Status.SATISFIABLE)) {
            System.out.println("\n" + Status.SATISFIABLE);
            for (LlLocation location : this.inputVars) {
                Expr locationExpr = symMemory.get(location);
                String locationValue = this.solver.getModel().evaluate(locationExpr, true).toString();
                putValues(symMemory, locationValue, location, resluts);
                System.out.println(locationExpr.toString() + ": " + locationValue);
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
            switch (type){
                case STRING:
                    System.err.println("string is not dealt with");
                    break;
                case FLOAT:
                    symMemory.put(llComponent,ctx.mkReal(value.getvDouble().toString()));
                    break;
                case INTEGER:
                    symMemory.put(llComponent,ctx.mkInt(value.getvLong()));
                    break;
                case BOOLEAN:
                    symMemory.put(llComponent,ctx.mkBool(value.getvBoolean()));
                    break;
                default:
                    System.out.println("wrong type!");
            }
        }
    }
}
