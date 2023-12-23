//Yehonatan Menashe 206478067
package game;

import Levels.LevelInformation;
import biuoop.KeyboardSensor;
import objects.Counter;
import objects.Point;
import objects.Rectangle;
import objects.Ball;
import objects.Block;
import objects.Paddle;
import biuoop.GUI;
import biuoop.DrawSurface;
import status.BallRemover;
import status.BlockRemover;
import status.LevelName;
import status.ScoreIndicator;
import status.ScoreTrackingListener;

import java.awt.Color;
import java.util.List;

/**
 * The Game controller.
 */
public class GameLevel implements Animation {
    private static final int EDGE = 10;
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final int RADIUS = 7;
    //fields
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private final GUI gui;
    private Counter blocksInGame;
    private Counter ballsInGame;
    private Counter score;
    private ScoreIndicator scoreIndicator;
    private final AnimationRunner runner;
    private boolean running;
    private final KeyboardSensor keyboard;
    private LevelInformation level;

    /**
     * Instantiates a new Game level.
     *
     * @param level the level
     * @param gui   the gui
     * @param score the score
     */
    public GameLevel(LevelInformation level, GUI gui, Counter score) {
        this.gui = gui;
        keyboard = gui.getKeyboardSensor();
        environment = new GameEnvironment();
        sprites = new SpriteCollection();
        ballsInGame = new Counter();
        this.score = score;
        blocksInGame = new Counter();
        runner = new AnimationRunner(gui);
        this.level = level;
    }

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
        level.getBackground().addToGame(this);
        //create the balls and add them to the game
        for (int i = 0; i < level.numberOfBalls(); i++) {
            Ball ball = new Ball(400, 450,
                    RADIUS, Color.white);
            ball.setVelocity(level.initialBallVelocities().get(i));
            ball.setGameEnvironment(environment);
            ball.addToGame(this);
        }
        ballsInGame.increase(level.numberOfBalls());
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
        ScoreTrackingListener scoreTracker = new ScoreTrackingListener(score);
        scoreIndicator = new ScoreIndicator(this);
        scoreIndicator.addToGame(this);
        //add blocks for the game
        blocksInGame.increase(level.numberOfBlocksToRemove());
        List<Block> blockList = level.blocks();
        BlockRemover remover = new BlockRemover(this, blocksInGame);
        for (Block block : blockList) {
            block.addHitListener(remover);
            block.addHitListener(scoreTracker);
            block.addToGame(this);
        }
        //create the paddle
        Paddle paddle = new Paddle(gui.getKeyboardSensor(),
                level.paddleWidth(), level.paddleSpeed());
        paddle.addToGame(this);
        //add the name of level
        LevelName name = new LevelName(level.levelName());
        name.addToGame(this);

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
     * Gets animation.
     *
     * @return the animation
     */
    public AnimationRunner getAnimation() {
        return this.runner;
    }

    /**
     * Gets balls in game.
     *
     * @return the balls in game
     */
    public Counter getBallsInGame() {
        return ballsInGame;
    }

    /**
     * Gets blocks in game.
     *
     * @return the blocks in game
     */
    public Counter getBlocksInGame() {
        return blocksInGame;
    }

    /**
     * Run the game -- start the animation loop.
     */
    public void run() {
        this.runner.run(new CountdownAnimation(2,
                3, sprites));
        running = true;
        runner.run(this);
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();
        if (this.keyboard.isPressed("p")) {
            KeyPressStoppableAnimation pause =
                    new KeyPressStoppableAnimation(keyboard,
                            KeyboardSensor.SPACE_KEY,
                            new PauseScreen(keyboard));
            this.runner.run(pause);
        }
    }

    @Override
    public boolean shouldStop() {
        boolean levelClear = false;
        if (blocksInGame.getValue() == 0) {
            this.score.increase(100);
            levelClear = true;
        }
        if (ballsInGame.getValue() == 0) {
            levelClear = true;
        }
        return levelClear;
    }
}