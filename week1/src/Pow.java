// 206478067 Yehonatan Menashe

/**
 * * power calculator function.
 */
public class Pow {
    /**
     * main to receive two integers as args.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        long x = Integer.parseInt(args[1]);
        long n = Integer.parseInt(args[0]);
        long recursive = powRecursive(n, x);
        System.out.println("recursive: " + recursive);
        long iterative = powIter(n, x);
        System.out.println("iterative: " + iterative);
    }

    /**
     * Power using recursion.
     *
     * @param base  the base number
     * @param power the power number
     * @return the base to a certain power using recursion
     */
    public static long powRecursive(long base, long power) {
        if (power != 0) {
            return (base * powRecursive(base, power - 1));
        } else {
            return 1;
        }
    }

    /**
     * Power using iteration.
     *
     * @param base  the base number
     * @param power the power number
     * @return the base to a certain number using iteration
     */
    public static long powIter(long base, long power) {
        int result = 1;
        for (int i = 1; i <= power; i++) {
            result *= base;
        }
        return result;
    }
}
