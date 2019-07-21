package Strings;

import java.util.Arrays;

public class KeyIndexedCounting {

	public static void main(String[] args) {
		int[] a = {1, 3, 4, 1, 30, 10, 332, 12, 31, 31, 100, 456, 236, 5, 76, 999};
		int[] b = Arrays.copyOf(a, a.length);
		int[] c = Arrays.copyOf(a, a.length);
		keyIndexedSort(a, 1000);
		print(a);
		keyIndexedSortStable(b, 1000);
		print(b);
		Arrays.sort(c);
		print(c);

	}
	
	private static void keyIndexedSortStable(int[] a, int R) {
		// 0<=ai<R ai belongs to [0, R)
		int[] count = new int[R];
		
		for(int i=0; i<a.length; i++) {
			count[a[i]]++;
		}
		
		//compute cummulative counts
		for(int i=1; i<R; i++) {
			count[i] += count[i-1];
		}
		
		int[] aux = new int[a.length];
		
		for(int i=a.length-1; i>=0; i--) {
			aux[--count[a[i]]] = a[i];
		}
		
		for(int i=0; i<a.length; i++) {
			a[i] = aux[i];
		}
		
	}
	
	private static void keyIndexedSort(int[] a, int R) {
		// 0<=ai<R ai belongs to [0, R)
		int[] count = new int[R];
		
		for(int i=0; i<a.length; i++) {
			count[a[i]]++;
		}
		
		int[] aux = new int[a.length];
		
		int j=0;
		for(int i=0; i<R; i++) {
			while(count[i]!=0) {
				count[i]--;
				aux[j++] = i;
			}
		}
		
		for(int i=0; i<a.length; i++) {
			a[i] = aux[i];
		}
		
	}
	
	private static void print(int[] a) {
		for(int x: a) {
			System.out.print(x+" ");
		}
		System.out.println();
	}

}
