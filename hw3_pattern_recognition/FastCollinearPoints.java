import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.In;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Comparator;

//import org.junit.runner.JUnitCore;

public class FastCollinearPoints {

	private ArrayList<LineSegment> mSegmentsList;

	// finds all line segments containing 4 or more points
 	public FastCollinearPoints(Point[] points) {

 		boolean duplicates = checkForDuplicatePoints(points);
		if (duplicates) {
			throw new java.lang.IllegalArgumentException();
		}

 		mSegmentsList = new ArrayList<LineSegment>();



 		for (Point p : points) {
		    // StdOut.println("Unsorted " + p.toString());
		}
 		if (points == null) {
			throw new java.lang.NullPointerException();
		}
		Arrays.sort(points);
		for (Point p : points) {
		    // StdOut.println("Sorted " + p.toString());
		}

		for (int i=0; i<points.length; i++) {
			Point origin = points[i];

			Comparator<Point> originComp = origin.slopeOrder();
			Point[] pointsBySlope = new Point[points.length-i];
			// this is copying ref, want a new copy
			// int[] b = Arrays.copyOf(a, a.length);
			pointsBySlope = Arrays.copyOfRange(points, i, points.length);
			// pointsBySlope = points;
			Arrays.sort(pointsBySlope, originComp);
			// The first item in the sorted array will be the point origin itself
			// The slope to the first item is Negative Infinity
			// StdOut.println("For point in array " + i);
			double previousSlope = Double.NEGATIVE_INFINITY;
			int slopeCount = 0;
			for (int j = 1; j<pointsBySlope.length; j++) {
				double currentSlope = origin.slopeTo(pointsBySlope[j]);
				// StdOut.println("Current slope after sorting: " + currentSlope);
				if (currentSlope == previousSlope) {
					slopeCount++;
					// StdOut.println("Collinear point " + pointsBySlope[j]);
					if (slopeCount > 2) {
						// StdOut.println("slopeCount: " + slopeCount);
						// StdOut.println("4 COLLINEAR POINTS FOUND" + pointsBySlope[0] + " " +
						// 	pointsBySlope[j-2] + " " + pointsBySlope[j-1] + " " + pointsBySlope[j]);
						Point[] subArray = new Point[4];
						subArray[0] = pointsBySlope[0];
						subArray[1] = pointsBySlope[j-2];
						subArray[2] = pointsBySlope[j-1];
						subArray[3] = pointsBySlope[j];
						Arrays.sort(subArray);
						// StdOut.println("Creating LineSegment from " + subArray[0] + " to " + subArray[3]);
						LineSegment segment = new LineSegment(subArray[0], subArray[3]);
						mSegmentsList.add(segment);
					}
				} else {
					slopeCount = 1;
					previousSlope = currentSlope;
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
		// temp
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

		FastCollinearPoints fcp = new FastCollinearPoints(points);

		for (LineSegment segment : fcp.segments()) {
		    StdOut.println(segment);
		    segment.draw();
		}
		StdDraw.show();

	}
}