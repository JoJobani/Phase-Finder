// Yehonatan Menashe 206478067
package Binary;

import Main.Expression;
import Main.Num;

import java.util.Map;

/**
 * The type Plus.
 */
public class Mult extends BinaryExpression implements Expression {
    /**
     * Instantiates a new Multiply operation.
     *
     * @param value1 the first value
     * @param value2 the second value
     */
    public Mult(Expression value1, Expression value2) {
        super(value1, value2);
    }

    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        return (getBinary1().evaluate(assignment)
                * getBinary2().evaluate(assignment));
    }

    @Override
    public double evaluate() throws Exception {
        return (getBinary1().evaluate() * getBinary2().evaluate());
    }

    @Override
    public String toString() {
        return ("(" + getBinary1().toString() + " * "
                + getBinary2().toString() + ")");
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return new Mult(getBinary1().assign(var, expression),
                getBinary2().assign(var, expression));
    }

    @Override
    public Expression differentiate(String var) {
        return new Plus(new Mult(getBinary1().differentiate(var),
                getBinary2()), new Mult(getBinary2().differentiate(var),
                getBinary1()));
    }

    @Override
    public Expression simplify() {
        Mult newMult = new Mult(getBinary1().simplify(),
                getBinary2().simplify());
        // no variables scenario
        if (newMult.getVariables().isEmpty()) {
            try {
                return new Num(newMult.evaluate());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        // X * 0 or X * 1 scenario
        if (newMult.getBinary2() instanceof Num) {
            if (((Num) newMult.getBinary2()).getNum() == 0.0) {
                return new Num(0);
            } else if (((Num) newMult.getBinary2()).getNum() == 1.0) {
                return newMult.getBinary1();
            }
            // 0 * X or 1 * X scenario
        } else if (newMult.getBinary1() instanceof Num) {
            if (((Num) newMult.getBinary1()).getNum() == 0.0) {
                return new Num(0);
            } else if (((Num) newMult.getBinary1()).getNum() == 1.0) {
                return newMult.getBinary2();
            }
        }
        return newMult;
    }
}