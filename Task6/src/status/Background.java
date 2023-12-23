//Yehonatan Menashe 206478067
package status;

import biuoop.DrawSurface;
import game.GameLevel;
import game.Sprite;

import java.awt.Color;

/**
 * The type Background.
 */
public class Background implements Sprite {
    private final Color color;

    /**
     * Instantiates a new Background.
     *
     * @param color the color
     */
    public Background(Color color) {
        this.color = color;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(color);
        d.fillRectangle(0, 0, 800, 600);
    }

    @Override
    public void timePassed() {
    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
