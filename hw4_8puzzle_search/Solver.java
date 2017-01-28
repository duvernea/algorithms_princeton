import java.util.Stack;
import java.lang.Iterable;
import java.util.Iterator;

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;


public class Solver {

	private class SearchNode implements Comparable<SearchNode> {
    	Board board;
    	int numMoves;
    	SearchNode previous;

    	private SearchNode(Board board, int moves, SearchNode previous) {
    		this.board = board;
    		numMoves = moves;
    		this.previous = previous;
    	}
    	public int compareTo(SearchNode that) {
    		int thisPriority = this.board.hamming() + this.numMoves;
    		int thatPriority = that.board.hamming() + that.numMoves;
    		if (thisPriority == thatPriority) {
    			return 0;
    		} else if (thisPriority < thatPriority) {
    			return -1;
    		} else {
    			return +1;
    		}
    	}
	}

	// find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {

    	int moves = 0;

    	MinPQ<SearchNode> pq = new MinPQ<SearchNode>();
    	SearchNode initialNode = new SearchNode(initial, 0, null);
    	pq.insert(initialNode);
    	StdOut.println("Size of priority queue initial: " + pq.size());

    	SearchNode min = pq.delMin();
    	Iterable<Board> neighbs1 = min.board.neighbors();
        Iterator<Board> iterator = neighbs1.iterator();
        moves++;
        while (iterator.hasNext()) {
            Board board = iterator.next();
            SearchNode node = new SearchNode(board, moves, min);
            pq.insert(node);
            StdOut.println("Board Neighbor Iterator " + board);
        }

    	StdOut.println("Size of priority queue after 1 move: " + pq.size());

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