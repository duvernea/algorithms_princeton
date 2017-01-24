import static org.junit.Assert.*;
import edu.princeton.cs.algs4.StdOut;
import java.util.Comparator;
import java.util.Arrays;

import org.junit.Test;

public class TestPoint {
	@Test
	public void testPointCreateCompare() {
		Point myPoint1 = new Point(3, 4);
		Point myPoint2 = new Point(1, 1);
		Point myPoint3 = new Point(3, 3);
		Point myPoint4 = new Point(1, 1);
		Point myPoint5 = new Point(-5, 1);

		assertEquals("Point (3, 4) compareTo (1, 1) should be 1", myPoint1.compareTo(myPoint2), 1);
		assertEquals("Point (1, 1) compareTo (3, 4) should be -1", myPoint2.compareTo(myPoint1), -1);
		assertEquals("Point (3, 3) compareTo (3, 4) should be -1", myPoint2.compareTo(myPoint1), -1);
		assertEquals("Point (3, 4) compareTo (3, 3) should be 1", myPoint1.compareTo(myPoint3), 1);
		assertEquals("Point (1, 1) compareTo (1, 1) should be 0", myPoint2.compareTo(myPoint4), 0);
		assertEquals("Point (-5, 1) compareTo (1, 1) should be -1", myPoint5.compareTo(myPoint2), -1);

		Point myPoint6 = new Point(64, 496);
		Point myPoint7 = new Point(64, 496);
		StdOut.println("Compare (64, 496) to self: " + myPoint6.compareTo(myPoint7));
	}
	@Test
	public void testPointVerticalSlope() {

 		Point point1 = new Point(4096, 22016);
 		Point point2 = new Point(4096, 23040);
 		StdOut.println("Slope from " + point1 + " to " + point2 + " is " + point1.slopeTo(point2));
	}
	@Test
	public void testPointSlope() {
		double epsilon = .001;

		Point myPoint1 = new Point(2, 2);
		Point myPoint2 = new Point(2, 4);
		Point myPoint3 = new Point(0, 2);
		Point myPoint4 = new Point(2, 4);
		Point myPoint5 = new Point(6, 20);
		Point myPoint6 = new Point(0, 0);
		Point myPoint7 = new Point(10, 1);
		Point myPoint8 = new Point(150, 2);

		assertEquals("Point (2, 2) slopeTo (2, 4) should be Positive Infinity", 
			myPoint1.slopeTo(myPoint2), Double.POSITIVE_INFINITY, epsilon);;

		assertEquals("Point (2, 2) slopeTo (0, 2) should be 0", 
			myPoint1.slopeTo(myPoint3), 0, epsilon);

		assertEquals("Point (2, 4) slopeTo (2, 4) should be Negative Infinity", 
			myPoint2.slopeTo(myPoint4), Double.NEGATIVE_INFINITY, epsilon);

		assertEquals("Point (2, 4) slopeTo (6, 20) should be 4", 
			myPoint2.slopeTo(myPoint5), 4, epsilon);

		assertEquals("Point (0, 0) slopeTo (10, 1) should be .1", 
			myPoint6.slopeTo(myPoint7), .1, epsilon);

		assertEquals("Point (0, 0) slopeTo (150, 2) should be .1", 
			myPoint6.slopeTo(myPoint8), .0133, epsilon);

	}
	@Test 
	public void testPointSlopeOrder() {
		Point[] myPoints = new Point[3];

		Point origin = new Point(0, 0);
		myPoints[0] = new Point(1, 5);
		myPoints[1] = new Point(5, 1);
		myPoints[2] = new Point(10, 1);

		Comparator<Point> originComp = origin.slopeOrder();


		Arrays.sort(myPoints, originComp);

		StdOut.println("Array Points sorted by slope to origin...");
		StdOut.println("Array Point 0: " + myPoints[0].toString());
		StdOut.println("Array Point 1: " + myPoints[1].toString());
		StdOut.println("Array Point 2: " + myPoints[2].toString());


	}
}

