// Yehonatan Menashe 206478067
package Binary;

import Main.Expression;
import Main.Num;

import java.util.Map;

/**
 * The type Plus.
 */
public class Plus extends BinaryExpression implements Expression {
    /**
     * Instantiates a new Plus expression.
     *
     * @param value1 the first value
     * @param value2 the second value
     */
    public Plus(Expression value1, Expression value2) {
        super(value1, value2);
    }

    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        return (getBinary1().evaluate(assignment)
                + getBinary2().evaluate(assignment));
    }

    @Override
    public double evaluate() throws Exception {
        return (getBinary1().evaluate() + getBinary2().evaluate());
    }

    @Override
    public String toString() {
        return ("(" + getBinary1().toString() + " + "
                + getBinary2().toString() + ")");
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return new Plus(getBinary1().assign(var, expression),
                getBinary2().assign(var, expression));
    }

    @Override
    public Expression differentiate(String var) {
        return new Plus(getBinary1().differentiate(var),
                getBinary2().differentiate(var));
    }

    @Override
    public Expression simplify() {
        Plus newPlus = new Plus(getBinary1().simplify(),
                getBinary2().simplify());
        // no variables scenario
        if (newPlus.getVariables().isEmpty()) {
            try {
                return new Num(newPlus.evaluate());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        // X + 0 scenario
        if (newPlus.getBinary2() instanceof Num) {
            if (((Num) newPlus.getBinary2()).getNum() == 0.0) {
                return newPlus.getBinary1();
            }
            // 0 + X scenario
        } else if (newPlus.getBinary1() instanceof Num) {
            if (((Num) newPlus.getBinary1()).getNum() == 0.0) {
                return newPlus.getBinary2();
            }
        }
        return newPlus;
    }
}