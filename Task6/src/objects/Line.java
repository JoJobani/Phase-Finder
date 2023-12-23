//Yehonatan Menashe 206478067
package objects;

import java.util.List;

/**
 * The type class Line defined by start and end points.
 */
public class Line {
    private static final double EPSILON = 0.00000001;
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
        double x1 = start.getX();
        double y1 = start.getY();
        double x2 = end.getX();
        double y2 = end.getY();
        double x3 = other.start().getX();
        double y3 = other.start().getY();
        double x4 = other.end().getX();
        double y4 = other.end().getY();

        double denominator = (x1 - x2) * (y3 - y4) - (y1 - y2) * (x3 - x4);

        if (Math.abs(denominator) < EPSILON) {
            // Lines are parallel or coincident
            return false;
        }

        double t = ((x1 - x3) * (y3 - y4) - (y1 - y3) * (x3 - x4)) / denominator;
        double u = -((x1 - x2) * (y1 - y3) - (y1 - y2) * (x1 - x3)) / denominator;

        return t >= 0 && t <= 1 && u >= 0 && u <= 1;
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