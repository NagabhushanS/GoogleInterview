package DataStructures.BST;

class SortedArrayST<Key extends Comparable<Key>, Value> {
	
	private Key[] keys;
	private Value[] values;
	private int capacity;
	private int size;
	
	public SortedArrayST() {
		keys = (Key[]) new Comparable[1];
		values = (Value[]) new Object[1];
		size=0;
		capacity = 1;
	}
	
	public void put(Key key, Value value) {
		int i;
		for(i=0; i<size && keys[i].compareTo(key)<=0; i++) {
			if (keys[i].equals(key)) {
				values[i] = value;
				return;
			}
		}
		
		if (size==capacity) {
			Key[] tempKeys = (Key[]) new Comparable[2*capacity];
			Value[] tempValues = (Value[]) new Object[2*capacity];
			for(int j=0; j<size; j++) {
				tempKeys[j] = keys[j];
				tempValues[j] = values[j];
			}
			capacity<<=1;
			keys = tempKeys;
			values = tempValues;
		}
		
		if (i<size) {
			for(int j=size; j>i; j--) {
				keys[j]=keys[j-1];
				values[j] = values[j-1];
			}
			keys[i] = key;
			values[i] = value;
		} else {
			keys[size] = key;
			values[size] = value;
		}
		size++;
		
	}
	
	public Value get(Key key) {
		int low=0;
		int high = size-1;
		int mid=0;
		while(low<=high) {
			mid = (low+high)/2;
			int cmp = key.compareTo(keys[mid]);
			if (cmp==0) {
				return values[mid];
			} else if (cmp>0) {
				low = mid+1;
			} else {
				high = mid-1;
			}
		}
		return null;
	}
	
	public int getSize() {
		return size;
	}
	
}

public class SortedArraySymbolTable {

	public static void main(String[] args) {
		SortedArrayST<Integer, Integer> st = new SortedArrayST<>();
		for(int i=0; i<100; i+=2) {
			st.put(i, i);
		}

		System.out.println(st.getSize());
		for(int i=99; i>=0; i-=2) {
			st.put(i, i*i);
		}

		System.out.println(st.getSize());
		System.out.println("DONE1");
		for(int i=100; i>=0; i--) {
			System.out.println(i+" "+st.get(i));
		}
		System.out.println("DONE2");
		System.out.println(st.getSize());

	}

}
