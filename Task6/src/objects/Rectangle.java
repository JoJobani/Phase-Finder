//Yehonatan Menashe 206478067
package objects;

import java.util.ArrayList;
import java.util.List;

/**
 * The Rectangle object.
 */
public class Rectangle {
    private final double x;
    private final double y;
    private final double width;
    private final double height;

    /**
     * Instantiates a new Rectangle.
     *
     * @param upperLeft the upper left point
     * @param width     the width
     * @param height    the height
     */
// Create a new rectangle with location and width/height.
    public Rectangle(Point upperLeft, double width, double height) {
        this.x = upperLeft.getX();
        this.y = upperLeft.getY();
        this.width = width;
        this.height = height;
    }

    /**
     * Return a (possibly empty) List of intersection points
     * with the specified line.
     *
     * @param line the line
     * @return a list of intersection points
     */
    public List<Point> intersectionPoints(Line line) {
        ArrayList<Point> intersections = new ArrayList<>();
        // Check if the line is entirely outside the rectangle's bounding box.
        if (line.end().getX() < this.getUpperLeft().getX()
                && line.start().getX() < this.getUpperLeft().getX()
                || line.end().getX() > this.getUpperLeft().getX() + this.width
                && line.start().getX() > this.getUpperLeft().getX() + this.width
                || line.end().getY() < this.getUpperLeft().getY()
                && line.start().getY() < this.getUpperLeft().getY()
                || line.end().getY() > this.getUpperLeft().getY() + this.height
                && line.start().getY() > this.getUpperLeft().getY() + this.height) {
            // The line is entirely outside the rectangle's bounding box,
            // so there can be no intersection points.
            return intersections;
        }
        //use helper method to get the four sides of rectangle
        List<Line> sides = fourSides();
        Line top = sides.get(0);
        Line left = sides.get(1);
        Line right = sides.get(2);
        Line bottom = sides.get(3);
        //add intersections to the list if they exist
        if (line.isIntersecting(top)) {
            intersections.add(line.intersectionWith(top));
        }
        if (line.isIntersecting(left)) {
            intersections.add(line.intersectionWith(left));
        }
        if (line.isIntersecting(right)) {
            intersections.add(line.intersectionWith(right));
        }
        if (line.isIntersecting(bottom)) {
            intersections.add(line.intersectionWith(bottom));
        }
        return intersections;
    }

    /**
     * Method to define the four sides of a rectangle as lines.
     *
     * @return a list of the rectangle sides
     */
    public List<Line> fourSides() {
        List<Line> lines = new ArrayList<>();
        //define the rectangle points
        Point upperLeft = this.getUpperLeft();
        Point upperRight = new Point(upperLeft.getX() + this.width,
                upperLeft.getY());
        Point bottomLeft = new Point(upperLeft.getX(), upperLeft.getY()
                + this.height);
        Point bottomRight = new Point(upperRight.getX(), bottomLeft.getY());
        //define the four lines of rectangle to use line intersection methods
        lines.add(new Line(upperLeft, upperRight));
        lines.add(new Line(upperLeft, bottomLeft));
        lines.add(new Line(upperRight, bottomRight));
        lines.add(new Line(bottomLeft, bottomRight));
        return lines;
    }

    /**
     * Get rectangle width.
     *
     * @return the width of the rectangle
     */
    public double getWidth() {
        return width;
    }

    /**
     * Get rectangle height.
     *
     * @return the height of the rectangle
     */
    public double getHeight() {
        return height;
    }

    /**
     * Get the upper left corner of the rectangle.
     *
     * @return the upper left point
     */
// Returns the upper-left point of the rectangle.
    public Point getUpperLeft() {
        return new Point(this.x, this.y);
    }
}