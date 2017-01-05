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
		Item item = items[index];
		items[index] = items[N-1];
		items[N-1] = null;
		N--;
		return item;

	}

	// return (but do not remove) a random item                 
	public Item sample() {
		int index = StdRandom.uniform(N);
		return items[index];

	}

	// return an independent iterator over items in random order            
	public Iterator<Item> iterator() {
		return new RandomizedQueueIterator();
	}

	private class RandomizedQueueIterator implements Iterator<Item> {

		private int index = 0;
		//private Item[] = new Item[N];

		public boolean hasNext() {
			return index<N;
		}
		public void remove() {
			throw new java.lang.UnsupportedOperationException();
		}

		public Item next() {
			if (index > N) {
				throw new java.util.NoSuchElementException();
			}
			return items[index++];
		}
	}

	// unit testing       
	public static void main(String[] args)  {

		org.junit.runner.JUnitCore.main("TestRandomizedQueue");

	} 
}