// Yehonatan Menashe 206478067
package Main;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * The type Variable.
 */
public class Var implements Expression {
    private final String variable;

    /**
     * Instantiates a new Variable.
     *
     * @param var the variable
     */
    public Var(String var) {
        this.variable = var;
    }

    /**
     * Gets variable.
     *
     * @return the variable
     */
    public String getVariable() {
        return variable;
    }

    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        if (assignment.containsKey(variable)) {
            return assignment.get(variable);
        } else {
            System.out.println("Variable has no value in map");
            throw new Exception();
        }
    }

    @Override
    public double evaluate() throws Exception {
        System.out.println("Variable has no value in map");
        throw new Exception();
    }

    @Override
    public List<String> getVariables() {
        ArrayList<String> list = new ArrayList<>();
        list.add(this.variable);
        return list;
    }

    @Override
    public String toString() {
        return this.variable;
    }

    @Override
    public Expression assign(String var, Expression expression) {
        if (var.equals(this.variable)) {
            return expression;
        } else {
            return this;
        }
    }

    @Override
    public Expression differentiate(String var) {
        if (var.equals(variable)) {
            return new Num(1);
        } else {
            return new Num(0);
        }
    }

    @Override
    public Expression simplify() {
        return this;
    }
}
