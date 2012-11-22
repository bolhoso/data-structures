// TODO: union find algorithm
public class Percolation {
	private int data[], n;
	private boolean openSites[];
	
	// create N-by-N grid, with all sites blocked
	public Percolation(int n) {
		this.data = new int[n*n];
		this.openSites = new boolean[n*n];
		this.n = n;
		
		for (int i = 0; i < n*n; i++) {
			this.data[i] = i;
			this.openSites[i] = true;
		}
	}

	// openSites site (row i, column j) if it is not already
	public void open (int i, int j) {
		this.checkBounds(i, j);
		
		int idx = this.getIdx (i, j);
		this.openSites[idx] = true;
		if (i != 0) {
			int otherIdx = this.getIdx (i - 1, j);
			
			int p = this.root(idx);
			int q = this.root(otherIdx);
			this.data[p] = q;
		}
	}

	// is site (row i, column j) openSites?
	public boolean isOpen(int i, int j) {
		this.checkBounds (i, j);

		return this.openSites[this.getIdx(i, j)];
	}

	// is site (row i, column j) full?
	// Full means connected to the top
	public boolean isFull(int i, int j) {
		this.checkBounds (i, j);

		// TODO: method stub
		return false;
	}

	// does the system percolate?
	public boolean percolates() {
		// TODO random....
		return StdRandom.bernoulli(0.001);
	}

	private int root (int i) {
		while (i != this.data[i]) {
			i = this.data[i];
		}
		
		return i;
	}

	/**
	 * Check if i and j are inside the limits
	 * @param i
	 * @param j
	 */
	private void checkBounds (int i, int j) {
		if (i < 0 || i >= this.n ||
				j < 0 || j >= this.n) {
			throw new ArrayIndexOutOfBoundsException ("values should be between 0 and N");
		}
	}

	private int getIdx (int i, int j) {
		return i * this.n + j;
	}
	
	public static void main(String[] args) {
		Percolation p = new Percolation (10);
		p.open(1,0);
		p.open(1,1);
	}
	
}
