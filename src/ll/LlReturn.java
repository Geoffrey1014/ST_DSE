package ll;


public class LlReturn extends LlStatement {

    private LlComponent returnValue = null;

    public LlReturn(LlComponent returnValue) {
        this.returnValue = returnValue;
    }
    public LlReturn() {
    }

    public LlComponent getReturnValue() {
        return this.returnValue;
    }

    @Override
    public String toString() {
        if (this.returnValue == null) {
            return "return ";
        }
        return "return " + this.returnValue.toString();
    }


    @Override
    public boolean equals(Object obj) {
        if (obj instanceof LlReturn) {
            LlReturn that = (LlReturn) obj;

            if (this.returnValue == null || that.returnValue == null) {
                return this.returnValue == null && that.returnValue == null;
            }
            return this.returnValue.equals(that.returnValue);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.returnValue.hashCode();
    }


}
