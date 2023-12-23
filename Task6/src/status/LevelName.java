//Yehonatan Menashe 206478067
package status;

import biuoop.DrawSurface;
import game.GameLevel;
import game.Sprite;

import java.awt.Color;

/**
 * The type Level name.
 */
public class LevelName implements Sprite {
    private final String levelName;

    /**
     * Instantiates a new Level name.
     *
     * @param levelName the level name
     */
    public LevelName(String levelName) {
        this.levelName = levelName;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.black);
        d.drawText(500, 18, "Level Name:   " + levelName, 20);
    }

    @Override
    public void timePassed() {

    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
