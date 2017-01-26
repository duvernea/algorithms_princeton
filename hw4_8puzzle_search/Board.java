
import java.util.Stack;

import edu.princeton.cs.algs4.StdOut;

public class Board {

    private final int[][] mBlocks;

    // construct a board from an n-by-n array of blocks
    // (where blocks[i][j] = block in row i, column j)
    public Board(int[][] blocks) {
        mBlocks = blocks;
        StdOut.println("Board created...");
        StdOut.println("rows: " + mBlocks.length);
        StdOut.println("columns: " + mBlocks[0].length);
    }

    // board dimension n
    public int dimension() {
        return mBlocks.length;
    }

    // number of blocks out of place    
    public int hamming() {
        int dim = dimension();
        int count = 0;
        for (int i = 0; i < dim; i++) {
        // StdOut.println("Row " + i);
            for (int j = 0; j < dim; j++) {
                if (i != dim - 1 || j != dim - 1) {
                    if (mBlocks[i][j] == dim * i + 1 + j) {
                        // StdOut.println("Block in Goal position");
                    } else {
                        count++;
                    }
                }
                    // StdOut.println("mBlocks[" + i + "][" + j + "]: " + mBlocks[i][j]); 
            }
        }
        return count;
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
                // if (i != mBlocks.length-1 || j != mBlocks[0].length-1) {
                    if (mBlocks[i][j] == mBlocks.length*i+1+j) {
                        // StdOut.println("Block in Goal position");
                    } else {
                        return false;
                    }
                    // StdOut.println("mBlocks[" + i + "][" + j + "]: " + mBlocks[i][j]);
                // }
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
        if (y == this) return true;
        if (y == null) return false;
        if (y.getClass() != this.getClass()) return false;
        Board that = (Board) y;
        // check that dimensions are equal
        StdOut.println("testing..." + that.mBlocks[0][0]);

        if (this.dimension() == that.dimension()) {
            int n = dimension();
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (this.mBlocks[i][j] != that.mBlocks[i][j]) {
                        return false;
                    }
                }
            }
        } else {
            return false;
        }
        return true;
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        // TEMP, so that class compiles
        return new Stack<Board>();
    }

    // string representation of this board (in the output format specified below)
    public String toString() {
        StringBuilder s = new StringBuilder();
        int n = dimension();
        StdOut.println("dimension: " + n);
        s.append(n + "\n");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                s.append(String.format("%2d ", mBlocks[i][j]));
            }
            s.append("\n");
        }
        return s.toString();
    }

    // unit tests (not graded)
    public static void main(String[] args) {
        StdOut.println("main run...");
        
        int [][] matrix = new int[][]{
            {1,2,3},
            {4,5,6},
            {7,8, 0}, 
        };
        int [][] matrix2 = new int[][]{
            {3,2,1},
            {4,5,6},
            {7,8, 0}, 
        };
        int [][] matrix3 = new int[][]{
            {1,2},
            {0,3} 
        };

        Board board1 = new Board(matrix);
        String board1String = board1.toString();
        StdOut.println("Board 1 toString()" + board1String);
        int hamming1 = board1.hamming();
        StdOut.println("Hamming board1: " + hamming1);
        Board board2 = new Board(matrix2);
        String board2String = board2.toString();
        StdOut.println("Board 2 toString()" + board2String);
        int hamming2 = board2.hamming();
        StdOut.println("Hamming board2: " + hamming2);
        boolean boardsEqual = board1.equals(board2);
        boolean goalReached = board1.isGoal();

        Board board3 = new Board(matrix3);
        String board3String = board3.toString();
        StdOut.println("Board 3 toString()" + board3String);
        int hamming3 = board3.hamming();
        StdOut.println("Hamming board3: " + hamming3);
        boolean boardsEqual2 = board2.equals(board3);
        StdOut.println("Are the boards equal 2x2 vs 3x3?: " + boardsEqual2);
        // StdOut.println("Board Goal Reached: " + goalReached);
        // StdOut.println("matrix.length = " + matrix.length);
        // StdOut.println("maxtrix[0].length" + matrix[0].length);
    }
}