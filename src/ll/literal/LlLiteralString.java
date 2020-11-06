package ll.literal;

import static java.util.Objects.hash;

public class LlLiteralString extends LlLiteral{
    private final String stringValue;

    public LlLiteralString(String stringValue) {
        this.stringValue = stringValue;
    }

    public String getRealValue() {
        return this.stringValue;
    }

    @Override
    public String toString() {
        return this.stringValue;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof LlLiteralReal)) {
            return false;
        }
        return ((LlLiteralString) obj).stringValue == this.stringValue;

    }

    @Override // TODO  这个对不对啊？
    public int hashCode() {
        return hash(this.stringValue);
    }
}
