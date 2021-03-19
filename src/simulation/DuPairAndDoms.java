package simulation;

import cfg.BasicBlock;
import cfg.VarAndStmt;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;

public class DuPairAndDoms {
    VarAndStmt def;
    VarAndStmt use;
    HashSet<BasicBlock> domNodeFromEntryToDef;
    HashSet<BasicBlock> domNodesFromDefToUse;
    ArrayList<BasicBlock> sortedDomNodeFromDefToUSe;
    ArrayList<BasicBlock> sortedDomNodeFromEntryToDef;
    public DuPairAndDoms(VarAndStmt def, VarAndStmt use, HashSet<BasicBlock> domNodeFromEntryToDef, HashSet<BasicBlock> domNodesFromDefToUse){
        this.def = def;
        this.use = use;

        this.domNodeFromEntryToDef = domNodeFromEntryToDef;
        this.domNodesFromDefToUse = domNodesFromDefToUse;
        this.sortedDomNodeFromEntryToDef = new ArrayList<>(domNodeFromEntryToDef);
        this.sortedDomNodeFromEntryToDef.sort(Comparator.comparingInt(BasicBlock::getDomTreeLevel));

        this.sortedDomNodeFromDefToUSe = new ArrayList<>(domNodesFromDefToUse);
        this.sortedDomNodeFromDefToUSe.sort(Comparator.comparingInt(BasicBlock::getDomTreeLevel));
    }

}
