public class FixedCapacityStackOfStrings {
	private int size;
	private String array[];
	
	public FixedCapacityStackOfStrings(int capacity) {
		this.size = 0;
		this.array = new String[capacity];
	}
	
	public void push(String data) {
		if (this.size == this.array.length) {
			this.resize(this.array.length * 2);
		}
		this.array[this.size++] = data;
	}
	
	public String pop () {
		String s = this.array[--this.size];
		this.array[this.size] = null;
		
		if (this.size == this.array.length/4) {
			this.resize(this.array.length/4);
		}
		
		return s;		
	}
	
	public int size() {
		return this.size;
	}
	
	public boolean isEmpty () {
		return this.size == 0;
	}
	
	private void resize(int newCapacity) {
		String newArray[] = new String[newCapacity];
		for (int i = 0; i < this.array.length; i++) {
			newArray[i] = this.array[i];
		}
		
		this.array = newArray;
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