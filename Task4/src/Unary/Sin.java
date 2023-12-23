// Yehonatan Menashe 206478067
package Unary;

import Main.Expression;
import Binary.Mult;
import Main.Num;

import java.util.Map;

/**
 * The type Sin.
 */
public class Sin extends UnaryExpression implements Expression {
    /**
     * Instantiates a new Sin operation.
     *
     * @param value the value
     */
    public Sin(Expression value) {
        super(value);
    }

    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        return Math.sin(Math.toRadians(getUnary().evaluate(assignment)));
    }

    @Override
    public double evaluate() throws Exception {
        return Math.sin(Math.toRadians(getUnary().evaluate()));
    }

    @Override
    public String toString() {
        return ("sin(" + getUnary().toString() + ")");

    }

    @Override
    public Expression assign(String var, Expression expression) {
        return new Sin(getUnary().assign(var, expression));
    }

    @Override
    public Expression differentiate(String var) {
        return new Mult(new Cos(getUnary()),
                getUnary().differentiate(var));
    }

    @Override
    public Expression simplify() {
        Sin newSin = new Sin(getUnary().simplify());
        // no variables scenario
        if (newSin.getVariables().isEmpty()) {
            try {
                return new Num(this.evaluate());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return newSin;
    }
}