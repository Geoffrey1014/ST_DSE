package simulation;

import cfg.BasicBlock;
import cfg.DomTree;
import cfg.VarAndStmt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class UdChainsAndDoms {
    public HashMap<VarAndStmt, HashSet<VarAndStmt>> useDefsChains;
    public HashMap<BasicBlock, HashSet<BasicBlock>> dominatorsMap;
    public List<DuPairAndDoms> duPairAndDomsList;
    private DomTree domTree;

    public UdChainsAndDoms(DomTree domTree, HashMap<VarAndStmt, HashSet<VarAndStmt>> useDefsChains, HashMap<BasicBlock, HashSet<BasicBlock>> dominatorsMap){
        this.useDefsChains = useDefsChains;
        this.dominatorsMap = dominatorsMap;
        this.duPairAndDomsList = calCtrNodes();
        this.domTree = domTree;
    }

    public List<DuPairAndDoms> calCtrNodes() {
        List<DuPairAndDoms> duPairAndDomsList = new ArrayList<>();
        for (VarAndStmt use : useDefsChains.keySet()) {
            for (VarAndStmt def : useDefsChains.get(use)) {
                HashSet<BasicBlock> domNodesFromDefToUse = calDomNodesFromDefToUse(def,use);
                duPairAndDomsList.add(new DuPairAndDoms(def,use,dominatorsMap.get(def.getBlock()),domNodesFromDefToUse));
            }
        }
        return duPairAndDomsList;
    }

    public HashSet<BasicBlock> calDomNodesFromDefToUse(VarAndStmt def, VarAndStmt use) {
        HashSet<BasicBlock> useDmt = new HashSet<>(dominatorsMap.get(use.getBlock()));
        HashSet<BasicBlock> defDmt = new HashSet<>(dominatorsMap.get(def.getBlock()));
        // 1。 DomTree 上，DEF 是 USE 的祖先节点
        useDmt.removeAll(defDmt);

        // TODO 2。 否则，在 DomTree 上，从DEF开始向上找到LOOP的开始节点 R，
        //         如果R是USE的祖先，ctrNodes 是DEF 节点到 其叶子节点，再从R 到USE
        //         否则 再向上找LOOP，如此迭代下去
        return useDmt;

    }
}
