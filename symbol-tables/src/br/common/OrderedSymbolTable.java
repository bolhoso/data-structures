package br.common;
public interface OrderedSymbolTable<Key extends Comparable<Key>, Value>extends SymbolTable<Key, Value> {
	public Key min();
	public Key max();
	public Key floor (Key k);
	public Key ceiling (Key k);

	public Key select (int k);
	public void deleteMin();
	public void deleteMax();
	
	public int size (Key lo, Key hi);
	public Iterable <Key> keys (Key lo, Key hi);
}
