import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;


public class PercolationStats {

	int N;
	int trials;
	Percolation percolation;
	double mean;
	// perform trials independent experiments on an n-by-n grid
   	public PercolationStats(int n, int trials) throws java.lang.IllegalArgumentException {
   		mean = 0;

   		if (n <= 0 || trials <= 0) {
         	throw new java.lang.IllegalArgumentException("n<=0 or trials <=0");
      	} else {
   			this.N = n;
   			this.trials = trials;
   			for (int i=0; i < trials; i++) {
   				percolation = new Percolation(N);
   				while (!percolation.percolates()) {
   					int row = StdRandom.uniform(N) +1;
   					int col = StdRandom.uniform(N) +1;
   					percolation.open(row, col);
   				}
   				int numOpenSites = percolation.numberOfOpenSites();
   				double threshold = (double) numOpenSites/(N*N);
   				System.out.println("Threshold at percolation: " + threshold);
   				mean += threshold;
   			}
   			mean = mean/trials;
   		}
   	} 

   	// sample mean of percolation threshold
   	public double mean() {
   		return mean;
   	}     

   	// sample standard deviation of percolation threshold                    
   	public double stddev() {
   		return 0;
   	}

   	// low  endpoint of 95% confidence interval                      
   	public double confidenceLo() {
   		return 0;
   	}

   	// high endpoint of 95% confidence interval                
   	public double confidenceHi() {
   		return 0;
   	}                 

	// test client (described below)
   	public static void main(String[] args) {

   		if (args.length != 2) {
   			System.out.println("Incorrect number of arguments. Should be 2 arguments (N, trials)");
   		} else {
	   		int N = Integer.parseInt(args[0]);
	   		int trials = Integer.parseInt(args[1]);
	        PercolationStats percStats = new PercolationStats(N, trials);
	        System.out.println("mean: " + percStats.mean());
   		}





   	}       
}