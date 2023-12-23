//Yehonatan Menashe 206478067
package objects;

import biuoop.DrawSurface;
import game.Sprite;
import status.Velocity;
import game.GameEnvironment;
import status.CollisionInfo;
import game.GameLevel;

import java.awt.Color;

/**
 * The Ball object.
 */
public class Ball implements Sprite {
    /**
     * The constant DEFAULT_V.
     */
    public static final double DEFAULT_V = 0;
    private Point center;
    private final int radius;
    private final java.awt.Color color;
    private Velocity velocity = new Velocity(DEFAULT_V, DEFAULT_V);
    private GameEnvironment game;
    private static final double EPSILON = 0.00001;

    /**
     * Instantiates a new Ball (Alternative).
     *
     * @param x     the x coordinate of the ball
     * @param y     the y coordinate of the ball
     * @param r     the radius
     * @param color the color of the ball
     */
    public Ball(double x, double y, int r, java.awt.Color color) {
        this.radius = r;
        this.center = new Point(x, y);
        this.color = color;
    }

    /**
     * Get the x coordinate of the center.
     *
     * @return the x variable
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * Get the y coordinate of the center.
     *
     * @return the y
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * Get the size of the ball.
     *
     * @return the size
     */
    public int getSize() {
        return (int) Math.PI * this.radius * this.radius;
    }

    /**
     * Get the color of the ball.
     *
     * @return the color
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * Set the game environment for the ball.
     *
     * @param game the game environment
     */
    public void setGameEnvironment(GameEnvironment game) {
        this.game = game;
    }

    /**
     * Draw a ball object on the given surface.
     *
     * @param surface the surface
     */
    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(Color.black);
        surface.drawCircle((int) this.center.getX(),
                (int) this.center.getY(), radius);
        surface.setColor(color);
        surface.fillCircle((int) this.center.getX(),
                (int) this.center.getY(), radius);
    }

    /**
     * Notify the sprite that time has passed.
     */
    @Override
    public void timePassed() {
        moveOneStep();
    }

    /**
     * Add a ball to the game.
     *
     * @param g the game
     */
    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    /**
     * Remove the ball from the game.
     *
     * @param g the game
     */
    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
    }

    /**
     * Sets/applies new velocity value to ball.
     *
     * @param v the new velocity
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * Sets/applies new velocity value to ball manually using x,y values.
     *
     * @param dx the x change value
     * @param dy the y change value
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /**
     * Get the velocity value of the ball.
     *
     * @return the velocity
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * Move one step and change velocity in case of collision.
     */
    public void moveOneStep() {
        Point destination = new Point(getX() + velocity.getDX(),
                getY() + velocity.getDY());
        Line trajectory = new Line(center, destination);
        CollisionInfo collision = game.getClosestCollision(trajectory);
        if (collision != null) {
            //in case a hit was detected gather info about the collision
            Rectangle collisionObj = collision
                    .collisionObject().getCollisionRectangle();
            Point topLeft = collisionObj.getUpperLeft();
            Point botRight = new Point(topLeft.getX()
                    + collisionObj.getWidth(), topLeft.getY()
                    + collisionObj.getHeight());
            Point collisionPoint = trajectory
                    .closestIntersectionToStartOfLine(collisionObj);
            //find which side of the block was hit and move the ball near it
            if (Math.abs(collisionPoint.getX() - topLeft.getX()) < EPSILON) {
                center = new Point(collisionPoint.getX() - radius, getY());
            }
            if (Math.abs(collisionPoint.getY() - topLeft.getY()) < EPSILON) {
                center = new Point(getX(), collisionPoint.getY() - radius);
            }
            if (Math.abs(collisionPoint.getX() - botRight.getX()) < EPSILON) {
                center = new Point(collisionPoint.getX() + radius, getY());
            }
            if (Math.abs(collisionPoint.getY() - botRight.getY()) < EPSILON) {
                center = new Point(getX(), collisionPoint.getY() + radius);
            }
            this.velocity = collision.collisionObject().hit(this,
                    collision.collisionPoint(), getVelocity());
        }
        //edge definition
        if (center.getX() < 0) {
            center = new Point(radius + 10, center.getY());
        }
        if (center.getX() > 800) {
            center = new Point(790 - radius, center.getY());
        }
        if (center.getY() < 0) {
            center = new Point(center.getX(), radius + 10);
        }
        //in case there is no collision
        center = getVelocity().applyToPoint(center);
    }
}