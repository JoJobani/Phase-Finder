//Yehonatan Menashe 206478067
package status;

import objects.Point;

/**
 * Velocity specifies the change in position on the `x` and the `y` axes.
 */
public class Velocity {
    private final double dx;
    private final double dy;

    /**
     * Instantiates a new Velocity setting.
     *
     * @param dx the change in x value
     * @param dy the change in y value
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Translates angle and speed to dx and dy values.
     *
     * @param angle the angle
     * @param speed the speed
     * @return the velocity
     */
    public Velocity fromAngleAndSpeed(double angle, double speed) {
        angle = Math.toRadians(angle);
        double dy = speed * Math.cos(angle);
        double dx = speed * Math.sin(angle);
        return new Velocity(dx, -dy);
    }

    /**
     * Convert dx and dy to speed value.
     *
     * @return the current speed
     */
    public double getSpeed() {
        return Math.sqrt((dx * dx) + (dy * dy));
    }


    /**
     * Velocity dx value.
     *
     * @return the dx value
     */
    public double getDX() {
        return this.dx;
    }

    /**
     * Velocity dy value.
     *
     * @return the dy value
     */
    public double getDY() {
        return this.dy;
    }

    /**
     * Apply the speed to a specified point therefore creating movement.
     *
     * @param p Take a point with position (x,y) and return a new point
     * @return return a new point with position (x+dx, y+dy)
     */
    public Point applyToPoint(Point p) {
        double newX = p.getX() + this.dx;
        double newY = p.getY() + this.dy;
        return new Point(newX, newY);
    }
}