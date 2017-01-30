import java.util.Stack;
import java.lang.Iterable;
import java.util.Iterator;

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;


public class Solver {

	private int mSolutionMoves;
	private Stack<Board> mSolutionBoards;

	private class SearchNode implements Comparable<SearchNode> {
    	private Board board;
    	private int numMoves;
    	private SearchNode previous;

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
    	public int getMoves() {
    		return numMoves;
    	}
	}

	// find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
    	mSolutionBoards = new Stack<Board>();

    	int moves = 0;

    	MinPQ<SearchNode> pq = new MinPQ<SearchNode>();
    	SearchNode node = new SearchNode(initial, 0, null);
    	StdOut.println("Initial manhattan: " + node.board.manhattan());
    	pq.insert(node);
    	// StdOut.println("Size of priority queue initial: " + pq.size());


    	//StdOut.println("Moves for 1st search node: " + moves);
    	// Iterable<Board> neighbs1 = min.board.neighbors();
        // Iterator<Board> iterator = neighbs1.iterator();
       	// int numberOfMoves = 5;
       	// SearchNode min;
       	int i = 0;

       	while (!pq.min().board.isGoal()) {
       		i++;
       	// for (int i = 0; i < numberOfMoves; i++){
       		StdOut.println("\n------MOVE " + i + " ---------");

       		node = pq.delMin();
       		Board board = node.board;
       		mSolutionBoards.push(board);
       		if (board.isGoal()) {
       			StdOut.println("GOAL BOARD REACHED");
       			StdOut.println("# of moves = " + node.numMoves);
       		}
       		StdOut.println ("Board Pulled from PQ: ");
       		StdOut.println(board);
    		moves = node.getMoves();
       		moves++;
       		StdOut.println("\n------NEIGHBOR BOARDS---------");
	        for (Board b : node.board.neighbors()) {
	            StdOut.println(b.toString());
	            StdOut.println("manhattan: " + b.manhattan());
	            int priority = b.manhattan() + moves;
	            StdOut.println("Priority function: " + priority + "\n");
	            SearchNode neighbor = new SearchNode(b, moves, node);
	            pq.insert(neighbor);
	        }

       	}
       	node = pq.delMin();
       	// Board board = min.board;
       	if (node.board.isGoal()) {
			StdOut.println("GOAL BOARD REACHED");
			StdOut.println("# of moves = " + node.numMoves);
		}
		mSolutionMoves = node.numMoves;


        // MOVE # 1


    	// StdOut.println("Size of priority queue after 1 move: " + pq.size() + "\n");
    	// // MOVE # 2
    	// // What is the min in PQ?
    	// SearchNode temp = pq.min();
    	// Board tempBoard = temp.board;

    	// // is this board the solution?
    	// StdOut.println("Is board Goal?: " + tempBoard.isGoal());
    	// Iterable<Board> neighbs2 = tempBoard.neighbors();
    	// Iterator<Board> iterator2 = neighbs2.iterator();
    	// StdOut.println("Move #2....");
    	// StdOut.println("Selected min priority board: " + "\n" + tempBoard);
    	// moves++;
    	// for (Board b : neighbs2) {

    	// 	StdOut.println(b.toString());
     //        StdOut.println("manhattan: " + b.manhattan());
     //        int priority = b.manhattan() + moves;
     //        StdOut.println("Priority function: " + priority + "\n");
     //        SearchNode node = new SearchNode(b, moves, min);
     //        pq.insert(node);
     //     }

    	// StdOut.println("Min board after 2nd move...");
    	// StdOut.println(pq.min().board.toString());

    }

	// is the initial board solvable?    
    public boolean isSolvable() {
    	return false;
    }

    // min number of moves to solve initial board; -1 if unsolvable
    public int moves() {
    	return mSolutionMoves;
    }

	// sequence of boards in a shortest solution; null if unsolvable    
    public Iterable<Board> solution() {
    	// TEMP, so class compiles
    	return mSolutionBoards;
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
		StdOut.println("# of moves for solution: " + solver.moves());
		for (Board b : solver.mSolutionBoards) {
			StdOut.println(b.toString());
		}
    }
}