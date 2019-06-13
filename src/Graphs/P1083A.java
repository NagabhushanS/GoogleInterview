package Graphs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Stack;

public class P1083A{
	private static class Edge {
		int v, c;
		
		public Edge(int u, int c) {
			v=u;
			this.c=c;
		}
	}
	
	static int n;
	static HashMap<Integer, ArrayList<Edge>> g;
	static int[] c;
	static long[] dp1;
	static long ans;
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		c = new int[n];
		g = new HashMap<>();
		dp1 = new long[n];
		for(int i=0; i<n; i++) {
			c[i] = in.nextInt();
		}
		for(int i=0; i<n; i++) {
			g.put(i, new ArrayList<>());
		}
		for(int i=0; i<n-1; i++) {
			int p = in.nextInt()-1;
			int q = in.nextInt()-1;
			int cost = in.nextInt();
			g.get(p).add(new Edge(q, cost));
			g.get(q).add(new Edge(p, cost));
		}
		
		ans=0;
		
		dfs(0, -1);

		System.out.println(ans);
	}
	
	
	public static void dfs(int u, int p) {
		
		dp1[u]=c[u];
		ArrayList<Long> list = new ArrayList<>();
		
		for(Edge e: g.get(u)) {
			int v = e.v;
			if (v==p) continue;
			
			dfs(v, u);
			
			dp1[u] = Math.max(dp1[u], c[u]-e.c+dp1[v]);
			list.add(-e.c+dp1[v]);
		}
		int k = list.size();
		Collections.sort(list);
		long maxVal = 0;
		for(int i=0; i<k; i++) {
			maxVal = Math.max(maxVal, list.get(i));
		}
		for(int i=0; i<k; i++) {
			if (list.get(i)==maxVal) {
				list.set(i, -1L);
				break;
			}
		}
		long secondMaxVal = 0;
		for(int i=0; i<k; i++) {
			secondMaxVal = Math.max(secondMaxVal, list.get(i));
		}
		if (k>=2) {
			ans = Math.max(ans, maxVal+secondMaxVal+c[u]);
		} 
		ans = Math.max(ans, dp1[u]);
			
	}

}
