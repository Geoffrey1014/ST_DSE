package ll.literal;

public class LlLiteralReal extends LlLiteral {
    private final double realValue;

    public LlLiteralReal(double intValue) {
        this.realValue = intValue;
    }

    public double getRealValue() {
        return this.realValue;
    }

    @Override
    public String toString() {
        return Double.toString(this.realValue);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof LlLiteralReal)) {
            return false;
        }
        return ((LlLiteralReal) obj).realValue == this.realValue;

    }

    @Override // TODO  这个对不对啊？
    public int hashCode() {
        return (int) this.realValue;
    }
}
