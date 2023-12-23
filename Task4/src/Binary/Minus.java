// Yehonatan Menashe 206478067
package Binary;

import Main.Expression;
import Main.Num;
import Unary.Neg;

import java.util.Map;

/**
 * The type Minus.
 */
public class Minus extends BinaryExpression implements Expression {
    /**
     * Instantiates a new Minus operation.
     *
     * @param value1 the first value
     * @param value2 the second value
     */
    public Minus(Expression value1, Expression value2) {
        super(value1, value2);
    }

    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        return (getBinary1().evaluate(assignment)
                - getBinary2().evaluate(assignment));
    }

    @Override
    public double evaluate() throws Exception {
        return (getBinary1().evaluate() - getBinary2().evaluate());
    }

    @Override
    public String toString() {
        return ("(" + getBinary1().toString() + " - "
                + getBinary2().toString() + ")");
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return new Minus(getBinary1().assign(var, expression),
                getBinary2().assign(var, expression));
    }

    @Override
    public Expression differentiate(String var) {
        return new Minus(getBinary1().differentiate(var),
                getBinary2().differentiate(var));
    }

    @Override
    public Expression simplify() {
        // simplify the inner expressions first
        Minus newMinus = new Minus(getBinary1().simplify(),
                getBinary2().simplify());
        // no variables scenario
        if (newMinus.getVariables().isEmpty()) {
            try {
                return new Num(newMinus.evaluate());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        if (newMinus.getBinary1() instanceof Num) {
            // 0-X scenario
            if (((Num) newMinus.getBinary1()).getNum() == 0.0) {
                return new Neg(newMinus.getBinary2());
            }
            // X-X scenario
        } else if (newMinus.getBinary1().equals(newMinus.getBinary2())) {
            return new Num(0);
            // X-0 scenario
        } else if (newMinus.getBinary2() instanceof Num) {
            if (((Num) newMinus.getBinary2()).getNum() == 0.0) {
                return newMinus.getBinary1();
            }
        }
        return newMinus;
    }
}