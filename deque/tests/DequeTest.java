import static org.junit.Assert.assertEquals;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Test;


public class DequeTest {

	@Test public void testInsertFirst () {
		Deque<Integer> d = new Deque<Integer>();
		
		d.addFirst(1);
		assertEquals(1, d.size());
		
		d.addFirst(2);
		assertEquals(2, d.size());
		
		d.addFirst(3);
		assertEquals(3, d.size());
		
		Iterator<Integer> i = d.iterator();
		assertEquals(new Integer(3), i.next());
		assertEquals(new Integer(2), i.next());
		assertEquals(new Integer(1), i.next());
	}
	
	@Test public void testInsertLast () {
		Deque<Integer> d = new Deque<Integer>();
		
		d.addLast(1);
		d.addLast(2);
		d.addLast(3);
		
		Iterator<Integer> i = d.iterator();
		assertEquals(new Integer(1), i.next());
		assertEquals(new Integer(2), i.next());
		assertEquals(new Integer(3), i.next());
	}
	
	@Test public void testMixedInserts () {
		Deque<Integer> d = new Deque<Integer>();
		int size = 0;
		
		d.addLast(1);
		assertEquals (++size, d.size());

		d.addLast(2);
		assertEquals (++size, d.size());
		
		d.addLast(3);
		assertEquals (++size, d.size());
		
		d.addFirst(0);
		assertEquals (++size, d.size());
		
		d.addLast(5);
		assertEquals (++size, d.size());
		
		Iterator<Integer> i = d.iterator();
		assertEquals(new Integer(0), i.next());
		assertEquals(new Integer(1), i.next());
		assertEquals(new Integer(2), i.next());
		assertEquals(new Integer(3), i.next());
		assertEquals(new Integer(5), i.next());
	}
	
	@Test public void testRemoveLast () {
		Deque<Integer> d = new Deque<Integer>();
		
		d.addLast(1);
		d.addLast(2);
		d.addLast(3);

		int size = 3; 
		assertEquals (new Integer(3), d.removeLast());
		assertEquals (--size, d.size());
		assertEquals (new Integer(2), d.removeLast());
		assertEquals (--size, d.size());
		assertEquals (new Integer(1), d.removeLast());
		assertEquals (--size, d.size());
	}
	
	@Test public void testRemoveFirst () {
		Deque<Integer> d = new Deque<Integer>();
		
		d.addLast(1);
		d.addLast(2);
		d.addLast(3);

		int size = 3; 
		assertEquals (new Integer(1), d.removeFirst());
		assertEquals (--size, d.size());
		assertEquals (new Integer(2), d.removeFirst());
		assertEquals (--size, d.size());
		assertEquals (new Integer(3), d.removeFirst());
		assertEquals (--size, d.size());
	}
	
	@Test(expected = NoSuchElementException.class) public void testRemoveEmpty () {
		Deque<Integer> d = new Deque<Integer>();
		d.removeFirst();
	}
	
	@Test(expected = NoSuchElementException.class) public void testRemoveEmptyLast () {
		Deque<Integer> d = new Deque<Integer>();
		d.removeLast();
	}
	
	@Test(expected = NullPointerException.class) public void testNullInsert () {
		Deque<Integer> d = new Deque<Integer>();
		d.addFirst (null);
	}
	
	@Test(expected = NullPointerException.class) public void testNullInsertLast () {
		Deque<Integer> d = new Deque<Integer>();
		d.addLast (null);
	}
}

