import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdOut;
import java.util.Stack;
import java.util.Comparator;

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
			StdOut.println("equals X_ORDER compartor");
			flippedCompare = Point2D.Y_ORDER;
		} else {
			StdOut.println("equals Y_ORDER compartor");
			flippedCompare = Point2D.X_ORDER;
		}
		// StdOut.println("insertNode recursive method called with point: " + p);
		if (node == null) {
			StdOut.println("node == null");
			return new Node(p);
		}
		// TODO - not sure yet how to determine levels
		// Comparator<Point2D> comparatorX = Point2D.X_ORDER;
		// Comparator<Point2D> comparatorY = Point2D.Y_ORDER;
		int compareX = pointCompare.compare(p, node.point);
		int compareY = pointCompare.compare(p, node.point);

		// StdOut.println("p: " + p);
		// StdOut.println("node.point: " + node.point);

		// StdOut.println("Compare by X: p, node.point = " + compareX);
		// StdOut.println("Compare by Y: p, node.point = " + compareY);

		// For now, just compare by X
		if (compareX == 1) {
			StdOut.println("node.right = ...." + p);
			node.right = insertNode(node.right, p, flippedCompare);
		} else if (compareX == -1) {
			StdOut.println("node.left = ...." + p);

			node.left = insertNode(node.left, p, flippedCompare);
		} else {
			StdOut.println("node.point set equal = ...." + p);

			node.point = p;
		}
		return node;
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

		Point2D p3 = new Point2D(.9, .9);
		kdTree.insert(p3);
		count = kdTree.size();
		StdOut.println("kdTree point 3 inserted.  getCount?: " + count);

		Point2D p4 = new Point2D(.7, .7	);
		kdTree.insert(p4);
		count = kdTree.size();
		StdOut.println("kdTree point 4 inserted.  getCount?: " + count);

		Point2D p5 = new Point2D(.6, .7	);
		kdTree.insert(p5);
		count = kdTree.size();
		StdOut.println("kdTree point 5 inserted.  getCount?: " + count);

		Point2D p6 = new Point2D(.6, .95	);
		kdTree.insert(p6);
		count = kdTree.size();
		StdOut.println("kdTree point 6 inserted.  getCount?: " + count);



	}
}