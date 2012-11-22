package bolha.data_structures.list;

import bolha.data_structures.Data;

// TODO: implementar Generics
public class Node {
	private Data data;
	private Node next;
	
	public Node (Data data, Node next) {
		this.data = data;
		this.next = next;
	}

	public Data getData() {
		return data;
	}

	public void setData(Data data) {
		this.data = data;
	}

	public Node getNext() {
		return next;
	}

	public void setNext(Node next) {
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
