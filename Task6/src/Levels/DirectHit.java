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
 * The direct hit level information.
 */
public class DirectHit implements LevelInformation {
    @Override
    public int numberOfBalls() {
        return 1;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        Velocity velocity = new Velocity(0, -4);
        List<Velocity> velocities = new ArrayList<>();
        velocities.add(velocity);
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
        return "Direct Hit";
    }

    @Override
    public Sprite getBackground() {
        return new Background(Color.darkGray);
    }

    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
        blocks.add(new Block(new Rectangle(new Point(375, 100),
                50, 15), Color.red));
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 1;
    }
}
