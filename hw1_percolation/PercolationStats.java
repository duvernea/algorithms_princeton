import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

	private int mN;
	private int mTrials;
	private Percolation mPercolation;
	private double[] mPercolation_threshold;
	// perform trials independent experiments on an n-by-n grid
   	public PercolationStats(int n, int trials) throws java.lang.IllegalArgumentException {

   		if (n <= 0 || trials <= 0) {
         	throw new java.lang.IllegalArgumentException("n<=0 or trials <=0");
      	} else {
   			mN = n;
   			mTrials = trials;
   			mPercolation_threshold = new double[trials];
   			for (int i = 0; i < mTrials; i++) {
   				mPercolation = new Percolation(mN);
   				while (!mPercolation.percolates()) {
   					int row = StdRandom.uniform(mN) +1;
   					int col = StdRandom.uniform(mN) +1;
   					mPercolation.open(row, col);
   				}
   				int numOpenSites = mPercolation.numberOfOpenSites();
   				double threshold = (double) numOpenSites/(mN*mN);
   				mPercolation_threshold[i] = threshold;
   			}
   		}
   	} 

   	// sample mean of percolation threshold
   	public double mean() {
   		return StdStats.mean(mPercolation_threshold);
   	}     

   	// sample standard deviation of percolation threshold                    
   	public double stddev() {
   		return StdStats.stddev(mPercolation_threshold);
   	}

   	// low  endpoint of 95% confidence interval                      
   	public double confidenceLo() {
   		return mean() - 1.96*stddev()/Math.sqrt(mTrials);
   	}

   	// high endpoint of 95% confidence interval                
   	public double confidenceHi() {
   		return mean() + 1.96*stddev()/Math.sqrt(mTrials);
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
	        System.out.println("std dev: " + percStats.stddev());
	        System.out.println("95% confidence low: " + percStats.confidenceLo());
	        System.out.println("95% confidence high: " + percStats.confidenceHi());
   		}
   	}       
}