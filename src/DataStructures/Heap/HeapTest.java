package DataStructures.Heap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

class MinHeap<Key extends Comparable<Key>>{
	
	private Key[] minHeap;
	private int size;
	private int capacity;
	
	
	public MinHeap(Key[] keys) {
		size = capacity = keys.length;
		minHeap = Arrays.copyOf(keys, keys.length);
		preprocess();
	}
	
	public MinHeap() {
		capacity = 2;
		size=0;
		minHeap = (Key[]) new Comparable[capacity];
	}
	
	private void preprocess() {
		for(int i=(size-1)/2; i>=0; i--) {
			shiftDown(i);
		}
	}
	
	public Key poll() {
		if (size()==0) {
			throw new IllegalStateException();
		}
		Key ans = minHeap[0];
		minHeap[0] = minHeap[--size];
		shiftDown(0);
		return ans;
	}
	
	private void shiftDown(int k) {
		
		while(2*k+1<size) {
			int j = 2*k+1;
			if (j+1<size && minHeap[j+1].compareTo(minHeap[j])<0) j++;
			if (minHeap[j].compareTo(minHeap[k])>0) break;
			Key temp = minHeap[j];
			minHeap[j] = minHeap[k];
			minHeap[k] = temp;
			k = j;
		}
	}

	public Key peek() {
		return minHeap[0];
	}
	
	public boolean isEmpty() {
		return size()==0?true:false;
	}
	
	public int size() {
		return size;
	}
	
	public void add(Key key) {
		if (size==capacity) {
			Key[] temp = (Key[]) new Comparable[capacity*2];
			for(int i=0; i<size; i++) {
				temp[i] = minHeap[i];
			}
			minHeap = temp;
			capacity*=2;
		}
		
		minHeap[size] = key;
		shiftUp(size);
		size++;
	}
	
	private void shiftUp(int k) {
		
		while(k>0 && minHeap[(k-1)/2].compareTo(minHeap[k])>0) {
			Key temp = minHeap[(k-1)/2];
			minHeap[(k-1)/2] = minHeap[k];
			minHeap[k] = temp;
			k = (k-1)/2;
		}
		
		
	}
	
	public void remove(Key key) {
		int i;
		for(i = 0; i<size; i++) {
			if (key.compareTo(minHeap[i])==0) {
				break;
			}
		}
		if (i==size) {
			//does not exist
			throw new IllegalArgumentException();
		}
		minHeap[i] = minHeap[--size];
		shiftDown(i);
	}
	
}

public class HeapTest {

	public static void main(String[] args) {
		MinHeap<Integer> heap = new MinHeap<>();
		Random rand = new Random();
		ArrayList<Integer> list = new ArrayList<>();
		Integer[] a = new Integer[500];
		for(int i=0; i<500; i++) {
			int u = rand.nextInt(1000);
			System.out.print(u+" ");
			heap.add(u);
			list.add(u);
			a[i] = u;
		}
		MinHeap<Integer> heap2 = new MinHeap<Integer>(a);
		System.out.println();
		Collections.sort(list);
		for(int x: list) {
			System.out.print(x+" ");
		}
		System.out.println();
		for(int i=0; i<500; i++) {
			int u = heap.peek();
			System.out.print(u+",");
//			heap.poll();
			heap.remove(u);
			System.out.print(heap2.poll()+" ");
		}
	}

}

//25.9 ---- 28