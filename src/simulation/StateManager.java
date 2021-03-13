package simulation;

import java.util.HashSet;

public class StateManager {
    HashSet<ConMemory> stateSet;
    public StateManager(){
        this.stateSet = new HashSet<>();
    }
    public void add(ConMemory conMemory){
        // TODO : add a conMemory to set
        this.stateSet.add(conMemory);
    }
}
