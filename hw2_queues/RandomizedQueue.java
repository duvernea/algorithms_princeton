import java.util.Iterator;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {

	private Item[] mItems = (Item[]) new Object[1];
	private int mN = 0;

// construct an empty randomized queue
	public RandomizedQueue() {

	}
	private void resize(int max) {
		Item[] temp = (Item[]) new Object[max];
		for (int i = 0; i < mN; i++) {
			temp[i] = mItems[i];
		}
		mItems = temp;
		// StdOut.println("Array resized to " + max + " items.");
	}

	// is the queue empty?
	public boolean isEmpty() { return (mN == 0); }

	// return the number of items on the queue
	public int size() { return mN; }

	// add the item
	public void enqueue(Item item) {
		if (item == null) {
			throw new java.lang.NullPointerException();
		}
		if (mN == mItems.length) {
			resize(2*mItems.length);
		}
		mItems[mN++] = item;

} 

	// remove and return a random item

	public Item dequeue() {
		if (mN == 0) {
			throw new java.util.NoSuchElementException();
		}
		// 	Returns a random integer uniformly in [0, n).
		int index = StdRandom.uniform(mN);

		// StdOut.println("random index: " + index);
		Item item = mItems[index];
		mItems[index] = mItems[mN-1];
		mItems[mN-1] = null;
		mN--;
		return item;

	}

	// return (but do not remove) a random item                 
	public Item sample() {
		if (mN == 0) {
			throw new java.util.NoSuchElementException();
		}
		int index = StdRandom.uniform(mN);
		return mItems[index];

	}

	// return an independent iterator over items in random order            
	public Iterator<Item> iterator() {
		return new RandomizedQueueIterator();
	}

	private class RandomizedQueueIterator implements Iterator<Item> {

		public RandomizedQueueIterator() {
			itemIterator = (Item[]) new Object[mN];
			for (int i = 0; i< mN; i++) {
				itemIterator[i] = mItems[i];
			}

			StdRandom.shuffle(itemIterator);
		}

		private int index = 0;
		private Item[]  itemIterator;

		public boolean hasNext() {
			return index < mN;
		}
		public void remove() {
			throw new java.lang.UnsupportedOperationException();
		}

		public Item next() {
			if (index >= mN) {
				throw new java.util.NoSuchElementException();
			}
			return itemIterator[index++];
		}
	}

	// unit testing       
	public static void main(String[] args)  {

		org.junit.runner.JUnitCore.main("TestRandomizedQueue");

	} 
}