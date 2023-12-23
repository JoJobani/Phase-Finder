//Yehonatan Menashe 206478067
package objects;

import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import game.Sprite;
import game.Collidable;
import status.HitListener;
import status.HitNotifier;
import status.Velocity;
import game.GameLevel;

/**
 * The type Block.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private final Rectangle shape;
    private final java.awt.Color color;
    private static final double EPSILON = 0.0001;
    private List<HitListener> hitListeners;

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
    public Velocity hit(Ball hitter, Point collisionPoint,
                        Velocity currentVelocity) {
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
        this.notifyHit(hitter);
        return new Velocity(newDX, newDY);
    }

    /**
     * Add a block to the game.
     *
     * @param g the game
     */
    @Override
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }

    /**
     * Remove a block from the game.
     *
     * @param game the game
     */
    public void removeFromGame(GameLevel game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }

    @Override
    public void addHitListener(HitListener hl) {
        if (hitListeners == null) {
            hitListeners = new ArrayList<>();
        }
        hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        hitListeners.add(hl);
    }

    private void notifyHit(Ball hitter) {
        //no action needed when edge is hit
        if (hitListeners == null) {
            return;
        }
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }

    }
}
