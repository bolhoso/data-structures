import java.util.Random;

public class PercolationStats {
	private int n, t;
	private int experiments[];
	
	// perform T independent computational experiments on an N-by-N grid
	public PercolationStats(int n, int t) {
		if (n <= 0 || t <= 0) {
			throw new IllegalArgumentException ("n and t should be > 0");
		}
	
		this.n = n;
		this.t = t;
		this.experiments = new int [t];
		
		this.simulate();
	}
	
	// sample mean of percolation threshold
	public double mean() {
		return StdStats.mean(this.experiments);
	}

	// sample standard deviation of percolation threshold
	public double stddev() {
		return StdStats.stddev(this.experiments);
	}

	/**
	 * Simulate:
	 * - Initialize all sites to be blocked.
	 * - Repeat the following until the system percolates:
	 *   - Choose a site (row i, column j) uniformly at random among all blocked sites.
	 *   - Open the site (row i, column j).
	 */
	private void simulate () {
		for (int currExp = 0; currExp < this.t; currExp++) {

			// Initialize an empty percolation matrix
			Percolation percMatrix = new Percolation (n);
			this.experiments[currExp] = 0;
			
			// Open sites until percolate and count the number of steps
			do {
				percMatrix.open(StdRandom.uniform(n),  StdRandom.uniform(n));
				this.experiments[currExp]++;
			} while (!percMatrix.percolates());
			
			this.experiments[currExp] /= this.n;
		}
	}
	
	// test client, described below
	public static void main(String[] args) {
		int t = 100;
		PercolationStats p = new PercolationStats(200, t);
		p.simulate();

		double stddev = p.stddev(),
				mean = p.mean();
		
		double confLeft = mean - (1.96*stddev/Math.sqrt(t)),
			confRight = mean + (1.96*stddev/Math.sqrt(t));
		
		StdOut.println(mean);
		StdOut.println(stddev);
		StdOut.printf("%f - %f\n", confLeft, confRight);
	}
}
