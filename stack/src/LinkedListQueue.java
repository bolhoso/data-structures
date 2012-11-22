public class LinkedListQueue {
	private int size;
	private Node head, tail;

	private class Node {
		String item;
		Node next;
	}

	public LinkedListQueue() {
		this.size = 0;
		this.head = this.tail = null;
	}

	public void enqueue (String data) {
		Node oldLast = this.tail;
		this.tail = new Node();
		this.tail.item = data;
		this.tail.next = null;
		
		if (this.isEmpty()) {
			this.head = this.tail;
		} else {
			oldLast.next = this.tail;
		}
	}

	public String dequeue() {
		String data = this.head.item;
		this.head = this.head.next;
		
		if (this.isEmpty()) {
			this.tail = null;
		}
		return data;
	}
	
	public boolean isEmpty () {
		return this.tail == null;
	}

	/**
	 * Reads strings and print them in the reverse order
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		StackOfStrings stack = new StackOfStrings();
		while (!StdIn.isEmpty()) {
			String s = StdIn.readString();

			if (s.equals("-")) {
				StdOut.print(stack.pop());
			} else {
				stack.push(s);
			}
		}

	}
}
