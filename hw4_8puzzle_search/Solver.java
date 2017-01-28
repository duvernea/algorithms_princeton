import java.util.Stack;

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class Solver {

	private class SearchNode {
    	Board board;
    	int numMoves;
    	SearchNode previous;

    	private SearchNode(Board board) {
    		this.board = board;
    		numMoves = 0;
    		previous = null;
    	}

	}

	// find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {

    	MinPQ<SearchNode> queue = new MinPQ<SearchNode>();
    	SearchNode initialNode = new SearchNode(initial);
    	queue.insert(initialNode);
    	StdOut.println("Size of priority queue initial: " + queue.size());

    }

	// is the initial board solvable?    
    public boolean isSolvable() {
    	return false;
    }

    // min number of moves to solve initial board; -1 if unsolvable
    public int moves() {
    	return 0;
    }

	// sequence of boards in a shortest solution; null if unsolvable    
    public Iterable<Board> solution() {
    	// TEMP, so class compiles
    	return new Stack<Board>();
    }

	// solve a slider puzzle (given below)    
    public static void main(String[] args) {

		// create initial board from file
		In in = new In(args[0]);
		int n = in.readInt();
		int[][] blocks = new int[n][n];
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				blocks[i][j] = in.readInt();
		Board initial = new Board(blocks);
		StdOut.println("Board read: " );
		StdOut.println(initial.toString());

		// solve the puzzle
		Solver solver = new Solver(initial);
    }
}