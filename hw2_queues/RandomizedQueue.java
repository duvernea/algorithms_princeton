import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> {

	private Item item;

	// construct an empty randomized queue
   	public RandomizedQueue() {

   	}

   	// is the queue empty?
   	public boolean isEmpty() {
   		return false;
   	}

   	// return the number of items on the queue
   	public int size() {
   		return 0;
   	}

   	// add the item
   	public void enqueue(Item item) {

	} 

	// remove and return a random item

   	public Item dequeue() {
   		return item;

   	}

   	// return (but do not remove) a random item                 
   	public Item sample() {
   		return item;

	}

	// return an independent iterator over items in random order            
	public Iterator<Item> iterator() {
		return new RandomizedQueueIterator();
	}

	private class RandomizedQueueIterator implements Iterator<Item> {

		private Item current = item;

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