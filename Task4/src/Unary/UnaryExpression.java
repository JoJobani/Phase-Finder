// Yehonatan Menashe 206478067
package Unary;

import Main.BaseExpression;
import Main.Expression;

import java.util.ArrayList;
import java.util.List;

/**
 * The super class Unary expression.
 */
public class UnaryExpression extends BaseExpression {
    private final Expression value;


    /**
     * Instantiates a new Unary expression.
     *
     * @param value the value
     */
    public UnaryExpression(Expression value) {
        this.value = value;
    }

    /**
     * Gets the value.
     *
     * @return the unary value
     */
    public Expression getUnary() {
        return this.value;
    }

    /**
     * Gets variables.
     *
     * @return the variables
     */
    public List<String> getVariables() {
        return new ArrayList<>(value.getVariables());
    }
}