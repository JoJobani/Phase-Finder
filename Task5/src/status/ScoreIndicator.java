//Yehonatan Menashe 206478067
package status;

import biuoop.DrawSurface;
import game.Game;
import game.Sprite;
import objects.Counter;

import java.awt.Color;

/**
 * The type Score indicator.
 */
public class ScoreIndicator implements Sprite {
    private Counter score;

    /**
     * Instantiates a new Score indicator.
     *
     * @param g the game
     */
    public ScoreIndicator(Game g) {
        score = g.getScore();
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.black);
        d.drawText(10, 18, "Score: " + score.getValue(), 20);
    }

    @Override
    public void timePassed() {
    }

    @Override
    public void addToGame(Game g) {
        g.addSprite(this);
    }
}
