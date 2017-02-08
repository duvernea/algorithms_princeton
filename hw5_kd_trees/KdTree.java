import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdOut;
import java.util.Stack;

public class KdTree {

	private Node root;

	private class Node {
		private Point2D point;
		private RectHV rect;
		private Node left;
		private Node right;
	}

	// construct an empty set of points 
	public KdTree() {
		root = new Node();
		root.point = null;
		root.rect = null;
		root.left = null;
		root.right = null;
		// root.point = null;

	}
	// is the set empty? 
	public boolean isEmpty() {
		if (root.point == null) {
			return true;
		} else {
			return false;
		}
	}
	// number of points in the set 
	public int size() {
		int count = getCount(root);
		return count;
	}
	private int getCount(Node node) {
		// Node is null if called on a left or right node that doesn't exist
		if (node == null) {
			return 0;
		} 
		// This is the case for the root node.
		else if (node.point == null) {
			return 0;
		} else {
			return 1 + getCount(node.left) + getCount(node.right);
		}
	}
	// add the point to the set (if it is not already in the set)
	public void insert(Point2D p) {
		// empty tree
		if (root.point == null) {
			root.point = p;
			root.rect = new RectHV(0, 0, 1, 1);
		} else {
			// TEMP insert a temp node to the left
			StdOut.println("Point 2: " + p);
			Node node = new Node();
			node.point = p;
			root.left = node;
			Node node2 = new Node();
			node2.point = p;
			node.left = node2;
		}

	}
	// does the set contain point p? 
	public boolean contains(Point2D p) {
		return false;
	}
	// draw all points to standard draw 
	public void draw() {

	}
	// all points that are inside the rectangle 
	public Iterable<Point2D> range(RectHV rect) {
		return new Stack<Point2D>();
	}
	// a nearest neighbor in the set to point p; null if the set is empty 
	public Point2D nearest(Point2D p) {
		return new Point2D(0, 0);
	}
	// unit testing of the methods (optional) 
	public static void main(String[] args) {

		KdTree kdTree = new KdTree();
		boolean empty = kdTree.isEmpty();
		StdOut.println("kdTree created.  isEmpty?: " + empty);
		StdOut.println("kdTree created.  size()?: " + kdTree.size());
		Point2D p1 = new Point2D(.5, .5);
		kdTree.insert(p1);
		empty = kdTree.isEmpty();
		StdOut.println("kdTree p1 inserted.  isEmpty?: " + empty);

		int count = kdTree.size();
		StdOut.println("kdTree.  getCount?: " + count);

		Point2D p2 = new Point2D(.25, .25);
		kdTree.insert(p2);
		count = kdTree.size();
		StdOut.println("kdTree point 2 inserted.  getCount?: " + count);
	}
}