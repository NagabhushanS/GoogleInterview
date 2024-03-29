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

public class P1076D {
	static class Edge{
		int u, v;
		int w;
		
		public Edge(int a, int b, int c) {
			u=a;
			v=b;
			w=c;
		}
		
		public int hashCode() {
			return u+v;
		}
		
		public boolean equals(Object o) {
			Edge e = (Edge)o;
			return (u==e.u && v==e.v || u==e.v && v==e.u);
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
			return Long.compare(dist, node.dist);
		}
	
	}
	
	static int n, m, k;
	static HashMap<Integer, ArrayList<Edge>> g = new HashMap<>();
	static HashMap<Edge, Integer> edgeMap = new HashMap<>();

	public static void main(String[] args) {
		InputReader in = new InputReader(System.in);
		PrintWriter w2 = new PrintWriter(System.out);
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
			edgeMap.put(e, (i+1));
		}
		
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(0, 0));
		
		HashSet<Integer> relaxed = new HashSet<>();
		long[] dist = new long[n];
		Arrays.fill(dist, (long)1e15);
		dist[0]=0;
		int[] par = new int[n];
		Arrays.fill(par, -1);
		int count=0;
		ArrayList<Integer> finalNodes = new ArrayList<>();
		while(!pq.isEmpty()) {
			Node node = pq.poll();
			int u = node.u;
			
			if (relaxed.contains(u)) continue;
			relaxed.add(u);
			count++;
			finalNodes.add(u);
			if (count==k+1) {
				break;
			}
			
			for(Edge e: g.get(u)) {
				int v = e.u==u?e.v:	e.u;
				if (dist[v]>dist[u]+e.w) {
					dist[v] = dist[u]+e.w;
					pq.add(new Node(v, dist[v]));
					par[v] = u;
				}
			}
		}
		w2.println(Math.min(n-1, k));
		for(int i: finalNodes) {
			if (par[i]!=-1) {
				w2.print(edgeMap.get(new Edge(i, par[i], 0))+" ");
			}
		}
		w2.flush();
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
