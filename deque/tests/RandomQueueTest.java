import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;

public class RandomQueueTest {
	private RandomizedQueue<Integer> q;
	private BST<Integer, Boolean> bagOfElems;
	
	private static final int MAX_ELEMS_TEST = 100;
	
	@Before public void setup () {
		
		Random r = new Random();
		
		this.q = new RandomizedQueue<Integer>();
		this.bagOfElems = new BST<Integer, Boolean>();
		
		int nofElems = r.nextInt(MAX_ELEMS_TEST);
		while (nofElems > 0) {
			this.bagOfElems.put(r.nextInt(), true);
			nofElems--;
		}
		
		int size = 0;
		assertEquals (this.q.size(), size);
		
		for (Integer i : this.bagOfElems.keys()) {
			this.q.enqueue(i);
			
			// Check the queue size
			size++;
			assertEquals (this.q.size(), size);
		}
	}
	
	@Test public void testIterator() {
		int iterations = 0;
		for (Integer rndCurr : this.q) {
			assertTrue(this.bagOfElems.get(rndCurr));
			iterations++;
		}
		
		assertEquals (iterations, this.bagOfElems.size());
	}
	
	@Test public void testDequeue() {
		int size = q.size();
		while (size > 0) {
			assertTrue (this.bagOfElems.get(q.dequeue()));
			size--;
		}
		
		assertTrue (q.isEmpty());
		assertEquals (q.size(), 0);
	}
}
