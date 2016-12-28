import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

   private int N;
   private int[] sites;
   private WeightedQuickUnionUF quickUnionUF;
   private int openSites = 0;
    
   // create n-by-n grid, with all sites blocked 
   public Percolation(int n) {
      N = n;
      sites = new int[N*N];  // values initialized to 0 in java, none open
      // for (int i=0; i<N*N-1; i++) {
      //    sites[i] = i;
      // }
      quickUnionUF = new WeightedQuickUnionUF(N*N);
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
      return true;
   }
   // number of open sites
   public int numberOfOpenSites() {
      return openSites;
   }
   // does the system percolate?    
   public boolean percolates()  {
       return true;
   }
   // test client (optional)    
   public static void main(String[] args) {
      Percolation myPercolation = new Percolation(9);
      int n = myPercolation.getN();
      System.out.println("Total Sites: " + n + " by " + n + " = " + n*n ); 
      System.out.println("Opening 3,4");
      myPercolation.open(3,4);
      System.out.println("Opening 3,5");
      myPercolation.open(3,5);
      System.out.println("Opening 7,7");
      myPercolation.open(7,7);
      System.out.println("Is 3,4 open?: " + myPercolation.isOpen(3,4));
      System.out.println("Is 4,4 open?: " + myPercolation.isOpen(4,4));
      int index1 = myPercolation.xyTo1D(3,4);
      int index2 = myPercolation.xyTo1D(3,6);
      int index3 = myPercolation.xyTo1D(3,5);
      System.out.println("3,4, and 3,6 connected?: " + myPercolation.quickUnionUF.connected(index1, index2));
      System.out.println("3,4, and 3,5 connected?: " + myPercolation.quickUnionUF.connected(index1, index3));
      System.out.println("Number of open sites: " + myPercolation.numberOfOpenSites());
      
   }
   public int xyTo1D(int row, int col) {
      if (xyValid(row, col)) {
         return (row-1)*N + (col-1);
      }
      else {
         return -1;
      }
   }
   public boolean xyValid(int row, int col) {
      return row <= N && col <= N && row >0 && col > 0;
   }
}