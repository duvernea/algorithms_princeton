import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdDraw;
import java.util.Stack;
import java.util.Comparator;

import org.junit.runner.JUnitCore;

public class KdTree {

	private Node root;
	private Point2D championPoint;
	private double championDistance;

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
			insertNode(root, p, root.rect, comparatorX);
		}
	}
	private Node insertNode(Node node, Point2D p, RectHV rect, Comparator<Point2D> pointCompare) {
		Comparator<Point2D> flippedCompare;
		boolean compareX;
		if (pointCompare.equals(Point2D.X_ORDER)) {
			compareX = true;
			flippedCompare = Point2D.Y_ORDER;
		} else {
			compareX = false;
			flippedCompare = Point2D.X_ORDER;
		}
		if (node == null) {
			Node nodeAdded = new Node(p);
			nodeAdded.rect = rect;
			// StdOut.println("Point " + p + " created with rectHV " + rect);
			return nodeAdded;
		}
		int compare = pointCompare.compare(p, node.point);
		RectHV rectAdded;
		if (compare == 1) {

			if (compareX) {
				rectAdded = new RectHV(node.point.x(), node.rect.ymin(), node.rect.xmax(), node.rect.ymax());
			} else {
				rectAdded = new RectHV(node.rect.xmin(), node.point.y(), node.rect.xmax(), node.rect.ymax());
			}
			node.right = insertNode(node.right, p, rectAdded, flippedCompare);

		} else if (compare == -1) {

			if (compareX) {
				rectAdded = new RectHV(node.rect.xmin(), node.rect.ymin(), node.point.x() , node.rect.ymax());
			} else {
				rectAdded = new RectHV(node.rect.xmin(), node.rect.ymin(), node.rect.xmax(), node.point.y());
			}
			node.left = insertNode(node.left, p, rectAdded, flippedCompare);

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
	private boolean containsNode(Node node, Point2D p, Comparator<Point2D> pointCompare) {
		if (node == null) {
			return false;
		}
		Comparator<Point2D> flippedCompare;
		if (pointCompare.equals(Point2D.X_ORDER)) {
		 	// StdOut.println("equals X_ORDER compartor");
		 	flippedCompare = Point2D.Y_ORDER;
		} else {
		 	// StdOut.println("equals Y_ORDER compartor");
		 	flippedCompare = Point2D.X_ORDER;
		}
		int compare = pointCompare.compare(p, node.point);
		if (compare == 0) {
			return true;
		} else if (compare == 1) {
			return containsNode(node.right, p, flippedCompare);
		} else if (compare == -1) {
			return containsNode(node.left, p, flippedCompare);
		} else {
			return false;
		}
	}
	// draw all points to standard draw 
	public void draw() {
		Comparator<Point2D> comparatorX = Point2D.X_ORDER;
		drawPoint(root, comparatorX);
	}
	private void drawPoint(Node node, Comparator<Point2D> pointCompare) {
		boolean compareX;
		if (node == null) return;
		Comparator<Point2D> flippedCompare;
		if (pointCompare.equals(Point2D.X_ORDER)) {
			compareX = true;
		 	flippedCompare = Point2D.Y_ORDER;
		} else {
		 	compareX = false;
		 	flippedCompare = Point2D.X_ORDER;
		}
		// Draw point
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.setPenRadius(0.01);
		StdDraw.point(node.point.x(), node.point.y());
		// Draw line
		StdDraw.setPenRadius();
		if (compareX) {
			StdDraw.setPenColor(StdDraw.RED);
			StdDraw.line(node.point.x(), node.rect.ymin(), node.point.x(), node.rect.ymax());
		} else {
			StdDraw.setPenColor(StdDraw.BLUE);
			StdDraw.line(node.rect.xmin(), node.point.y(), node.rect.xmax(), node.point.y());
		}
		drawPoint(node.left, flippedCompare);
		drawPoint(node.right, flippedCompare);
	}
	// all points that are inside the rectangle 
	public Iterable<Point2D> range(RectHV rect) {
		return new Stack<Point2D>();
	}
	// a nearest neighbor in the set to point p; null if the set is empty 
	public Point2D nearest(Point2D p) {
		// StdOut.println("Nearest point being calculated...");
		Comparator<Point2D> comparatorX = Point2D.X_ORDER;
		if (root.point == null) {
			return null;
		} else {
			championPoint = root.point;
			championDistance = root.point.distanceTo(p);
			Node closest = searchNearest(root, p, comparatorX);
			return championPoint;
		}
	}
	private Node searchNearest(Node node, Point2D p, Comparator<Point2D> pointCompare) {
		StdOut.println("Point " + p + " searchNearest() recursive at " + node.point);
		if (node == null) {
			// parent was the closest
			return node;
		}
		Comparator<Point2D> flippedCompare;
		if (pointCompare.equals(Point2D.X_ORDER)) {
		 	flippedCompare = Point2D.Y_ORDER;
		} else {
		 	flippedCompare = Point2D.X_ORDER;
		}
		int compare = pointCompare.compare(p, node.point);
		double distance = p.distanceTo(node.point);
		// StdOut.println("Distance from " + p + " to " + node.point + " = " + distance);
		if (distance < championDistance) {
			championDistance = distance;
			championPoint = node.point;
			// StdOut.println("Champion point updated: " + championDistance + " " + championPoint);
		}

		if (compare == 0) {
			return node;
		} else if (compare == 1) {
			if (node.right == null) {
				// this is the last point here
				return node;
			}
			// true if current champion point is to the right/top of this point node
			// if so, need to search the right/top side
			boolean searchRight = (pointCompare.compare(championPoint, node.point) != -1);
			// StdOut.println("championPoint: " + championPoint + " node.point: " + node.point + " searchright: " + searchRight);
			if (searchRight) {
				node = searchNearest(node.right, p, flippedCompare);
			}
		} else {
			if (node.left == null) {
				// this is the last point here
				return node;
			}
			// true if current champion point is to the left/bottom of this point node
			// if so, need to search the right/top side
			boolean searchLeft = (pointCompare.compare(championPoint, node.point) != 1);
			// StdOut.println("championPoint: " + championPoint + " node.point: " + node.point + " searchleft: " + searchLeft);

			if (searchLeft) {
				node = searchNearest(node.left, p, flippedCompare);
			}
		} 
		// StdOut.println("Do we ever reach this point?");
		return node;
	}
	// unit testing of the methods (optional) 
	public static void main(String[] args) {
		JUnitCore.main("TestKdTree");

		// KdTree kdTree = new KdTree();

		// Point2D p1 = new Point2D(.5, .5);
		// kdTree.insert(p1);

		// Point2D p2 = new Point2D(.25, .25);
		// kdTree.insert(p2);

		// Point2D p3 = new Point2D(.9, .9);
		// kdTree.insert(p3);

		// Point2D p4 = new Point2D(.7, .7	);
		// kdTree.insert(p4);

		// Point2D p5 = new Point2D(.6, .7	);
		// kdTree.insert(p5);

		// Point2D p6 = new Point2D(.6, .95);
		// kdTree.insert(p6);

		// kdTree.draw();

		// Point2D checkPoint = new Point2D(.61, .7);

		// Point2D nearest = kdTree.nearest(checkPoint);
		// StdOut.println("Nearest point to " + checkPoint + " = " + nearest);
	}
}