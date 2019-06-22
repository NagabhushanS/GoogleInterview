package Graphs;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

public class P1081D {
	class Edge implements Comparable<Edge> {
		int u;
		int v;
		int w;
		
		public Edge(int a, int b, int c) {
			u=a;
			v=b;
			w=c;
		}
		
		public int compareTo(Edge e) {
			return w-e.w;
		}
		
		
		
	}
	
	private Edge[] edges;
	private int[] id; //dsu
	private int n;
	private int m;
	private int[] count;
	private HashSet<Integer> special;
	public P1081D (int[][] edgeList, int n, int[] sp) {
		this.n = n;
		m = edgeList.length;
		edges = new Edge[m];
		for(int i=0; i<m; i++) {
			edges[i] = new Edge(edgeList[i][0], edgeList[i][1], edgeList[i][2]);
		}
		id = new int[n];
		for(int i=0; i<n; i++) id[i] = i;
		Arrays.sort(edges);
		special = new HashSet<>();
		for(int i=0; i<sp.length; i++) {
			special.add(sp[i]);
		}
		count = new int[n];
		for(int i=0; i<n; i++) {
			if (special.contains(i)) {
				count[i]=1;
			}
		}
	}
	
	public int getSolution() {
		int ans=0;
		for(Edge e: edges) {
			int u = e.u;
			int v = e.v;
			
			if (connected(u, v)) {
				continue;
			}
			ans = e.w;
			union(u, v);
			
			if (count[root(u)]==special.size()) {
				break;
			}
		}
		
		return ans;
	}

	private boolean connected(int u, int v) {
		return root(u)==root(v);
	}
	
	private int root(int u) {
		while(u!=id[u]) {
			id[u] = id[id[u]]; //path compression //logn
			u = id[u];
		}
		return u;
	}
	
	private void union(int u, int v) {
		int p = root(u);
		int q = root(v);
		id[p]=q;
		count[q]+=count[p];
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int m = in.nextInt();
		int k = in.nextInt();
		int[] sp = new int[k];
		for(int i=0; i<k; i++) {
			sp[i] = in.nextInt()-1;
		}
		int[][] edges = new int[m][3];
		for(int i=0; i<m; i++) {
			edges[i] = new int[] {in.nextInt()-1, in.nextInt()-1, in.nextInt()};
		}
		P1081D sol = new P1081D(edges, n, sp);
		int ans= sol.getSolution();
		for(int i=0; i<k; i++) {
			System.out.print(ans+" ");
		}
	}

}
