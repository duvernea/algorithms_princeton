import static org.junit.Assert.*;
import edu.princeton.cs.algs4.StdOut;
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

	}
}

