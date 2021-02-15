package cfg;

import helper.LlBuilder;
import helper.LlSymbolTable;
import ll.location.LlLocationVar;
import org.junit.Assert;
import org.junit.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

import static cfg.testHelper.genGraphViz;

public class NewLivenessAnalysisTest {

    LlBuilder llBuilder;
    LlSymbolTable llSymbolTable;
    CFG cfg;
    NewLivenessAnalysis analysts;
    HashMap<String, HashSet<LlLocationVar>> livenessOut;


    public void prepareCalculateLiveness(String name){
        testHelper helper = new testHelper();
        llBuilder = new LlBuilder(name);
        llSymbolTable  = new LlSymbolTable(name);
        if(name.equals("HIT")){
            helper.createLlBuilderAndLlSymbolTableOfHIT(llBuilder, llSymbolTable);
            livenessOut = helper.createOutLivenessOfHIT();
        }
        else if(name.equals("MITLiveness")){
            helper.createLlBuilderAndLlSymbolTableMITLiveness(llBuilder,llSymbolTable);
            livenessOut = helper.createOutLivenessOfMIT();
        }

        cfg = new CFG(this.llBuilder, this.llSymbolTable,false);
        genGraphViz(name,cfg,"tests/");
        analysts = new NewLivenessAnalysis(cfg);
    }
    public void calculateLiveness(int choice){

        if(choice == 1){
            analysts.livenessAnalysis();
        }
        else{
            analysts.livenessAnalysis2();
        }
    }

    @Test
    public void testNewLivrnessAnalysisTest(){
        // test pass
        prepareCalculateLiveness("MITLiveness");
        calculateLiveness(1);
        for(BasicBlock basicBlock: analysts.livenessOUT.keySet()){
            String bbName= basicBlock.getLabelsToStmtsMap().keySet().iterator().next();
            if(livenessOut.get(bbName) != null){
                Assert.assertEquals(livenessOut.get(bbName).toString(), analysts.livenessOUT.get(basicBlock).toString());
            }
        }

        prepareCalculateLiveness("HIT");
        calculateLiveness(1);
        for(BasicBlock basicBlock: analysts.livenessOUT.keySet()){
            String bbName= basicBlock.getLabelsToStmtsMap().keySet().iterator().next();
            if(livenessOut.get(bbName) != null){
                Assert.assertEquals(livenessOut.get(bbName).toString(), analysts.livenessOUT.get(basicBlock).toString());
            }
        }


        // 那原来的的livenessAnalysis测试一下， 是错误的
//        LivenessAnalysis livenessAnalysis = new LivenessAnalysis(cfg);
    }

    @Test
    public void testCalculateDefinitionUseChain(){
        prepareCalculateLiveness("HIT");
        calculateLiveness(2);
        writeLivenessToFile(2);
        analysts.calculateDefinitionUseChain();
        writeDefUseChainToFile();
    }

    public void writeDefUseChainToFile(){
        ArrayList<NewLivenessAnalysis.VarAndStmt> defs= new ArrayList<>(analysts.definitionUseChain.keySet());
        Collections.sort(defs);
        StringBuilder stringBuilder = new StringBuilder();
        for(NewLivenessAnalysis.VarAndStmt varAndStmt : defs){
            stringBuilder.append("\ndef => ").append(varAndStmt);
            stringBuilder.append("\nuse => ").append(analysts.definitionUseChain.get(varAndStmt)).append("\n");
        }
        writeFile(stringBuilder.toString(),"tests/DefUseChainHIT" +".txt");
    }

    public void writeLivenessToFile(int livenessCalulationchioce){
        StringBuilder stringBuilder = new StringBuilder();
        for (BasicBlock bb : analysts.livenessOUT2.keySet()) {
            stringBuilder.append("\n\nbasicBlock=").append(bb.getLabelsToStmtsMap().keySet().iterator().next()).append(":\n");
            stringBuilder.append(analysts.livenessOUT2.get(bb).keySet()).append("\n");
            stringBuilder.append(analysts.livenessOUT2.get(bb)).append("\n");
        }
        writeFile(stringBuilder.toString(),"tests/LivenessHIT"+ livenessCalulationchioce +".txt");
    }

    public void writeFile(String content, String pathName) {

        try {

            File file = new File(pathName);
            file.createNewFile();
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(content);
            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
