// 206478067 Yehonatan Menashe

/**
 * function to find out if three integers in an array sum up to zero.
 */
public class TripletOfZero {
    /**
     * main receives the arguments.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        int[] numbers = stringsToArray(args);
        boolean asc = isAsc(args);
        int[] triplet = tripletOfZero(numbers);
        if (triplet[0] != -1) {
            if (asc) {
                ascTripletPrint(triplet);
            } else {
                descTripletPrint(triplet);
            }
        } else {
            System.out.println("the triplet is: -1");
        }
    }

    /**
     * Asc triplet print.
     *
     * @param numbers the numbers
     */
    public static void ascTripletPrint(int[] numbers) {
        for (int i = 0; i < numbers.length; i++) {
            for (int j = i + 1; j < numbers.length; j++) {
                if (numbers[i] > numbers[j]) {
                    int temp = numbers[i];
                    numbers[i] = numbers[j];
                    numbers[j] = temp;
                }
            }
        }
        System.out.println("the triplet is: [" + numbers[0] + ", " + numbers[1] + ", " + numbers[2] + "]");
    }

    /**
     * Desc triplet print.
     *
     * @param numbers the numbers
     */
    public static void descTripletPrint(int[] numbers) {
        for (int i = 0; i < numbers.length; i++) {
            for (int j = i + 1; j < numbers.length; j++) {
                if (numbers[i] < numbers[j]) {
                    int temp = numbers[i];
                    numbers[i] = numbers[j];
                    numbers[j] = temp;
                }
            }
        }
        System.out.println("the triplet is: [" + numbers[0] + ", " + numbers[1] + ", " + numbers[2] + "]");
    }

    /**
     * Triplet of zero int [ ].
     *
     * @param numbers the numbers
     * @return the int [ ]
     */
    public static int[] tripletOfZero(int[] numbers) {
        int arrLen = numbers.length;
        for (int i = 0; i < arrLen - 2; i++) {
            for (int j = i + 1; j < arrLen - 1; j++) {
                for (int k = j + 1; k < arrLen; k++) {
                    if (numbers[i] + numbers[j] + numbers[k] == 0) {
                        return new int[]{numbers[i], numbers[j], numbers[k]};
                    }
                }
            }
        }
        return new int[]{-1, -1, -1};
    }

    /**
     * Strings to array int [ ].
     *
     * @param numbers the numbers
     * @return the int [ ]
     */
    public static int[] stringsToArray(String[] numbers) {
        int length = numbers.length;
        int[] array = new int[length - 1];
        for (int i = 0; i < length - 1; i++) {
            array[i] = Integer.parseInt(numbers[i + 1]);
        }
        return array;
    }

    /**
     * Is asc boolean.
     *
     * @param order the order
     * @return the boolean
     */
    public static boolean isAsc(String[] order) {
        String bool = order[0];
        return bool.equals("asc");
    }
}
