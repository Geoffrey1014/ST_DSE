package ll;


import ll.location.LlLocation;

import java.util.ArrayList;
import java.util.List;

public class LlMethodCallStmt extends LlStatement {

    private final LlLocation returnLocation;
    private final String methodName;
    private final List<LlComponent> argsList;

    public LlMethodCallStmt(String methodName, List<LlComponent> argsList, LlLocation returnLocation) {
        this.methodName = methodName;
        this.argsList = argsList;
        this.returnLocation = returnLocation;
    }

    public String getMethodName() {
        return this.methodName;
    }

    public LlLocation getReturnLocation() {
        return this.returnLocation;
    }

    public List<LlComponent> getArgsList() {
        return new ArrayList<>(this.argsList);
    }

    @Override
    public String toString() {
        StringBuilder argsString = new StringBuilder();
        for (LlComponent arg : argsList) {
            argsString.append(arg.toString()).append(",");
        }
        return this.returnLocation.toString() + " = " + this.methodName + "(" + argsString + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof LlMethodCallStmt)) {
            return false;
        }
        return ((LlMethodCallStmt) obj).returnLocation.equals(this.returnLocation)
                && ((LlMethodCallStmt) obj).methodName.equals(this.methodName)
                && ((LlMethodCallStmt) obj).argsList.equals(this.argsList);

    }

    @Override
    public int hashCode() {
        return this.returnLocation.hashCode() * this.methodName.hashCode() * this.argsList.hashCode();
    }

}
