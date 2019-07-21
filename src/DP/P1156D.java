package DP;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class P1156D {

	private static class Edge {
		private int u, v, type;
		
		public Edge(int a, int b, int c) {
			u=a;
			v=b;
			type=c;
		}
	}
	
	private static int n;
	private static HashMap<Integer, ArrayList<Edge>> g;
	private static long[] t1; //00000
	private static long[] t2; //11111
	private static long[] t3; //00011
	private static long ans=0;
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		g = new HashMap<>();
		for(int i=0; i<n; i++) {
			g.put(i, new ArrayList<>());
		}
		
		t1 = new long[n];
		t2 = new long[n];
		t3 = new long[n];
		
		for(int i=0; i<n-1; i++) {
			int u = in.nextInt()-1;
			int v = in.nextInt()-1;
			int c = in.nextInt();
			Edge e = new Edge(u, v, c);
			g.get(u).add(e);
			g.get(v).add(e);
		}
		
		dfs(0, -1);
		go(0, -1);
		
		System.out.println(ans);

	}
	
	private static void dfs(int u, int p) {
		
		for(Edge e: g.get(u)) {
			int v = e.u==u?e.v:e.u;
			if (v==p) continue;
			int t = e.type;
			dfs(v, u);
			if (t==0) {
				t1[u] += 1+t1[v];
				t2[u] += 0;
				t3[u] += t2[v]+t3[v];
			} else {
				t1[u] += 0;
				t2[u] += 1+t2[v];
				t3[u] += 0;
			}
		}
	}

	private static void go(int u, int p) {
		//u is at root
		ans += t1[u]+t2[u]+t3[u];
		
		for(Edge e: g.get(u)) {
			int v = e.u==u?e.v:e.u;
			if (v==p) continue;
			int t = e.type;
			if (t==0) {
				t1[u] -= 1+t1[v];
				t2[u] -= 0;
				t3[u] -= t2[v]+t3[v];
				t1[v] += 1+t1[u];
				t2[v] += 0;
				t3[v] += t2[u]+t3[u];
				go(v, u);
				t3[v] -= t2[u]+t3[u];
				t2[v] -= 0;
				t1[v] -= 1+t1[u];
				t3[u] += t2[v]+t3[v];
				t2[u] += 0;
				t1[u] += 1+t1[v];
			} else {
				t1[u] -= 0;
				t2[u] -= 1+t2[v];
				t3[u] -= 0;
				t1[v] += 0;
				t2[v] += 1+t2[u];
				t3[v] += 0;
				go(v, u);
				t3[v] -= 0;
				t2[v] -= 1+t2[u];
				t1[v] -= 0;
				t3[u] += 0;
				t2[u] += 1+t2[v];
				t1[u] += 0;
			}
		}
	}
	
}
