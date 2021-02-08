package cfg;

import helper.LlBuilder;
import helper.LlSymbolTable;
import org.junit.Test;

import static cfg.testHelper.createLlBuilderAndLlSymbolTable2;
import static cfg.testHelper.genGraphViz;

public class NewLivenessAnalysisTest {

    LlBuilder llBuilder;
    LlSymbolTable llSymbolTable;
    @Test
    public void testNewLivrnessAnalysisTest(){
        // test pass
        llBuilder = new LlBuilder("test");
        llSymbolTable  = new LlSymbolTable("test");
        createLlBuilderAndLlSymbolTable2(llBuilder, llSymbolTable);
        CFG cfg = new CFG(this.llBuilder, this.llSymbolTable);
        genGraphViz("2",cfg,"tests/");
        NewLivenessAnalysis newLivenessAnalysis = new NewLivenessAnalysis(cfg);
        for(BasicBlock basicBlock: newLivenessAnalysis.livenessOUT.keySet()){
            System.out.println("\nbasicBlock=" + basicBlock.getLabelsToStmtsMap().keySet().iterator().next());
            System.out.println(newLivenessAnalysis.livenessOUT.get(basicBlock));
        }


        // 那原来的的livenessAnalysis测试一下， 是错误的
//        LivenessAnalysis livenessAnalysis = new LivenessAnalysis(cfg);

    }



}
