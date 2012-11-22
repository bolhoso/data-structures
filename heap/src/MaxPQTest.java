import java.util.NoSuchElementException;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


/**
 * Test the Max Priority Queue
 */
public class MaxPQTest {
	private static final int DEFAULT_SIZE = 1000;
	MaxPQ<Integer> q = null;
	
	@Before public void setup () {
		this.q = new MaxPQ<Integer>(DEFAULT_SIZE);
	}
	
	@Test public void testEmpty () {
		assertTrue (q.isEmpty());
	}
	
	@Test public void testSizeIncrease () {
		assertEquals (0, q.size());
		
		q.insert(1);
		assertEquals (1, q.size());

		q.insert(100);
		assertEquals (2, q.size());
	}
	
	@Test public void testSizeDecrease () {
		assertEquals (0, q.size());
		
		q.insert(1);
		q.insert(2);
		q.insert(10);
		assertEquals (3, q.size());

		q.delMax();
		assertEquals (2, q.size());
		
		q.delMax();
		assertEquals (1, q.size());
		
		q.delMax();
		assertEquals (0, q.size());
		assertTrue(q.isEmpty());
	}
	
	/**
	 * Insert random numbers and check if they are dequeued in the reverse order
	 */
	@Test public void testRandomInsertSortedDeque () {
		Random r = new Random();
		final int MAX = 10;
		int r2[]={1,		4,		4,		3,		0,		7,		0,		5,		7,		7};
		for (int i = 0; i < MAX; i++) {
			int n = r2[i];
			q.insert(n);
		}
		
		this.validateHeap();
	}
	
	@Test public void testSortedInsertSortedDequeue () {
		for (int i = DEFAULT_SIZE; i > 0; i--) {
			q.insert(i);
		}

		this.validateHeap();
	}
	
	// TODO: test overflow also
	@Test(expected=NoSuchElementException.class) public void testUnderFlow () {
		assertTrue (q.isEmpty());
		q.delMax();
	}
	
	@Test public void testHeapSort () {
		Integer r2[]={null, 9, 3, 1, 10, 11, 0, -1, 2, 7};
		MaxPQ.heapSort(r2, r2.length - 1);
		
		
		int min = Integer.MIN_VALUE;
		for (int i = 1; i < r2.length; i++) {
			assertTrue (r2[i] > min);
			min = r2[i];
		}
	}
	
	/**
	 * Check the heap condition and the reverse sorted elements removal
	 */
	private void validateHeap () {
		assertTrue (q.validHeap());
		
		int max = Integer.MAX_VALUE;
		while (!q.isEmpty()) {
			int curr = q.delMax();
			assertTrue(curr <= max);
			max = curr;
		}
	}
}
