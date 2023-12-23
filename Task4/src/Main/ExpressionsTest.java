// Yehonatan Menashe 206478067
package Main;

import Binary.*;
import Unary.Cos;
import Unary.Sin;

import java.util.Map;
import java.util.TreeMap;

/**
 * The type Expressions test.
 */
public class ExpressionsTest {
    /**
     * The small test from the GitHub.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        Var x = new Var("x");
        Var y = new Var("y");
        Var e = new Var("e");
        //step 1
        Expression e1 = new Plus(new Plus(new Mult(new Num(2), x),
                new Sin(new Mult(new Num(4), y))), new Pow(e, x));
        //step 2
        System.out.println(e1);
        Map<String, Double> assignment = new TreeMap<>();
        assignment.put("x", 2.0);
        assignment.put("y", 0.25);
        assignment.put("e", 2.71);
        //step 3
        try {
            double e2 = e1.evaluate(assignment);
            System.out.println(e2);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        //step 4
        Expression xDiff = e1.differentiate("x");
        System.out.println(xDiff);
        //step 5
        try {
            System.out.println(xDiff.evaluate(assignment));
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        //step 6
        System.out.println(xDiff.simplify());
    }
}