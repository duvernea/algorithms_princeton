import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.In;

import java.util.Arrays;
import java.util.ArrayList;

// import org.junit.runner.JUnitCore;


public class BruteCollinearPoints {

	private ArrayList<LineSegment> mSegmentsList;

	// finds all line segments containing 4 points
	public BruteCollinearPoints(Point[] points)  {
		if (points == null) {
			throw new java.lang.NullPointerException();
		}
		boolean duplicates = checkForDuplicatePoints(points);
		if (duplicates) {
			throw new java.lang.IllegalArgumentException();
		}

		Point[] collinearPoints = new Point[4];
		mSegmentsList = new ArrayList<LineSegment>();
		int i, j, k, l;
		for (i = 0; i < points.length - 3; i++ ) {
			for (j = i+1; j<points.length - 2; j++) {
					double slope1 = points[i].slopeTo(points[j]);
				for (k = j+1; k<points.length - 1; k++) {
						double slope2 = points[i].slopeTo(points[k]);
					for (l = k+1; l<points.length; l++) {
						if (points[i] != null && points[j] != null && points[k] != null && points[l] != null) {
							double slope3 = points[i].slopeTo(points[l]);
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
   	private boolean checkForDuplicatePoints(Point[] points) {
   		Point[] sortedPoints = new Point[points.length];
		sortedPoints = Arrays.copyOf(points, points.length);
		Arrays.sort(sortedPoints);
		for (int i=0; i<sortedPoints.length-1; i++) {
			int compare = sortedPoints[i].compareTo(sortedPoints[i+1]);
			if (compare == 0) {
				return true;
			}
		}
		return false;
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