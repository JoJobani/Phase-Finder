//Yehonatan Menashe 206478067

package game;

import biuoop.DrawSurface;
import biuoop.Sleeper;

import java.awt.Color;

/**
 * The type Countdown animation.
 */
public class CountdownAnimation implements Animation {
    private final double numOfSeconds;
    private int countFrom;
    private final SpriteCollection gameScreen;
    private boolean stop;
    private final Sleeper sleeper;

    /**
     * Instantiates a new Countdown animation.
     *
     * @param numOfSeconds the num of seconds
     * @param countFrom    the count from
     * @param gameScreen   the game screen
     */
    public CountdownAnimation(double numOfSeconds, int countFrom,
                              SpriteCollection gameScreen) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        this.stop = false;
        this.sleeper = new Sleeper();
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        if (countFrom == 0) {
            stop = true;
        }
        d.setColor(Color.cyan);
        int wGUI = 800;
        int hGUI = 600;
        d.fillRectangle(0, 0, wGUI, hGUI);
        d.setColor(Color.BLACK);
        gameScreen.drawAllOn(d);
        d.drawText(d.getWidth() / 2, d.getHeight() / 2,
                Integer.toString(countFrom), 200);
        if (countFrom != 3) {
            sleeper.sleepFor((int) (numOfSeconds * 1000) / 3);
        }
        countFrom--;
    }


    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}