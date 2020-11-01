package ll;

public class LlParallelMethodStmt extends LlStatement {

    private final String parallelMethodName;

    public LlParallelMethodStmt(String methodName) {
        this.parallelMethodName = methodName;
    }

    @Override
    public String toString() {
        return "create_and_run_threads(" + this.parallelMethodName + ")";
    }

}
