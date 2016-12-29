import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

   private int mN;
   private int[] mSites;
   private WeightedQuickUnionUF mQuickUnionUF;
   private int mOpenSites = 0;
    
   // create n-by-n grid, with all sites blocked 
   public Percolation(int n) throws java.lang.IllegalArgumentException {
      if (n <= 0) {
         throw new java.lang.IllegalArgumentException("n<=0");
      } else {
         mN = n;
         mSites = new int[mN*mN+2];  // values initialized to 0 in java, none open
         mQuickUnionUF = new WeightedQuickUnionUF(mN*mN+2);
         if (mN > 1) {
            for (int i = 0; i <= mN; i++) {
               mQuickUnionUF.union(0, i);
               mQuickUnionUF.union(mN*mN+1, mN*mN+1-i);
            }
         }
      }
   }  
   // open site (row, col) if it is not open already
   public void open(int row, int col) 
         throws java.lang.IndexOutOfBoundsException {
      if (xyValid(row, col)) {
         int index = xyTo1D(row, col);
         if (mN == 1) {
            mOpenSites +=1;
            mSites[index] = 1;
            mQuickUnionUF.union(0,1);
            mQuickUnionUF.union(2,1);
         } else {
            if (mSites[index] == 0) {
               mOpenSites += 1;
               mSites[index] = 1;
               // System.out.println("Total Sites opened: " + openSites); 

               // right
               if (col != mN) {
                  if (mSites[index+1] == 1) {
                     mQuickUnionUF.union(index, index+1);
                  }
               }
               // left
               if (col != 1) {
                  if (mSites[index-1] == 1) {
                     mQuickUnionUF.union(index, index-1);
                  }
               }
               // up
               if (row != 1) {
                  if (mSites[index-mN] == 1) {
                     mQuickUnionUF.union(index, index-mN);
                  }
               }
               // down
               if (row != mN) {
                  if (mSites[index+mN] == 1) {
                     mQuickUnionUF.union(index, index+mN);
                  }
               }
            }
         }
      } else {
         throw new java.lang.IndexOutOfBoundsException();
      }
   } 
   // is site (row, col) open? 
   public boolean isOpen(int row, int col) {
      if (xyValid(row, col)) {
         int index = xyTo1D(row, col);
         return (mSites[index] == 1);
      } else {
         throw new java.lang.IndexOutOfBoundsException();
      }
   } 
   // is site (row, col) full?
   public boolean isFull(int row, int col) {
      boolean full = false;
      if (xyValid(row, col)) {

         if (isOpen(row, col)) {
            int index = xyTo1D(row, col);
            // index 0 is virtual node, connected to first row
            full = mQuickUnionUF.connected(index, 0);
            //System.out.println("Is Full, index through node 1: " + full);

         }
      } else {
         throw new java.lang.IndexOutOfBoundsException();
      }
      return full;
   }
   // number of open sites
   public int numberOfOpenSites() {
      return mOpenSites;
   }
   // does the system percolate?    
   public boolean percolates()  {
       boolean percolates = mQuickUnionUF.connected(0, mN*mN+1);
       return percolates;
   }
   private int xyTo1D(int row, int col) {
   if (xyValid(row, col)) {
      return (row-1)*mN + (col-1) + 1;
   }
   else {
      return -1;
   }
   }
   private boolean xyValid(int row, int col) {
      return row <= mN && col <= mN && row > 0 && col > 0;
   } 
   // test client (optional)    
   public static void main(String[] args) {
      // Percolation myPercolation = new Percolation(9);
      // System.out.println("Total Sites: " + n + " by " + n + " = " + n*n ); 
      // System.out.println("Opening 1,3");
      // myPercolation.open(1,3);
      // System.out.println("Opening 2,3");
      // myPercolation.open(2,3);
      // System.out.println("Opening 3,3");
      // myPercolation.open(3,3);
      // System.out.println("Opening 4,3");
      // myPercolation.open(4,3);
      // System.out.println("Opening 4,2");
      // myPercolation.open(4,2);
      // System.out.println("Opening 5,2");
      // myPercolation.open(5,2);
      // System.out.println("Opening 5,3");
      // myPercolation.open(5,3);
      // System.out.println("Opening 6,3");
      // myPercolation.open(6,3);
      // System.out.println("Opening 7,3");
      // myPercolation.open(7,3);
      // System.out.println("Opening 8,3");
      // myPercolation.open(8,3);
      // System.out.println("Opening 9,3");
      // myPercolation.open(9,3);

      // System.out.println("Is 4,2 open?: " + myPercolation.isOpen(4,2));
      // System.out.println("Is 4,4 open?: " + myPercolation.isOpen(4,4));
      // int index1 = myPercolation.xyTo1D(1,3);
      // int index2 = myPercolation.xyTo1D(3,6);
      // int index3 = myPercolation.xyTo1D(5,2);
      // System.out.println("1,3, and 3,6 connected?: " + myPercolation.quickUnionUF.connected(index1, index2));
      // System.out.println("1,3, and 5,2 connected?: " + myPercolation.quickUnionUF.connected(index1, index3));
      // System.out.println("Number of open sites: " + myPercolation.numberOfOpenSites());
      // System.out.println("Is 5,3 full? " + myPercolation.isFull(5,3));
      // System.out.println("Does system percolate? " + myPercolation.percolates());
      // try {
      //    Percolation temp = new Percolation(-1);
      // } catch (java.lang.IllegalArgumentException iae) {
      //    System.err.println("error: " + iae.getMessage());
      // }
      Percolation myPercolation = new Percolation(1);
      System.out.println("Does system percolate? " + myPercolation.percolates());

      myPercolation.open(1,1);
      System.out.println("Opening 1,1");

      System.out.println("Does system percolate? " + myPercolation.percolates());
   }

}