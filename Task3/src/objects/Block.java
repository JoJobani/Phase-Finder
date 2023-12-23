//Yehonatan Menashe 206478067
package objects;

import biuoop.DrawSurface;

import java.awt.Color;
import java.util.List;

import game.Sprite;
import game.Collidable;
import shapes.Rectangle;
import shapes.Line;
import status.Velocity;
import shapes.Point;
import game.Game;

/**
 * The type Block.
 */
public class Block implements Collidable, Sprite {
    private final Rectangle shape;
    private final java.awt.Color color;
    private static final double EPSILON = 0.0001;

    /**
     * Instantiates a new Block.
     *
     * @param rectangle the rectangle
     * @param color     the color
     */
    public Block(Rectangle rectangle, Color color) {
        this.shape = rectangle;
        this.color = color;
    }

    /**
     * Draw a block object on the given surface.
     *
     * @param surface the surface
     */
    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(Color.black);
        surface.drawRectangle((int) shape.getUpperLeft().getX(),
                (int) shape.getUpperLeft().getY(), (int) shape.getWidth(),
                (int) shape.getHeight());
        surface.setColor(color);
        surface.fillRectangle((int) shape.getUpperLeft().getX(),
                (int) shape.getUpperLeft().getY(), (int) shape.getWidth(),
                (int) shape.getHeight());

    }

    /**
     * Notify the sprite that time has passed.
     */
    @Override
    public void timePassed() {

    }

    /**
     * Get the "collision shape" of the object.
     *
     * @return the "collision shape" of the object
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return shape;
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
    public Velocity hit(Point collisionPoint, Velocity currentVelocity) {
        List<Line> sides = shape.fourSides();
        double newDY = currentVelocity.getDY();
        double newDX = currentVelocity.getDX();
        Line top = sides.get(0);
        Line left = sides.get(1);
        Line right = sides.get(2);
        Line bottom = sides.get(3);
        if (Math.abs(collisionPoint.distance(top.start())
                + collisionPoint.distance(top.end())
                - top.start().distance(top.end())) < EPSILON
                || Math.abs(collisionPoint.distance(bottom.start())
                + collisionPoint.distance(bottom.end())
                - bottom.start().distance(bottom.end())) < EPSILON) {
            newDY = currentVelocity.getDY() * (-1);
        }
        if (Math.abs(collisionPoint.distance(left.start())
                + collisionPoint.distance(left.end())
                - left.start().distance(left.end())) < EPSILON
                || Math.abs(collisionPoint.distance(right.start())
                + collisionPoint.distance(right.end())
                - right.start().distance(right.end())) < EPSILON) {
            newDX = currentVelocity.getDX() * (-1);
        }
        return new Velocity(newDX, newDY);
    }

    /**
     * Add a block to the game.
     *
     * @param g the game
     */
    @Override
    public void addToGame(Game g) {
        g.addCollidable(this);
        g.addSprite(this);
    }
}
