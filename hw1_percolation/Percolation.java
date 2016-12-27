public class Percolation {

   private int N;
    
   // create n-by-n grid, with all sites blocked 
   public Percolation(int n) {
      N = n;
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
      System.out.println("Hello World"); 
   }
}