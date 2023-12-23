//Yehonatan Menashe 206478067

package status;

import shapes.Point;
import game.Collidable;

/**
 * The Collision info.
 */
public class CollisionInfo {
    private Point point;
    private Collidable object;

    /**
     * Sets collision point.
     *
     * @param p the point
     */
    public void setCollisionPoint(Point p) {
        point = p;
    }

    /**
     * Sets collision object.
     *
     * @param c the object
     */
    public void setCollisionObject(Collidable c) {
        object = c;
    }

    /**
     * Collision point method.
     *
     * @return the point at which the collision occurs
     */
    public Point collisionPoint() {
        return point;
    }

    /**
     * Collision object.
     *
     * @return the collidable object involved in the collision
     */
    public Collidable collisionObject() {
        return object;
    }
}