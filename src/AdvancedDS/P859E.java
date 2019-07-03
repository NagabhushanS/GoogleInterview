package AdvancedDS;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

public class P859E {

	static int n;
	static int[] id;
	static int[] size;
	static final int MOD = (int)1e9+7;
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);	
		n = in.nextInt();
		id = new int[2*n];
		size = new int[2*n];
		
		for(int i=0; i<2*n; i++) id[i] = i;
		
		Arrays.fill(size, 1);
		
		boolean[] mark = new boolean[2*n];
		boolean[] selfLoops = new boolean[2*n];
		
		for(int i=0; i<n; i++) {
			int p = in.nextInt()-1;
			int q = in.nextInt()-1;
			if (p!=q && connected(p, q)) {
				mark[root(p)] = true;
			} else {
				union(p, q);
				if (p==q) {
					selfLoops[root(p)]=true;
				}
			}
		}
		
		HashSet<Integer> roots = new HashSet<>();
		
		for(int i=0; i<2*n; i++) {
			roots.add(root(i));
		}
		

		long ans=1;
		
		for(int x: roots) {
			if (selfLoops[x]){
			} else if (mark[x] && size[x]!=1) {
				ans = (ans*2)%MOD;
			}
			else {
				ans = (ans*size[x])%MOD;
			}
		}
		
		System.out.println(ans);
		
	}
	

	private static void union(int u, int v) {
		if (u==v) return;
		int p = root(u);
		int q = root(v);
		id[p] = q;
		size[q]+=size[p];
		
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
