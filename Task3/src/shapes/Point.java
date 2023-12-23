//Yehonatan Menashe 206478067
package shapes;

/**
 * Point class with X and Y value.
 */
public class Point {
    private double x;
    private double y;
    private static final double EPSILON = 0.00001;

    /**
     * Instantiates a new Point (the constructor).
     *
     * @param x the x value of the point
     * @param y the y value of the point
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Distance double.
     *
     * @param other the point from which distance we want to measure
     * @return the distance of this point to the other point
     */
    public double distance(Point other) {
        double distance;
        distance = Math.sqrt((this.x - other.x) * (this.x - other.x)
                + (this.y - other.y) * (this.y - other.y));
        return distance;
    }

    /**
     * Equals boolean.
     *
     * @param other the other point
     * @return true is the points are equal, false otherwise
     */
    public boolean equals(Point other) {
        return (((this.x - other.x <= EPSILON) && (other.x - this.x <= EPSILON))
                && ((this.y - other.y <= EPSILON)
                && (other.y - this.y <= EPSILON)));
    }

    /**
     * Get the x value of the point.
     *
     * @return the x value
     */
    public double getX() {
        return this.x;
    }

    /**
     * Gets y value of the point.
     *
     * @return the y value
     */
    public double getY() {
        return this.y;
    }
}