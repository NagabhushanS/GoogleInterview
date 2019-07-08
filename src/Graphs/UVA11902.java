package Graphs;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.InputMismatchException;

public class UVA11902 {

	private static int n;
	private static HashMap<Integer, ArrayList<Integer>> g;
	private static boolean[] mark;
	
	public static void main(String[] args) {
		InputReader in = new InputReader(System.in);
		PrintWriter w = new PrintWriter(System.out);
		int t = in.nextInt();
		for(int z=1; z<=t; z++) {
			n = in.nextInt();
			g = new HashMap<>();
			for(int i=0; i<n; i++) g.put(i, new ArrayList<Integer>());
			
			for(int i=0; i<n; i++) {
				for(int j=0; j<n; j++) {
					int p = in.nextInt();
					if (p==1) {
						g.get(i).add(j);
					}
				}
			}
			mark = new boolean[n];
			
			dfs(0);
			boolean[] initReachable = new boolean[n];
			for(int i=0; i<n; i++) {
				initReachable[i] = mark[i];
			}
			
			boolean[][] ans = new boolean[n][n];
			
			for(int i=0; i<n; i++) {
				//find all nodes for which i dominates
				//disconect i, then all nodes which were init reachable, but not anymore are the ans for this node
				Arrays.fill(mark, false);
				mark[i] = true;
				if (mark[0]==false) dfs(0);
				for(int j=0; j<n; j++) {
					if (initReachable[j] && !mark[j]) {
						ans[i][j] = true;
					}
				}
			}
			
			w.println("Case "+z+":");
			for(int i=0; i<n; i++) {
				w.print('+');
				for(int k=0; k<2*n-1; k++) {
					w.print('-');
				}
				w.println('+');
				for(int j=0; j<n; j++) {
					if ((initReachable[i] && i==j) || ans[i][j]) {
						w.print("|Y");
					} else {
						w.print("|N");
					}
				}
				w.println("|");
			}
			w.print('+');
			for(int k=0; k<2*n-1; k++) {
				w.print('-');
			}
			if (z==t)
				w.println('+');
			else
				w.println('+');
				
		}
		w.flush();
		
		
	}

	private static void dfs(int u) {
		mark[u] = true;
		
		for(int v: g.get(u)) {
			if (mark[v]) continue;
			dfs(v);
		}
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
