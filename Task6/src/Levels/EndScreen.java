//Yehonatan Menashe 206478067
package Levels;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import game.Animation;
import objects.Counter;

import java.awt.Color;

/**
 * The type End screen.
 */
public class EndScreen implements Animation {
    private final KeyboardSensor keyboard;
    private final int score;
    private final boolean victory;


    /**
     * Instantiates a new End screen.
     *
     * @param keyboard the keyboard
     * @param score    the score
     * @param victory  the victory
     */
    public EndScreen(KeyboardSensor keyboard, Counter score, boolean victory) {
        this.keyboard = keyboard;
        this.score = score.getValue();
        this.victory = victory;
    }


    @Override
    public void doOneFrame(DrawSurface d) {
        if (victory) {
            d.setColor(Color.GREEN);
            d.fillRectangle(0, 0, 800, 600);
            d.setColor(Color.black);
            d.drawText(100, 200,
                    "You Win! Your score is " + score, 50);
        } else {
            d.setColor(Color.pink);
            d.fillRectangle(0, 0, 800, 600);
            d.setColor(Color.black);
            d.drawText(100, 200,
                    "Game Over. Your score is " + score, 50);
        }
    }

    @Override
    public boolean shouldStop() {
        return false;
    }
}
