public class StackOfStrings {
	private int size;
	private Node head;
	
	private class Node {
		String item;
		Node next;
	}
	
	public StackOfStrings() {
		this.size = 0;
		this.head = null;
	}
	
	public void push(String data) {
		Node newNode = new Node();
		newNode.item = data;
		newNode.next = this.head;
		this.head = newNode;
	}
	
	public String pop () {
		String data = this.head.item;
		this.head = this.head.next;
		return data;
	}
	
	public int size() {
		return this.size;
	}
	
	public boolean isEmpty () {
		return this.size == 0;
	}
	
	/**
	 * Reads strings and print them in the reverse order
	 * @param args
	 */
	public static void main(String[] args) {
		StackOfStrings stack = new StackOfStrings ();
		while (!StdIn.isEmpty()) {
			String s = StdIn.readString();
			
			if (s.equals ("-")) {
				StdOut.print(stack.pop());
			} else {
				stack.push(s);
			}
		}
			
	}
}