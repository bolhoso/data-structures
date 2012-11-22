import java.util.NoSuchElementException;

/**
 * Priority queue implemented using heap
 * @author lucas
 *
 * @param <Key>
 */
public class MaxPQ<Key extends Comparable<Key>> {
	private Key keys[];
	private int size;
	
	@SuppressWarnings("unchecked")
	public MaxPQ (int size) {
		this.keys = (Key[]) new Comparable[size+1];
		this.size = 0;
	}
	
	/**
	 * Inserts in the end of the tree and then fix the heap condition
	 * @param k
	 */
	public void insert (Key k) {
		this.keys[++this.size] = k;
		swim (this.keys, this.size);
	}
	
	/**
	 * Removes the greatest key from the tree (in the root, according to the heap condition) by exchanging
	 * it with the last element. Then, call the sink condition to fix the heap condition in the tree.
	 */
	public Key delMax () {
		Key max = (Key) this.keys[1];
		
		if (this.size == 0) {
			throw new NoSuchElementException("The heap is empty");
		}
		
		xchange (this.keys, this.size--, 1);
		sink(this.keys, this.size, 1); // sink the first element
		this.keys[this.size+1] = null;
		
		return max;
	}
	
	public boolean isEmpty () {
		return this.size == 0;
	}
	
	/**
	 * Recover from the condition where a child key is bigger than its parent's key
	 * O(log N)
	 * @param k
	 */
	public static void swim (Comparable<?> array[], int k) {
		// Compare the current elemet with the parent until the heap condition is met (curr < parent)
		// or the while reach the root
		while (k > 1 && MaxPQ.less (array, k / 2, k)) {
			MaxPQ.xchange(array, k, k/2);
			k /= 2;
		}
	}
	
	/**
	 * Fix the condition where parent key becomes smaller than one of its child keys (or both)
	 * The key is exchanged with its largest child (otherwise the child can be smaller than its sibling and
	 * the heap condition gets violated again)
	 * 
	 * @param k
	 */
	
	// TODO bug
	public static void sink (Comparable<?> array[], int size, int k) {
		// while the current key have a child
		while (2*k <= size) {
			// get the biggest child
			int biggestChild = 2*k;
			if (biggestChild < size && MaxPQ.less (array, biggestChild, biggestChild + 1)) 
				biggestChild++;
			
			// Is the heap condition ok (parent greater than child)?
			if (!MaxPQ.less(array, k,  biggestChild)) 
				break;
			
			// Change the child and walk to the next level of the tree
			MaxPQ.xchange(array, k, biggestChild);
			k = biggestChild;
		}
	}
	
	public static void xchange (Object array[], int elem1, int elem2) {
		Object tmp   = array[elem1];
		array[elem1] = array[elem2];
		array[elem2] = tmp;
	}
	
	/**
	 * Compare c1 to c2
	 * @param c1
	 * @param c2
	 * @return Return true wether c1 is smaller than c2
	 */
	@SuppressWarnings("unchecked")
	public static boolean less (Comparable keys[], int i, int j) {
		return keys[i].compareTo (keys[j]) < 0;
	}
	
	public int size() {
		return this.size;
	}
	
	public boolean validHeap () {
		int k = this.size;
		while (k > 1) {
			if (k/2 > 1 && less(this.keys, k/2, k)) {
				return false;
			}
			k--;
		}
		
		return true;
	}
	
	public static void heapSort (Comparable keys[], int size) {
		// First, build a minHeap
		for (int k = size / 2; k >= 1; k--) {
			sink (keys, size, k);
		}
		
		// Now, build a max-heap without nulling the last element
		while(size > 0) {
			xchange(keys, 1, size--);
			sink (keys, size, 1);
		}
	}
}