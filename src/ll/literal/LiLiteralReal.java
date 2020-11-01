package ll.literal;

public class LiLiteralReal extends LlLiteral {
    private final double realValue;

    public LiLiteralReal(double intValue) {
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
        if (!(obj instanceof LiLiteralReal)) {
            return false;
        }
        return ((LiLiteralReal) obj).realValue == this.realValue;

    }

    @Override // TODO  这个对不对啊？
    public int hashCode() {
        return (int) this.realValue;
    }
}
