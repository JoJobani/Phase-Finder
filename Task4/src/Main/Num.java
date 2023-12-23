// Yehonatan Menashe 206478067
package Main;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * The type Number.
 */
public class Num implements Expression {
    private final double number;

    /**
     * Instantiates a new Number.
     *
     * @param num the number
     */
    public Num(Double num) {
        this.number = num;
    }

    /**
     * Instantiates a new Num using int to Double conversion.
     *
     * @param num the number
     */
    public Num(int num) {
        this.number = num;
    }

    /**
     * Gets the number.
     *
     * @return the number
     */
    public double getNum() {
        return this.number;
    }

    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        return this.getNum();
    }

    @Override
    public double evaluate() throws Exception {
        return this.getNum();
    }

    @Override
    public String toString() {
        return Double.toString(this.number);
    }

    @Override
    public List<String> getVariables() {
        return new ArrayList<>();
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return this;
    }

    @Override
    public Expression differentiate(String var) {
        return new Num(0);
    }

    @Override
    public Expression simplify() {
        return this;
    }
}