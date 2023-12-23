//Yehonatan Menashe 206478067
package game;

import java.util.ArrayList;
import java.util.List;

import objects.Line;
import status.CollisionInfo;

/**
 * The Game environment.
 */
public class GameEnvironment {
    /**
     * A list of the collidable objects in the game.
     */
    private final List<Collidable> objects = new ArrayList<>();

    /**
     * Add the given collidable to the environment.
     *
     * @param c the collidable
     */
    public void addCollidable(Collidable c) {
        objects.add(c);
    }

    /**
     * Remove collidable from game.
     *
     * @param c the collidable
     */
    public void removeCollidable(Collidable c) {
        objects.remove(c);
    }

    /**
     * Get a list of all collidable objects.
     *
     * @return the object list
     */
    public List<Collidable> getObjects() {
        return objects;
    }

    /**
     * Get info on the closest collision on the trajectory.
     *
     * @param trajectory the trajectory line
     * @return the collision info
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        List<CollisionInfo> collisions = collisionList(trajectory);
        CollisionInfo closestCollision = null;
        for (CollisionInfo collision : collisions) {
            if (closestCollision == null && collision != null) {
                closestCollision = collision;
            } else if (closestCollision != null && collision != null
                    && trajectory.start().distance(closestCollision
                    .collisionPoint())
                    > trajectory.start().distance(collision
                    .collisionPoint())) {
                closestCollision = collision;
            }
        }
        return closestCollision;
    }

    private List<CollisionInfo> collisionList(Line line) {
        List<CollisionInfo> collisions = new ArrayList<>();
        for (Collidable object : getObjects()) {
            if (line.closestIntersectionToStartOfLine(object
                    .getCollisionRectangle()) != null) {
                CollisionInfo boop = new CollisionInfo(line.
                        closestIntersectionToStartOfLine(object.
                                getCollisionRectangle()), object);
                collisions.add(boop);
            }
        }
        return collisions;
    }
}