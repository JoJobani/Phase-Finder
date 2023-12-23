//Yehonatan Menashe 206478067
package objects;

/**
 * Point class with X and Y value.
 */
public class Point {
    private final double xCoordinate;
    private final double yCoordinate;
    private static final double EPSILON = 0.00001;

    /**
     * Instantiates a new Point (the constructor).
     *
     * @param x the x value of the point
     * @param y the y value of the point
     */
    public Point(double x, double y) {
        this.xCoordinate = x;
        this.yCoordinate = y;
    }

    /**
     * Distance double.
     *
     * @param other the point from which distance we want to measure
     * @return the distance of this point to the other point
     */
    public double distance(Point other) {
        //Math.hypot returns the square root of the sum of squares of the args
        return Math.hypot(this.xCoordinate - other.xCoordinate,
                this.yCoordinate - other.yCoordinate);
    }

    /**
     * Equals boolean.
     *
     * @param other the other point
     * @return true is the points are equal, false otherwise
     */
    public boolean equals(Point other) {
        return Math.abs(this.xCoordinate - other.xCoordinate) <= EPSILON
                && Math.abs(this.yCoordinate - other.yCoordinate) <= EPSILON;
    }

    /**
     * Get the x value of the point.
     *
     * @return the x value
     */
    public double getX() {
        return this.xCoordinate;
    }

    /**
     * Gets y value of the point.
     *
     * @return the y value
     */
    public double getY() {
        return this.yCoordinate;
    }
}