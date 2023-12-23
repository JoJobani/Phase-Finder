//Yehonatan Menashe 206478067
package game;

import java.util.ArrayList;
import java.util.List;

import biuoop.DrawSurface;

/**
 * The Sprite collection.
 */
public class SpriteCollection {
    private final List<Sprite> sprites = new ArrayList<>();


    /**
     * Add a sprite to the game.
     *
     * @param s the sprite
     */
    public void addSprite(Sprite s) {
        sprites.add(s);
    }

    /**
     * Call timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        for (Sprite sprite : sprites) {
            sprite.timePassed();
        }
    }

    /**
     * Call drawOn(d) on all sprites.
     *
     * @param d the surface
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite sprite : sprites) {
            sprite.drawOn(d);
        }
    }
}
