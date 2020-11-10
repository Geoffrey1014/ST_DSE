package cfg;

import helper.LlBuilder;
import ll.LlComponent;
import ll.LlStatement;
import ll.assignStmt.LlAssignStmtBinaryOp;
import ll.assignStmt.LlAssignStmtRegular;
import ll.assignStmt.LlAssignStmtUnaryOp;
import ll.location.LlLocation;
import ll.location.LlLocationArray;
import ll.location.LlLocationVar;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;

public class LocalCSE {
    //    private final HashSet<ExprObject> computedExpressions = new HashSet<>();
    private final HashMap<ExprObject, LlLocationVar> tempsForExpressions = new HashMap<>();
    private final LlBuilder builder;

    public LocalCSE(LlBuilder builder) {
        this.builder = builder;
    }

    public static void performLocalCSE(BasicBlock bb, HashSet<LlLocation> globalVariables) {
        LocalCSE cse = new LocalCSE(bb.getBuilder());
        LinkedHashMap<String, LlStatement> labelsToStmtsMap = bb.getLabelsToStmtsMap();

        // 2) loop through the current linked HashMap
        for (String label : labelsToStmtsMap.keySet()) {
            LlStatement stmt = labelsToStmtsMap.get(label);

            // 3) only do CSE on stmts that have a computation
            if (stmt instanceof LlAssignStmtUnaryOp) {
                LlAssignStmtUnaryOp unaryOp = (LlAssignStmtUnaryOp) stmt;

                // we only store computations if they are variables (we don't do this for a[i]'s)
                if (unaryOp.getStoreLocation() instanceof LlLocationVar
                        && !(unaryOp.getOperand() instanceof LlLocationArray)) {

                    // check to see if this computation has been made before
                    UnaryExprObject uniExpr = cse.new UnaryExprObject(unaryOp);
                    if (cse.tempsForExpressions.containsKey(uniExpr)) {

                        // if it has been made before, swap the computation
                        // with the temp that already stores the value
                        LlAssignStmtRegular optimalStmt = new LlAssignStmtRegular(
                                unaryOp.getStoreLocation(),
                                cse.tempsForExpressions.get(uniExpr)
                        );
                        labelsToStmtsMap.put(label, optimalStmt);
                    }
//                     if the computation has not been made before, store it for later
                    else if (!uniExpr.containsAnyOfTheseVariables(globalVariables)) {
                        cse.tempsForExpressions.put(uniExpr, (LlLocationVar) unaryOp.getStoreLocation());
                    }

                    // check cse.tempsForExpressions for any expressions whose operand
                    // is the storeLocation and remove those expressions because they are no longer valid.
                    for (ExprObject exprObject : new HashSet<>(cse.tempsForExpressions.keySet())) {
                        if (exprObject.containsVariable(unaryOp.getStoreLocation())) {
                            cse.tempsForExpressions.remove(exprObject);
                        }
                    }
                }
            } else if (stmt instanceof LlAssignStmtBinaryOp) {
                LlAssignStmtBinaryOp binaryOp = (LlAssignStmtBinaryOp) stmt;

                // we only store computations if they are variables (we don't do this for a[i]'s)
                if (binaryOp.getStoreLocation() instanceof LlLocationVar
                        && !(binaryOp.getLeftOperand() instanceof LlLocationArray)
                        && !(binaryOp.getRightOperand() instanceof LlLocationArray)) {

                    // check to see if this computation has been made before
                    BinaryExprObject binaryExpr = cse.new BinaryExprObject(binaryOp);
                    if (cse.tempsForExpressions.containsKey(binaryExpr)) {

                        // if it has been made before, swap the computation
                        // with the temp that already stores the value
                        LlAssignStmtRegular optimalStmt = new LlAssignStmtRegular(
                                binaryOp.getStoreLocation(),
                                cse.tempsForExpressions.get(binaryExpr)
                        );
                        labelsToStmtsMap.put(label, optimalStmt);
                    }
                    // if the computation has not been made before, store it for later
                    else if (!binaryExpr.containsAnyOfTheseVariables(globalVariables)) {
                        cse.tempsForExpressions.put(binaryExpr, (LlLocationVar) binaryOp.getStoreLocation());
                    }

                    // check cse.tempsForExpressions for any expressions whose operand
                    // is the storeLocation and remove those expressions because they are no longer valid.
                    for (ExprObject exprObject : new HashSet<>(cse.tempsForExpressions.keySet())) {
                        if (exprObject.containsVariable(binaryOp.getStoreLocation())) {
                            cse.tempsForExpressions.remove(exprObject);
                        }
                    }
                }
            } else if (stmt instanceof LlAssignStmtRegular) {
                LlAssignStmtRegular stmtRegular = (LlAssignStmtRegular) stmt;

                // check cse.tempsForExpressions for any expressions whose operand
                // is the storeLocation and remove those expressions because they are no longer valid.
                for (ExprObject exprObject : new HashSet<>(cse.tempsForExpressions.keySet())) {
                    if (exprObject.containsVariable(stmtRegular.getStoreLocation())) {
                        cse.tempsForExpressions.remove(exprObject);
                    }
                }
            }
        }

    }



    // create the ExprObject
    // Required API:
    // 1) .containsVariable()
    // 2) .equals()
    // 3) .hash() **

    private abstract class ExprObject {
        protected final String operation;
        protected final LlComponent rightOperand;

        public ExprObject(String operation, LlComponent rightOperand) {
            this.operation = operation;
            this.rightOperand = rightOperand;
        }
        public abstract boolean containsVariable(LlComponent comp);

        public boolean containsAnyOfTheseVariables(Collection<LlLocation> variables) {
            for (LlLocation var : variables) {
                if (this.containsVariable(var)) {
                    return true;
                }
            }
            return false;
        }

    }

    private class UnaryExprObject extends ExprObject {
        public UnaryExprObject(LlAssignStmtUnaryOp unaryOp) {
            super(unaryOp.getOperator(), unaryOp.getOperand());
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof UnaryExprObject) {
                UnaryExprObject that = (UnaryExprObject) obj;
                return this.operation.equals(that.operation) && this.rightOperand.equals(that.rightOperand);
            }
            return false;
        }

        @Override
        public boolean containsVariable(LlComponent comp) {
            return this.rightOperand.equals(comp);
        }

        @Override
        public int hashCode() {
            return this.operation.hashCode() * this.rightOperand.hashCode();
        }
    }

    private class BinaryExprObject extends ExprObject {
        private final LlComponent leftOperand;

        public BinaryExprObject(LlAssignStmtBinaryOp binaryOp) {
            super(binaryOp.getOperation(), binaryOp.getRightOperand());
            this.leftOperand = binaryOp.getLeftOperand();
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof BinaryExprObject) {
                BinaryExprObject that = (BinaryExprObject) obj;

                // straight up equal
                if (this.operation.equals(that.operation)
                        && this.leftOperand.equals(that.leftOperand)
                        && this.rightOperand.equals(that.rightOperand)) {
                    return true;
                }

                // associativity of + and *
                if (this.operation.equals("+") || this.operation.equals("*")) {
                    if (this.operation.equals(that.operation)) {
                        if (this.leftOperand.equals(that.rightOperand) && this.rightOperand.equals(that.leftOperand)) {
                            return true;
                        }
                    }
                }
            }
            return false;
        }

        @Override
        public boolean containsVariable(LlComponent comp) {
            return this.leftOperand.equals(comp) || this.rightOperand.equals(comp);
        }

        @Override
        public int hashCode() {
            return this.leftOperand.hashCode() * this.operation.hashCode() * this.rightOperand.hashCode();
        }
    }



}
