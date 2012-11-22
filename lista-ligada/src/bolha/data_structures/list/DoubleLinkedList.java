package bolha.data_structures.list;

import bolha.data_structures.Data;

// TODO: heranca
public class DoubleLinkedList {

	private DoubleLinkedNode head, tail;
	
	public DoubleLinkedList() {
		this.head = this.tail = null;
	}
	
	public void append (Data d) {
		if (this.head == null) {
			this.tail = this.head = new DoubleLinkedNode (null, d, null);
		} else {
			DoubleLinkedNode newNode = new DoubleLinkedNode (this.tail, d, null);
			this.tail.setNext(newNode);
			this.tail = newNode;
		}
	}
	
	private void insertFirst (Data d) {
		if (this.head == null) {
			this.tail = this.head = new DoubleLinkedNode (null, d, null);
		} else {
			DoubleLinkedNode newNode = new DoubleLinkedNode (null, d, this.head);
			this.head.setPrev(newNode);
			this.head = newNode;
		}
	}
	
	private void insertAfter (Data d, DoubleLinkedNode curr) {
		if (curr == null) {
			return;
		}
		
		DoubleLinkedNode newNode = new DoubleLinkedNode (curr, d, curr.getNext());
		if (curr.getNext() != null) {
			curr.getNext().setPrev (newNode);
		}
		curr.setNext(newNode);
		
		// Adjust the tail of the list
		if (curr == this.tail) {
			this.tail = curr;
		}
	}
	
	public void insert (Data d, int pos) {
		
		// Insercao na cabecao
		// TODO: da pra fazer melhor?
		if (pos == 0) {
			this.insertFirst(d);
			return;
		}
		
		DoubleLinkedNode curr = this.head;
		while (curr != null && --pos > 0) {
			// TODO: cast do foda-se....
			curr = (DoubleLinkedNode) curr.getNext();
		}
		
		// Insercao no meio
		if (curr != null) {
			this.insertAfter(d, curr);
		} else {
			// insercao no fim
			this.append(d);
		}
	}
	
	public void insertInOrder () {
		// TODO
	}
	
	// TODO: buna remocao de item do meio
	public void delete (Data d) {
		DoubleLinkedNode n = this.search(d);
		this.deleteNode(n);
	}
	
	public DoubleLinkedNode search (Data d) {
		DoubleLinkedNode curr = this.head;
		
		while (curr != null) {
			if (curr.getData().equals(d)) {
				return curr;
			}
			curr = curr.getNext();
		}
		
		return null;
	}
	
	private void deleteNode (DoubleLinkedNode n) {
		if (n != null) {
			// Remocao do primeiro no?
			if (n.getPrev() == null) {
				this.head = n.getNext();
			} else {
				n.getPrev().setNext(n.getNext());
			}
			
			// Remocao do ultimo no?
			if (n.getNext() == null) {
				this.tail = n.getPrev();
			} else {
				n.getNext().setPrev(n.getPrev());
			}
			
			// Is it the last node from the list?
			if (n == this.head && n == this.tail) {
				this.head = this.tail = null;
			}
			
			n.setNext(null);
			n.setPrev(null);
		}
	}
	
	public void remove (int pos) {
		DoubleLinkedNode curr = this.head;
		for (int i = 0; i < pos && curr != null; i++) {
			curr = curr.getNext();
		}
		
		this.deleteNode(curr);
	}
	
	public String toString () {
		StringBuffer b = new StringBuffer ("[");
		DoubleLinkedNode curr = this.head;
		
		while (curr != null) {
			b.append(curr.toString());
			curr = curr.getNext();
		}
		
		return b.toString();
	}
	
	public static void main(String[] args) {
		DoubleLinkedList l = new DoubleLinkedList();
		
		l.append(new Data("1"));
		l.append(new Data("3"));
		l.append(new Data("4"));
		l.insert(new Data("2"), 1);
		l.insert(new Data("0"), 0);
		l.insert(new Data("5"), 5);
		System.out.println(l);

		l.delete(new Data ("0"));
		l.delete(new Data ("3"));
		l.delete(new Data ("5"));
		System.out.println(l);
		
		l.remove(0);
		l.remove(1);
		l.remove(0);
		System.out.println(l);
	}
}
