package Graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.TreeSet;

public class P1037D {

	static class Node implements Comparable<Node>{
		int u;
		int index;
		
		public Node(int a, int b) {
			u=a;
			index=b;
		}
		
		public int compareTo(Node node) {
			return index-node.index;
		}
	}
	
	static int n;
	static HashMap<Integer, TreeSet<Node>> g;
	static int[] dist;
	static int[] index;
	static int[][] edges;
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		g = new HashMap<>();
		for(int i=0; i<n; i++) g.put(i, new TreeSet<>());
		edges = new int[n-1][2];
		for(int i=0; i<n-1; i++) {
			int p = in.nextInt()-1;
			int q = in.nextInt()-1;
			edges[i] = new int[] {p, q};
		}
		
		index = new int[n];
		
		
		for(int i=0; i<n; i++) {
			int p = in.nextInt()-1;
			index[p]=i;
		}
		
		for(int i=0; i<n-1; i++) {
			int p = edges[i][0];
			int q = edges[i][1];
			g.get(p).add(new Node(q, index[q]));
			g.get(q).add(new Node(p, index[p]));
		}
		
		Queue<Integer> queue = new LinkedList<>();
		queue.add(0);
		
		ArrayList<Integer> list = new ArrayList<>();
		boolean[] mark = new boolean[n];
		mark[0]=true;
		
		while(!queue.isEmpty()) {
			int u = queue.poll();
			list.add(u);
			
			for(Node node: g.get(u)) {
				int v = node.u;
				if (mark[v]) continue;
				queue.add(v);
				mark[v]=true;
			}
		}
		
		for(int i=0; i<n; i++) {
			if (index[list.get(i)]!=i) {
				System.out.println("No");
				return;
			}
		}
		System.out.println("Yes");

	}
	
//	private static void dfs(int u, int p) {
//		if (p!=-1)
//			dist[u]=dist[p]+1;
//		
//		for(Node node: g.get(u)) {
//			if (v==p) continue;
//			dfs(v, u);
//		}
//	}

}
