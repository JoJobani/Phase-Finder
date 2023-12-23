//Yehonatan Menashe 206478067

package status;

/**
 * The interface Hit notifier.
 * an object that implement this sends notifications when it is being hit
 */
public interface HitNotifier {
    /**
     * Add a hit listener as a listener to hit events.
     *
     * @param hl the hl
     */
    void addHitListener(HitListener hl);

    /**
     * Remove hit listener from the list of listeners to hit events.
     *
     * @param hl the hl
     */
    void removeHitListener(HitListener hl);
}