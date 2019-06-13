package AdvancedDS;

import java.io.BufferedInputStream;
import java.util.Scanner;

public class P1167C {

	private static class DSU {
		int[] id;
		int[] size;
		int[] height;
		
		public DSU (int n){
			id = new int[n];
			size = new int[n];
			height = new int[n];
			for(int i=0; i<n; i++) {
				id[i] = i;
			}
		}
		
		public boolean connected(int u, int v) {
			return root(u)==root(v);
		}
		
		public int root(int u) {
			
			while(u!=id[u]) {
//				id[u] = id[id[u]]; //path compression
				u = id[u];
			}
			
			return u;
		}
		
		public void union(int u, int v) {
			int i = root(u);
			int j = root(v);
			//weighted DSU
//			if (size[i]>size[j]) {
//				id[j]=i;
//				size[i]+=size[j];
//			} else {
//				id[i]=j;
//				size[j]+=size[i];
//			}
			if (height[i]>=height[j]) {
				id[j]=i;
				height[i]=Math.max(height[i], 1+height[j]);
			} else {
				id[i]=j;
				height[j]=Math.max(height[j], 1+height[i]);	
			}
		}
		
		public int[] getRoots() {
			int[] roots = new int[id.length];
			for(int i=0; i<id.length; i++) {
				roots[i]=root(i);
			}
			
			return roots;
		}
		
	}
	
	public static void main(String[] args) {
		Scanner in = new Scanner(new BufferedInputStream(System.in));
		int n = in.nextInt();
		int m = in.nextInt();
		DSU dsu = new DSU(n);
		for(int i=0; i<m; i++) {
			int q = in.nextInt();
			int last=-1;
			for(int j=0; j<q; j++) {
				int u = in.nextInt()-1;
				if (last==-1) {
					last=u;
				} else {
					dsu.union(last, u);
				}
			}
		}
		
		int roots[] = dsu.getRoots();
		int[] counts = new int[n];
		for(int i=0; i<n; i++) {
			counts[roots[i]]++;
		}
		
		StringBuilder builder = new StringBuilder();
		
		for(int i=0; i<n; i++) {
			builder.append(counts[roots[i]]+" ");
		}
		
		System.out.println(builder.toString());
		
		
	}

}
