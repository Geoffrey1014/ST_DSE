package ll.literal;


public class LlLiteralInt extends LlLiteral {
    private final long intValue;

    public LlLiteralInt(long intValue) {
        this.intValue = intValue;
    }

    public long getIntValue() {
        return this.intValue;
    }

    @Override
    public String toString() {
        return Long.toString(this.intValue);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof LlLiteralInt)) {
            return false;
        }
        return ((LlLiteralInt) obj).intValue == this.intValue;

    }

    @Override
    public int hashCode() {
        return (int) this.intValue;
    }

}
