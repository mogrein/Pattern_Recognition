import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

public class Fast {

    private static boolean isPrinted(HashMap<Double, ArrayList<Point>> map
                                     , double slope
                                     , Point[] p) {
        ArrayList<Point> array = map.get(slope);
        if (array != null && array.contains(p[0])) {
            return true;
        } else {
            if (array == null) {
                array = new ArrayList<Point>(p.length);
                Collections.addAll(array, p);
                map.put(slope, array);
            } else {
                Collections.addAll(array, p);
            }
            return false;
        }
    }

    public static void main(String[] args) {

        // rescale coordinates and turn on animation mode
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        StdDraw.show(0);
        StdDraw.setPenRadius(0.01);  // make the points a bit larger

        // read in the input
        String filename = args[0];
        In in = new In(filename);
        int N = in.readInt();
        Point[] points = new Point[N];
        //ArrayList<Point> sortedPoints = new ArrayList<Point>(N);
        for (int i = 0; i < N; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
            points[i].draw();
            //sortedPoints.add(points[i]);
        }

        HashMap<Double, ArrayList<Point>> printedPts
                = new HashMap<Double, ArrayList<Point>>();

        for (int i = 0; i < N; ++i) {
            Arrays.sort(points, i + 1, N, points[i].SLOPE_ORDER);
            int newj;
            x: for (int j = i + 1; j < N; j = newj) {
                double currentSlope = points[i].slopeTo(points[j]);
                double newSlope;
                int endIndex = j;
                y: for (int k = j + 1; k < N; ++k) {
                    newSlope = points[i].slopeTo(points[k]);
                    if (newSlope != currentSlope) break y;
                    else endIndex = k;
                }
                // if i-th + all from j-th to end >= 4 elements
                if (endIndex - j >= 2) {
                    Point[] tmp = new Point[endIndex - j + 2];
                    tmp[0] = points[i];
                    for (int k = 1; k < tmp.length; ++k)
                        tmp[k] = points[j + k - 1];

                    //already drawn?
                    if (isPrinted(printedPts, currentSlope, tmp)) {
                        newj = endIndex + 1;
                        continue x;
                    }
                    Arrays.sort(tmp);
                    tmp[0].drawTo(tmp[tmp.length - 1]);
                    for (int k = 0; k < tmp.length - 1; ++k)
                        StdOut.print(tmp[k] + " -> ");
                    StdOut.printf("%s%n", tmp[tmp.length - 1]);
                }
                newj = endIndex + 1;
            }
        }

        // display to screen all at once
        StdDraw.show(0);

        // reset the pen radius
        StdDraw.setPenRadius();
    }
}
