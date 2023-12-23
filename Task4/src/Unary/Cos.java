// Yehonatan Menashe 206478067
package Unary;

import Main.Expression;
import Binary.Mult;
import Main.Num;

import java.util.Map;

/**
 * The type Sin.
 */
public class Cos extends UnaryExpression implements Expression {
    /**
     * Instantiates a new Cos.
     *
     * @param value the value
     */
    public Cos(Expression value) {
        super(value);
    }

    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        return Math.cos(Math.toRadians(getUnary().evaluate(assignment)));
    }

    @Override
    public double evaluate() throws Exception {
        return Math.cos(Math.toRadians(getUnary().evaluate()));
    }

    @Override
    public String toString() {
        return ("cos(" + getUnary().toString() + ")");

    }

    @Override
    public Expression assign(String var, Expression expression) {
        return new Cos(getUnary().assign(var, expression));
    }

    @Override
    public Expression differentiate(String var) {
        return new Neg(new Mult(new Sin(getUnary()),
                getUnary().differentiate(var)));
    }

    @Override
    public Expression simplify() {
        Cos newCos = new Cos(getUnary().simplify());
        // no variables scenario
        if (newCos.getVariables().isEmpty()) {
            try {
                return new Num(this.evaluate());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return newCos;
    }
}