package ll.location;


import ll.LlComponent;

public abstract class LlLocation extends LlComponent {
    private final String varName;

    public LlLocation(String varName) {
        this.varName = varName;
    }

    public String getVarName() {
        return this.varName;
    }

    @Override
    public String toString() {
        return this.getVarName();
    }

}
