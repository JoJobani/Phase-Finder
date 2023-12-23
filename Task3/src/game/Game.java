//Yehonatan Menashe 206478067
package game;

import shapes.Point;
import shapes.Rectangle;
import objects.Ball;
import objects.Block;
import objects.Paddle;
import biuoop.GUI;
import biuoop.DrawSurface;
import biuoop.Sleeper;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * The Game controller.
 */
public class Game {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final int B_WIDTH = 50;
    private static final int B_HEIGHT = 17;
    private static final int DEF_RAD = 10;
    private static final int NUM_BALLS = 2;
    private static final int EDGE = 10;
    private static final int COLOR_RANGE = 255;
    private static final int VELOCITY_RANGE = 4;
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI gui;
    private Sleeper sleeper;

    /**
     * Add a collidable to the game environment.
     *
     * @param c the collidable object
     */
    public void addCollidable(Collidable c) {
        environment.addCollidable(c);
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
        //create the edge blocks for the game
        List<Block> blocks = new ArrayList<>();
        blocks.add(new Block(new Rectangle(new Point(0, 0), WIDTH,
                EDGE), Color.black));
        blocks.add(new Block(new Rectangle(new Point(0, 0), EDGE,
                HEIGHT), Color.black));
        blocks.add(new Block(new Rectangle(new Point(WIDTH - EDGE, 0),
                EDGE, HEIGHT), Color.black));
        blocks.add(new Block(new Rectangle(new Point(0, HEIGHT - EDGE),
                WIDTH, EDGE), Color.black));
        //create the game blocks for the game
        //for loops with "i" for lines and "j" for blocks per line
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
        //add all of our blocks to the game
        for (Block block : blocks) {
            block.addToGame(this);
        }
        //create the paddle
        Paddle paddle = new Paddle(gui.getKeyboardSensor());
        paddle.addToGame(this);
        //create the balls and add them to the game
        List<Ball> balls = new ArrayList<>();
        for (int i = 0; i < NUM_BALLS; i++) {
            balls.add(new Ball(200, 200,
                    DEF_RAD, Color.black));
            balls.get(i).setVelocity(VELOCITY_RANGE
                    + rand.nextInt(VELOCITY_RANGE), VELOCITY_RANGE
                    + rand.nextInt(VELOCITY_RANGE));
            balls.get(i).setGameEnvironment(environment);
            balls.get(i).addToGame(this);
        }
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
        }
    }
}