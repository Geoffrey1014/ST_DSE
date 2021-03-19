package simulation;

import java.util.HashSet;
import java.util.LinkedList;

public class StateManager {
    HashSet<ConMemory> statesAppeared;
    LinkedList<ConMemory> stateCandidates;
    public StateManager(){
        this.statesAppeared = new HashSet<>();
        this.stateCandidates = new LinkedList<>();
    }
    public StateManager(HashSet<ConMemory> states){
        this.statesAppeared = new HashSet<>(states);
        this.stateCandidates = new LinkedList<>(states);
    }

    public void add(ConMemory conMemory){
        if(!this.statesAppeared.contains(conMemory)){
            this.statesAppeared.add(conMemory);
            this.stateCandidates.add(conMemory);
        }

    }
    public ConMemory popLeft(){
        return this.stateCandidates.pollFirst();
    }
    public int candidatesSize(){
        return this.stateCandidates.size();
    }
}
