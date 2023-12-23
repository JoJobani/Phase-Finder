//Yehonatan Menashe 206478067

package game;

import shapes.Point;
import shapes.Rectangle;
import status.Velocity;

/**
 * The collidable interface.
 */
public interface Collidable {
    /**
     * Get the "collision shape" of the object.
     *
     * @return the "collision shape" of the object
     */
    Rectangle getCollisionRectangle();

    /**
     * Notify the object that we collided with it at collisionPoint
     * with a given velocity.
     *
     * @param collisionPoint  the collision point
     * @param currentVelocity the current velocity
     * @return the new velocity expected after the hit
     */
    Velocity hit(Point collisionPoint, Velocity currentVelocity);

    /**
     * Add a collidable to the game.
     *
     * @param g the game
     */
    void addToGame(Game g);
}