/*************************************************************************
 * Name:
 * Email:
 *
 * Compilation:  javac Point.java
 * Execution:
 * Dependencies: StdDraw.java
 *
 * Description: An immutable data type for points in the plane.
 *
 *************************************************************************/

import java.util.ArrayList;
import java.util.Comparator;

public class Point implements Comparable<Point> {

    // compare points by slope
    public final Comparator<Point> SLOPE_ORDER = new SlopeOrder();

    private final int x;                              // x coordinate
    private final int y;                              // y coordinate

    // create the point (x, y)
    public Point(int x, int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
    }

    // plot this point to standard drawing
    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    // draw line between this point and that point to standard drawing
    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    // slope between this point and that point
    public double slopeTo(Point that) {
        /* YOUR CODE HERE */
        if (x == that.x) {
            if (y == that.y)
                return Double.NEGATIVE_INFINITY;
            else
                return Double.POSITIVE_INFINITY;
        } else if (y == that.y)
            return +0.0;
        else
            return (double) (that.y - y) / (that.x - x);
    }

    // is this point lexicographically smaller than that one?
    // comparing y-coordinates and breaking ties by x-coordinates
    public int compareTo(Point that) {
        /* YOUR CODE HERE */
        if ((y < that.y)
                || (y == that.y && x < that.x))
            return -1;
        if ((y > that.y)
                || (y == that.y && x > that.x))
            return 1;
        return 0;
    }

    // return string representation of this point
    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }

    private class SlopeOrder implements Comparator<Point> {
        public int compare(Point p1, Point p2) {
            double tmp = slopeTo(p1) - slopeTo(p2);
            if (tmp > 0)
                return 1;
            if (tmp < 0)
                return -1;
            return 0;
        }
    }

    // unit test
    public static void main(String[] args) {
        Point p1 = new Point(1, 2),
              p2 = new Point(2, 2),
              p3 = new Point(1, 3);
        System.out.println("Slope "+ p1 +" to "+ p2 +" = "+ p1.slopeTo(p2));
        System.out.println("Slope "+ p1 +" to "+ p3 +" = "+ p1.slopeTo(p3));
        System.out.println("Slope "+ p1 +" to "+ p1 +" = "+ p1.slopeTo(p1));
        ArrayList<Point> a = new ArrayList<Point>();
        a.add(p1);
        a.add(p2);
        a.add(p3);
        a.add(p1);
        //a.sort(p1.SLOPE_ORDER);
        for (Point q : a) {
            System.out.print(q + " ");
        }
    }
}