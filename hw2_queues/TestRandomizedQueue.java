import static org.junit.Assert.*;
import edu.princeton.cs.algs4.StdOut;
import java.util.Iterator;


import org.junit.Test;

public class TestRandomizedQueue {
	@Test
	public void testEnqueue() {
		RandomizedQueue<String> randQueue = new RandomizedQueue<String>();
		String[] testStrings = new String[5];

		testStrings[0] = "Ashley";
		testStrings[1] = "Bob";
		testStrings[2] = "Chris";
		testStrings[3] = "Dan";
		testStrings[4] = "Erica";

		assertEquals("Added 0 items, size is not equal 0", randQueue.size(), 0);
		assertTrue("Added 0 items, isEmpty() is not true", randQueue.isEmpty());
		randQueue.enqueue(testStrings[0]);
		assertEquals(randQueue.size(), 1);
		randQueue.enqueue(testStrings[1]);
		randQueue.enqueue(testStrings[2]);
		randQueue.enqueue(testStrings[3]);
		randQueue.enqueue(testStrings[4]);
		assertEquals("Added 5 items, size() is not equal 5", randQueue.size(), 5);
		assertFalse("Added 5 items, isEmpty() is not false",randQueue.isEmpty());

		// Get random sample from the queue (do not delete it)
		for (int i = 0; i<20; i++) {
			StdOut.println("random sample: " + randQueue.sample());
		}
		String a = randQueue.dequeue();
		StdOut.println("String " + a + " dequeued");
		assertEquals("Removed 1 item, size() is not equal 4", randQueue.size(), 4);

		for (int i = 0; i<20; i++) {
			StdOut.println("random sample: " + randQueue.sample());
		}
		Iterator<String> i = randQueue.iterator();
		StdOut.println("Create Iterator...");
		while (i.hasNext()) {
			String s = i.next();
			StdOut.println("Iterator " + s);
		}
		String b = randQueue.dequeue();
		StdOut.println("String " + b + " dequeued");
		assertEquals("Removed 1 item, size() is not equal 3", randQueue.size(), 3);

		i = randQueue.iterator();
		while (i.hasNext()) {
			String s = i.next();
			StdOut.println("Iterator " + s);
		}

	}
}