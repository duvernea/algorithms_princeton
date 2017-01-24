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

 		if (points == null) {
			throw new java.lang.NullPointerException();
		}

		Arrays.sort(points);

		for (int i = 0; i < points.length; i++) {
			Point origin = points[i];

			Comparator<Point> originComp = origin.slopeOrder();
			Point[] pointsBySlope = new Point[points.length];
			pointsBySlope = Arrays.copyOf(points, points.length);
			Arrays.sort(pointsBySlope, originComp);

			double previousSlope = Double.NEGATIVE_INFINITY;
			int slopeCount = 0;
			for (int j = 0; j < pointsBySlope.length; j++) {
				double currentSlope = origin.slopeTo(pointsBySlope[j]);
				if (currentSlope == previousSlope) {
					slopeCount++;
				} else {
					if (slopeCount >= 3) {
						createLineSegment(pointsBySlope, slopeCount, j);
					}
					slopeCount = 1;
					previousSlope = currentSlope;
				}
				if (j == pointsBySlope.length - 1 && slopeCount >= 3) {
					createLineSegment(pointsBySlope, slopeCount, j+1);
				}
			}
		}
 	}
 	private void createLineSegment(Point[] pointsBySlope, int slopeCount, int j) {
		Point[] subArray = new Point[slopeCount+1];
		subArray[0] = pointsBySlope[0];
		Point origin = subArray[0];
		int k=1;
		for (k=1; k<=slopeCount; k++) {
			int index = j-slopeCount+k-1;
			subArray[k] = pointsBySlope[index];
		}
		Arrays.sort(subArray);
 		if (origin.compareTo(subArray[0]) < 1) {
 			LineSegment segment = new LineSegment(subArray[0], subArray[slopeCount]);
 			mSegmentsList.add(segment);
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