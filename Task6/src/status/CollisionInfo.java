//Yehonatan Menashe 206478067

package status;

import objects.Point;
import game.Collidable;

/**
 * The Collision info.
 */
public class CollisionInfo {
    private final Point point;
    private final Collidable object;

    /**
     * Instantiates a new Collision info.
     *
     * @param point      the point
     * @param collidable the collidable
     */
    public CollisionInfo(Point point, Collidable collidable) {
        this.point = point;
        this.object = collidable;
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