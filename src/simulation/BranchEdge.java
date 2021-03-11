package simulation;

public enum BranchEdge {
    DEFAULT(false), ALERT(true);

    private  boolean choice;
    BranchEdge(boolean choice){
        this.choice = choice;
    }
}
