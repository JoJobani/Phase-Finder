//Yehonatan Menashe 206478067
package game;

import objects.Ball;
import objects.Block;
import objects.Counter;
import status.HitListener;

/**
 * The type Score tracking listener.
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * Instantiates a new Score tracking listener.
     *
     * @param scoreCounter the score counter
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * Adds point to the player when needed.
     *
     * @param beingHit the block being hit.
     * @param hitter   the ball
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        currentScore.increase(5);
    }
}