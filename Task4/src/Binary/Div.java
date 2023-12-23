// Yehonatan Menashe 206478067
package Binary;

import Main.Expression;
import Main.Num;

import java.util.Map;

/**
 * The type Divide.
 */
public class Div extends BinaryExpression implements Expression {
    private static final double SQUARE = 2;

    /**
     * Instantiates a new Divide operation.
     *
     * @param value1 the first value
     * @param value2 the second value
     */
    public Div(Expression value1, Expression value2) {
        super(value1, value2);
    }

    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        return (getBinary1().evaluate(assignment)
                / getBinary2().evaluate(assignment));
    }

    @Override
    public double evaluate() throws Exception {
        return (getBinary1().evaluate() / getBinary2().evaluate());
    }

    @Override
    public String toString() {
        return ("(" + getBinary1().toString() + " / "
                + getBinary2().toString() + ")");
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return new Div(getBinary1().assign(var, expression),
                getBinary2().assign(var, expression));
    }

    @Override
    public Expression differentiate(String var) {
        return new Div(new Minus(new Mult(getBinary2(),
                getBinary1().differentiate(var)),
                new Mult(getBinary1(), getBinary2().differentiate(var))),
                new Pow(getBinary2(), new Num(SQUARE)));
    }

    @Override
    public Expression simplify() {
        // simplify the inner expressions first
        Div newDiv = new Div(getBinary1().simplify(), getBinary2().simplify());
        // no variables scenario
        if (newDiv.getVariables().isEmpty()) {
            try {
                return new Num(newDiv.evaluate());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        // X/X scenario
        if (newDiv.getBinary1().equals(newDiv.getBinary2())) {
            return newDiv.getBinary1();
            // X/1 scenario
        } else if (newDiv.getBinary2() instanceof Num) {
            if (((Num) newDiv.getBinary2()).getNum() == 1.0) {
                return newDiv.getBinary1();
            }
        }
        return newDiv;
    }
}