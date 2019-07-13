package Graphs;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.PriorityQueue;

public class UVA12047 {

	private static class Node implements Comparable<Node> {
		private int u;
		private long dist;

		public Node(int a, long b) {
			u = a;
			dist = b;
		}

		public int compareTo(Node node) {
			return Long.compare(dist, node.dist);
		}

	}

	private static class Edge {
		int u, v, w;

		public Edge(int a, int b, int c) {
			u = a;
			v = b;
			w = c;
		}
	}

	private static int n, m;
	private static HashMap<Integer, ArrayList<Edge>> g;
	private static HashMap<Integer, ArrayList<Edge>> gR;
	private static int s, t, p;

	public static void main(String[] args) {
		InputReader in = new InputReader(System.in);
		PrintWriter w = new PrintWriter(System.out);
		int T = in.nextInt();
		for (int z = 1; z <= T; z++) {
			n = in.nextInt();
			m = in.nextInt();
			s = in.nextInt()-1;
			t = in.nextInt()-1;
			p = in.nextInt();
			g = new HashMap<>();
			gR = new HashMap<>();
			for (int i = 0; i < n; i++) {
				g.put(i, new ArrayList<>());
				gR.put(i, new ArrayList<>());
			}
			for (int i = 0; i < m; i++) {
				int p = in.nextInt() - 1;
				int q = in.nextInt() - 1;
				int w1 = in.nextInt();
				Edge e = new Edge(p, q, w1);
				g.get(p).add(e);
				gR.get(q).add(e);
			}

			long[] dist1 = new long[n];
			long[] dist2 = new long[n];
			Arrays.fill(dist1, (long) 1e15);
			Arrays.fill(dist2, (long) 1e15);
			dist1[s] = 0;
			PriorityQueue<Node> pq = new PriorityQueue<>();
			pq.add(new Node(s, 0));
			boolean[] relaxed = new boolean[n];
			while (!pq.isEmpty()) {
				Node node = pq.poll();

				if (relaxed[node.u])
					continue;
				relaxed[node.u] = true;

				int u = node.u;

				for (Edge e : g.get(node.u)) {
					int v = (e.u == u ? e.v : e.u);
					dist1[v] = Math.min(dist1[v], dist1[u]+e.w);
					pq.add(new Node(v, dist1[u] + e.w));
				}
			}
			dist2[t]=0;
			pq.add(new Node(t, 0));
			Arrays.fill(relaxed, false);
			while (!pq.isEmpty()) {
				Node node = pq.poll();

				if (relaxed[node.u])
					continue;
				relaxed[node.u] = true;

				int u = node.u;

				for (Edge e : gR.get(node.u)) {
					int v = (e.u == u ? e.v : e.u);
					dist2[v] = Math.min(dist2[v], dist2[u]+e.w);
					pq.add(new Node(v, dist2[u] + e.w));
				}
			}
			long ans = -1*(long)1e15;
			
			for(int i=0; i<n; i++) {
				for(Edge e: g.get(i)) {
					int v = (e.u==i?e.v:e.u);
					if (dist1[i]+e.w+dist2[v]<=p) {
						ans = Math.max(ans, e.w);
					}
				}
			}
			
			w.println(ans<0?-1:ans);
		}
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
