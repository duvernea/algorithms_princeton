import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.Stack;
import java.util.Comparator;
import java.lang.Math;

// import org.junit.runner.JUnitCore;

public class KdTree {

	private Node root;
	private int size;
	private Point2D championPoint;
	private double championDistance;
	private Stack<Point2D> pointsInside;

	private class Node {
		private Point2D point;
		private RectHV rect;
		private Node left;
		private Node right;

		private Node(Point2D p) {
			point = p;
		}
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
		return (root.point == null);
	}
	// number of points in the set 
	public int size() {

		// int count = getCount(root);
		return size;
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
			size++;

		} else {
			insertNode(root, p, root.rect, comparatorX);
		}
	}
	private Node insertNode(Node node, Point2D p, RectHV rect, Comparator<Point2D> pointCompare) {
		if (node == null) {
			Node nodeAdded = new Node(p);
			nodeAdded.rect = rect;
			size++;
			return nodeAdded;
		}
		if (p.equals(node.point)) {
			return node;
		} 

		Comparator<Point2D> flippedCompare;
		boolean compareX;
		if (pointCompare.equals(Point2D.X_ORDER)) {
			compareX = true;
			flippedCompare = Point2D.Y_ORDER;
		} else {
			compareX = false;
			flippedCompare = Point2D.X_ORDER;
		}

		int compare = pointCompare.compare(p, node.point);

		RectHV rectAdded;

		if (compare > 0) {
			if (compareX) {
				rectAdded = new RectHV(node.point.x(), node.rect.ymin(), 
					node.rect.xmax(), node.rect.ymax());
			} else {
				rectAdded = new RectHV(node.rect.xmin(), node.point.y(), 
					node.rect.xmax(), node.rect.ymax());
			}
			node.right = insertNode(node.right, p, rectAdded, flippedCompare);

		} else if (compare <= 0) {
			if (compareX) {
				rectAdded = new RectHV(node.rect.xmin(), node.rect.ymin(), 
					node.point.x(), node.rect.ymax());
			} else {
				rectAdded = new RectHV(node.rect.xmin(), node.rect.ymin(), 
					node.rect.xmax(), node.point.y());
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
		if (p.equals(node.point)) {
			return true;
		} 
		Comparator<Point2D> flippedCompare = flipComparator(pointCompare);

		int compare = pointCompare.compare(p, node.point);
		if (compare > 0) {
			return containsNode(node.right, p, flippedCompare);
		} else {
			return containsNode(node.left, p, flippedCompare);
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
		Comparator<Point2D> comparatorX = Point2D.X_ORDER;
		pointsInside = new Stack<Point2D>();
		if (isEmpty()) {
			return pointsInside;
		} else {
			searchRect(root, rect, comparatorX);
		}
		return pointsInside;

	}
	private void searchRect(Node node, RectHV rect, Comparator<Point2D> pointCompare) {

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
		if (!rect.intersects(node.rect)) {
			return;
		}
		if (rect.contains(node.point)) {
			pointsInside.push(node.point);
		}
		searchRect(node.left, rect, flippedCompare);
		searchRect(node.right, rect, flippedCompare);
	}
	// a nearest neighbor in the set to point p; null if the set is empty 
	public Point2D nearest(Point2D p) {
		Comparator<Point2D> comparatorX = Point2D.X_ORDER;
		if (isEmpty()) {
			return null;
		} else {
			championPoint = root.point;
			championDistance = root.point.distanceTo(p);
			searchNearest(root, p, comparatorX);
			return championPoint;
		}
	}
	private void searchNearest(Node node, Point2D p, Comparator<Point2D> pointCompare) {
		if (node == null) {
			return;
		}
		double distance = p.distanceTo(node.point);
		if (distance < championDistance) {
			championDistance = distance;
			championPoint = node.point;
			if (p.equals(node.point)) return;
		}
		Node first, second;
		if (pointCompare.compare(p, node.point) > 0) {
			first = node.left;
			second = node.right;
		} else {
			first = node.right;
			second = node.left;
		}
		Comparator<Point2D> flippedCompare = flipComparator(pointCompare);
		if (first != null && first.rect.distanceTo(p) < championDistance) {
			searchNearest(first, p, flippedCompare);
		}
		if (second != null && second.rect.distanceTo(p) < championDistance) {
			searchNearest(second, p, flippedCompare);
		}
	}
	private Comparator<Point2D> flipComparator(Comparator<Point2D> comparator) {
		Comparator<Point2D> flippedComparator;
		if (comparator.equals(Point2D.X_ORDER)) {
		 	flippedComparator = Point2D.Y_ORDER;
		} else {
		 	flippedComparator = Point2D.X_ORDER;
		}
		return flippedComparator;
	}
	// unit testing of the methods (optional) 
	public static void main(String[] args) {
		// JUnitCore.main("TestKdTree");
	}
}