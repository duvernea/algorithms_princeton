import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;


public class Permutation {

	public static void main(String[] args) {

		RandomizedQueue<String> randQueue = new RandomizedQueue<String>();

		int k = Integer.parseInt(args[0]); 
		// StdOut.println("Read K: " + K);

		while (!StdIn.isEmpty()) {
			String readString = StdIn.readString();
			randQueue.enqueue(readString);
			// StdOut.println("Read string: " + test);
		}
		// for (String a : randQueue) {
		// 	StdOut.println("read string: " + a);
		// }
		for (int i = 0; i < k; i++) {
			String randString = randQueue.dequeue();
			StdOut.println(randString);
		}
	}
}