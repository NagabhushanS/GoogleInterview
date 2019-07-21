package Graphs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class UVA796 {

	private static class Edge implements Comparable<Edge>{
		private int u, v;
		
		public Edge(int a, int b) {
			u=a;
			v=b;
		}
		
		public int compareTo(Edge e) {
			if (u>e.u) {
				return 1;
			} else if (u<e.u) {
				return -1;
			} else {
				return v-e.v;
			}
		}
	}
	
	private static int n;
	private static HashMap<Integer, HashSet<Integer>> g;
	private static int[] low;
	private static int[] num;
	private static ArrayList<Edge> edges;
	private static boolean[] mark;
	private static int time;
	
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while(in.hasNext()) {
			n = in.nextInt();
//			if (n==0) {
//				System.out.println("0 critical links");
//				break;
//			}
			g = new HashMap<>();
			for(int i=0; i<n; i++) g.put(i, new HashSet<>());
			for(int i=0; i<n; i++) {
				int u = in.nextInt();
				String deg = in.next();
				deg = deg.trim().substring(1, deg.length()-1);
//				System.out.println("DEG "+deg);
				for(int j=0; j<Integer.parseInt(deg); j++) {
					int v = in.nextInt();
//					System.out.println(v);
					g.get(u).add(v);
					g.get(v).add(u);
				}
				
			}
			
			low = new int[n];
			num = new int[n];
			mark = new boolean[n];
			time = 0;
			edges = new ArrayList<>();
			
			for(int u=0; u<n; u++) {
				if (!mark[u])
					dfs(u, -1);
			}
			
			Collections.sort(edges);
			System.out.println(edges.size()+" critical links");
			for(Edge e: edges) {
				System.out.println(Math.min(e.u, e.v)+" - "+Math.max(e.v, e.u));
			}
			
			System.out.println();
		}

	}

	private static void dfs(int u, int p) {
		low[u] = num[u] = time++;
		mark[u] = true;	
		
		for(int v: g.get(u)) {
			if (v==p) continue;
			
			if (!mark[v]) {
				dfs(v, u);
				
				low[u] = Math.min(low[u], low[v]);
				
				if (low[v]>num[u]) {
					edges.add(new Edge(u, v));
				}
			} else {
				low[u] = Math.min(low[u], num[v]);
			}
		}
	}
}
