package Graphs;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;

public class P1088E {

	static int n;
	static HashMap<Integer, ArrayList<Integer>> g;
	static int[] w;
	static long[] dp;
	static long ans;
	static int k;
	
	public static void main(String[] args) {
		InputReader in = new InputReader(System.in);
		g = new HashMap<>();
		n = in.nextInt();
		w = new int[n];
		dp = new long[n];
		ans = -(long)1e17;
		k = 0;
		for(int i=0; i<n; i++) {
			w[i] = in.nextInt();
			g.put(i, new ArrayList<>());
		}
		
		for(int i=0; i<n-1; i++) {
			int p = in.nextInt()-1;
			int q = in.nextInt()-1;
			g.get(p).add(q);
			g.get(q).add(p);
		}
		
		dfs(0, -1, false);
		dfs(0, -1, true);
		
		System.out.println(ans*k+" "+k);
	}
	
	public static void dfs(int u, int p, boolean flag) {
		
		dp[u] = w[u];
		for(int v: g.get(u)) {
			if (v==p) continue;
			
			dfs(v, u, flag);
			dp[u] += Math.max(0, dp[v]);
		}
		
		ans = Math.max(ans, dp[u]);
		
		if (flag) {
			if (dp[u]==ans) {
				dp[u]=0;
				k++;
			}
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
