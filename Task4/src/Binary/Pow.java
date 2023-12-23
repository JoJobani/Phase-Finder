// Yehonatan Menashe 206478067
package Binary;

import Main.Expression;
import Main.Num;
import Main.Var;

import java.util.Map;

/**
 * The type Pow.
 */
public class Pow extends BinaryExpression implements Expression {
    /**
     * Instantiates a new power expression.
     *
     * @param value1 the base
     * @param value2 the exponent
     */
    public Pow(Expression value1, Expression value2) {
        super(value1, value2);
    }

    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        return (Math.pow(getBinary1().evaluate(assignment),
                getBinary2().evaluate(assignment)));
    }

    @Override
    public double evaluate() throws Exception {
        return (Math.pow(getBinary1().evaluate(), getBinary2().evaluate()));
    }

    @Override
    public String toString() {
        return ("(" + getBinary1().toString() + "^"
                + getBinary2().toString() + ")");
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return new Pow(getBinary1().assign(var, expression),
                getBinary2().assign(var, expression));
    }

    @Override
    public Expression differentiate(String var) {
        return new Mult(new Pow(getBinary1(), getBinary2()),
                new Plus(new Mult(getBinary2().differentiate(var),
                        new Log(new Var("e"), getBinary1())),
                        new Mult(getBinary2(),
                                new Div(getBinary1().differentiate(var),
                                        getBinary1()))));
    }

    @Override
    public Expression simplify() {
        Pow newPow = new Pow(getBinary1().simplify(), getBinary2().simplify());
        // no variables scenario
        if (newPow.getVariables().isEmpty()) {
            try {
                return new Num(newPow.evaluate());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return newPow;
    }
}