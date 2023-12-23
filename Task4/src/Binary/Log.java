// Yehonatan Menashe 206478067
package Binary;

import Main.Expression;
import Main.Num;
import Main.Var;

import java.util.Map;

/**
 * The type Log.
 */
public class Log extends BinaryExpression implements Expression {
    private static final double SQUARE = 2;

    /**
     * Instantiates a new Log.
     *
     * @param value1 the base
     * @param value2 the argument
     */
    public Log(Expression value1, Expression value2) {
        super(value1, value2);
    }

    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        return (Math.log(getBinary2().evaluate(assignment))
                / Math.log(getBinary1().evaluate(assignment)));
    }

    @Override
    public double evaluate() throws Exception {
        return (Math.log(getBinary2().evaluate())
                / Math.log(getBinary1().evaluate()));
    }

    @Override
    public String toString() {
        return ("(log(" + getBinary1().toString() + ", "
                + getBinary2().toString() + "))");
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return new Log(getBinary1().assign(var, expression),
                getBinary2().assign(var, expression));
    }

    @Override
    public Expression differentiate(String var) {
        return new Div(new Minus(new Div(new Mult(new Log(new Var("e"),
                getBinary1()), getBinary2().differentiate(var)),
                getBinary2()), new Div(new Mult(getBinary1().
                differentiate(var), new Log(new Var("e"), getBinary2())),
                getBinary1())), new Pow(new Log(new Var("e"), getBinary1()),
                new Num(SQUARE)));
    }

    @Override
    public Expression simplify() {
        Log newLog = new Log(getBinary1().simplify(), getBinary2().simplify());
        // no variables scenario
        if (newLog.getVariables().isEmpty()) {
            try {
                return new Num(newLog.evaluate());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        // log(X,X) scenario
        if (newLog.getBinary1().toString().
                equals(newLog.getBinary2().toString())) {
            return new Num(1);
        }
        return newLog;
    }
}