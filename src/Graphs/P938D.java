package Graphs;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.PriorityQueue;

public class P938D {
	
	private static class Node implements Comparable<Node>{
		private int u;
		private int watch;
		private long dist;
		
		public Node(int a, int b, long c) {
			u=a;
			watch=b;
			dist=c;
		}
		
		public int compareTo(Node node) {
			return Long.compare(dist, node.dist);
		}
	}
	
	private static class Edge{
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
	static int[] a;

	public static void main(String[] args) {
		InputReader in = new InputReader(System.in);
		PrintWriter w = new PrintWriter(System.out);
		n = in.nextInt();
		m = in.nextInt();
		g = new HashMap<>();
		for(int i=0; i<n; i++) g.put(i, new ArrayList<>());
		for(int i=0; i<m; i++) {
			int p = in.nextInt()-1;
			int q = in.nextInt()-1;
			int ww = 2*in.nextInt();
			Edge e = new Edge(p, q, ww);
			g.get(p).add(e);
			g.get(q).add(e);
		}
		a = new int[n];
		for(int i=0; i<n; i++) a[i] = in.nextInt();
		
		long[][] dist = new long[n][2];
		for(int i=0; i<n; i++) Arrays.fill(dist[i], (long)1e15);
		
		PriorityQueue<Node> pq = new PriorityQueue<>();
		HashSet<Integer> relaxed = new HashSet<>();
		
		for(int i=0; i<n; i++) {
			dist[i][1] = a[i];
			pq.add(new Node(i, 1, a[i]));
		}
		
		while(!pq.isEmpty()) {
			Node node = pq.poll();
			int u = node.u;
//			if (node.watch==1) continue;
			
			if (relaxed.contains(u)) continue;
			relaxed.add(u);
			
			for(Edge e: g.get(u)) {
				int v = e.other(u);
				
//				if (dist[v][1]>node.dist+e.w+a[v]) {
//					System.out.println("HELLO");
//					dist[v][1] = node.dist+e.w+a[v];
//					pq.add(new Node(v, 1, dist[v][1]));
//				}
				if (dist[v][1]>node.dist+e.w) {
//					System.out.println("HELLO");
					dist[v][1] = node.dist+e.w;
					pq.add(new Node(v, 1, dist[v][1]));
				}
			}
		}
		
		for(int i=0; i<n; i++) {
			w.print(dist[i][1]+" ");
		}
		w.println();
		w.flush();
	}
	
	static class InputReader {
		 
		private final InputStream stream;
		private final byte[] buf = new byte[8192];
		private int curChar, snumChars;
 
		public InputReader(InputStream st) {
			this.stream = st;
		}
 
		public int read() {
			if (snumChars == -1)
				throw new InputMismatchException();
			if (curChar >= snumChars) {
				curChar = 0;
				try {
					snumChars = stream.read(buf);
				} catch (IOException e) {
					throw new InputMismatchException();
				}
				if (snumChars <= 0)
					return -1;
			}
			return buf[curChar++];
		}
 
		public int nextInt() {
			int c = read();
			while (isSpaceChar(c)) {
				c = read();
			}
			int sgn = 1;
			if (c == '-') {
				sgn = -1;
				c = read();
			}
			int res = 0;
			do {
				res *= 10;
				res += c - '0';
				c = read();
			} while (!isSpaceChar(c));
			return res * sgn;
		}
 
		public long nextLong() {
			int c = read();
			while (isSpaceChar(c)) {
				c = read();
			}
			int sgn = 1;
			if (c == '-') {
				sgn = -1;
				c = read();
			}
			long res = 0;
			do {
				res *= 10;
				res += c - '0';
				c = read();
			} while (!isSpaceChar(c));
			return res * sgn;
		}
 
		public int[] nextIntArray(int n) {
			int a[] = new int[n];
			for (int i = 0; i < n; i++) {
				a[i] = nextInt();
			}
			return a;
		}
 
		public String readString() {
			int c = read();
			while (isSpaceChar(c)) {
				c = read();
			}
			StringBuilder res = new StringBuilder();
			do {
				res.appendCodePoint(c);
				c = read();
			} while (!isSpaceChar(c));
			return res.toString();
		}
 
		public String nextLine() {
			int c = read();
			while (isSpaceChar(c))
				c = read();
			StringBuilder res = new StringBuilder();
			do {
				res.appendCodePoint(c);
				c = read();
			} while (!isEndOfLine(c));
			return res.toString();
		}
 
		public boolean isSpaceChar(int c) {
			return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
		}
 
		private boolean isEndOfLine(int c) {
			return c == '\n' || c == '\r' || c == -1;
		}
 
	}

}
