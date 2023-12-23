//Yehonatan Menashe 206478067
package game;

import Levels.EndScreen;
import Levels.LevelInformation;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import objects.Counter;

import java.util.List;

/**
 * The type Game flow.
 */
public class GameFlow {
    private Counter score;
    private final KeyboardSensor keyboard;
    private AnimationRunner animation;
    private final GUI gui;

    /**
     * Instantiates a new Game flow.
     */
    public GameFlow() {
        this.score = new Counter();
        this.gui = new GUI("Arkanoid game", 800, 600);
        this.keyboard = gui.getKeyboardSensor();
    }

    /**
     * Run levels.
     *
     * @param levels the levels
     */
    public void runLevels(List<LevelInformation> levels) {
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo, gui, score);
            level.initialize();
            this.animation = level.getAnimation();
            while (level.getBallsInGame().getValue() > 0
                    && level.getBlocksInGame().getValue() > 0) {
                level.run();
            }
            if (level.getBallsInGame().getValue() == 0) {
                KeyPressStoppableAnimation loss
                        = new KeyPressStoppableAnimation(keyboard,
                        KeyboardSensor.SPACE_KEY,
                        new EndScreen(keyboard, score, false));
                this.animation.run(loss);
                break;
            }
        }
        KeyPressStoppableAnimation victory
                = new KeyPressStoppableAnimation(keyboard,
                KeyboardSensor.SPACE_KEY,
                new EndScreen(keyboard, score, true));
        this.animation.run(victory);
        this.gui.close();
    }
}

