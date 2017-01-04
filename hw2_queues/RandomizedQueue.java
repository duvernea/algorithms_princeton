import java.util.Iterator;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdOut;

public class RandomizedQueue<Item> implements Iterable<Item> {

	private Item[] items = (Item[]) new Object[1];
	private int N = 0;

	// construct an empty randomized queue
   	public RandomizedQueue() {

   	}
   	private void resize(int max) {
   		Item[] temp = (Item[]) new Object[max];
   		for (int i = 0; i < N; i++) {
   			temp[i] = items[i];
   		}
   		items = temp;
   		StdOut.println("Array resized to " + max + " items.");
   	}

   	// is the queue empty?
   	public boolean isEmpty() { return (N==0); }

   	// return the number of items on the queue
   	public int size() { return N; }

   	// add the item
   	public void enqueue(Item item) {
   		if (N == items.length) {
   			resize(2*items.length);
   		}
   		items[N++] = item;

	} 

	// remove and return a random item

   	public Item dequeue() {
   		// 	Returns a random integer uniformly in [0, n).
   		int index = StdRandom.uniform(N);

   		StdOut.println("random index: " + index);

   		return items[0];

   	}

   	// return (but do not remove) a random item                 
   	public Item sample() {
   		return items[0];

	}

	// return an independent iterator over items in random order            
	public Iterator<Item> iterator() {
		return new RandomizedQueueIterator();
	}

	private class RandomizedQueueIterator implements Iterator<Item> {

		private Item current = items[0];

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
			return current;
		}
	}

	// unit testing       
	public static void main(String[] args)  {

		RandomizedQueue<String> randQueue = new RandomizedQueue<String>();

		int N = 5;
		String[] myStrings = new String[N];
		for (int i=0; i<N; i++) {
			myStrings[i] = "Item " + i + " " + Integer.toString(i);
		}

		randQueue.enqueue(myStrings[0]);
		randQueue.enqueue(myStrings[1]);
		randQueue.enqueue(myStrings[2]);
		randQueue.enqueue(myStrings[3]);
		randQueue.enqueue(myStrings[4]);



		StdOut.println("is empty?: " + randQueue.isEmpty());
		StdOut.println("size: " + randQueue.size());

		StdOut.println("dequeue test: " + randQueue.dequeue());

	} 
}