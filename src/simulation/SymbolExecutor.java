package simulation;

import cfg.BasicBlock;
import cfg.CFG;
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

public class SymbolExecutor {
    private final CFG cfg;
    private SymMemory symMemory;
    private HashSet<BasicBlock> coveredBlocks;
    private HashSet<BasicBlock> branchBlocks;
    private HashSet<BasicBlock> stmtBlocks;
    private Solver solver;
    private Context ctx;
    private HashSet<LlLocation> inputVars;
    public SymbolExecutor(CFG cfg){
        this.cfg = cfg;
        this.symMemory = new SymMemory();
        this.inputVars = cfg.getInputVars();
        HashMap<String, String> config = new HashMap<String, String>();
        config.put("model", "true");
        this.ctx = new Context(config);
        this.solver = this.ctx.mkSolver();
        // get input var
        this.inputVars = new HashSet<>();


    }

    public LinkedList<HashMap<LlLocation,ValueOfDiffType>> symExe(List<String> route, List<Tuple2<Integer,Boolean>> branches){
        // TODO  需要过滤算法，去除route中不必要的分支
        // TODO 把函数整理一下
        // TODO 需要拆分函数功能
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

                //TODO :
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
            for (LlLocation location : this.inputVars) {
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

    public void mkInputSymbolic(Context ctx) {
        Hashtable<LlComponent, LlLiteral> inputVarsInit = this.cfg.getLlSymbolTable().varInput;
        for (LlComponent location : inputVarsInit.keySet()) {
            Expr varSym = null;
            String varName = ((LlLocation)location).getVarName();
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
            this.symMemory.put(location, varSym);
        }

    }
}
