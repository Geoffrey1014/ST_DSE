package cfg;

import helper.LlBuilder;
import helper.LlSymbolTable;
import ll.location.LlLocationVar;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;

import static cfg.testHelper.genGraphViz;

public class NewLivenessAnalysisTest {

    LlBuilder llBuilder;
    LlSymbolTable llSymbolTable;
    CFG cfg;
    NewLivenessAnalysis newLivenessAnalysis;
    HashMap<String, HashSet<LlLocationVar>> livenessOut;
    public void calculateLiveness(String name){
        testHelper helper = new testHelper();
        llBuilder = new LlBuilder(name);
        llSymbolTable  = new LlSymbolTable(name);
        helper.createLlBuilderAndLlSymbolTableOfHIT(llBuilder, llSymbolTable);
        cfg = new CFG(this.llBuilder, this.llSymbolTable,false);
        genGraphViz(name,cfg,"tests/");
        newLivenessAnalysis = new NewLivenessAnalysis(cfg);
        livenessOut = helper.createOutLivenessOfHIT();
    }
    @Test
    public void testNewLivrnessAnalysisTest(){
        // test pass
       calculateLiveness("HIT");

        for(BasicBlock basicBlock: newLivenessAnalysis.livenessOUT.keySet()){
            String bbName= basicBlock.getLabelsToStmtsMap().keySet().iterator().next();
            if(livenessOut.get(bbName) != null){
                Assert.assertEquals(livenessOut.get(bbName).toString(),newLivenessAnalysis.livenessOUT.get(basicBlock).toString());
            }
//            System.out.println("\nbasicBlock=" + bbName);
//            System.out.println(newLivenessAnalysis.livenessOUT.get(basicBlock));
        }


        // 那原来的的livenessAnalysis测试一下， 是错误的
//        LivenessAnalysis livenessAnalysis = new LivenessAnalysis(cfg);

        calculateLiveness("MITLiveness");
        for(BasicBlock basicBlock: newLivenessAnalysis.livenessOUT.keySet()){
            String bbName= basicBlock.getLabelsToStmtsMap().keySet().iterator().next();
            if(livenessOut.get(bbName) != null){
                Assert.assertEquals(livenessOut.get(bbName).toString(),newLivenessAnalysis.livenessOUT.get(basicBlock).toString());
            }
            //            System.out.println("\nbasicBlock=" + bbName);
//            System.out.println(newLivenessAnalysis.livenessOUT.get(basicBlock));
        }

    }


}
