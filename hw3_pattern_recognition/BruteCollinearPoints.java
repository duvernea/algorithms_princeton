import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.In;

import java.util.Arrays;
import java.util.ArrayList;

import org.junit.runner.JUnitCore;


public class BruteCollinearPoints {

	private ArrayList<LineSegment> mSegmentsList;

	// finds all line segments containing 4 points
	public BruteCollinearPoints(Point[] points)  {
		if (points == null) {
			throw new java.lang.NullPointerException();
		}
		Point[] collinearPoints = new Point[4];
		mSegmentsList = new ArrayList<LineSegment>();
		int i, j, k, l;
		for (i = 0; i < points.length - 3; i++ ) {
			for (j = i+1; j<points.length - 2; j++) {
				for (k = j+1; k<points.length - 1; k++) {
					for (l = k+1; l<points.length; l++) {
						if (points[i] != null && points[j] != null && points[k] != null & points[l] != null) {
							double slope1 = points[i].slopeTo(points[j]);
							double slope2 = points[i].slopeTo(points[k]);
							double slope3 = points[i].slopeTo(points[l]);
							if (slope1 == Double.NEGATIVE_INFINITY || slope2 == Double.NEGATIVE_INFINITY || slope3 == Double.NEGATIVE_INFINITY) {
								throw new java.lang.IllegalArgumentException();
							}
							if (slope1 == slope2 && slope1 == slope3) {
								collinearPoints[0] = points[i];
								collinearPoints[1] = points[j];
								collinearPoints[2] = points[k];
								collinearPoints[3] = points[l];
								Arrays.sort(collinearPoints);
								LineSegment segment = new LineSegment(collinearPoints[0], collinearPoints[3]);
								mSegmentsList.add(segment);
							}
						} else {
							throw new java.lang.NullPointerException();
						}
					}
				}
			}
		}
   	}

 	// the number of line segments
	public int numberOfSegments() {
		return mSegmentsList.size();
	}

	// the line segments
	public LineSegment[] segments() {
		LineSegment[] seg = new LineSegment[mSegmentsList.size()];
		seg = mSegmentsList.toArray(seg);
		return seg;
	}

	public static void main(String[] args) {

		In in = new In(args[0]);
		int n = in.readInt();
		Point[] points = new Point[n];
		for (int i = 0; i < n; i++) {
		    int x = in.readInt();
		    int y = in.readInt();
		    points[i] = new Point(x, y);
		}

		// draw the points
		StdDraw.enableDoubleBuffering();
		StdDraw.setXscale(0, 32768);
		StdDraw.setYscale(0, 32768);
		for (Point p : points) {
		    p.draw();
		}
		StdDraw.show();

		// print and draw the line segments
		BruteCollinearPoints collinear = new BruteCollinearPoints(points);
		for (LineSegment segment : collinear.segments()) {
		    StdOut.println(segment);
		    segment.draw();
		}
		StdDraw.show();

		// JUnitCore.main("TestBruteCollinearPoints");

	}
}