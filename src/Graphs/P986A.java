package Graphs;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Queue;

public class P986A {

	private static int n, m, k, s;
	private static HashMap<Integer, ArrayList<Integer>> g;
	private static HashMap<Integer, ArrayList<Integer>> typeToTown;
	
	public static void main(String[] args) {
		InputReader in = new InputReader(System.in);
		PrintWriter w = new PrintWriter(System.out);
		
		n = in.nextInt();
		m = in.nextInt();
		k = in.nextInt();
		s = in.nextInt();
		
		g = new HashMap<>();
		typeToTown = new HashMap<>();
		
		for(int i=0; i<n; i++) {
			g.put(i, new ArrayList<>());
			if (i<k) typeToTown.put(i, new ArrayList<Integer>());
		}
		
		for(int i=0; i<n; i++) {
			int type = in.nextInt()-1;
			typeToTown.get(type).add(i);
		}
		
		for(int i=0; i<m; i++) {
			int p = in.nextInt()-1;
			int q = in.nextInt()-1;
			g.get(p).add(q);
			g.get(q).add(p);
		}
		
		int[][] dist = new int[n][k];
		for(int i=0; i<n; i++) {
			Arrays.fill(dist[i], (int)1e9);
		}
		
		boolean[] mark = new boolean[n];
		Queue<Integer> queue = new ArrayDeque<>();
		
		for(int type=0; type<k; type++) {
			Arrays.fill(mark, false);
			for(int x: typeToTown.get(type)) {
				queue.add(x);
				dist[x][type]=0;
				mark[x]=true;
			}
			while(!queue.isEmpty()) {
				int u = queue.poll();
				
				for(int v: g.get(u)) {
					if (mark[v]) continue;
					queue.add(v);
					mark[v]=true;
					dist[v][type] = dist[u][type]+1;
				}
			}
			
			
		}
		
		for(int i=0; i<n; i++) {
			int[] distances = dist[i];
			int cost=0;
			Arrays.sort(distances);
			for(int j=0; j<s; j++) {
				cost+=distances[j];
			}
			w.print(cost+" ");
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
