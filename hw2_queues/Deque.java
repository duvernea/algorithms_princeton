import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {

	private Node first;
	private Node last;
	private int N = 0;

	private class Node {
		Item item;
		Node next;
		Node prev;
	}

	// construct an empty deque
   	public Deque() {}

   	// is the deque empty?
   	public boolean isEmpty() {
   		return (N==0);
   	}  

   	// return the number of items on the deque
   	public int size() {
   		return N;
   	}           

   	// add the item to the front
	public void addFirst(Item item) {

		N++;
		Node newFirst = new Node();
		newFirst.item = item;
		newFirst.prev = null;

		if (N == 1) {
			// this is the first node being added
			newFirst.next = null;
			first = newFirst;
			last = newFirst;
		} else {
			newFirst.next = first;
			first.prev = newFirst;
			first = newFirst;
		}
	}

	// add the item to the end        
	public void addLast(Item item) {

		N++;
		Node newLast = new Node();
		newLast.item = item;
		newLast.next = null;

		if (N == 1) {
			newLast.prev = last;
		} else {
			newLast.prev = last;
			last.next = newLast;
			last = newLast;
		}
	}

	// remove and return the item from the front
	public Item removeFirst() {
		if (N==0) {
			throw new java.lang.UnsupportedOperationException();
		}
		Item firstItem = first.item;
		first = first.next;
		N--;
		return firstItem;
	}

	// remove and return the item from the end
	public Item removeLast() {
		if (N==0) {
			throw new java.lang.UnsupportedOperationException();
		}
		Item item = last.item;
		if (N==1) {
			last = null;
			first = null;

		} else {
			last = last.prev;
			last.next = null;
		}

		N--;
		return item;
	} 

	// return an iterator over items in order from front to end              
	public Iterator<Item> iterator()  {
		return new QueueIterator();
	}

	private class QueueIterator implements Iterator<Item> {
		private Node current = first;

		public boolean hasNext() {
			return current != null;
		}
		public void remove() {}

		public Item next() {
			Item item = current.item;
			current = current.next;
			return item;
		}
	}

	// unit testing       
	public static void main(String[] args)  {

		Deque<String> myDeque = new Deque<String>();

		int N = 5;
		String[] myStrings = new String[N];
		for (int i=0; i<N; i++) {
			myStrings[i] = "Item " + i + " " + Integer.toString(i);
		}

		System.out.println("Add 5 strings");
		myDeque.addFirst(myStrings[0]);
		myDeque.addFirst(myStrings[1]);
		myDeque.addFirst(myStrings[2]);
		myDeque.addFirst(myStrings[3]);
		myDeque.addFirst(myStrings[4]);

		System.out.println("Deque size: " + myDeque.size());

		// First
		System.out.println("First item removed: " + myDeque.removeFirst());
		System.out.println("Last item removed: " + myDeque.removeLast());
		System.out.println("Last item removed: " + myDeque.removeLast());
		System.out.println("First item removed: " + myDeque.removeFirst());
		System.out.println("First item removed: " + myDeque.removeFirst());

		System.out.println("Deque size: " + myDeque.size());

		myDeque.addFirst("Adding a new string after N=0");
		System.out.println("Deque size: " + myDeque.size());
		System.out.println("Last item removed: " + myDeque.removeLast());
		System.out.println("Last item removed: " + myDeque.removeLast());
		System.out.println("Last item removed: " + myDeque.removeLast());




	} 
}