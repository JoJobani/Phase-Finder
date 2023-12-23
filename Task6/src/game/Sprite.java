//Yehonatan Menashe 206478067
package game;

import biuoop.DrawSurface;

/**
 * The Sprite interface.
 */
public interface Sprite {
    /**
     * Draw the sprite to the screen.
     *
     * @param d the screen
     */
    void drawOn(DrawSurface d);

    /**
     * Notify the sprite that time has passed.
     */
    void timePassed();

    /**
     * Add a sprite to the game.
     *
     * @param g the game
     */
    void addToGame(GameLevel g);
}