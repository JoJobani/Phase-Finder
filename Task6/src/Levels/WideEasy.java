//Yehonatan Menashe 206478067
package Levels;

import game.Sprite;
import objects.Block;
import objects.Point;
import objects.Rectangle;
import status.Background;
import status.Velocity;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The Wide easy level.
 */
public class WideEasy implements LevelInformation {
    @Override
    public int numberOfBalls() {
        return 10;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        int dx = -10;
        List<Velocity> velocities = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Velocity v = new Velocity(dx, -4);
            dx += 2;
            velocities.add(v);
        }
        return velocities;
    }

    @Override
    public int paddleSpeed() {
        return 10;
    }

    @Override
    public int paddleWidth() {
        return 600;
    }

    @Override
    public String levelName() {
        return "Wide Easy";
    }

    @Override
    public Sprite getBackground() {
        return new Background(Color.white);
    }

    @Override
    public List<Block> blocks() {
        int xStart = 20;
        int y = 150;
        List<Block> blocks = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            Color color = Color.red;
            if (i > 1) {
                color = Color.orange;
            }
            if (i > 3) {
                color = Color.yellow;
            }
            if (i > 5) {
                color = Color.green;
            }
            if (i > 8) {
                color = Color.blue;
            }
            if (i > 10) {
                color = Color.pink;
            }
            if (i > 12) {
                color = Color.cyan;
            }
            blocks.add(new Block(new Rectangle(new Point(xStart, y),
                    50, 40), color));
            xStart += 51;
        }
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 15;
    }
}
