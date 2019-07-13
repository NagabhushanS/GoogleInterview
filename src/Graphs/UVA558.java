package Graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;


public class UVA558 {

	private static class Edge {
		private int u, v, w;
		
		public Edge(int a, int b, int c) {
			u=a;
			v=b;
			w=c;
		}
		
		public int other(int x) {
			return x==u?v:u;
		}
	}
	
	private static int n, m;
	private static HashMap<Integer, ArrayList<Edge>> g;
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int T = in.nextInt();
		for(int z=1; z<=T; z++) {
			n = in.nextInt();
			m = in.nextInt();
			g = new HashMap<>();
			for(int i=0; i<n; i++) {
				g.put(i, new ArrayList<>());
			}
			for(int i=0; i<m; i++) {
				int u = in.nextInt();
				int v = in.nextInt();
				int w = in.nextInt();
				g.get(u).add(new Edge(u, v, w));
			}
			
			long[] dist = new long[n];
			Arrays.fill(dist, (long)1e15);
			boolean ans = false;
			
			for(int i=0; i<n; i++) {
				for(int u=0; u<n; u++) {
					for(Edge e: g.get(u)) {
						int v = e.other(u);
						if (dist[v]>dist[u]+e.w) {
							if (i==n-1) {
								ans=true;
							}
							dist[v]=dist[u]+e.w;
						}
					}
				}
			}
			
			System.out.println(ans?"possible":"not possible");
		}
	}

}
