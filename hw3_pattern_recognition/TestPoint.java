import static org.junit.Assert.*;
import edu.princeton.cs.algs4.StdOut;
import org.junit.Test;

public class TestPoint {
	@Test
	public void testPointCreate() {
		Point myPoint = new Point(3, 4);
		myPoint.draw();
	}
}

