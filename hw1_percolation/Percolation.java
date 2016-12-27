import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

   private int N;
   private int[] sites;
    
   // create n-by-n grid, with all sites blocked 
   public Percolation(int n) {
      N = n;
      sites = new int[N*N];
      for (int i=0; i<N*N-1; i++) {
         sites[i] = i;
      }
   }  
   public int getN() {
      return N*N;
   }
   public int getValue(int idx) {
      return sites[idx];
   }
   // open site (row, col) if it is not open already
   public void open(int row, int col) {

   } 
   // is site (row, col) open? 
   public boolean isOpen(int row, int col) {
      return true;
   } 
   // is site (row, col) full?
   public boolean isFull(int row, int col) {
      return true;
   }
   // number of open sites
   public int numberOfOpenSites() {
      return 0;
   }
   // does the system percolate?    
   public boolean percolates()  {
       return true;
   }
   // test client (optional)    
   public static void main(String[] args) {
      Percolation myPercolation = new Percolation(5);
      int n = myPercolation.getN();
      System.out.println("Total Sites: " + n); 
      for (int i=0; i<n-1; i++) {
         System.out.println("Site " + i + " Value: " + myPercolation.getValue(i) );
      }
   }
}