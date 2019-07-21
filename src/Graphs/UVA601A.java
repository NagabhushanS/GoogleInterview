package Graphs;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Queue;

public class UVA601A {

	private static class Node{
		private int u, v;
		//u train v is bus
		private long dist;
		
		public Node(int a, int b, long c) {
			u=a;
			v=b;
			dist=c;
		}
	}
	
	private static int n, m;
	private static HashMap<Integer, HashSet<Integer>> g;
	private static int[][] adj;
	
	
	public static void main(String[] args) {
		InputReader in = new InputReader(System.in);
		g = new HashMap<>();
		n = in.nextInt();
		m = in.nextInt();
		for(int i=0; i<n; i++) {
			g.put(i, new HashSet<>());
		}
		for(int i=0; i<m; i++) {
			int p = in.nextInt()-1;
			int q = in.nextInt()-1;
			g.get(p).add(q);
			g.get(q).add(p);
		}
		adj = new int[n][];
		for(int i=0; i<n; i++) {
			adj[i] = new int[n-g.get(i).size()];
			int counter=0;
			for(int j=0; j<n; j++) {
				if (!g.get(i).contains(j))
					adj[i][counter++] = j;
			}
		}
		
		Queue<Node> queue = new LinkedList<>();
		queue.add(new Node(0, 0, 0));
		
		boolean[][] mark = new boolean[n][n];
		mark[0][0] = true;
		
		while(!queue.isEmpty()) {
			
			Node node = queue.poll();
			
			if (node.u==n-1 && node.v==n-1) {
				System.out.println(node.dist);
				return;
			}
			int u = node.u;  //train
			int v = node.v;  //bus
			
			for(int u1: g.get(u)) {
				for(int v1: adj[v]) {
//					if (v1==v) continue;
					
					if (u1==v1 && u1!=n-1) continue;
					if (mark[u1][v1]) continue;
					if (g.get(v).contains(v1)) continue;
					mark[u1][v1] = true;
					queue.add(new Node(u1, v1, node.dist+1));
				}
				//bus same
				if (!mark[u1][v]) {
					if (u1==v && u1!=n-1) {
						queue.add(new Node(u1, v, node.dist+1));
						mark[u1][v]=true;
					}
				}
			}
		}
		System.out.println(-1);
		

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
