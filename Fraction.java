public class Fraction implements INumber<Fraction>, Comparable<Fraction> {
    private int numerator;
    private int denominator;

    public Fraction(int numerator, int denominator) {
        if (denominator == 0) {
            throw new IllegalArgumentException("Denominator cannot be zero.");
        }
        this.numerator = numerator;
        this.denominator = denominator;
    }
    @Override
    public Fraction plus(Fraction input) {
        int commonDenominator = this.denominator * input.denominator;
        int newNumerator = this.numerator * input.denominator + input.numerator * this.denominator;
        return new Fraction(newNumerator, commonDenominator);
    }

    @Override
    public Fraction minus(Fraction input) {
        int commonDenominator = this.denominator * input.denominator;
        int newNumerator = this.numerator * input.denominator - input.numerator * this.denominator;
        return new Fraction(newNumerator, commonDenominator);
    }

    @Override
    public Fraction divide(Fraction input) {
        if (input.numerator == 0) {
            throw new IllegalArgumentException("Cannot divide by zero.");
        }
        int newNumerator = this.numerator * input.denominator;
        int newDenominator = this.denominator * input.numerator;
        return new Fraction(newNumerator, newDenominator);
    }
    @Override
    public Fraction multiply(Fraction input) {
        int newNumerator = this.numerator * input.numerator;
        int newDenominator = this.denominator * input.denominator;
        return new Fraction(newNumerator, newDenominator);
    }
    @Override
    public void print() {
        System.out.println(this.numerator + "/" + this.denominator);
    }

    /**
     * Compares this object with the specified object for order.  Returns a
     * negative integer, zero, or a positive integer as this object is less
     * than, equal to, or greater than the specified object.
     *
     * <p>The implementor must ensure
     * {@code sgn(x.compareTo(y)) == -sgn(y.compareTo(x))}
     * for all {@code x} and {@code y}.  (This
     * implies that {@code x.compareTo(y)} must throw an exception iff
     * {@code y.compareTo(x)} throws an exception.)
     *
     * <p>The implementor must also ensure that the relation is transitive:
     * {@code (x.compareTo(y) > 0 && y.compareTo(z) > 0)} implies
     * {@code x.compareTo(z) > 0}.
     *
     * <p>Finally, the implementor must ensure that {@code x.compareTo(y)==0}
     * implies that {@code sgn(x.compareTo(z)) == sgn(y.compareTo(z))}, for
     * all {@code z}.
     *
     * <p>It is strongly recommended, but <i>not</i> strictly required that
     * {@code (x.compareTo(y)==0) == (x.equals(y))}.  Generally speaking, any
     * class that implements the {@code Comparable} interface and violates
     * this condition should clearly indicate this fact.  The recommended
     * language is "Note: this class has a natural ordering that is
     * inconsistent with equals."
     *
     * <p>In the foregoing description, the notation
     * {@code sgn(}<i>expression</i>{@code )} designates the mathematical
     * <i>signum</i> function, which is defined to return one of {@code -1},
     * {@code 0}, or {@code 1} according to whether the value of
     * <i>expression</i> is negative, zero, or positive, respectively.
     *
     * @param o the object to be compared.
     * @return a negative integer, zero, or a positive integer as this object
     * is less than, equal to, or greater than the specified object.
     * @throws NullPointerException if the specified object is null
     * @throws ClassCastException   if the specified object's type prevents it
     *                              from being compared to this object.
     */
    @Override
    public int compareTo(final Fraction o) {
        return Double.compare((double)numerator / denominator, (double)o.numerator / o.denominator);
    }
}