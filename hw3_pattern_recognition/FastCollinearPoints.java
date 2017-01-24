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

 	// 	for (Point p : points) {
		//     // StdOut.println("Unsorted " + p.toString());
		// }
 		if (points == null) {
			throw new java.lang.NullPointerException();
		}
		Arrays.sort(points);
		// for (Point p : points) {
		//     // StdOut.println("Sorted " + p.toString());
		// }

		for (int i=0; i<points.length; i++) {
			Point origin = points[i];
			StdOut.println("NEW ORIGIN: " + points[i]);

			Comparator<Point> originComp = origin.slopeOrder();
			Point[] pointsBySlope = new Point[points.length-i];
			pointsBySlope = Arrays.copyOfRange(points, i, points.length);
			Arrays.sort(pointsBySlope, originComp);
			// for (Point point : pointsBySlope) {
			// 	// StdOut.println("points sorted by slope: " + point + " Slope: " + origin.slopeTo(point));
			// }
			// The first item in the sorted array will be the point origin itself
			// The slope to the first item is Negative Infinity
			// StdOut.println("For point in array " + i);
			double previousSlope = Double.NEGATIVE_INFINITY;
			int slopeCount = 0;
			for (int j = 1; j<pointsBySlope.length; j++) {
				// is current point less than all points on line
				StdOut.println(" origin.compareTo(pointsBySlope[j]: " + origin.compareTo(pointsBySlope[j]));

				double currentSlope = origin.slopeTo(pointsBySlope[j]);
				StdOut.println("pointsBySlope Current point: " + pointsBySlope[j]);
				// StdOut.println("Current slope after sorting: " + currentSlope);
				if (currentSlope == previousSlope) {
					slopeCount++;
					// StdOut.println("Slopes equal");
					// StdOut.println("Previous slope: " + previousSlope);
					// StdOut.println("Current slope: " + currentSlope);
					// StdOut.println("Collinear point " + pointsBySlope[j] + "Slopecount: " + slopeCount);
				} else {
					// StdOut.println("Slopes NOT equal");
					// StdOut.println("Previous slope: " + previousSlope);
					// StdOut.println("Current slope: " + currentSlope);
					if (slopeCount >= 3) {
						// need to log this as a point
						createLineSegment(pointsBySlope, slopeCount, j);
					}
					slopeCount = 1;
					previousSlope = currentSlope;
				}
				if (j == pointsBySlope.length-1 && slopeCount >= 3) {
					// StdOut.println("SlopeCount >= 3 and last element in the pointsBySlope array");
					createLineSegment(pointsBySlope, slopeCount, j+1);
				}
			}
		}
 	}
 	private void createLineSegment(Point[] pointsBySlope, int slopeCount, int j) {
 		// StdOut.println("slopeCount: " + slopeCount);
		Point[] subArray = new Point[slopeCount+1];
		subArray[0] = pointsBySlope[0];
		Point origin = subArray[0];
		StdOut.println("origin point 1: " + origin);
		int k=1;
		for (k=1; k<=slopeCount; k++) {
			int index = j-slopeCount+k-1;
			subArray[k] = pointsBySlope[index];
			// StdOut.println("subArray[" + k + "] = " + subArray[k]);
		}
		Arrays.sort(subArray);
		StdOut.println("origin compare to sorted [0] element: " + origin.compareTo(subArray[0]));
		StdOut.println("origin point 2: " + origin);
 		// StdOut.println("Creating LineSegment from " + subArray[0] + " to " + subArray[slopeCount]);
 		LineSegment segment = new LineSegment(subArray[0], subArray[slopeCount]);
		mSegmentsList.add(segment);
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