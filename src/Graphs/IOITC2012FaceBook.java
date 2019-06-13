package Graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Scanner;

public class IOITC2012FaceBook {

	private static class Edge{
		int u, v, w;
		
		public Edge(int a, int b, int c) {
			u=a;
			v=b;
			w=c;
		}
		
		public int other(int a) {
			if (a==u) return v;
			else return u;
		}
	}
	
	static class Node {
		int u;
		Edge deletedEdge;
		
		public Node(int a, Edge e) {
			u=a;
			deletedEdge = e;
		}
		
		public int hashCode() {
			return u+(deletedEdge==null?0:deletedEdge.u+deletedEdge.v);
		}
		
		public boolean equals(Object o) {
			Node node = (Node)o;
			if (node.deletedEdge==null && deletedEdge!=null) {
				return false;
			} else if (node.deletedEdge!=null && deletedEdge==null) {
				
			}
			return u==node.u && (deletedEdge==null && node.deletedEdge==null || deletedEdge.u==node.deletedEdge.u && deletedEdge.v==node.deletedEdge.v);
		}
	}
	
	static int n, m;
	static HashMap<Node, ArrayList<Edge>> g;
	static Edge[] edges;
	static HashMap<Node, Integer> dist;
	static HashSet<Node> relaxed;
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		g = new HashMap<>();
		int n = in.nextInt();
		int m = in.nextInt();
		int Q = in.nextInt();
		for(int i=0; i<n; i++) {
			g.put(new Node(i, null), new ArrayList<>());
		}
		edges = new Edge[m];
		for(int i=0; i<m; i++) {
			int p = in.nextInt()-1;
			int q = in.nextInt()-1;
			int w = in.nextInt();
			edges[i] = new Edge(p, q, w);
			g.get(new Node(p, null)).add(edges[i]);
			g.get(new Node(q, null)).add(edges[i]);
		}
		relaxed = new HashSet<>();
		dist = new HashMap<>();
		dist.put(new Node(0, null), 0);
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(0, null));
		
		while(!pq.isEmpty()) {
			Node node = pq.poll();
			int u = node.u;
			if (relaxed.contains(node)) continue;
			relaxed.add(node);
			
			for(Edge e: g.get(node)) {
				int v = e.other(u);
				if (dist.get(new Node(v, node.deletedEdge))>dist.get(node)+e.w) {
					dist.put(new Node(v, node.deletedEdge), dist.get(node)+e.w);
				}
			}
			if (node.deletedEdge==null) {
				for(Edge e: g.get(node)) {
					//edge e is deleted
					for(Edge e2: g.get(node)) {
						int v = e2.other(u);
						if (e.u==e2.u && e.v==e2.v) continue;
						if (dist.get(new Node(v, e2))>dist.get(node)+e.w) {
							dist.put(new Node(v, e2), dist.get(node)+e.w);
						}
					}
				}
			}
		}
		
		for(int i=0; i<Q; i++) {
			int j = in.nextInt();
			Edge edgeDeleted = edges[j];
			System.out.println(dist.get(new Node(n-1, edgeDeleted)));
		}
	}

}

//4 4 2
//0 1 2
//1 2 2
//0 2 3
//2 3 4
//2
//3
