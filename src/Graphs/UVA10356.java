package Graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Scanner;

public class UVA10356 {

	private static class Node implements Comparable<Node>{
		private int u;
		private int f; //0--bycycle, 1--bywalk
		private long dist;
		
		public Node(int a, int b, long c) {
			u=a;
			f=b;
			dist=c;
		}
		
		public int compareTo(Node node) {
			return Long.compare(dist, node.dist);
		}
	}
	
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
	
	private static HashMap<Integer, ArrayList<Edge>> g;
	private static int n, m;
	
	public static void main(String[] args) {
		Scanner in  =new Scanner(System.in);
		int z=1;
		while(in.hasNext()) {
			n = in.nextInt();
			m = in.nextInt();
			
			g = new HashMap<>();
			for(int i=0; i<n; ++i) g.put(i, new ArrayList<>());
			for(int i=0; i<m; i++) {
				int u = in.nextInt();
				int v = in.nextInt();
				int w = in.nextInt();
				Edge e = new Edge(u, v, w);
				g.get(u).add(e);
				g.get(v).add(e);
			}
			
			long[][] dist = new long[n][2];
			for(int i=0; i<n; i++) {
				Arrays.fill(dist[i], (long)1e15);
			}
			
			PriorityQueue<Node> pq = new PriorityQueue<>();
			
			for(Edge e: g.get(0)) {
				int v = e.other(0);
				dist[v][1]=e.w;
				pq.add(new Node(v, 1, e.w));
			}
			
			boolean[][] relaxed = new boolean[n][2];
			boolean flag=false;
			while(!pq.isEmpty()) {
				Node node = pq.poll();
				
				int u = node.u;
				if (node.u==n-1 && node.f==0) {
					flag=true;
					System.out.println("Set #"+z+"\n"+node.dist);
					break;
				}
				
				if (relaxed[node.u][node.f]) continue;
				relaxed[node.u][node.f]=true;
				
				for(Edge e: g.get(u)) {
					int v = e.other(u);
					
					if (dist[v][1-node.f]>dist[u][node.f]+e.w) {
						dist[v][1-node.f]=dist[u][node.f]+e.w;
						pq.add(new Node(v, 1-node.f, dist[v][1-node.f]));
					}
				}
			}
			if (!flag) System.out.println("Set #"+z+"\n"+"?");
			z++;
		}
	}

}
