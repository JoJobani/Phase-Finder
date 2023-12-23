// Yehonatan Menashe 206478067
package Binary;

import Main.BaseExpression;
import Main.Expression;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Binary expression.
 */
public class BinaryExpression extends BaseExpression {
    private final Expression value1;
    private final Expression value2;

    /**
     * Instantiates a new Binary expression.
     *
     * @param value1 the first value
     * @param value2 the second value
     */
    public BinaryExpression(Expression value1, Expression value2) {
        this.value1 = value1;
        this.value2 = value2;
    }

    /**
     * Gets first value.
     *
     * @return the first value
     */
    public Expression getBinary1() {
        return this.value1;
    }

    /**
     * Gets second value.
     *
     * @return the second value
     */
    public Expression getBinary2() {
        return this.value2;
    }

    /**
     * Gets variables.
     *
     * @return the variables
     */
    public List<String> getVariables() {
        List<String> list = new ArrayList<>();
        list.addAll(value1.getVariables());
        list.addAll(value2.getVariables());
        return list;
    }
}