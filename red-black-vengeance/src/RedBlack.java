
public class RedBlack<K extends Comparable<K>,V> {
		
	/**
	 * A simple red-black node
	 */
	private class Node<K, V> {
		public static final boolean 
			BLACK = true,
			RED = false;
		
		K key;
		V val;
		Node<K,V> left, right;
		boolean color;
	
		public Node(K key, V val, boolean color) {
			this.key = key;
			this.val = val;
			this.color = color;
			
			this.left = this.right = null;
		}
	}
	
	private Node<K, V> root = null;
	
	/**
	 * Initialize the tree
	 */
	public RedBlack() {
		this.root = null;
	}
	
	//=========================================================================
	// Insertion in the tree
	//=========================================================================
	
	/**
	 * Inserts a node in the tree preserving the black balance
	 * 
	 * @param key
	 * @param val
	 */
	public void insert (K key, V val) {
		if (this.root == null) {
			this.root = new Node<K, V> (key, val, Node.RED);
		} else {
			this.root = this.insert (this.root, key, val);
		}
		
		this.root.color = Node.BLACK;
	}
	
	/**
	 * Recursive insertion in the tree
	 * @param node
	 * @param data
	 * @return
	 */
	private Node<K,V> insert (Node<K,V> node, K key, V val) {
		if (node == null) {
			return new Node<K,V> (key, val, Node.RED);
		}

		// Find the position to the node
		int cmp = key.compareTo(node.key);
		if      (cmp < 0) node.left  = this.insert(node.left, key, val);
		else if (cmp > 0) node.right = this.insert(node.right, key, val);
		else              throw new UnsupportedOperationException("The tree " +
				"can't stand for duplicate nodes, your god'am bastard!");
		
		// Now, check the tree conditions to maintain it balanced
		//  1) Red link standing on the right
		if (isRed (node.right)) {
			node = rotateLeft (node);
		}
		
		//  2) Or two consecutive red nodes in the left link
		if (isRed (node.left) && isRed (node.left.left)) {
			node = rotateRight (node);
		}
		
		//  3) Two red links in both left and right
		if (isRed (node.left) && isRed (node.right)) {
			flipColors (node);
		}
		
		return node;
	}
	
	//=========================================================================
	// RedBlack operations to maintain balance
	//=========================================================================
	/**
	 * Left rotation: (/ black link, // red link)
	 *     X                   Y
	 *    / \\     (left)    // \
	 *  (<x)  Y     ==>       X  (>y)   
	 *       /  \            / \     
	 *  (X<.<y) (>y)      (<X) (X<.<y) 
	 *  
	 * @param x The node to rotate
	 * @return The new root of this subtree 
	 */
	private Node<K,V> rotateLeft (Node<K,V> x) {
		Node<K,V> y = x.right;
		
		x.right = y.left;
		y.left  = x;
		
		// Correct the colors
		y.color = x.color;
		x.color = Node.RED;
		
		// The new root of this subtree
		return y;
	}
	
	/**
	 * Left rotation: (/ black link, // red link)
	 *      x               Y        
	 *    // \             / \\      
	 *     y  (>x)  ==>  (<y)  X     
	 *    / \                 /  \   
	 * (<y) (y<.<x)      (y<.<x) (>x)
	 *  
	 * @param x The node to rotate
	 * @return The new root of this subtree 
	 */
	private Node<K,V> rotateRight (Node<K,V> x) {
		Node<K,V> y = x.left;
		
		x.left = y.right;
		y.right = x;
		
		y.color = x.color;
		x.color = Node.RED;
		
		return y;
	}
	
	private void flipColors (Node<K,V> x) {
		x.color = Node.RED;
		x.right.color = Node.BLACK;
		x.left.color  = Node.BLACK;
	}
	
	/**
	 * Check if a node is red
	 * 
	 * @param x
	 * @return
	 */
	private boolean isRed (Node x) {
		return x != null && x.color == Node.RED;
	}
	
	
	//=========================================================================
	// Manual testing...
	//=========================================================================
	public static void main(String[] args) {
		RedBlack<String, String> t = new RedBlack<String,String>();
		
		while (!StdIn.isEmpty()) {
			String val = StdIn.readLine();
			t.insert(val, val);
			
		}
	}
}
