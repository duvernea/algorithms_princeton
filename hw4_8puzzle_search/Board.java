
import java.util.Stack;
import java.util.Arrays;
import java.util.List;
import java.lang.Math;
import java.lang.Iterable;
import java.util.Iterator;

import edu.princeton.cs.algs4.StdOut;

public class Board {

    private final int[][] mBlocks;
    private final int mEmptyRow;
    private final int mEmptyCol;

    // construct a board from an n-by-n array of blocks
    // (where blocks[i][j] = block in row i, column j)
    public Board(int[][] blocks) {
        boolean emptyAssigned = false;

        int dim = blocks.length;
        int empty_i = -1;
        int empty_j = -1;
        mBlocks = new int[dim][dim];

        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                if (blocks[i][j] == 0) {
                    emptyAssigned = true;
                    empty_i = i;
                    empty_j = j;
                }
                mBlocks[i][j] = blocks[i][j];
            }
        }
        if (emptyAssigned) {
            mEmptyRow = empty_i;
            mEmptyCol = empty_j;
        } else {
            mEmptyRow = -1;
            mEmptyCol = -1;
        }
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
                int goalTile = dim * i + 1 + j;
                if (i != dim - 1 || j != dim - 1) {
                    if (mBlocks[i][j] == goalTile) {
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
        int dim = dimension();
        int count = 0;

        for (int i=0; i < dim; i++) {
            for (int j=0; j < dim; j++) {
                int currentTile = mBlocks[i][j];
                int goalTile = dim * i + 1 + j;
                if (currentTile == 0) {
                    // SKIP - DO NOTHING
                }
                else if (currentTile == goalTile) {
                    // SKIP - DO NOTHING
                } else {
                    int i_goal = goalTile / dim;
                    int j_goal = goalTile % dim;
                    int di = Math.abs(i_goal-i);
                    int dj = Math.abs(j_goal-j);
                    count = count + di + dj;
                }
            }
        }
        return count;
    }

    // is this board the goal board?
    public boolean isGoal() {
        int dim = dimension();


        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                int goalTile = dim * i + 1 + j;
                if (mBlocks[i][j] == goalTile) {
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    // a board that is obtained by exchanging any pair of blocks
    public Board twin() {
        int dim = dimension();

        int[][] blocks = new int[dim][dim];
        blocks = copyTiles();

        // exchange pair of blocks
        if (mEmptyRow == 0) {
            // empty block in 1st row, swap in 2nd row
            int temp = blocks[1][1];
            blocks[1][1] = blocks[1][0];
            blocks[1][0] = temp; 
        } else {
            // empty block not in 1st row, swap in 1st row
            int temp = blocks[0][1];
            blocks[0][1] = blocks[0][0];
            blocks[0][0] = temp; 
        }
        return new Board(blocks);
    }

    // does this board equal y?
    public boolean equals(Object y) {
        if (y == this) return true;
        if (y == null) return false;
        if (y.getClass() != this.getClass()) return false;
        Board that = (Board) y;
        // check that dimensions are equal

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
        int dim = dimension();
        Stack<Board> neighbors = new Stack<Board>();
        int[][] copyTiles = copyTiles();

        // 3 options for location of empty square

        // 1. Square is in the corner -> Has 2 neighbors
        if (mEmptyRow == 0 && mEmptyCol == 0) {

            StdOut.println("Empty in the upper left corner");
            // swap
            int temp = copyTiles[0][1];
            int temp2 = copyTiles[1][0];

            copyTiles[0][1] = 0;
            copyTiles[0][0] = temp;
            Board a = new Board(copyTiles);
            neighbors.add(a);
            copyTiles[1][0] = 0;
            copyTiles[0][0] = temp2;
            copyTiles[0][1] = temp;
            Board b = new Board(copyTiles);
            neighbors.add(b);

        } else if (mEmptyRow == 0 && mEmptyCol == dim - 1) {
            StdOut.println("Empty in the upper right corner");
            int temp = copyTiles[0][dim - 2];
            int temp2 = copyTiles[1][dim - 1];

            copyTiles[0][dim - 2] = 0;
            copyTiles[0][dim - 1] = temp;
            Board a = new Board(copyTiles);
            neighbors.add(a);
            copyTiles[1][dim - 1] = 0;
            copyTiles[0][dim - 1] = temp2;
            copyTiles[0][dim - 2] = temp;
            Board b = new Board(copyTiles);
            neighbors.add(b);

        } else if (mEmptyRow == dim - 1 && mEmptyCol == 0) {
            StdOut.println("Empty in the lower left corner");
            int temp = copyTiles[dim - 1][1];
            int temp2 = copyTiles[dim - 2][0];

            copyTiles[dim - 1][1] = 0;
            copyTiles[dim - 1][0] = temp;
            Board a = new Board(copyTiles);
            neighbors.add(a);
            copyTiles[dim - 2][0] = 0;
            copyTiles[dim - 1][0] = temp2;
            copyTiles[dim - 1][1] = temp;
            Board b = new Board(copyTiles);
            neighbors.add(b);

        } else if (mEmptyRow == dim - 1 && mEmptyCol == dim - 1) {
            StdOut.println("Empty in the lower right corner");
            int temp = copyTiles[dim - 1][dim - 2];
            int temp2 = copyTiles[dim - 2][dim - 1];

            copyTiles[dim - 1][dim - 2] = 0;
            copyTiles[dim - 1][dim - 1] = temp;
            Board a = new Board(copyTiles);
            neighbors.add(a);
            copyTiles[dim - 2][dim - 1] = 0;
            copyTiles[dim - 1][dim - 1] = temp2;
            copyTiles[dim - 1][dim - 2] = temp;
            Board b = new Board(copyTiles);
            neighbors.add(b);
        } else if (mEmptyRow == 0 || mEmptyRow == dim - 1 ) {
            StdOut.println("Empty in the first or last row, but not corner");
            if (mEmptyRow == 0) {
                int left = copyTiles[0][mEmptyCol - 1];
                int right = copyTiles[0][mEmptyCol + 1];
                int down = copyTiles[1][mEmptyCol];

                copyTiles[0][mEmptyCol] = left;
                copyTiles[0][mEmptyCol - 1] = 0;
                Board a = new Board(copyTiles);
                neighbors.add(a);
                copyTiles[0][mEmptyCol + 1] = 0;
                copyTiles[0][mEmptyCol] = right;
                copyTiles[0][mEmptyCol - 1] = left;
                Board b = new Board(copyTiles);
                neighbors.add(b);
                copyTiles[0][mEmptyCol - 1] = left;
                copyTiles[0][mEmptyCol + 1] = right;
                copyTiles[0][mEmptyCol] = down;
                copyTiles[1][mEmptyCol] = 0;
                Board c = new Board(copyTiles);
                neighbors.add(c);
            }
            if (mEmptyRow == dim - 1) {
                int left = copyTiles[dim - 1][mEmptyCol - 1];
                int right = copyTiles[dim - 1][mEmptyCol + 1];
                int up = copyTiles[dim - 2][mEmptyCol - 1];

                copyTiles[dim - 1][mEmptyCol] = left;
                copyTiles[dim - 1][mEmptyCol - 1] = 0;
                Board a = new Board(copyTiles);
                neighbors.add(a);
                copyTiles[dim - 1][mEmptyCol + 1] = 0;
                copyTiles[dim - 1][mEmptyCol] = right;
                copyTiles[dim - 1][mEmptyCol - 1] = left;
                Board b = new Board(copyTiles);
                neighbors.add(b);
                copyTiles[dim - 1][mEmptyCol - 1] = left;
                copyTiles[dim - 1][mEmptyCol + 1] = right;
                copyTiles[dim - 1][mEmptyCol] = up;
                copyTiles[dim - 2][mEmptyCol] = 0;
                Board c = new Board(copyTiles);
                neighbors.add(c);
            }
        } else if (mEmptyCol == 0 || mEmptyCol == dim - 1 ) {
            StdOut.println("Empty in the first or last col, but not corner");
            if (mEmptyCol == 0) {
                int up = copyTiles[mEmptyRow - 1][0];
                int down = copyTiles[mEmptyRow + 1][0];
                int right = copyTiles[mEmptyRow][1];

                copyTiles[mEmptyRow][0] = up;
                copyTiles[mEmptyRow - 1][0] = 0;
                Board a = new Board(copyTiles);
                neighbors.add(a);
                copyTiles[mEmptyRow + 1][0] = 0;
                copyTiles[mEmptyRow][0] = down;
                copyTiles[mEmptyRow - 1][0] = up;
                Board b = new Board(copyTiles);
                neighbors.add(b);
                copyTiles[mEmptyRow + 1][0] = down;
                copyTiles[mEmptyRow][0] = right;
                copyTiles[mEmptyRow][1] = 0;
                Board c = new Board(copyTiles);
                neighbors.add(c);
            } 
            if (mEmptyCol == dim - 1) {
                int up = copyTiles[mEmptyRow - 1][dim - 1];
                int down = copyTiles[mEmptyRow + 1][dim - 1];
                int left = copyTiles[mEmptyRow][dim - 2];

                copyTiles[mEmptyRow][dim - 1] = up;
                copyTiles[mEmptyRow - 1][dim - 1] = 0;
                Board a = new Board(copyTiles);
                neighbors.add(a);
                copyTiles[mEmptyRow + 1][dim - 1] = 0;
                copyTiles[mEmptyRow][dim - 1] = down;
                copyTiles[mEmptyRow - 1][dim - 1] = up;
                Board b = new Board(copyTiles);
                neighbors.add(b);
                copyTiles[mEmptyRow + 1][dim - 1] = down;
                copyTiles[mEmptyRow][dim - 1] = left;
                copyTiles[mEmptyRow][dim - 2] = 0;
                Board c = new Board(copyTiles);
                neighbors.add(c);
            }
        } else {
            StdOut.println("Empty not along a wall");
            int up = copyTiles[mEmptyRow - 1][mEmptyCol];
            int down = copyTiles[mEmptyRow + 1][mEmptyCol];
            int left = copyTiles[mEmptyRow][mEmptyCol - 1];
            int right = copyTiles[mEmptyRow][mEmptyCol + 1];

            // Create left neighbor
            copyTiles[mEmptyRow][mEmptyCol - 1] = 0;
            copyTiles[mEmptyRow][mEmptyCol] = left;
            Board a = new Board(copyTiles);
            neighbors.add(a);
            // Create right neighbor
            copyTiles[mEmptyRow][mEmptyCol - 1] = left;
            copyTiles[mEmptyRow][mEmptyCol + 1] = 0;
            copyTiles[mEmptyRow][mEmptyCol] = right;
            Board b = new Board(copyTiles);
            neighbors.add(b);
            // Create up neighbor
            copyTiles[mEmptyRow][mEmptyCol + 1] = right;
            copyTiles[mEmptyRow - 1][mEmptyCol] = 0;
            copyTiles[mEmptyRow][mEmptyCol] = up;
            Board c = new Board(copyTiles);
            neighbors.add(c);
            // Create down neighbor
            copyTiles[mEmptyRow - 1][mEmptyCol] = up;
            copyTiles[mEmptyRow + 1][mEmptyCol] = 0;
            copyTiles[mEmptyRow][mEmptyCol] = down;
            Board d = new Board(copyTiles);
            neighbors.add(d);
        }
        return neighbors;
    }
    private int[][] copyTiles() {
        int dim = dimension();
        int[][] neighborTiles = new int[dim][dim];
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                neighborTiles[i][j] = mBlocks[i][j];
            }
        }

        return neighborTiles;
    }

    // string representation of this board (in the output format specified below)
    public String toString() {
        StringBuilder s = new StringBuilder();
        int dim = dimension();
        StdOut.println("dimension: " + dim);
        s.append(dim + "\n");
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
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
            {2,7,8},
            {6,0,4},
            {3,5,1}, 
        };
        int [][] matrix2 = new int[][]{
            {3,2,1},
            {4,5,6},
            {7,8, 0}, 
        };
        int [][] matrix3 = new int[][]{
            {0,2},
            {1,3} 
        };

        StdOut.println("***************** BOARD 1 *************");
        Board board1 = new Board(matrix);
        String board1String = board1.toString();
        StdOut.println("Board 1 toString() " + board1String);
        int hamming1 = board1.hamming();
        int manhattan1 = board1.manhattan();
        StdOut.println("Hamming board1: " + hamming1);
        StdOut.println("Manhattan board1: " + manhattan1);
        Board twin1 = board1.twin();
        StdOut.println("Board 1 twin toString() " + twin1);


        Iterable<Board> neighbs1 = board1.neighbors();
        Iterator<Board> iterator = neighbs1.iterator();
        while (iterator.hasNext()) {
            Board board = iterator.next();
            StdOut.println("Board Neighbor Iterator " + board);
        }

        StdOut.println("***************** BOARD 2 *************");

        Board board2 = new Board(matrix2);
        String board2String = board2.toString();
        StdOut.println("Board 2 toString() " + board2String);
        int hamming2 = board2.hamming();
        int manhattan2 = board2.manhattan();

        StdOut.println("Hamming board2: " + hamming2);
        StdOut.println("Manhattan board2: " + manhattan2);

        Board twin2 = board2.twin();
        StdOut.println("Board 2 twin toString() " + twin2);

        boolean boardsEqual = board1.equals(board2);
        boolean goalReached = board1.isGoal();

        Board board3 = new Board(matrix3);
        String board3String = board3.toString();
        StdOut.println("Board 3 toString() " + board3String);
        int hamming3 = board3.hamming();
        int manhattan3 = board3.manhattan();
        StdOut.println("Hamming board3: " + hamming3);
        StdOut.println("Manhattan board3: " + manhattan3);

        Board twin3 = board3.twin();
        StdOut.println("Board 3 twin toString() " + twin3);

        boolean boardsEqual2 = board2.equals(board3);
        StdOut.println("Are the boards equal 2x2 vs 3x3?: " + boardsEqual2);
        // StdOut.println("Board Goal Reached: " + goalReached);
        // StdOut.println("matrix.length = " + matrix.length);
        // StdOut.println("maxtrix[0].length" + matrix[0].length);
    }
}