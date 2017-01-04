import java.util.Iterator;
import edu.princeton.cs.algs4.StdRandom;

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
   	}

   	// is the queue empty?
   	public boolean isEmpty() { return (N==0); }

   	// return the number of items on the queue
   	public int size() { return N; }

   	// add the item
   	public void enqueue(Item item) {

	} 

	// remove and return a random item

   	public Item dequeue() {
   		// 	Returns a random integer uniformly in [0, n).
   		int index = StdRandom.uniform(N);

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

	} 
}