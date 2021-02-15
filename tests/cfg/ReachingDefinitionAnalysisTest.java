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
        helper.createLlBuilderAndLlSymbolTableMITRechingDef(llBuilder,llSymbolTable);
        CFG cfg = new CFG(this.llBuilder, this.llSymbolTable,false);
        genGraphViz("1",cfg,"tests/");
        ReachingDefinitionAnalysis rdAnalysis = new ReachingDefinitionAnalysis(cfg);
        System.out.println("printDefsReachINAndOUT-------------");
        rdAnalysis.printDefsReachINAndOUT();
        rdAnalysis.genUseDefinitionChains();
        System.out.println("printUseDefsChains----------------");
        rdAnalysis.printUseDefsChains();

    }



}
