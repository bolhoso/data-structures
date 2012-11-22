package bolha.data_structures.list;

import bolha.data_structures.Data;

public class DoubleLinkedNode {
	private DoubleLinkedNode prev, next;
	private Data data;
	
	
	public DoubleLinkedNode (DoubleLinkedNode prev, Data d, DoubleLinkedNode next) {
		this.setPrev (prev);
		this.setNext (next);
		this.setData(d);
	}

	public DoubleLinkedNode getPrev() {
		return prev;
	}
	
	public void setPrev(DoubleLinkedNode prev) {
		this.prev = prev;
	}
	
	public DoubleLinkedNode (Data data, DoubleLinkedNode next) {
		this.data = data;
		this.next = next;
	}

	public Data getData() {
		return data;
	}

	public void setData(Data data) {
		this.data = data;
	}

	public DoubleLinkedNode getNext() {
		return next;
	}

	public void setNext(DoubleLinkedNode next) {
		this.next = next;
	}
	
	public String toString () {
		StringBuffer out = new StringBuffer(this.data.toString());
		if (this.next == null) {
			out.append("]");
		} else {
			out.append(", ");
		}
		
		return out.toString();
	}

}
