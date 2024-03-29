package simulation;

import cfg.CFG;
import ll.LlComponent;
import ll.literal.*;
import ll.location.LlLocation;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;

public class CoverageTest {
    final CFG cfg;
    private HashSet<LlLocation> inputVars;
    private RandomGen randomGen = new RandomGen();
    public CoverageTest(CFG cfg){
        this.cfg = cfg;
        this.inputVars = cfg.getInputVars();         // get input var

    }

    public ConMemory createInitMemory() {
        ConMemory conMemory = new ConMemory(inputVars);
        putNonInputVarInitToMemory(conMemory);
        return conMemory;
    }

    public ConMemory createRandomMemory() {
        ConMemory conMemory = new ConMemory(inputVars);
        putNonInputVarRandomToMemory(conMemory);
        return conMemory;
    }

    public void putNonInputVarRandomToMemory(ConMemory conMemory) {
        Hashtable<LlComponent, LlLiteral> varNonInput = this.cfg.getLlSymbolTable().varNonInput;
        for (LlComponent llComponent : varNonInput.keySet()) {
            LlLiteral llLiteral = varNonInput.get(llComponent);
            if (llLiteral instanceof LlLiteralBool)
                conMemory.put(llComponent, new ValueOfDiffType(randomGen.nextBoolean()));
            else if (llLiteral instanceof LlLiteralInt)
                conMemory.put(llComponent, new ValueOfDiffType((long) randomGen.nextInt()));
            else if (llLiteral instanceof LlLiteralReal)
                conMemory.put(llComponent, new ValueOfDiffType(randomGen.nextDouble()));
            else if (llLiteral instanceof LlLiteralString)
                conMemory.put(llComponent, new ValueOfDiffType(randomGen.nextString()));
            else System.out.println("wrong type!");
        }
    }

    public void putNonInputVarInitToMemory(ConMemory conMemory) {
        Hashtable<LlComponent, LlLiteral> varNonInput = this.cfg.getLlSymbolTable().varNonInput;
        for (LlComponent llComponent : varNonInput.keySet()) {
            LlLiteral llLiteral = varNonInput.get(llComponent);
            if (llLiteral instanceof LlLiteralBool)
                conMemory.put(llComponent, new ValueOfDiffType(((LlLiteralBool) llLiteral).getBoolValue()));
            else if (llLiteral instanceof LlLiteralInt)
                conMemory.put(llComponent, new ValueOfDiffType(((LlLiteralInt) llLiteral).getIntValue()));
            else if (llLiteral instanceof LlLiteralReal)
                conMemory.put(llComponent, new ValueOfDiffType(((LlLiteralReal) llLiteral).getRealValue()));
            else if (llLiteral instanceof LlLiteralString)
                conMemory.put(llComponent, new ValueOfDiffType(((LlLiteralString) llLiteral).getStringValue()));
            else System.out.println("wrong type!");
        }
    }

    public HashMap<LlLocation, ValueOfDiffType> createDefaultInputs() {
        HashMap<LlLocation, ValueOfDiffType> inputs = new HashMap<>();
        Hashtable<LlComponent, LlLiteral> inputVarsInit = this.cfg.getLlSymbolTable().varInput;
        for (LlComponent llComponent : inputVarsInit.keySet()) {
            LlLiteral llLiteral = inputVarsInit.get(llComponent);
            if (llLiteral instanceof LlLiteralBool)
                inputs.put((LlLocation) llComponent, new ValueOfDiffType(((LlLiteralBool) llLiteral).getBoolValue()));
            else if (llLiteral instanceof LlLiteralInt)
                inputs.put((LlLocation) llComponent, new ValueOfDiffType(((LlLiteralInt) llLiteral).getIntValue()));
            else if (llLiteral instanceof LlLiteralReal)
                inputs.put((LlLocation) llComponent, new ValueOfDiffType(((LlLiteralReal) llLiteral).getRealValue()));
            else if (llLiteral instanceof LlLiteralString)
                inputs.put((LlLocation) llComponent, new ValueOfDiffType(((LlLiteralString) llLiteral).getStringValue()));
            else System.out.println("wrong type!");
        }
        return inputs;
    }



    public void putInputVarInitToMemory(ConMemory conMemory) {
        Hashtable<LlComponent, LlLiteral> inputVarsInit = this.cfg.getLlSymbolTable().varInput;
        for (LlComponent llComponent : inputVarsInit.keySet()) {
            LlLiteral llLiteral = inputVarsInit.get(llComponent);
            if (llLiteral instanceof LlLiteralBool)
                conMemory.put(llComponent, new ValueOfDiffType(((LlLiteralBool) llLiteral).getBoolValue()));
            else if (llLiteral instanceof LlLiteralInt)
                conMemory.put(llComponent, new ValueOfDiffType(((LlLiteralInt) llLiteral).getIntValue()));
            else if (llLiteral instanceof LlLiteralReal)
                conMemory.put(llComponent, new ValueOfDiffType(((LlLiteralReal) llLiteral).getRealValue()));
            else if (llLiteral instanceof LlLiteralString)
                conMemory.put(llComponent, new ValueOfDiffType(((LlLiteralString) llLiteral).getStringValue()));
            else System.out.println("wrong type!");
        }
    }
}
