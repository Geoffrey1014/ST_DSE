package ll.location;


import ll.LlComponent;

public class LlLocationArray extends LlLocation {
    private final LlComponent elementIndex;

    public LlLocationArray(String varName, LlComponent elementIndex) {
        super(varName);
        this.elementIndex = elementIndex;
    }

    public LlComponent getElementIndex() {
        return this.elementIndex;
    }

    @Override
    public String toString() {
        return this.getVarName() + "[" + elementIndex.toString() + "] ";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof LlLocationArray)) {
            return false;
        }
        return ((LlLocationArray) obj).elementIndex.equals(this.elementIndex)
                && ((LlLocationArray) obj).getVarName().equals(this.getVarName());
    }

    @Override
    public int hashCode() {
        return this.elementIndex.hashCode() * this.getVarName().hashCode();
    }

    // the return value of this didnt neccesarily have to be an integer :)
    public String getArrayHead(String arrayLocation) {

        return Integer.toString((Integer.parseInt(arrayLocation.substring(0, arrayLocation.length() - 6))) / 8);
    }


}
