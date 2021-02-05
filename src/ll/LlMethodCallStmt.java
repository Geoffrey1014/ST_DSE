package ll;


import cfg.LlStatementVisitor;
import ll.location.LlLocation;
import simulation.Memory;

import java.util.ArrayList;
import java.util.List;

public class LlMethodCallStmt extends LlStatement {

    private  LlLocation returnLocation = null;
    private final String methodName;
    private final List<LlComponent> argsList;

    public LlMethodCallStmt(String methodName, List<LlComponent> argsList, LlLocation returnLocation) {
        this.methodName = methodName;
        this.argsList = argsList;
        this.returnLocation = returnLocation;
    }
    public LlMethodCallStmt(String methodName, List<LlComponent> argsList) {
        this.methodName = methodName;
        this.argsList = argsList;
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
        if (this.returnLocation != null){
            return this.returnLocation.toString() + " = " + this.methodName + "(" + argsString + ")";
        }
        else {
            return  this.methodName + "(" + argsString + ")";
        }

    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof LlMethodCallStmt)) {
            return false;
        }
        if (this.returnLocation == null){
            return ((LlMethodCallStmt) obj).methodName.equals(this.methodName)
                    && ((LlMethodCallStmt) obj).argsList.equals(this.argsList);

        }
        else {
            return ((LlMethodCallStmt) obj).returnLocation.equals(this.returnLocation)
                    && ((LlMethodCallStmt) obj).methodName.equals(this.methodName)
                    && ((LlMethodCallStmt) obj).argsList.equals(this.argsList);

        }

    }

    @Override
    public int hashCode() {
        return this.returnLocation.hashCode() * this.methodName.hashCode() * this.argsList.hashCode();
    }

    @Override
    public void exe(Memory memory){

    }

    @Override
    public void accept(LlStatementVisitor llStatementVisitor, Memory memory) {
        llStatementVisitor.visitor(this,memory);
    }
}
