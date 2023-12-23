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
 * The Green standard level.
 */
public class GreenStandard implements LevelInformation {
    @Override
    public int numberOfBalls() {
        return 3;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        int dx = -5;
        List<Velocity> velocities = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Velocity v = new Velocity(dx, -4);
            velocities.add(v);
            dx += 5;
        }
        return velocities;
    }

    @Override
    public int paddleSpeed() {
        return 10;
    }

    @Override
    public int paddleWidth() {
        return 100;
    }

    @Override
    public String levelName() {
        return "The Green Level";
    }

    @Override
    public Sprite getBackground() {
        return new Background(Color.GREEN);
    }

    @Override
    public List<Block> blocks() {
        //positioning of blocks
        int xBlock = 730;
        int yBlock = 68;
        //amount of blocks
        int lines = 5;
        int blocksPerLine = 10;
        //the actual blocks
        List<Block> blocks = new ArrayList<>();
        for (int i = 0; i < lines; i++) {
            Color color = Color.gray;
            if (i > 0) {
                color = Color.red;
            }
            if (i > 1) {
                color = Color.YELLOW;
            }
            if (i > 2) {
                color = Color.blue;
            }
            if (i > 3) {
                color = Color.white;
            }
            for (int j = 0; j < blocksPerLine; j++) {
                blocks.add(new Block(new Rectangle(new Point(xBlock - (j * 60),
                        yBlock), 60, 25), color));
            }
            blocksPerLine--;
            yBlock += 25;
        }
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 40;
    }
}
