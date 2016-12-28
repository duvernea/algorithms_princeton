import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

   private int N;
   private int[] sites;
   private WeightedQuickUnionUF quickUnionUF;
   private int openSites = 0;
    
   // create n-by-n grid, with all sites blocked 
   public Percolation(int n) throws java.lang.IllegalArgumentException {
      if (n <= 0) {
         throw new java.lang.IllegalArgumentException("n<=0");
      } else {
         N = n;
         sites = new int[N*N];  // values initialized to 0 in java, none open
         // for (int i=0; i<N*N-1; i++) {
         //    sites[i] = i;
         // }
         quickUnionUF = new WeightedQuickUnionUF(N*N+2);
         // union
         for (int i = 0; i <= N; i++) {
            quickUnionUF.union(0, i);
            quickUnionUF.union(N*N+1, N*N+1-i);
         }
      }
   }  
   private int getN() {
      return N;
   }
   private int getValue(int idx) {
      return sites[idx];
   }
   // open site (row, col) if it is not open already
   public void open(int row, int col) {
      if (xyValid(row, col)) {
         int index = xyTo1D(row, col);
         if (sites[index] == 0) {
            openSites += 1;
            sites[index] = 1;
            System.out.println("Total Sites opened: " + openSites); 

            //right
            if (col != N) {
               if (sites[index+1] == 1) {
                  quickUnionUF.union(index, index+1);
               }
            }
            // left
            if (col != 1) {
               if (sites[index-1] == 1) {
                  quickUnionUF.union(index, index-1);
               }
            }
            // up
            if (row != 1) {
               if (sites[index-N] == 1) {
                  quickUnionUF.union(index, index-N);
               }
            }
            // down
            if (row != N) {
               if (sites[index+N] == 1) {
                  quickUnionUF.union(index, index+N);
               }
            }
         }
      }
   } 
   // is site (row, col) open? 
   public boolean isOpen(int row, int col) {
      int index = xyTo1D(row, col);
      return (sites[index] == 1);
   } 
   // is site (row, col) full?
   public boolean isFull(int row, int col) {
      boolean full = false;
      if (isOpen(row, col)) {
         int index = xyTo1D(row, col);
         // index 0 is virtual node, connected to first row
         full = quickUnionUF.connected(index, 0);
         System.out.println("Is Full, index through node 1: " + full);

      }
      return full;
   }
   // number of open sites
   public int numberOfOpenSites() {
      return openSites;
   }
   // does the system percolate?    
   public boolean percolates()  {
       boolean percolates = quickUnionUF.connected(0, N*N+1);
       return percolates;
   }
   // test client (optional)    
   public static void main(String[] args) {
      Percolation myPercolation = new Percolation(9);
      int n = myPercolation.getN();
      System.out.println("Total Sites: " + n + " by " + n + " = " + n*n ); 
      System.out.println("Opening 1,3");
      myPercolation.open(1,3);
      System.out.println("Opening 2,3");
      myPercolation.open(2,3);
      System.out.println("Opening 3,3");
      myPercolation.open(3,3);
      System.out.println("Opening 4,3");
      myPercolation.open(4,3);
      System.out.println("Opening 4,2");
      myPercolation.open(4,2);
      System.out.println("Opening 5,2");
      myPercolation.open(5,2);
      System.out.println("Opening 5,3");
      myPercolation.open(5,3);
      System.out.println("Opening 6,3");
      myPercolation.open(6,3);
      System.out.println("Opening 7,3");
      myPercolation.open(7,3);
      System.out.println("Opening 8,3");
      myPercolation.open(8,3);
      System.out.println("Opening 9,3");
      myPercolation.open(9,3);

      System.out.println("Is 4,2 open?: " + myPercolation.isOpen(4,2));
      System.out.println("Is 4,4 open?: " + myPercolation.isOpen(4,4));
      int index1 = myPercolation.xyTo1D(1,3);
      int index2 = myPercolation.xyTo1D(3,6);
      int index3 = myPercolation.xyTo1D(5,2);
      System.out.println("1,3, and 3,6 connected?: " + myPercolation.quickUnionUF.connected(index1, index2));
      System.out.println("1,3, and 5,2 connected?: " + myPercolation.quickUnionUF.connected(index1, index3));
      System.out.println("Number of open sites: " + myPercolation.numberOfOpenSites());
      System.out.println("Is 5,3 full? " + myPercolation.isFull(5,3));
      System.out.println("Does system percolate? " + myPercolation.percolates());
      try {
         Percolation temp = new Percolation(-1);
      } catch (java.lang.IllegalArgumentException iae) {
         System.err.println("error: " + iae.getMessage());
      }

   }
   public int xyTo1D(int row, int col) {
      if (xyValid(row, col)) {
         return (row-1)*N + (col-1) + 1;
      }
      else {
         return -1;
      }
   }
   public boolean xyValid(int row, int col) {
      return row <= N && col <= N && row >0 && col > 0;
   }
}