public class Brute {
    private static boolean isCollinear(Point p1, Point p2, Point p3) {
        //double tmp = p1.slopeTo(p2) - p1.slopeTo(p3);
        //return (tmp < 1e-10) && (tmp > -1e-10);
        return (p1.slopeTo(p2) == p1.slopeTo(p3));
    }
    private static Point getMaxPoint(Point p1, Point... points) {
        Point pmax = p1;
        for (Point p : points) {
            if (pmax.compareTo(p) < 0) pmax = p;
        }
        return pmax;
    }

    private static Point getMinPoint(Point p1, Point... points) {
        Point pmin = p1;
        for (Point p : points) {
            if (p.compareTo(pmin) < 0) pmin = p;
        }
        return pmin;
    }

    private static void drawAndPrint(Point... p) {
        //standart 5comparisons sorting algorithm
        assert p.length >= 4;
        if (p[1].compareTo(p[0]) > 0) swap(p, 0, 1);
        if (p[3].compareTo(p[2]) > 0) swap(p, 2, 3);
        if (p[3].compareTo(p[1]) > 0) swap(p, 1, 3);
        if (p[2].compareTo(p[0]) > 0) swap(p, 0, 2);
        if (p[2].compareTo(p[1]) > 0) swap(p, 1, 2);
        p[0].drawTo(p[3]);

        StdOut.printf("%s -> %s -> %s -> %s%n"
                , p[0], p[1], p[2], p[3]);
    }

    private static void swap(Point[] p, int i, int j) {
        Point tmp = p[i];
        p[i] = p[j];
        p[j] = tmp;
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
        for (int i = 0; i < N; ++i) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
            points[i].draw();
        }

        for (int i = 0; i < N; ++i) {
            /*x:*/ for (int j = i + 1; j < N; ++j) {
                for (int k = j + 1; k < N; ++k) {
                    if (isCollinear(points[i], points[j], points[k])) {
                        for (int l = k + 1; l < N; ++l) {
                            if (isCollinear(points[i], points[j], points[l])) {
                                Point tmp1 = points[i],
                                      tmp2 = points[j],
                                      tmp3 = points[k],
                                      tmp4 = points[l];

                                drawAndPrint(tmp1, tmp2, tmp3, tmp4);
                            }
                        }
                        //continue x;
                    }
                }
            }
        }


        // display to screen all at once
        StdDraw.show(0);

        // reset the pen radius
        StdDraw.setPenRadius();
    }
}
