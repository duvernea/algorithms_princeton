import static org.junit.Assert.*;
import edu.princeton.cs.algs4.StdOut;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

import java.lang.Iterable;
import java.util.Iterator;

import org.junit.Test;


public class TestPointSET {

	@Test
	public void testCreateContainsPoints() {
		PointSET pointSet = new PointSET();
		assertTrue("New pointSET created, is empty", pointSet.isEmpty());

		Point2D a = new Point2D(0, 0);
		pointSet.insert(a);
		Point2D b = new Point2D(.5, .5);
		// Duplicate point values
		Point2D c = new Point2D(.5, .5);
		pointSet.insert(b);

		boolean contains = pointSet.contains(b);

		assertTrue("Inserted point a is not 'contained' in the pointSET", pointSet.contains(a));
		assertTrue("Inserted point b is not 'contained' in the pointSET", pointSet.contains(b));
		assertEquals("Set does not contain correct number of points = 2", pointSet.size(), 2);
		assertTrue("pointSET created, is not empty", !pointSet.isEmpty());

		// Try inserting the same point again
		pointSet.insert(c);
		assertEquals("Duplicate point added. Set does not contain correct number of points = 2", pointSet.size(), 2);

		assertFalse("Point should not be contained in pointSET", pointSet.contains(new Point2D(.7, .7)));
	}
	@Test
	public void testRangePoints() {
		PointSET pointSet = new PointSET();
		Point2D a = new Point2D(0, 0);
		Point2D b = new Point2D(.5, .5);
		Point2D c = new Point2D(.7, 1);
		Point2D d = new Point2D(.3, .3);
		pointSet.insert(a);
		pointSet.insert(b);
		pointSet.insert(c);
		pointSet.insert(d);

		RectHV rect = new RectHV(.3, .3, .7, .7);

		// Iterable<Point2D> iterablePoints = pointSet.range(rect);
		// Iterator<Point2D> iterator = iterablePoints.iterator();
		int numPoints = 0;

		StdOut.println("Testing for range of points in rectangle...");
		for (Point2D point : pointSet.range(rect)) {
			numPoints++;
			StdOut.print(point);
			assertFalse("Rect should not contain point (0, 0)", point.equals(a));
			assertFalse("Rect should not contain point (.7, 1)", point.equals(c));

		}
		assertEquals("2 points should be contained in the rectangle", numPoints, 2);






	}
}