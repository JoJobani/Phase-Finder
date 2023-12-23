//Yehonatan Menashe 206478067
package objects;

/**
 * The type Counter.
 */
public class Counter {
    private int counter = 0;

    /**
     * Add number to current count.
     *
     * @param number the number to add to counter
     */
    public void increase(int number) {
        counter += number;
    }

    /**
     * Subtract number from current count.
     *
     * @param number the number to remove from counter
     */
    public void decrease(int number) {
        counter -= number;
    }

    /**
     * Get current count.
     *
     * @return the value
     */
    public int getValue() {
        return counter;
    }
}