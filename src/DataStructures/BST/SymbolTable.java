package DataStructures.BST;

import java.util.HashMap;

class ST<Key, Value> {
	
	public ST() {
		
	}
	
	public void put(Key key, Value value) {
		
	}
	
	public Value get(Key key) {
		return null;
	}
	
	public void remove(Key key) {
		
	}
	
	public boolean isEmpty() {
		return false;
	}
	
	public int size() {
		return 0;
	}
	
	public Iterable<Key> keys(){
		return null;
	}
	
	public boolean contain(Key key) {
		return false;
	}
	
}

public class SymbolTable {

	public static void main(String[] args) {
		HashMap<Integer, Integer> map = new HashMap<>();
		map.put(1, null);
		System.out.println(map.containsKey(2));
	}

}
