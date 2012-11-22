import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
	protected class Node {
		Item data = null;
		Node prev = null, next = null;
	}
	
	protected Node head = new Node(), tail = new Node();
	private int size;
	
	// construct an empty deque
	public Deque() {
		this.head = new Node();
		this.tail = new Node();
		
		this.head.next = this.tail;
		this.tail.prev = this.head;
		this.size = 0;
	}

	// is the deque empty?
	public boolean isEmpty() {
		return this.size == 0;
	}

	// return the number of items on the deque
	public int size() {
		return this.size;
	}

	// insert the item at the front
	public void addFirst(Item item) {
		if (item == null) {
			throw new NullPointerException("Don't insert nulls into the list");
		}
		
		Node newNode = new Node();
		newNode.data = item;
		newNode.prev = this.head;
		newNode.next = this.head.next;

		this.head.next.prev = newNode;
		this.head.next = newNode;
		
		this.size++;
	}

	// insert the item at the end
	public void addLast(Item item) {
		if (item == null) {
			throw new NullPointerException("Don't insert nulls into the list");
		}
		
		Node newNode = new Node();
		newNode.data = item;
		newNode.next = this.tail;
		newNode.prev = this.tail.prev;
		
		this.tail.prev.next = newNode;
		this.tail.prev = newNode;
		
		this.size++;
	}

	// delete and return the item at the front
	public Item removeFirst() {
		if (this.size == 0) {
			throw new NoSuchElementException("You cannot remove something from a null list");
		}
		
		Node removed = this.head.next;
		this.head.next = removed.next;
		
		this.size--;
		
		return removed.data;
	}

	// delete and return the item at the end
	public Item removeLast() {
		if (this.size == 0) {
			throw new NoSuchElementException("You cannot remove something from a null list");
		}
		
		Node removed = this.tail.prev;
		this.tail.prev = removed.prev;
		
		this.size--;
		
		return removed.data;
	}

	// return an iterator over items in order from front to end
	public Iterator<Item> iterator() {
		return new Iterator<Item>() {
			private Node curr = Deque.this.head;
			
			@Override
			public boolean hasNext() {
				return curr.next != Deque.this.tail;
			}
			
			@Override
			public Item next() {
				this.curr = this.curr.next;
				
				if (this.curr == Deque.this.tail) {
					throw new NoSuchElementException("No more elements");
				}
				
				return this.curr.data;
			}
			
			@Override
			public void remove() {
				throw new UnsupportedOperationException("Man, you can't remove elements from my Iterator!");
			}
		};
	}

	private static void asserte (boolean condition) {
		if (!condition)
			throw new RuntimeException("Assertion failed...");
	}
	
	public static void main (String args[]) {
		Deque<Integer> d = new Deque<Integer> ();
		
		d.addFirst(10);
		d.addFirst(20);
		d.addFirst(30);
		
		asserte (d.removeLast().equals(10));
		asserte (d.removeLast().equals(20));
		asserte (d.removeLast().equals(30));
		
	}
}