// TODO: when the array is empty, the two pointers point to idx 0. This may be a bug (check the size of an empty array, for instance)
public class ResizableArrayQueue {
	private int first, last;
	private String array[];
	
	public ResizableArrayQueue(int capacity) {
		this.first = this.last = 0;
		this.array = new String[capacity];
	}
	
	public void enqueue(String data) {
		if (this.last == this.array.length) {
			this.resize(this.array.length * 2);
		}
		this.array[this.last++] = data;
	}
	
	public String dequeue () {
		String s = this.array[this.first];
		this.array[this.first++] = null;
		
		// Is 75% of the array empty?
		if (this.first == this.array.length/4*3) {
			this.resize(this.array.length/4);
		}
		
		return s;		
	}
	
	public int size() {
		return this.last - this.first + 1;
	}
	
	public boolean isEmpty () {
		return this.first == this.last;
	}
	
	private void resize(int newCapacity) {
		String newArray[] = new String[newCapacity];
		int idx = 0;
		for (int i = this.first; i < this.last; i++) {
			newArray[idx++] = this.array[i];
		}
		
		this.last = this.last - this.first;
		this.first = 0;
		this.array = newArray;
	}
	/**
	 * Reads strings and print them in the reverse order
	 * @param args
	 */
	public static void main(String[] args) {
		ResizableArrayQueue stack = new ResizableArrayQueue (2);
		while (!StdIn.isEmpty()) {
			String s = StdIn.readString();
			
			if (s.equals ("-")) {
				StdOut.print(stack.dequeue());
			} else {
				stack.enqueue(s);
			}
		}
			
	}
}