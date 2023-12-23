// 206478067 Yehonatan Menashe

/**
 * function to find first and last occurrence of a certain number in an array.
 */
public class PlaceInArray {
    /**
     * main to receive the arguments.
     *
     * @param args the array with the last spot being the number we want to find
     */
    public static void main(String[] args) {
        int target = Integer.parseInt(args[args.length - 1]);
        int length = args.length;
        int[] arr = new int[length];
        for (int i = 0; i < length; i++) {
            arr[i] = Integer.parseInt(args[i]);
        }
        placeInArray(arr, length, target);
    }

    /**
     * find the first and last occurrences of a number in an array and print them.
     *
     * @param arr    the number array
     * @param length the length of the array
     * @param target the target number
     */
    public static void placeInArray(int[] arr, int length, int target) {
        int first = -1, last = -1;
        for (int i = 0; i < length - 1; i++) {
            if (target != arr[i]) {
                continue;
            }
            if (first == -1) {
                first = arr[i];
            }
            last = i;
        }
        System.out.print(target + " start in " + first + " and end in " + last);
    }
}
