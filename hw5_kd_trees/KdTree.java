import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdOut;
import java.util.Stack;
import java.util.Comparator;

import org.junit.runner.JUnitCore;

public class KdTree {

	private Node root;

	private class Node {

		private Node(Point2D p) {
			point = p;
		}

		private Point2D point;
		private RectHV rect;
		private Node left;
		private Node right;
	}

	// construct an empty set of points 
	public KdTree() {
		root = new Node(null);
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
		Comparator<Point2D> comparatorX = Point2D.X_ORDER;
		// empty tree
		if (root.point == null) {
			root.point = p;
			root.rect = new RectHV(0, 0, 1, 1);
		} else {
			insertNode(root, p, comparatorX);
		}
	}
	private Node insertNode(Node node, Point2D p, Comparator<Point2D> pointCompare) {
		Comparator<Point2D> flippedCompare;
		if (pointCompare.equals(Point2D.X_ORDER)) {
			flippedCompare = Point2D.Y_ORDER;
		} else {
			flippedCompare = Point2D.X_ORDER;
		}
		if (node == null) {
			return new Node(p);
		}
		int compare = pointCompare.compare(p, node.point);

		if (compare == 1) {
			node.right = insertNode(node.right, p, flippedCompare);
		} else if (compare == -1) {
			node.left = insertNode(node.left, p, flippedCompare);
		} else {
			node.point = p;
		}
		return node;
	}
	// does the set contain point p? 
	public boolean contains(Point2D p) {
		Comparator<Point2D> comparatorX = Point2D.X_ORDER;
		boolean contains = false;
		// empty tree
		if (root.point == null) {
			return false;
		} else {
			contains = containsNode(root, p, comparatorX);
		}
		return contains;
	}
	private boolean containsNode(Node node, Point2D point, Comparator<Point2D> pointCompare) {
		Comparator<Point2D> flippedCompare;
		if (pointCompare.equals(Point2D.X_ORDER)) {
		 	StdOut.println("equals X_ORDER compartor");
		 	flippedCompare = Point2D.Y_ORDER;
		} else {
		 	StdOut.println("equals Y_ORDER compartor");
		 	flippedCompare = Point2D.X_ORDER;
		}
		return false;
		// return true;
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
		JUnitCore.main("TestKdTree");
	}
}