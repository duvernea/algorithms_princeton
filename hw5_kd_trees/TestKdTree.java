import static org.junit.Assert.*;
import edu.princeton.cs.algs4.StdOut;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

import java.lang.Iterable;
import java.util.Iterator;

import org.junit.Test;

public class TestKdTree {


	@Test
	public void testCreateInsertKdTree() {

		KdTree kdTree = new KdTree();
		assertTrue("New KdTree created.  Should be empty", kdTree.isEmpty());
		assertEquals("New KdTree. Size should be 0", kdTree.size(), 0);

		Point2D p1 = new Point2D(.5, .5);
		kdTree.insert(p1);
		assertFalse("Point " + p1 + " inserted.  KdTree should not be empty", kdTree.isEmpty());
		assertEquals("1 point inserted, size should be 1", kdTree.size(), 1);

		Point2D p2 = new Point2D(.25, .25);
		kdTree.insert(p2);
		assertEquals("2 points inserted, size should be 2", kdTree.size(), 2);

		Point2D p3 = new Point2D(.9, .9);
		kdTree.insert(p3);
		assertEquals("3 points inserted, size should be 3", kdTree.size(), 3);

		Point2D p4 = new Point2D(.7, .7	);
		kdTree.insert(p4);
		assertEquals("4 points inserted, size should be 4", kdTree.size(), 4);

		Point2D p5 = new Point2D(.6, .7	);
		kdTree.insert(p5);
		assertEquals("5 points inserted, size should be 5", kdTree.size(), 5);

		Point2D p6 = new Point2D(.6, .95	);
		kdTree.insert(p6);
		assertEquals("6 points inserted, size should be 6", kdTree.size(), 6);

		// boolean contain = kdTree.contains(p5);
		// StdOut.println("kdTree contains? " + p5 + " = " + contain);
	}
	@Test
	public void testContainsKdTree() {

		KdTree kdTree = new KdTree();
		assertTrue("New KdTree created.  Should be empty", kdTree.isEmpty());
		assertEquals("New KdTree. Size should be 0", kdTree.size(), 0);

		Point2D p1 = new Point2D(.5, .5);
		kdTree.insert(p1);
		assertFalse("Point " + p1 + " inserted.  KdTree should not be empty", kdTree.isEmpty());
		assertEquals("1 point inserted, size should be 1", kdTree.size(), 1);

		Point2D p2 = new Point2D(.25, .25);
		kdTree.insert(p2);
		assertEquals("2 points inserted, size should be 2", kdTree.size(), 2);

		Point2D p3 = new Point2D(.9, .9);
		kdTree.insert(p3);
		assertEquals("3 points inserted, size should be 3", kdTree.size(), 3);

		Point2D p4 = new Point2D(.7, .7	);
		kdTree.insert(p4);
		assertEquals("4 points inserted, size should be 4", kdTree.size(), 4);

		Point2D p5 = new Point2D(.6, .7	);
		kdTree.insert(p5);
		assertEquals("5 points inserted, size should be 5", kdTree.size(), 5);

		Point2D p6 = new Point2D(.6, .95	);
		kdTree.insert(p6);
		assertEquals("6 points inserted, size should be 6", kdTree.size(), 6);

		boolean contain = kdTree.contains(p5);
		assertTrue("Point " + p5 + " not found with contains()", kdTree.contains(p5));
		assertTrue("Point " + p1 + " not found with contains()", kdTree.contains(p1));

		Point2D p7 = new Point2D(.61, .61);
		assertFalse("Point " + p7 + " should not be found with contains()", kdTree.contains(p7));
	}
	@Test
	public void testSearchClosest() {
		KdTree kdTree = new KdTree();

		Point2D p1 = new Point2D(.5, .5);
		kdTree.insert(p1);

		Point2D p2 = new Point2D(.25, .25);
		kdTree.insert(p2);

		Point2D p3 = new Point2D(.9, .9);
		kdTree.insert(p3);

		Point2D p4 = new Point2D(.7, .7	);
		kdTree.insert(p4);

		Point2D p5 = new Point2D(.6, .7	);
		kdTree.insert(p5);

		Point2D p6 = new Point2D(.6, .95);
		kdTree.insert(p6);

		Point2D checkPoint1 = new Point2D(.1, 0);
		assertEquals("Nearest point to " + checkPoint1 + " incorrect.", kdTree.nearest(checkPoint1), p2);

		Point2D checkPoint2 = new Point2D(.25, .25);
		assertEquals("Nearest point to " + checkPoint2 + " incorrect.", kdTree.nearest(checkPoint2), p2);

		Point2D checkPoint3 = new Point2D(.25, .4);
		assertEquals("Nearest point to " + checkPoint3 + " incorrect.", kdTree.nearest(checkPoint3), p2);

		Point2D checkPoint4 = new Point2D(.49, .5);
		assertEquals("Nearest point to " + checkPoint4 + " incorrect.", kdTree.nearest(checkPoint4), p1);

		Point2D checkPoint5 = new Point2D(.51, .55);
		assertEquals("Nearest point to " + checkPoint5 + " incorrect.", kdTree.nearest(checkPoint5), p1);

		Point2D checkPoint6 = new Point2D(.59, .67);
		assertEquals("Nearest point to " + checkPoint6 + " incorrect.", kdTree.nearest(checkPoint6), p5);

		Point2D checkPoint7 = new Point2D(.66, .7);
		assertEquals("Nearest point to " + checkPoint7 + " incorrect.", kdTree.nearest(checkPoint7), p4);

		Point2D checkPoint8 = new Point2D(.55, .91);;
		assertEquals("Nearest point to " + checkPoint8 + " incorrect.", kdTree.nearest(checkPoint8), p6);

		Point2D checkPoint9 = new Point2D(.92, .85);;
		assertEquals("Nearest point to " + checkPoint9 + " incorrect.", kdTree.nearest(checkPoint9), p3);

		Point2D checkPoint10 = new Point2D(.4, .6);;
		assertEquals("Nearest point to " + checkPoint10 + " incorrect.", kdTree.nearest(checkPoint10), p1);
	}
	@Test
	public void testSearchRange() {
		StdOut.println("\n\ntestSearchRange\n\n");
		KdTree kdTree = new KdTree();

		Point2D p1 = new Point2D(.5, .5);
		kdTree.insert(p1);

		Point2D p2 = new Point2D(.25, .25);
		kdTree.insert(p2);

		Point2D p3 = new Point2D(.9, .9);
		kdTree.insert(p3);

		Point2D p4 = new Point2D(.7, .7	);
		kdTree.insert(p4);

		Point2D p5 = new Point2D(.6, .7	);
		kdTree.insert(p5);

		Point2D p6 = new Point2D(.6, .95);
		kdTree.insert(p6);

		RectHV rect = new RectHV(.3, .3, .7, .7);
		// Iterable<Point2D> rectRange = kdTree.range(rect); 
		// for (Point2D point : kdTree.range(rect)) {
		// 	StdOut.println("Point " + point + " found inside rect " + rect);
		// }
		int numPoints = 0;
		StdOut.println("Testing for range of points in rectangle...");
		for (Point2D point : kdTree.range(rect)) {
			numPoints++;
			assertFalse("Rect should not contain point (.9, .9)", point.equals(p3));
			assertFalse("Rect should not contain point (.7, 1)", point.equals(p6));
		}
		assertEquals("2 points should be contained in the rectangle", numPoints, 3);
	}
}