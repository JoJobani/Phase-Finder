// Yehonatan Menashe 206478067
package Main;

import java.util.Map;
import java.util.List;

/**
 * The interface Main.Expression.
 */
public interface Expression {
    /**
     * Evaluate the expression using the variable values provided
     * in the assignment, and return the result.
     *
     * @param assignment the assignment with the variable values.
     * @return If the expression contains a variable which is not in the assignment, an exception is thrown.
     * @throws Exception the exception
     */
    double evaluate(Map<String, Double> assignment) throws Exception;

    /**
     * Like the `evaluate(assignment)` method, but uses an empty assignment.
     *
     * @return the double
     * @throws Exception the exception
     */
    double evaluate() throws Exception;

    /**
     * Returns a list of the variables in the expression.
     *
     * @return the variables
     */
    List<String> getVariables();

    /**
     * Returns a nice string representation of the expression.
     *
     * @return a string.
     */
    String toString();

    /**
     * Assign expression.
     *
     * @param var        the var
     * @param expression the expression
     * @return a new expression in which all occurrences of the variable
     * var are replaced with the provided expression
     */
    Expression assign(String var, Expression expression);

    /**
     * Differentiation method.
     *
     * @param var the expression
     * @return Returns the expression tree resulting from differentiating the
     * current expression relative to variable `var`.
     */
    Expression differentiate(String var);

    /**
     * Simplify expression.
     *
     * @return a simplified version of the current expression
     */
    Expression simplify();
}