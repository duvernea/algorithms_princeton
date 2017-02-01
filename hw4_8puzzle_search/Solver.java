	import java.lang.Iterable;
	import java.util.Iterator;

	import edu.princeton.cs.algs4.MinPQ;
	import edu.princeton.cs.algs4.In;
	import edu.princeton.cs.algs4.StdOut;
	import edu.princeton.cs.algs4.Stack;


	public class Solver {

	private int mSolutionMoves;
	private Stack<Board> mSolutionBoards;
	private boolean mSolution;

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
			int thisPriority = this.board.manhattan() + this.numMoves;
			int thatPriority = that.board.manhattan() + that.numMoves;

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
		Board twinInitial = initial.twin();

		int moves = 0;
		int movesTwin = 0;

		MinPQ<SearchNode> pq = new MinPQ<SearchNode>();
		MinPQ<SearchNode> pqTwin = new MinPQ<SearchNode>();

		SearchNode node = new SearchNode(initial, 0, null);
		SearchNode nodeTwin = new SearchNode(twinInitial, 0, null);

		pq.insert(node);
		pqTwin.insert(nodeTwin);

	    while (!pq.min().board.isGoal() && !pqTwin.min().board.isGoal() ) {
	        // StdOut.println("********* NEW MOVE ***************");

	    	node = pq.delMin();
	    	nodeTwin = pqTwin.delMin();
	    	Board board = node.board;

			moves = node.getMoves();
			movesTwin = nodeTwin.getMoves();
			moves++;
			movesTwin++;

			boolean previousNull;
			if (node.previous == null) {
				previousNull = true;
			} else {
				previousNull = false;
			}
	    	for (Board b : node.board.neighbors()) {
				if (previousNull || !node.previous.board.equals(b)) {
					int priority = b.manhattan() + moves;
					SearchNode neighbor = new SearchNode(b, moves, node);
					pq.insert(neighbor);
				}
	    	}
	    	for (Board b : nodeTwin.board.neighbors()) {
	    		int priority = b.manhattan() + movesTwin;
	    		SearchNode neighbor = new SearchNode(b, movesTwin, nodeTwin);
	    		pqTwin.insert(neighbor);
	    	}
	    }
	    // Looping finished
	    if (pq.min().board.isGoal()) {
	    	node = pq.delMin();
	    	Board board = node.board;
	    	while (node.previous != null) {
	    		mSolutionBoards.push(node.board);
	    		node = node.previous;
	    	}
	    	// Add initial node
	    	mSolutionBoards.push(node.board);
	    	mSolution = true;
	    } else {
	    	mSolution = false;
		}
	}
	// is the initial board solvable?    
	public boolean isSolvable() {
		return mSolution;
	}

	// min number of moves to solve initial board; -1 if unsolvable
	public int moves() {
		return mSolutionBoards.size() - 1;
	}
	// sequence of boards in a shortest solution; null if unsolvable    
	public Iterable<Board> solution() {
		if (mSolution) {
			return mSolutionBoards;
		} else {
			return null;
		}
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

		// solve the puzzle
		Solver solver = new Solver(initial);
		if (solver.isSolvable()) {
			StdOut.println("Minimum number of moves = " + solver.moves() + "\n");
		for (Board b : solver.solution()) {
			StdOut.println(b.toString());
		}
		} else {
			StdOut.println("No soultion possible");
		}
	}
	}