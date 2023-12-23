//Yehonatan Menashe 206478067

package objects;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;

import game.Sprite;
import game.Collidable;
import status.Velocity;
import game.GameLevel;

/**
 * The type Paddle.
 */
public class Paddle implements Sprite, Collidable {
    private final KeyboardSensor keyboard;
    private Block paddle;
    private final int pWidth;
    private final int speed;
    private final int pHeight = 15;
    private int widthSpot;
    private final int heightSpot = 520;
    private static final int GAME_WIDTH = 800;
    private static final int EDGE_WIDTH = 20;

    /**
     * Instantiates the Paddle.
     *
     * @param keyboard the keyboard sensor
     * @param width    the paddle width
     * @param speed    the paddle speed
     */
    public Paddle(KeyboardSensor keyboard, int width, int speed) {
        this.keyboard = keyboard;
        this.pWidth = width;
        this.speed = speed;
        widthSpot = (GAME_WIDTH - width) / 2;
        paddle = new Block(new Rectangle(new Point(widthSpot, heightSpot),
                pWidth, pHeight), Color.black);
    }

    /**
     * Move left but not beyond game limits.
     */
    public void moveLeft() {
        if (paddle.getCollisionRectangle().getUpperLeft().getX()
                >= EDGE_WIDTH) {
            widthSpot = widthSpot - speed;
            Point newPosition = new Point(widthSpot, heightSpot);
            paddle = new Block(new Rectangle(newPosition, pWidth, pHeight),
                    Color.black);
        }
    }

    /**
     * Move right but not beyond game limits.
     */
    public void moveRight() {
        if (paddle.getCollisionRectangle().getUpperLeft().getX()
                <= (GAME_WIDTH - pWidth - EDGE_WIDTH)) {
            widthSpot = widthSpot + speed;
            Point newPosition = new Point(widthSpot, heightSpot);
            paddle = new Block(new Rectangle(newPosition, pWidth, pHeight),
                    Color.black);
        }
    }

    /**
     * Notify the sprite that time has passed.
     */
    @Override
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        } else if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
    }

    /**
     * Draw the paddle on the given surface.
     *
     * @param d the surface
     */
    @Override
    public void drawOn(DrawSurface d) {
        Point curSpot = paddle.getCollisionRectangle().getUpperLeft();
        d.setColor(Color.orange);
        d.drawRectangle((int) curSpot.getX(), (int) curSpot.getY(),
                pWidth, pHeight);
        d.fillRectangle((int) curSpot.getX(), (int) curSpot.getY(),
                pWidth, pHeight);
    }

    /**
     * Get the "collision shape" of the object.
     *
     * @return the "collision shape" of the object
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return paddle.getCollisionRectangle();
    }

    /**
     * Notify the object that we collided with it at collisionPoint
     * with a given velocity.
     *
     * @param collisionPoint  the collision point
     * @param currentVelocity the current velocity
     * @return the new velocity expected after the hit
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint,
                        Velocity currentVelocity) {
        Point topLeft = this.getCollisionRectangle().getUpperLeft();
        double speed = currentVelocity.getSpeed();
        double angle = 0;
        if (collisionPoint.getX() < (topLeft.getX() + (pWidth * 0.2))) {
            angle = 300;
        } else if (collisionPoint.getX() < (topLeft.getX() + (pWidth * 0.4))
                && collisionPoint.getX() > (topLeft.getX() + (pWidth * 0.2))) {
            angle = 330;
        } else if (collisionPoint.getX() < (topLeft.getX() + (pWidth * 0.6))
                && collisionPoint.getX() > (topLeft.getX() + (pWidth * 0.4))) {
            angle = 0;
        } else if (collisionPoint.getX() < (topLeft.getX() + (pWidth * 0.8))
                && collisionPoint.getX() > (topLeft.getX() + (pWidth * 0.6))) {
            angle = 30;
        } else if (collisionPoint.getX() > (topLeft.getX() + (pWidth * 0.8))) {
            angle = 60;
        }
        return currentVelocity.fromAngleAndSpeed(angle, speed);
    }

    /**
     * Add the paddle to the game.
     *
     * @param g the game
     */
    @Override
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }
}