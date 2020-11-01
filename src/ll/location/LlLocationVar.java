package ll.location;


public class LlLocationVar extends LlLocation {
    public LlLocationVar(String varName) {
        super(varName);
    }

    @Override
    public String toString() {
        return this.getVarName().toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof LlLocationVar)) {
            return false;
        }
        return ((LlLocationVar) obj).getVarName().equals(this.getVarName());
    }

    @Override
    public int hashCode() {
        return this.getVarName().hashCode();
    }

    public boolean isStringLoc() {
        return this.getVarName().contains("str");
    }

}
