// 206478067 Yehonatan Menashe

/**
 * reversing a number function.
 */
public class ReverseNumber {
    /**
     * main to receive the arguments.
     *
     * @param args get a single number from user
     */
    public static void main(String[] args) {
        int input = Integer.parseInt(args[0]);
        int reverse = reverseNum(input);
        System.out.println("reverse number: " + reverse);
    }

    /**
     * Reverse num function itself.
     *
     * @param number the original number
     * @return the reverse number
     */
    public static int reverseNum(int number) {
        int reverse = 0;
        while (number != 0) {
            reverse = reverse * 10 + number % 10;
            number = number / 10;
        }
        return reverse;
    }
}
