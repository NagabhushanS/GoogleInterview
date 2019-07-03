package AdvancedDS;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class P899E {

	private static int n;
	private static int[] a;
	private static int[] id;
	private static Queue<Integer> roots;
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		a = new int[n];
		for(int i=0; i<n; i++) {
			a[i] = in.nextInt();
		}

		id = new int[n];
		for(int i=0; i<n; i++) {
			id[i] = i;
		}
		
		roots = new LinkedList<>();
		for(int i=0; i<n; i++) {
			roots.add(i);
		}
		
		
		int count=n;
		while(count>0) {
			
		}
	}
	
	private static void union(int u, int v) {
		int p = root(u);
		int q = root(v);
		id[p] = q;
		
	}
	
	private static int root(int u) {
		while(u!=id[u]) {
			id[u] = id[id[u]];
			u = id[u];
		}
		return u;
	}
	
	private static boolean connected(int u, int v) {
		return root(u)==root(v);
	}

}
