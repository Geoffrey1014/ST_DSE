package helper;

import cfg.CFG;
import ll.LlComponent;
import ll.literal.LlLiteral;
import ll.location.LlLocationVar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;

public class LlSymbolTable {
    /**
     * store variables declared in VARBlock and initialize them
     */
    private final String methodName;
    public Hashtable<LlComponent, String> llStringTable;
    public Hashtable<LlLocationVar, String> paramTable;
    public Hashtable<LlLocationVar, Integer> arrayTable;
    public HashMap<CFG.SymbolDef, ArrayList<CFG.defBlockLocationTuple>> useDef;
    public Hashtable<LlLocationVar, Integer> globalArrays;
    public ArrayList<LlLocationVar> globalVars;

    public Hashtable<LlComponent, LlLiteral>  varInput;
    public Hashtable<LlComponent, LlLiteral> varNonInput;
    public Hashtable<LlComponent, ArrayList<LlLiteral>>  varInputArray;
    public Hashtable<LlComponent, ArrayList<LlLiteral>> varNonInputArray;


    public LlSymbolTable(String methodName) {
        this.llStringTable = new Hashtable<>();
        this.paramTable = new Hashtable<>();
        this.arrayTable = new Hashtable<>();
        this.useDef = new HashMap<>();
        this.globalArrays = new Hashtable<>();
        this.globalVars = new ArrayList<>();
        this.methodName = methodName;

        this.varInput = new Hashtable<>();
        this.varNonInput = new Hashtable<>();
        this.varInputArray = new Hashtable<>();
        this.varNonInputArray = new Hashtable<>(); // 这个应该不支持，用不上
    }

    public String getMethodName() {
        return this.methodName;
    }

    public void putOnStringTable(LlComponent key, String value) {
        this.llStringTable.put(key, value);
    }

    public String getFromStringTable(LlComponent key) {
        return this.llStringTable.get(key);
    }

    public Hashtable<LlComponent, String> getLlStringTable() {
        return this.llStringTable;
    }

    public void putOnParamTable(LlLocationVar key, String value) {
        this.paramTable.put(key, value);
    }

    public String getFromParamTable(LlLocationVar key) {
        for (LlLocationVar loc : this.paramTable.keySet()) {
            if (key.equals(loc)) {
                return this.paramTable.get(loc);
            }

        }
        return null;
    }

    public Hashtable<LlLocationVar, String> getParamTable() {
        return this.paramTable;
    }

    public void setUseDef(HashMap<CFG.SymbolDef, ArrayList<CFG.defBlockLocationTuple>> useDef) {
        this.useDef = useDef;
    }

    public HashMap<CFG.SymbolDef, ArrayList<CFG.defBlockLocationTuple>> getUseDef() {
        return this.useDef;
    }

    public void putOnArrayTable(LlLocationVar key, int val) {
        this.arrayTable.put(key, val);
    }

    public Integer getFromArrayTable(LlLocationVar key) {
        for (LlLocationVar loc : this.arrayTable.keySet()) {
            if (key.equals(loc)) {
                return this.arrayTable.get(loc);
            }

        }
        return null;
    }

    public Hashtable<LlLocationVar, Integer> getArrayTable() {
        return this.arrayTable;
    }


    public void addToGlobalArrays(LlLocationVar var, int size) {
        this.globalArrays.put(var, size);
    }

    public Hashtable<LlLocationVar, Integer> getGlobalArrays() {
        return this.globalArrays;
    }

    public void addToGlobalVars(LlLocationVar var) {
        this.globalVars.add(var);
    }

    public ArrayList<LlLocationVar> getGlobalVars() {
        return this.globalVars;
    }

    public boolean isInGlobalArraysTable(LlLocationVar var) {
        for (LlLocationVar locationVar : this.globalArrays.keySet()) {
            if (locationVar.equals(var)) {
                return true;
            }
        }
        return false;
    }

    public boolean isInGlobalVarsTable(LlLocationVar var) {
        for (LlLocationVar locationVar : this.globalVars) {
            if (locationVar.equals(var)) {
                return true;
            }
        }
        return false;
    }
}