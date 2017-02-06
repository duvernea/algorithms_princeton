import static org.junit.Assert.*;
import edu.princeton.cs.algs4.StdOut;

import edu.princeton.cs.algs4.Point2D;

import org.junit.Test;


public class TestPointSET {

	@Test
	public void testCreateContainsPoints() {
		PointSET pointSet = new PointSET();
		Point2D a = new Point2D(0, 0);
		pointSet.insert(a);
		Point2D b = new Point2D(.5, .5);
		pointSet.insert(b);

		boolean contains = pointSet.contains(b);

		assertTrue("Inserted point a is not 'contained' in the pointSET", pointSet.contains(a));
		assertTrue("Inserted point b is not 'contained' in the pointSET", pointSet.contains(b));
		assertFalse("Point should not be contained in pointSET", pointSet.contains(new Point2D(.7, .7)));


	}
}