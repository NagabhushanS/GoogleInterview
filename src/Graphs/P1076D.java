package Graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Scanner;

public class P1076D {
	static class Edge{
		int u, v;
		int w;
		
		public Edge(int a, int b, int c) {
			u=a;
			v=b;
			w=c;
		}
		
	}
	
	static class Node implements Comparable<Node>{
		int u;
		long dist;
		
		public Node(int a, long d) {
			u=a;
			dist=d;
		}
		
		public int compareTo(Node node) {
			long cp = dist-node.dist;
			if (cp>0) {
				return 1;
			} else if (cp<0) {
				return -1;
			} else {
				return 0;
			}
		}
//		
//		public int hashCode() {
//			return u;
//		}
	}
	
	static int n, m, k;
	static HashMap<Integer, ArrayList<Edge>> g = new HashMap<>();
	static int[] count;
	static boolean[] mark;
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		m = in.nextInt();
		k = in.nextInt();
		
		for(int i=0; i<n; i++) g.put(i, new ArrayList<>());
		
		for(int i=0; i<m; i++) {
			int p = in.nextInt()-1;
			int q = in.nextInt()-1;
			int w = in.nextInt();
			Edge e = new Edge(p, q, w);
			g.get(p).add(e);
			g.get(q).add(e);
		}
		
		PriorityQueue<Node> pq = new PriorityQueue<>();
		HashSet<Integer> relaxed = new HashSet<>();
		
		pq.add(new Node(0, 0));
		
		
		long[] dist = new long[n];
		Arrays.fill(dist, (long)1e15);
		dist[0]=0;
		
		int[] parent = new int[n];
		parent[0]=-1;
		int count=1;
		
		while(!pq.isEmpty()) {
			Node node = pq.poll();
			int u = node.u;
			
			if (!relaxed.contains(u)) {
				relaxed.add(u);
				count++;
				if (count==k+2) break;
				
				for(Edge e: g.get(u)) {
					int v = e.u==u?e.v:e.u;
					if (dist[v]>dist[u]+e.w) {
						parent[v]=u;
						dist[v]=dist[u]+e.w;
						pq.add(new Node(v, dist[v]));
					}
				}
			}
		}
		
//		count = new int[n];
//		mark = new boolean[n];
//		dfs(0);
		
		
		
	}
	
	public static void dfs(int u) {
		mark[u]=true;
		
		count[u]=1;
		
		for(Edge e: g.get(u)) {
			int v = e.u==u?e.v:e.u;
			if (mark[v]) continue;
			
			dfs(v);
			
			count[u]+=count[v];
		}
	}

}
