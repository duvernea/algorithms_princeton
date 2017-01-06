import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {

	private Node mFirst;
	private Node mLast;
	private int mN = 0;

	private class Node {
		private Item item;
		private Node next;
		private Node prev;
	}

	// construct an empty deque
	public Deque() { }

	// is the deque empty?
	public boolean isEmpty() {
		return (mN == 0);
	}

	// return the number of items on the deque
	public int size() {
		return mN;
	}

	// add the item to the front
	public void addFirst(Item item) {
		if (item == null) {
			throw new java.lang.NullPointerException();
		}

		mN++;
		Node newFirst = new Node();
		newFirst.item = item;
		newFirst.prev = null;

		if (mN == 1) {
			// this is the first node being added
			newFirst.next = null;
			mFirst = newFirst;
			mLast = newFirst;
		} else {
			newFirst.next = mFirst;
			mFirst.prev = newFirst;
			mFirst = newFirst;
		}
	}

	// add the item to the end        
	public void addLast(Item item) {
		if (item == null) {
			throw new java.lang.NullPointerException();
		}

		mN++;
		Node newLast = new Node();
		newLast.item = item;
		newLast.next = null;

		if (mN == 1) {
			newLast.prev = null;
		} else {
			newLast.prev = mLast;
			mLast.next = newLast;
		}
		mLast = newLast;
	}

	// remove and return the item from the front
	public Item removeFirst() {
		if (mN == 0) {
			throw new java.util.NoSuchElementException();
		}
		Item firstItem = mFirst.item;
		mFirst = mFirst.next;
		mN--;
		return firstItem;
	}

	// remove and return the item from the end
	public Item removeLast() {
		if (mN == 0) {
			throw new java.util.NoSuchElementException();
		}
		Item item = mLast.item;
		if (mN == 1) {
			mLast = null;
			mFirst = null;

		} else {
			mLast = mLast.prev;
			mLast.next = null;
		}

		mN--;
		return item;
	} 

	// return an iterator over items in order from front to end              
	public Iterator<Item> iterator()  {
		return new QueueIterator();
	}

	private class QueueIterator implements Iterator<Item> {

		private Node current = mFirst;

		public boolean hasNext() {
			return current != null;
		}
		public void remove() {
			throw new java.lang.UnsupportedOperationException();
		}

		public Item next() {
			if (current == null) {
				throw new java.util.NoSuchElementException();
			}
			Item item = current.item;
			current = current.next;
			return item;
		}
	}

	// unit testing       
	public static void main(String[] args)  {

		org.junit.runner.JUnitCore.main("TestDeque");

		// Deque<String> myDeque = new Deque<String>();

		// int n = 5;
		// String[] myStrings = new String[n];
		// for (int i = 0; i < n; i++) {
		// 	myStrings[i] = "Item " + i + " " + Integer.toString(i);
		// }

		// System.out.println("Add 5 strings");
		// myDeque.addFirst(myStrings[0]);
		// myDeque.addFirst(myStrings[1]);
		// myDeque.addFirst(myStrings[2]);
		// myDeque.addFirst(myStrings[3]);
		// myDeque.addFirst(myStrings[4]);
		// myDeque.addLast(myStrings[4]);

		// System.out.println("Deque size: " + myDeque.size());

		// Iterator<String> i = myDeque.iterator();
		// while (i.hasNext())
		// {
		//  String s = i.next();
		//  System.out.println(s);
		// }

		// // Equivalent iterator test
		// for (String s : myDeque) {
 	// 		System.out.println(s);
 	// 	}

		// System.out.println("First item removed: " + myDeque.removeFirst());
		// System.out.println("Last item removed: " + myDeque.removeLast());
		// System.out.println("Last item removed: " + myDeque.removeLast());
		// System.out.println("First item removed: " + myDeque.removeFirst());
		// System.out.println("First item removed: " + myDeque.removeFirst());

		// System.out.println("Deque size: " + myDeque.size());

		// myDeque.addFirst("Adding a new string after N=0");
		// System.out.println("Deque size: " + myDeque.size());
		// System.out.println("Last item removed: " + myDeque.removeLast());
		// System.out.println("Last item removed: " + myDeque.removeLast());
		// System.out.println("Last item removed: " + myDeque.removeLast());
	} 
}