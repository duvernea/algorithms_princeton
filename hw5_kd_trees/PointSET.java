import java.util.TreeSet;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdOut;
import java.util.Stack;

import org.junit.runner.JUnitCore;


public class PointSET {

	TreeSet<Point2D> treeSet;

	// construct an empty set of points 
	public PointSET() {
		treeSet = new TreeSet<Point2D>();

	}
	// is the set empty? 
	public boolean isEmpty() {
		return false;
	}
	// number of points in the set 
	public int size() {
		return treeSet.size();
	}

	// add the point to the set (if it is not already in the set)
	public void insert(Point2D p) {
		if (!treeSet.contains(p)) {
			treeSet.add(p);
		}

	}
	// does the set contain point p?
	public boolean contains(Point2D p) {
		return treeSet.contains(p);
	}
	// draw all points to standard draw 
	public void draw() {

	}
	// all points that are inside the rectangle 
	public Iterable<Point2D> range(RectHV rect) {
		for (Point2D point : treeSet) {
			StdOut.println(point);
		}
		return new Stack<Point2D>();
	}
	// a nearest neighbor in the set to point p; null if the set is empty 
	public Point2D nearest(Point2D p) {
		return new Point2D(0, 0);
	}
	// unit testing of the methods (optional) 
	public static void main(String[] args) {

	JUnitCore.main("TestPointSET");

	}
}