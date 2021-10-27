package simulation;

import ll.location.LlLocation;

import java.util.HashMap;
import java.util.LinkedList;

public class PtestWithState {
    ConMemory PLCStartState;
    ConMemory PLCEndState;
    HashMap<LlLocation, ValueOfDiffType> Ptest;
    LinkedList<PtestWithState> nextPtestWithStates = new LinkedList<>();
    boolean improveCoverage = false;
    boolean visited = false;
    PtestWithState fatherPtestWithStates = null;
    int id;


    public PtestWithState(ConMemory PLCStartState, HashMap<LlLocation, ValueOfDiffType> Ptest, int id){
        this.PLCStartState = PLCStartState;
        this.Ptest = Ptest;
        this.id = id;
    }
    public void addPLCEndState(ConMemory PLCEndState){this.PLCEndState = PLCEndState;}

    @Override
    public String toString(){
        String s = "\n\n";
        s += "Start State: \n";
        s += PLCStartState.toString();
        s += "\nPtest: \n";
        s += Ptest.toString();
        s += "\nEnd State: \n";
        s += PLCEndState.toString();
        return s;
    }

    public String getID() {
        return "S"+id;
    }
}
