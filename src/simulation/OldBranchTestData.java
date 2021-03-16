package simulation;

import java.util.HashMap;

public class OldBranchTestData {
    HashMap<String,Double> data;
    public OldBranchTestData(){
        data = new HashMap<>();
        data.put("01_UPPAAL_LLATCH1_I", 1.0D);
        data.put("02_UPPAAL_FAN_CONTROL", 1.0D);
        data.put("03_NumericalLTLRefined", 86.11/100);
        data.put("04_SimpleConveyorBelt", 88.24/100);
        data.put("05_HydraulicRamp", 90.63/100);
        data.put("06_ArbitorLTL", 87.09/100);
        data.put("Responder1", 75.00/100);
        data.put("Responder2", 96.00/100);
        data.put("Responder3", 94.58/100);
        data.put("07_PriorityArbitorLTL", 87.32/100);

        data.put("FB_G4LTL1", 66.67/100);
        data.put("FB_G4LTL2", 66.67/100);
        data.put("FB_G4LTL3", 91.77/100);
        data.put("FB_G4LTL4", 83.33/100);
        data.put("FB_G4LTL5", 80.36/100);
        data.put("FB_G4LTL6", 74.29/100);
        data.put("FB_G4LTL7", 85.71/100);
        data.put("FB_G4LTL8", 63.28/100);
        data.put("FB_G4LTL11", 84.38/100);

    }
}
