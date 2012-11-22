package br.array;
import java.util.Iterator;
import java.util.NoSuchElementException;

import br.common.OrderedSymbolTable;


public class BinarySearchArrayST<Key extends Comparable<Key>, Value> implements OrderedSymbolTable<Key, Value>, Iterable<Key> {

	private static final int DEFAULT_SIZE = 100;
	
	private Key keys[];
	private Value data[];
	private int size;
	
	@SuppressWarnings("unchecked")
	public BinarySearchArrayST() {
		this.keys = (Key[]) new Object[DEFAULT_SIZE];
		this.data = (Value[]) new Object[DEFAULT_SIZE];
		this.size = 0;		
	}
	
	@Override
	public void put(Key k, Value v) {
		if (k == null) {
			throw new NoSuchElementException("Key can't be null!");
		}
		
		// Find the position and open up some space in the keys and value array
		int rank = this.rank(k);
		for (int i = this.size; i > rank; i--) {
			this.keys[i] = this.keys[i-1];
			this.data[i] = this.data[i-1];
		}
		
		// now insert the data in the 'rank' position and grow the structure
		this.keys [rank] = k;
		this.data [rank] = v;
		this.size++;
	}

	@Override
	public Value get(Key k) {
		if (isEmpty ()) return null;
		
		int idx = this.rank(k);
		if (idx < DEFAULT_SIZE && this.keys[idx].equals(k)) {
			return this.data[idx];
		} else {
			return null;
		}
	}

	@Override
	public void delete(Key k) {
		this.put (k, null);
	}

	@Override
	public boolean contains(Key k) {
		return this.get(k) != null;
	}

	@Override
	public boolean isEmpty() {
		return this.size() == 0;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return this.size;
	}


	@Override
	public Iterator<Key> iterator() {
		return new Iterator<Key> () {
			private int i = 0;
			private int size = BinarySearchArrayST.this.size;
			
			@Override
			public boolean hasNext() {
				return this.i < this.size;
			}

			@Override
			public Key next() {
				if (!this.hasNext()) {
					throw new NoSuchElementException("No more elements to iterate");
				}
				
				return BinarySearchArrayST.this.keys[i++];
			}

			@Override
			public void remove() {
				throw new UnsupportedOperationException();
			}
			
		};
	}
	
	@Override
	public Iterable<Key> keys() {
		return this;
	}

	public int rank (Key k) {
		int min = 0, max = this.size - 1;
		
		while (min <= max) {
			int middle = (min + max) / 2;
			
			int comparison = k.compareTo(this.keys[middle]); 
			if      (comparison < 0) max = middle - 1;
			else if (comparison > 0) min = middle + 1;
			else                     return middle; 
		}
		
		return min;
	}
	
	@Override
	public Key min() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Key max() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Key floor(Key k) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Key ceiling(Key k) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Key select(int k) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteMin() {
		// TODO Auto-generated method stub
	}

	@Override
	public void deleteMax() {
		// TODO Auto-generated method stub
	}

	@Override
	public int size(Key lo, Key hi) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Iterable<Key> keys(Key lo, Key hi) {
		// TODO Auto-generated method stub
		return null;
	}
}
