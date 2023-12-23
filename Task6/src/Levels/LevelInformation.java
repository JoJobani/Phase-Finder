//Yehonatan Menashe 206478067
package Levels;

import game.Sprite;
import objects.Block;
import status.Velocity;

import java.util.List;

/**
 * The interface Level information.
 */
public interface LevelInformation {
    /**
     * Number of balls in level.
     *
     * @return the number of balls
     */
    int numberOfBalls();

    /**
     * The initial velocity of each ball.
     *
     * @return the ball velocities
     */
    List<Velocity> initialBallVelocities();

    /**
     * Paddle speed.
     *
     * @return the speed
     */
    int paddleSpeed();

    /**
     * Paddle width int.
     *
     * @return the int
     */
    int paddleWidth();

    /**
     * The level name will be displayed at the top of the screen.
     *
     * @return the name
     */
    String levelName();

    /**
     * Returns a sprite with the background of the level.
     *
     * @return the background
     */
    Sprite getBackground();

    /**
     * The Blocks that make up this level, each block contains
     * its size, color and location.
     *
     * @return the list
     */
    List<Block> blocks();

    /**
     * Number of blocks that should be removed
     * before the level is considered to be "cleared".
     *
     * @return the int
     */
    int numberOfBlocksToRemove();
}