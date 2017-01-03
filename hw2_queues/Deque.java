import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {

	private Node first;
	private Node last;
	private int N = 0;

	private class Node {
		Item item;
		Node next;
	}

	// construct an empty deque
   	public Deque() {}

   	// is the deque empty?
   	public boolean isEmpty() {
   		return false;
   	}  

   	// return the number of items on the deque
   	public int size() {
   		return N;
   	}           

   	// add the item to the front
	public void addFirst(Item item) {

		Node newFirst = new Node();
		newFirst.item = item;
		newFirst.next = first;

		first = newFirst;
		N++;
		if (N == 1) {
			last = first;
		}

	}

	// add the item to the end        
	public void addLast(Item item) {
		Node oldLast = last;

		Node newLast = new Node();
		oldLast.next = newLast;
		newLast.item = item;

		last = newLast;
	}

	// remove and return the item from the front
	public Item removeFirst() {
		return first.item;
	}

	// remove and return the item from the end
	public Item removeLast() {
		return last.item;
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

		myDeque.addFirst("testing 1");
		myDeque.addFirst("testing 2");
		myDeque.addFirst("Last item added to beginning");
		myDeque.addLast("ITEM ADDED AT LAST");
		System.out.println("Deque size: " + myDeque.size());

		// Item not actually removed for now
		System.out.println("First item: " + myDeque.removeFirst());
		System.out.println("Last item: " + myDeque.removeLast());

	} 
}