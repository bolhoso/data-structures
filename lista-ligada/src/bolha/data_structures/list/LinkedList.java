package bolha.data_structures.list;

import bolha.data_structures.Data;

public class LinkedList {
	private Node head, last;
	
	public LinkedList() {
		this.head = null;
		this.last = null;
	}
	
	public void append (Data data) {
		if (this.head == null) {
			this.head = new Node (data, null);
			this.last = this.head;
		} else {
			Node newLast = new Node (data, null);
			this.last.setNext(newLast);
			this.last = newLast;
		}
	}
	
	public String toString () {
		StringBuffer b = new StringBuffer ("[");
		Node curr = this.head;
		
		while (curr != null) {
			b.append(curr.toString());
			curr = curr.getNext();
		}
		
		return b.toString();
	}
	
	protected Node getHead() {
		return head;
	}

	protected void setHead(Node head) {
		this.head = head;
	}

	protected Node getLast() {
		return last;
	}

	protected void setLast(Node last) {
		this.last = last;
	}

	public static void main(String[] args) {
		LinkedList l = new LinkedList();
		
		l.append(new Data("Lucas"));
		l.append(new Data("Maria"));
		l.append(new Data("Pedro"));
		l.append(new Data("Joao"));
		
		System.out.println(l);
	}
}
