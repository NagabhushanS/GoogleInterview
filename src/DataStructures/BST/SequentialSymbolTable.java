package DataStructures.BST;

import java.util.ArrayList;

class SequentialST<Key, Value> {
	
	private class Node{
		Key key;
		Value value;
		Node next;
		
		public Node(Key k, Value v, Node n) {
			key = k;
			value=v;
			next=n;
		}
		
	}
	
	private Node head;
	private int count;
	
	public SequentialST() {
		head = null;
	}
	
	public void put(Key key, Value value) {
		Node node = head;
		while(node!=null) {
			if (node.key.equals(key)) {
				node.value = value;
				return;
			}
			node = node.next;
		}
		count++;
		head = new Node(key, value, head);
	}
	
	public Value get(Key key) {
		Node node = head;
		while(node!=null) {
			if (node.key.equals(key)) {
				return node.value;
			}
			node = node.next;
		}
		return null;
	}
	
	public int size() {
		return count;
	}
	
	public boolean equals() {
		return size()==0;
	}
	
	public Iterable<Key> keys(){
		ArrayList<Key> keys = new ArrayList<>();
		Node node = head;
		while(node!=null) {
			keys.add(node.key);
			node = node.next;
		}
		return keys;
	}
	
	
}

public class SequentialSymbolTable {

	public static void main(String[] args) {
		SequentialST<Integer, Integer> st = new SequentialST<>();
		st.put(1, null);
		st.put(2, 2);
		for(int k: st.keys()) {
			System.out.println(k+" "+st.get(k));
		}
	}

}
