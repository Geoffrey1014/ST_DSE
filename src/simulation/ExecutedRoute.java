package simulation;

import cfg.BasicBlock;
import tools.Tuple2;

import java.util.LinkedHashMap;
import java.util.List;

public class ExecutedRoute {
    List<Tuple2<Integer,Boolean>> branches;
    List<BasicBlock> route;
    LinkedHashMap<BasicBlock,Boolean> executedBranches;
    public ExecutedRoute(List<BasicBlock> route, List<Tuple2<Integer,Boolean>> branches){
        this.branches =branches;
        this.route = route;
        executedBranches = new LinkedHashMap<>();
        for(Tuple2<Integer, Boolean> b : branches){
            executedBranches.put(route.get(b.a1),b.a2);
        }
    }

    public LinkedHashMap<BasicBlock,Boolean> getExecutedBranches(){
        return executedBranches;
    }
}
