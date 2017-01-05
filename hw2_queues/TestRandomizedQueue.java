import static org.junit.Assert.*;

import org.junit.Test;

public class TestRandomizedQueue {
	    
	@Test
    public void testEnqueue() {
        RandomizedQueue<String> randQueue = new RandomizedQueue<String>();
        assertEquals(randQueue.size(), 0);
        randQueue.enqueue("test string");
        assertEquals(randQueue.size(), 1);
    }
}