
import java.util.Stack;

import edu.princeton.cs.algs4.StdOut;

public class Board {

    final int[][] mBlocks;

    // construct a board from an n-by-n array of blocks
    // (where blocks[i][j] = block in row i, column j)
    public Board(int[][] blocks) {
        mBlocks = blocks;
        StdOut.println("mBlocks.length: " + mBlocks.length);
    StdOut.println("mBlocks[0].length: " + mBlocks[0].length);
    }

    // board dimension n
    public int dimension() {
        return mBlocks.length;
    }

    // number of blocks out of place    
    public int hamming() {
        return 0;
    }

    // sum of Manhattan distances between blocks and goal
    public int manhattan() {
        return 0;
    }

    // is this board the goal board?
    public boolean isGoal() {


        for (int i = 0; i < mBlocks.length; i++) {
            // StdOut.println("Row " + i);
            for (int j = 0; j < mBlocks[0].length; j++) {
                if (i != mBlocks.length-1 || j != mBlocks[0].length-1) {
                    if (mBlocks[i][j] == mBlocks.length*i+1+j) {
                        StdOut.println("Block in Goal position");
                    } else {
                        return false;
                    }
                    StdOut.println("mBlocks[" + i + "][" + j + "]: " + mBlocks[i][j]);
                }
            }
        }
        return true;
    }

    // a board that is obtained by exchanging any pair of blocks
    public Board twin() {
        return this;
    }

    // does this board equal y?
    public boolean equals(Object y) {
        return false;
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        // TEMP, so that class compiles
        return new Stack<Board>();
    }

    // string representation of this board (in the output format specified below)
    public String toString() {
        return "";
    }

    // unit tests (not graded)
    public static void main(String[] args) {
        StdOut.println("main run...");
          int [][] matrix = new int[][]{
            {1,2,3},
            {4,5,6},
            {8,7},

    };
    StdOut.println("matrix.length: " + matrix.length);
    StdOut.println("matrix[0].length: " + matrix[0].length);

    Board board = new Board(matrix);
    boolean goalReached = board.isGoal();
    StdOut.println("Board Goal Reached: " + goalReached);
    StdOut.println("matrix.length = " + matrix.length);
    StdOut.println("maxtrix[0].length" + matrix[0].length);

    }
}