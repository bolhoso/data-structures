import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

import org.junit.Before;

public class RandomizedQueue<Item> extends Deque<Item> implements Iterable<Item> {

	private class RandomIterator implements Iterator<Item>{

		private Object data[];
		int currIdx;

		/**
		 * Shuffle the queue elements into a local array
		 * so that the iteration is trivial
		 */
		public RandomIterator(RandomizedQueue<Item> queue) {
			this.data    = new Object[RandomizedQueue.this.size()];
			this.currIdx = 0;
			
			Random rndIdx = new Random();

			// Copy the queue elements and randomize them using Djisktra Shuffling
			int i = 0;
			Node curr = queue.head.next;
			while (curr != queue.tail) {
				this.data [i] = curr.data;
				exchange (this.data, i, rndIdx.nextInt(i+1));
				
				curr = curr.next;
				i++;
			}
		}
		
		// simply exchange two elements
		private void exchange (Object array[], int i, int j) {
			Object tmp = array[i];
			array[i] = array[j];
			array[j] = tmp;
		}
		

		@Override
		public boolean hasNext() {
			return this.currIdx < this.data.length;
		}

		@Override
		@SuppressWarnings("unchecked")
		public Item next() {
			if (!this.hasNext()) {
				throw new NoSuchElementException("No more elements");
			}
			
			return (Item) this.data[this.currIdx++];
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException("Man, you can't remove elements from my Iterator!");
		}
	};

	// construct an empty randomized queue
	public RandomizedQueue() {
		super();
	}
	
	// is the queue empty?
	public boolean isEmpty() {
		return super.isEmpty();
	}

	// return the number of items on the queue
	public int size() {
		return super.size();
	}

	// add the item
	public void enqueue(Item item) {
		super.addLast(item);
	}
	
	// delete and return a random item
	public Item dequeue() {
		return super.removeFirst();
	}

	// return (but do not delete) a random item
	// by generating a random number N and iterating the list N times
	public Item sample() {
		Node curr = this.head;
		
		Random r = new Random();
		int i = r.nextInt(this.size());
		while (curr.next != this.tail && i > 0) {
			curr = curr.next;
			i--;
		}
		
		if (curr != this.head) {
			return curr.data;
		}
		
		return null;
	}

	// return an independent iterator over items in random order
	public Iterator<Item> iterator() {
		return new RandomIterator(this);
	}
	
	public static void main (String args[]) {
		RandomizedQueue<Integer> q;
		final int MAX_ELEMS_TEST = 100;
		
		Random r = new Random();
		
		q = new RandomizedQueue<Integer>();
		
		int nofElems = r.nextInt(MAX_ELEMS_TEST);
		while (nofElems > 0) {
			q.enqueue(r.nextInt(nofElems+1));
			nofElems--;
		}
		
		for (Integer rndCurr : q) {
			System.out.println(rndCurr);
		}
		
		System.out.println();
		for (Integer rndCurr : q) {
			System.out.println(rndCurr);
		}
	}
}