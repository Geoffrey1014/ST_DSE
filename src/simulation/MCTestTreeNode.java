package simulation;

import ll.location.LlLocation;

import java.util.HashMap;
import java.util.LinkedList;

public class MCTestTreeNode {
    public ConMemory state;
    public LinkedList<ConMemory> nextStates;
    public LinkedList<HashMap<LlLocation, ValueOfDiffType>> ptestLinkedList;

    public MCTestTreeNode(ConMemory state, ConMemory nextState, HashMap<LlLocation, ValueOfDiffType> ptest){
        this.state = state;
        nextStates.add(nextState);
        ptestLinkedList.add(ptest);
    }

}
