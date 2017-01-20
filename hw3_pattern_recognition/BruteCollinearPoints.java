import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.In;

import java.util.Arrays;
import java.util.ArrayList;

import org.junit.runner.JUnitCore;


public class BruteCollinearPoints {

	private Point[] mPoints;
	// private int mNumSegments = 0;
	private ArrayList<LineSegment> mSegmentsList;


	// Write a program BruteCollinearPoints.java that examines 4 points at a time 
	// and checks whether they all lie on the same line segment, 
	// returning all such line segments. 
	// To check whether the 4 points p, q, r, and s are collinear, 
	// check whether the three slopes between p and q, between p and r, and between p and s are all equal.


	// finds all line segments containing 4 points
	public BruteCollinearPoints(Point[] points)  {
		Point[] collinearPoints = new Point[4];
		mSegmentsList = new ArrayList<LineSegment>();
		// mPoints = points;
		int i, j, k, l;
		for (i = 0; i < points.length - 3; i++ ) {
			for (j = i+1; j<points.length - 2; j++) {
				for (k = j+1; k<points.length - 1; k++) {
					for (l = k+1; l<points.length; l++) {
						double slope1 = points[i].slopeTo(points[j]);
						double slope2 = points[i].slopeTo(points[k]);
						double slope3 = points[i].slopeTo(points[l]);
						if (slope1 == slope2 && slope1 == slope3) {
							collinearPoints[0] = points[i];
							collinearPoints[1] = points[j];
							collinearPoints[2] = points[k];
							collinearPoints[3] = points[l];
							Arrays.sort(collinearPoints);
							// StdOut.println("Found 4 collinear points");
							// StdOut.println("Point 1: " + points[i]);
							// StdOut.println("Point 2: " + points[j]);
							// StdOut.println("Point 3: " + points[k]);
							// StdOut.println("Point 4: " + points[l]);
							// StdOut.println("First point: " + collinearPoints[0]);
							// StdOut.println("Last point: " + collinearPoints[3]);
							LineSegment segment = new LineSegment(collinearPoints[0], collinearPoints[3]);
							mSegmentsList.add(segment);
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