package ll;


public class LlEmptyStmt extends LlStatement {
    @Override
    public String toString() {
        return "EMPTY_STATEMENT";
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof LlEmptyStmt;
    }

    @Override
    public int hashCode() {
        return 17; // some arbitrary prime;
    }
}
