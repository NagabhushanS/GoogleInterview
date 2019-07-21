package DP;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class P1083A {

	private static class Edge {
		private int u, v, w;
		
		public Edge(int a, int b, int c) {
			u=a;
			v=b;
			w=c;
		}
	}

	private static int n;
	private static int[] a;
	private static HashMap<Integer, ArrayList<Edge>> g;
	private static long[] maxPetrol;
	private static long ans=0;
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		a = new int[n];
		maxPetrol = new long[n];
		for(int i=0; i<n; i++) {
			a[i] = in.nextInt();
		}
		g = new HashMap<>();
		for(int i=0; i<n; i++) {
			g.put(i, new ArrayList<>());
		}
		for(int i=0; i<n-1; i++) {
			int p = in.nextInt()-1;
			int q = in.nextInt()-1;
			int w = in.nextInt();
			Edge e = new Edge(p, q, w);
			g.get(p).add(e);
			g.get(q).add(e);
		}

		dfs(0, -1);
		System.out.println(ans);
	}

	private static void dfs(int u, int p) {
		
		ArrayList<Long> pathRewards = new ArrayList<Long>();
		
		for(Edge e: g.get(u)) {
			int v = e.u==u?e.v:e.u;
			if (v==p) continue;
			dfs(v, u);
			maxPetrol[u] = Math.max(maxPetrol[u], maxPetrol[v]+a[v]-e.w);
			if (maxPetrol[v]+a[v]-e.w>0)
				pathRewards.add(maxPetrol[v]+a[v]-e.w);
		}
		
		Collections.sort(pathRewards);
		if (pathRewards.size()>=2) {
			int ss = pathRewards.size();
			ans = Math.max(ans, pathRewards.get(ss-1)+pathRewards.get(ss-2)+a[u]);
		} else if (pathRewards.size()==1) {
			int ss = pathRewards.size();
			ans = Math.max(ans, pathRewards.get(0)+a[u]);
		} else {
			ans = Math.max(ans, a[u]);
		}
	}
	
}
