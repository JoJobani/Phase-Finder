//Yehonatan Menashe 206478067

package status;

import objects.Block;
import objects.Ball;

/**
 * The interface Hit listener.
 * notifies objects about a hit event
 */
public interface HitListener {
    /**
     * This method is called whenever the beingHit object is hit.
     *
     * @param beingHit the object being hit
     * @param hitter   the Ball that's doing the hitting
     */
    void hitEvent(Block beingHit, Ball hitter);
}
