package cfg;

import helper.LlBuilder;
import helper.LlSymbolTable;
import org.junit.Test;

import static cfg.testHelper.createLlBuilderAndLlSymbolTable2;
import static cfg.testHelper.genGraphViz;

public class ReachingDefinitionAnalysisTest {
    LlBuilder llBuilder;
    LlSymbolTable llSymbolTable;
    @Test
    public void testPrintDefsReachINAndOUT(){
        // test pass
        this.llBuilder = new LlBuilder("test");
        this.llSymbolTable  = new LlSymbolTable("test");
        createLlBuilderAndLlSymbolTable2(llBuilder,llSymbolTable);
        CFG cfg = new CFG(this.llBuilder, this.llSymbolTable);
        genGraphViz("2",cfg,"tests/");
        ReachingDefinitionAnalysis rdAnalysis = new ReachingDefinitionAnalysis(cfg);
        rdAnalysis.printDefsReachINAndOUT();
        rdAnalysis.genUseDefinitionChains();
        rdAnalysis.printUseDefsChains();

    }



}
