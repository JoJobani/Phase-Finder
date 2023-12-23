// Yehonatan Menashe 206478067
package Unary;

import Main.Expression;
import Main.Num;

import java.util.Map;

/**
 * The type Negative.
 */
public class Neg extends UnaryExpression implements Expression {
    /**
     * Instantiates a new Negative operation.
     *
     * @param value the value
     */
    public Neg(Expression value) {
        super(value);
    }

    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        return getUnary().evaluate(assignment) * (-1);
    }

    @Override
    public double evaluate() throws Exception {
        return getUnary().evaluate() * (-1);
    }

    @Override
    public String toString() {
        return ("(-" + getUnary().toString() + ")");
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return new Neg(getUnary().assign(var, expression));
    }

    @Override
    public Expression differentiate(String var) {
        return new Neg(getUnary().differentiate(var));
    }

    @Override
    public Expression simplify() {
        Neg newNeg = new Neg(getUnary().simplify());
        // no variables scenario
        if (newNeg.getVariables().isEmpty()) {
            try {
                return new Num(newNeg.evaluate());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return newNeg;
    }
}
