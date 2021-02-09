package cfg;

import helper.LlBuilder;
import helper.LlSymbolTable;
import org.junit.Test;

import static cfg.testHelper.genGraphViz;

public class ReachingDefinitionAnalysisTest {
    LlBuilder llBuilder;
    LlSymbolTable llSymbolTable;
    @Test
    public void testPrintDefsReachINAndOUT(){
        // test pass
        testHelper helper = new testHelper();
        this.llBuilder = new LlBuilder("test");
        this.llSymbolTable  = new LlSymbolTable("test");
        helper.createLlBuilderAndLlSymbolTable1(llBuilder,llSymbolTable);
        CFG cfg = new CFG(this.llBuilder, this.llSymbolTable,false);
        genGraphViz("1",cfg,"tests/");
        ReachingDefinitionAnalysis rdAnalysis = new ReachingDefinitionAnalysis(cfg);
        rdAnalysis.printDefsReachINAndOUT();
        rdAnalysis.genUseDefinitionChains();
        rdAnalysis.printUseDefsChains();

    }



}
