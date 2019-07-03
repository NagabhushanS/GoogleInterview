package AdvancedDS;

import java.util.HashMap;
import java.util.Scanner;

public class P459D {

	private static int n;
	private static int[] a;
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		a = new int[n];
		for(int i=0; i<n; i++) {
			a[i] = in.nextInt();
		}
		
		HashMap<Integer, Integer> set = new HashMap<>();
		int[] left = new int[n];
		for(int i=0; i<n; i++) {
			set.put(a[i], set.getOrDefault(a[i], 0)+1);
			left[i] = set.get(a[i]);
		}
		set.clear();
		int[] right = new int[n];
		for(int i=n-1; i>=0; i--) {
			set.put(a[i], set.getOrDefault(a[i], 0)+1);
			right[i] = set.get(a[i]);
		}
		long ans=0;
		for(int i=n-2; i>=0; i--) {
			for(int j=i+1; j<n; j++) {
				if (left[i]>right[j]) {
					ans++;
				}
			}
		}
		System.out.println(ans);

	}
	
	
	
	

}
