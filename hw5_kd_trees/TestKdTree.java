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
}