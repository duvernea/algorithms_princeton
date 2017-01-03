import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {

	private Node first;
	private Node last;
	private int N;

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
   		return 0;
   	}           

   	// add the item to the front
	public void addFirst(Item item) {

	}

	// add the item to the end        
	public void addLast(Item item) {

	}

	// remove and return the item from the front
	public Item removeFirst() {
		return first.item;
	}

	// remove and return the item from the end
	public Item removeLast() {
		return first.item;
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

	} 
}