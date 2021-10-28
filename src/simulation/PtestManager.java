package simulation;

import java.util.*;

public class PtestManager {
    LinkedList<PtestWithState> ptestWithStateLinkedList = new LinkedList<>();
    LinkedList<PtestWithState> roots =  new LinkedList<>();
    Map<String,List> pathMap=new HashMap();


    public void addPtestWithStates(PtestWithState ptestWithState){
        ptestWithStateLinkedList.add(ptestWithState);
    }

    public void buildMCTestTree(){
        for(int i=0  ; i< ptestWithStateLinkedList.size(); i++){
            boolean findPreStateFlag = false;
            for(int j=0  ; j< i; j++){
                PtestWithState curPtestWithState = ptestWithStateLinkedList.get(i);
                PtestWithState prePtestWithState = ptestWithStateLinkedList.get(j);
                if(curPtestWithState.PLCStartState.equals(prePtestWithState.PLCEndState)){
                    prePtestWithState.nextPtestWithStates.add(curPtestWithState);
                    curPtestWithState.fatherPtestWithStates = prePtestWithState;
                    findPreStateFlag = true;
                    break;
                }
            }
            if(findPreStateFlag == false){
                roots.add(ptestWithStateLinkedList.get(i));
            }

        }
        // 删除无用的叶子节点
        boolean changed = true;
        while(changed){
            changed = false;
            LinkedList<PtestWithState> ptestWithStateLinkedListCopy = (LinkedList<PtestWithState>) ptestWithStateLinkedList.clone();
            for(PtestWithState ptestWithState: ptestWithStateLinkedList){
                if(ptestWithState.nextPtestWithStates.size() == 0){
                    if(!ptestWithState.improveCoverage && ptestWithState.fatherPtestWithStates != null){
                        ptestWithState.fatherPtestWithStates.nextPtestWithStates.remove(ptestWithState);
                        ptestWithStateLinkedListCopy.remove(ptestWithState);
                        changed = true;
                    }
                }
            }
            ptestWithStateLinkedList = ptestWithStateLinkedListCopy;
        }
    }

    public String generatedMCtest(){
        HashSet<String> mc_tests = new HashSet<>();
        for(PtestWithState root : roots){
            travelTree(root);

            for(List<PtestWithState> path : pathMap.values()){
                String s = "";
                for(PtestWithState ptestWithState : path){

                    s += ptestWithState.Ptest.toString();
                }
                mc_tests.add(s);

            }
        }
        StringBuilder result = new StringBuilder();
        Iterator<String> itr = mc_tests.iterator();
        int i = 1;
        while(itr.hasNext()){
            result.append("MC_test").append(i++).append(" : ").append(itr.next()).append("\n");
        }
        return result.toString();
    }

    private void travelTree(PtestWithState root){
        Stack <PtestWithState> pathstack =new Stack();
        iteratorNode(root,pathstack);
    }

    public void iteratorNode(PtestWithState n,Stack<PtestWithState> pathstack) {
        pathstack.push(n);//入栈
        List childlist = n.nextPtestWithStates;
        if (childlist.size() == 0)//没有孩子 说明是叶子结点
        {
            List lst = new ArrayList();
            Iterator stackIt = pathstack.iterator();
            while (stackIt.hasNext()) {
                lst.add(stackIt.next());

            }
            pathMap.put(n.getID(), lst);//保存路径信息
            return;
        } else {
            Iterator it = childlist.iterator();
            while (it.hasNext()) {
                PtestWithState child = (PtestWithState) it.next();
                iteratorNode(child, pathstack);//深度优先 进入递归
                pathstack.pop();//回溯时候出栈
            }
        }
    }


}
