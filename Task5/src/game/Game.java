//Yehonatan Menashe 206478067
package game;

import objects.Counter;
import objects.Point;
import objects.Rectangle;
import objects.Ball;
import objects.Block;
import objects.Paddle;
import biuoop.GUI;
import biuoop.DrawSurface;
import biuoop.Sleeper;
import status.BallRemover;
import status.BlockRemover;
import status.ScoreIndicator;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * The Game controller.
 */
public class Game {
    //game screen properties
    private static final int EDGE = 10;
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final int B_WIDTH = 50;
    private static final int B_HEIGHT = 17;
    //misc
    private static final int DEF_RAD = 10;
    private static final int NUM_BALLS = 3;
    private static final int COLOR_RANGE = 255;
    private static final int VELOCITY_RANGE = 4;
    //fields
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI gui;
    private Sleeper sleeper;
    private Counter blocksInGame;
    private Counter ballsInGame;
    private Counter score;
    private ScoreIndicator scoreIndicator;

    /**
     * Add a collidable to the game environment.
     *
     * @param c the collidable object
     */
    public void addCollidable(Collidable c) {
        environment.addCollidable(c);
    }

    /**
     * Remove collidable from game.
     *
     * @param c the collidable
     */
    public void removeCollidable(Collidable c) {
        environment.removeCollidable(c);
    }

    /**
     * Add a sprite to the sprite collection.
     *
     * @param s the sprite
     */
    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }

    /**
     * Remove sprite from game.
     *
     * @param s the sprite
     */
    public void removeSprite(Sprite s) {
        sprites.removeSprite(s);
    }

    /**
     * Initialize a new game: create the Blocks and Ball (and Paddle)
     * and add them to the game.
     */
    public void initialize() {
        Random rand = new Random();
        //create the basic GUI, sleeper, environment, and sprites
        gui = new GUI("Game", WIDTH, HEIGHT);
        sleeper = new biuoop.Sleeper();
        environment = new GameEnvironment();
        sprites = new SpriteCollection();
        //create the balls and add them to the game
        for (int i = 0; i < NUM_BALLS; i++) {
            Ball ball = new Ball(200, 200,
                    DEF_RAD, Color.black);
            ball.setVelocity(VELOCITY_RANGE
                    + rand.nextInt(VELOCITY_RANGE), VELOCITY_RANGE
                    + rand.nextInt(VELOCITY_RANGE));
            ball.setGameEnvironment(environment);
            ball.addToGame(this);
        }
        ballsInGame = new Counter();
        ballsInGame.increase(NUM_BALLS);
        //create the edge blocks for the game
        Block top = new Block(new Rectangle(new Point(0, 0), WIDTH,
                EDGE * 2), Color.gray);
        top.addToGame(this);
        Block left = new Block(new Rectangle(new Point(0, 20), EDGE,
                HEIGHT), Color.gray);
        left.addToGame(this);
        Block right = new Block(new Rectangle(new Point(WIDTH - EDGE, 20),
                EDGE, HEIGHT), Color.gray);
        right.addToGame(this);
        //create the bottom killer block
        BallRemover ballRemover = new BallRemover(this, ballsInGame);
        Block bottom = new Block(new Rectangle(new Point(0, HEIGHT),
                WIDTH, EDGE), Color.gray);
        bottom.addHitListener(ballRemover);
        bottom.addToGame(this);
        //add the score counter
        score = new Counter();
        ScoreTrackingListener scoreTracker = new ScoreTrackingListener(score);
        scoreIndicator = new ScoreIndicator(this);
        scoreIndicator.addToGame(this);
        //create the game blocks for the game
        //for loops with "i" for lines and "j" for blocks per line
        List<Block> blocks = new ArrayList<>();
        int xBlock = 750 - EDGE;
        int yBlock = B_HEIGHT * 4;
        int lines = 6;
        int blocksPerLine = 12;
        for (int i = 0; i < lines; i++) {
            int red = rand.nextInt(COLOR_RANGE);
            int green = rand.nextInt(COLOR_RANGE);
            int blue = rand.nextInt(COLOR_RANGE);
            Color randomColor = new Color(red, green, blue);
            for (int j = 0; j < blocksPerLine; j++) {
                blocks.add(new Block(new Rectangle(new Point(xBlock
                        - (j * B_WIDTH), yBlock), B_WIDTH, B_HEIGHT),
                        randomColor));
            }
            blocksPerLine--;
            yBlock += B_HEIGHT;
        }
        blocksInGame = new Counter();
        blocksInGame.increase(blocks.size());
        BlockRemover remover = new BlockRemover(this, blocksInGame);
        //add all of our blocks to the game
        for (Block block : blocks) {
            block.addHitListener(remover);
            block.addHitListener(scoreTracker);
            block.addToGame(this);
        }
        //create the paddle
        Paddle paddle = new Paddle(gui.getKeyboardSensor());
        paddle.addToGame(this);
    }

    /**
     * Gets score.
     *
     * @return the score
     */
    public Counter getScore() {
        return score;
    }

    /**
     * Run the game -- start the animation loop.
     */
    public void run() {
        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (true) {
            long startTime = System.currentTimeMillis();
            DrawSurface d = gui.getDrawSurface();
            this.sprites.drawAllOn(d);
            gui.show(d);
            this.sprites.notifyAllTimePassed();
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
            if (blocksInGame.getValue() == 0) {
                score.increase(100);
                gui.close();
                return;
            }
            if (ballsInGame.getValue() == 0) {
                gui.close();
                return;
            }
        }
    }
}