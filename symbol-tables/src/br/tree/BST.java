package br.tree;

import java.util.Iterator;

import br.common.OrderedSymbolTable;
import br.common.Queue;

public class BST <Key extends Comparable<Key>, Value> implements OrderedSymbolTable <Key, Value> {
	private Node root;
	
	private class Node {
		Key key;
		Value val;
		Node left, right;
		int count;
		
		public Node (Key k, Value v) {
			this.key = k;
			this.val = v;
			
			this.count = 1;
			this.left = this.right = null;
		}
	}
	
	public BST () {
		this.root = null;
	}
	
	public void put (Key k, Value v) {
		root = this.put(this.root, k, v);
	}
	
	private Node put (Node r, Key k, Value v) {
		if (r == null) {
			return new Node (k, v);
		}
		
		int cmp = k.compareTo(r.key);
		if      (cmp < 0) r.left = put (r.left, k, v);
		else if (cmp > 0) r.right = put (r.right, k, v);
		else              r.val = v;
		
		r.count = 1 + size (r.left) + size (r.right);
		
		return r;
	}
	
	public Value get (Key k) {
		Node cRoot = this.root;

		while (cRoot != null) {
			int cmp = k.compareTo(cRoot.key);
			if      (cmp > 0) cRoot = cRoot.right; 
			else if (cmp < 0) cRoot = cRoot.left;
			else              return cRoot.val;
		}
		
		return null;
	}
	
	public void delete (Key k) {
		this.root = this.delete (this.root, k);
	}
	
	public Node delete (Node r, Key k) {
		if (r == null) {
			return null;
		}
		
		int cmp = k.compareTo(r.key);

		if      (cmp < 0) r.left  = delete (r.left, k);
		else if (cmp > 0) r.right = delete (r.right, k);
		else {
			if (r.right == null) {
				return r.left;
			} else {
				// Find and delete the minimum node in the right subtree
				Node minRight = this.min(r.right);
				r.right = this.deleteMin(r.right);
				
				minRight.right = r.right;
				minRight.left  = r.left;
				return minRight;
			}
		}
		
		return r;
	}
	
	public Iterable<Key> iterable() {
		Queue<Key> q = new Queue<Key>();
		this.inorder (this.root, q);
		return q;
	}

	/**
	 * Traverse the tree in order using a queue to store the keys
	 * @param n
	 * @param q
	 */
	private void inorder (Node n, Queue<Key> q) {
		if (n == null) return;
		
		this.inorder (n.left, q);
		q.enqueue (n.key);
		this.inorder (n.right, q);
	}

	@Override
	public boolean contains(Key k) {
		return this.get(k) != null;
	}

	@Override
	public boolean isEmpty() {
		return this.root == null;
	}

	@Override
	public int size() {
		return size (this.root);
	}
	
	private int size (Node n) {
		if (n == null) {
			return 0;
		}
		
		return n.count;
	}

	@Override
	public Iterable<Key> keys() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int rank(Key k) {
		return rank (this.root, k);
	}
	
	private int rank (Node r, Key k) {
		if (r == null) {
			return 0;
		}
		
		int cmp = k.compareTo(r.key);
		if      (cmp < 0) return this.rank(r.left, k);
		else if (cmp > 0) return 1 + this.size (r.left) + this.rank(r.right, k);
		else 
			return this.size (r.left); 
	}

	@Override
	public Key min() {
		Node n = this.min (this.root);
		if (n != null) 
			return n.key;
		
		return null;
	}
	
	private Node min (Node n) {
		if (this.root == null) {
			return null;
		}

		while (n.left != null) {
			n = n.left;
		}
		return n;
	}

	@Override
	public Key max() {
		Node n = this.max (this.root);
		if (n != null)
			return n.key;
		
		return null;		
	}
	
	private Node max (Node n) {
		if (this.root == null) {
			return null;
		}
		
		while (n.right != null) {
			n = n.right;
		}
		return n;
	}

	@Override
	public Key floor(Key k) {
		Node n = this.floor (this.root, k);
		if (n == null) {
			return null;
		}
		
		return n.key;
	}
	
	private Node floor (Node r, Key k) {
		if (r == null) {
			return null;
		}
		
		Node ret = null;
		int cmp = k.compareTo (r.key);
		if (cmp == 0) return r;
		if (cmp < 0) {
			ret = floor (r.left, k);
		} else {
			ret = floor (r.right, k);
		}
		
		if (ret != null) {
			return ret;
		}
		
		return r;
	}
	

	@Override
	public Key ceiling(Key k) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Key select(int k) {
		
		return null;
	}

	public void deleteMin() {
		this.root = this.deleteMin(this.root);
	}
	
	private Node deleteMin (Node n) {
		if (n == null) return null;

		if (n.left == null) {
			return n.right;
		}
		
		n.left = deleteMin (n.left);
		n.count = 1 + size(n.left) + size(n.right);
		
		return n;
	}

	@Override
	public void deleteMax() {
	}

	@Override
	public int size(Key lo, Key hi) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Iterable<Key> keys(Key lo, Key hi) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static void main(String[] args) {
		BST<String, Integer> t = new BST<String, Integer>();
		
		String a[] = {"S", "E", "X", "B", "O", "M", "N"};
		for (String s : a) {
			t.put (s, 1);
		}
		
		int i = t.get("S");
		t.delete("E");
		t.delete("S");
		
	}
}
