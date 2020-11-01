package ll.literal;


public class LlLiteralBool extends LlLiteral {
    private final boolean boolValue;

    public LlLiteralBool(boolean boolValue) {
        this.boolValue = boolValue;
    }

    public boolean getBoolValue() {
        return this.boolValue;
    }

    @Override
    public String toString() {
        return Boolean.toString(this.boolValue);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof LlLiteralBool)) {
            return false;
        }
        return ((LlLiteralBool) obj).boolValue == (this.boolValue);

    }

    @Override
    public int hashCode() {
        // hash to 2 different primes depending on whether it's true or false
        return this.boolValue ? 3 : 5;
    }


}
