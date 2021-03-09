package cfg;

import ll.LlStatement;
import ll.location.LlLocation;
import ll.location.LlLocationVar;

public class VarAndStmt implements Comparable<VarAndStmt> {
    public final String stmtLabel;
    // the def is of the form
    // (locationVar, statement, i, pos) which represents LlLocationVar  and LlStatement; @ instruction pos in block i
    public final LlLocation location;
    public final LlStatement statement;
    public final BasicBlock block;

    public VarAndStmt(LlLocation location, LlStatement statement, BasicBlock block, String stmtLabel) {
        this.location = location;
        this.statement = statement;
        this.block = block;
        this.stmtLabel = stmtLabel;
    }

    public LlLocation getLocation() {
        return this.location;
    }

    public LlStatement getStatement() {
        return this.statement;
    }

    public boolean containsVar(LlLocationVar var) {
        return this.location.equals(var);
    }

    public BasicBlock getBlock() {
        return block;
    }

    @Override
    public String toString() {
        return this.location.toString()+ " @ "+ this.stmtLabel + ": " + this.statement.toString() +" @ "+this.block.name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof VarAndStmt) {
            VarAndStmt that = (VarAndStmt) obj;
            // two defs will be equal if they have equivalent block, stmtLabel and statement
            return this.block.equals(that.block) && that.stmtLabel.equals(that.stmtLabel)
                    && this.location.equals(that.location);
        }
        return false;
    }

    @Override
    public int hashCode() {
        if (this.location != null) {
            return this.block.hashCode() * this.stmtLabel.hashCode() * this.location.hashCode();
        } else {
            return this.block.hashCode() * this.stmtLabel.hashCode();
        }

    }

    public int compareTo(VarAndStmt o) {
        return Integer.parseInt(this.stmtLabel.substring(1)) - Integer.parseInt(o.stmtLabel.substring(1));
    }
}
