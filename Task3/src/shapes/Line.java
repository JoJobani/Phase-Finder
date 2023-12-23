//Yehonatan Menashe 206478067
package shapes;

import java.util.List;

/**
 * The type class Line defined by start and end points.
 */
public class Line {
    private static final double EPSILON = 0.00000001;
    private static final double LINE_ON_LINE = 0;
    private static final double CLOCKWISE = 1;
    private static final double COUNTER_CLOCKWISE = 2;
    private final Point start;
    private final Point end;

    /**
     * New line constructor - receiving start and end point through Point class.
     *
     * @param start the starting point
     * @param end   the end point
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * New line constructor - receiving start and end points manually.
     *
     * @param x1 the x value of start point
     * @param y1 the y value of start point
     * @param x2 the x value of end point
     * @param y2 the y value of end point
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * Calculate the length of our line.
     *
     * @return the length of the line
     */
    public double length() {
        return Math.sqrt((start.getX() - end.getX())
                * (start.getX() - end.getX()) + (start.getY() - end.getY())
                * (start.getY() - end.getY()));
    }

    /**
     * Middle point finder.
     *
     * @return the middle point of the line
     */
    public Point middle() {
        double xMid = ((start.getX() + end.getX()) / 2);
        double yMid = ((start.getY() + end.getY()) / 2);
        return new Point(xMid, yMid);
    }

    /**
     * Start point.
     *
     * @return the start point of the line
     */
    public Point start() {
        return this.start;
    }

    /**
     * End point.
     *
     * @return the end point of the line
     */
    public Point end() {
        return this.end;
    }

    /**
     * Find if two lines are intersecting or not.
     * (method remade from scratch for task 3 for easier debugging)
     *
     * @param other the other line
     * @return true if the lines intersect, false otherwise
     */
    public boolean isIntersecting(Line other) {
        double dir1 = direction(this.start, this.end, other.start);
        double dir2 = direction(this.start, this.end, other.end);
        double dir3 = direction(other.start, other.end, this.start);
        double dir4 = direction(other.start, other.end, this.end);
        // general case
        if ((Math.abs(dir1 - dir2) > EPSILON)
                && (Math.abs(dir3 - dir4) > EPSILON)) {
            return true;
        }
        //edge cases for intersections and final return for no intersections
        return (dir1 == LINE_ON_LINE
                && onSegment(this.start, other.start, this.end))
                || (dir2 == LINE_ON_LINE
                && onSegment(this.start, other.end, this.end))
                || (dir3 == LINE_ON_LINE
                && onSegment(other.start, this.start, other.end))
                || (dir4 == LINE_ON_LINE
                && onSegment(other.start, this.end, other.end));
    }

    //helper method - is the point p2 on the p1p3 line.
    private boolean onSegment(Point p1, Point p2, Point p3) {
        return (p2.getX() <= Math.max(p1.getX(), p3.getX()) + EPSILON
                && p2.getX() >= Math.min(p1.getX(), p3.getX()) - EPSILON
                && p2.getY() <= Math.max(p1.getY(), p3.getY()) + EPSILON
                && p2.getY() >= Math.min(p1.getY(), p3.getY()) - EPSILON);
    }

    //helper method - find the orientation of three given points
    private double direction(Point p1, Point p2, Point p3) {
        double result = (p2.getY() - p1.getY()) * (p3.getX() - p2.getX())
                - (p2.getX() - p1.getX()) * (p3.getY() - p2.getY());
        if (Math.abs(result) < EPSILON) {
            return LINE_ON_LINE;
        } else if (result > 0) {
            return CLOCKWISE;
        } else {
            return COUNTER_CLOCKWISE;
        }
    }

    /**
     * Intersection point finder.
     * (rebuilt and simplified for easier debugging)
     *
     * @param other the other line
     * @return the intersection point if the lines intersect, or null otherwise
     */
    public Point intersectionWith(Line other) {
        //first test if lines are intersecting at all
        boolean intersecting = isIntersecting(other);
        if (!intersecting) {
            return null;
        }
        //check if it's the same line
        if (this.equals(other)) {
            return null;
        }
        //check if lines are both intersecting and parallel to Y
        if ((this.start.getX() == this.end.getX())
                && (other.start.getX() == other.end.getX())) {
            return null;
        }
        //edge case - this line is parallel to Y
        if (this.start.getX() == this.end.getX()) {
            double m2 = (other.end.getY() - other.start.getY())
                    / (other.end.getX() - other.start.getX());
            double b2 = other.start.getY() - m2 * other.start.getX();
            double midY = m2 * this.start.getX() + b2;
            return new Point(this.start.getX(), midY);
        }
        //edge case - other line is parallel to Y
        if (other.start.getX() == other.end.getX()) {
            double m1 = (this.end.getY() - this.start.getY())
                    / (this.end.getX() - this.start.getX());
            double b1 = this.start.getY() - m1 * this.start.getX();
            double midY = m1 * other.start.getX() + b1;
            return new Point(other.start.getX(), midY);
        }
        //general case
        double m1 = (this.end.getY() - this.start.getY())
                / (this.end.getX() - this.start.getX());
        double m2 = (other.end.getY() - other.start.getY())
                / (other.end.getX() - other.start.getX());
        //edge case - lines are parallel to each other
        if (m1 == m2) {
            return null;
        }
        //now we find the b value of each line
        double b1 = this.start.getY() - m1 * this.start.getX();
        double b2 = other.start.getY() - m2 * other.start.getX();
        //and now the point itself
        double midX = ((b2 - b1) / (m1 - m2));
        double midY = ((m1 * midX) + b1);
        return new Point(midX, midY);
    }

    /**
     * Check to see if the two lines are the same line.
     *
     * @param other the other line
     * @return true is the lines are equal, false otherwise
     */
    public boolean equals(Line other) {
        return ((this.start.equals(other.start)
                && (this.end.equals(other.end)))
                || ((this.start.equals(other.end)
                && (this.end.equals(other.start)))));
    }


    /**
     * Find the closest intersection point to start of the line.
     *
     * @param rect the rectangle
     * @return the closest intersection point (if exists, null otherwise)
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        Point closestPoint = null;
        //use rectangle intersection points list method
        List<Point> intList = rect.intersectionPoints(this);
        //if list is empty there is no intersection
        for (Point point : intList) {
            if (point == null) {
                return closestPoint;
            }
            //if closest point doesn't have a value, give it one
            if (intList.size() != 0 && closestPoint == null) {
                closestPoint = point;
                //if a closer intersection point is found, replace current one
            } else if (this.start.distance(closestPoint)
                    > this.start.distance(point)) {
                closestPoint = point;
            }
        }
        return closestPoint;
    }
}