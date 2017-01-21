import static org.junit.Assert.*;
import org.junit.Test;
import edu.princeton.cs.algs4.StdOut;

public class TestBruteCollinearPoints {

	@Test
	public void testCreate() {
		Point[] myPoints = new Point[2];
		myPoints[0] = new Point(1, 1);
		myPoints[1] = new Point(3, 4);
		BruteCollinearPoints bcp = new BruteCollinearPoints(myPoints);
	}
	
}