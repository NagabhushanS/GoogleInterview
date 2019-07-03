package AdvancedDS;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;

public class P875B {

	private static int n;
	private static int[] p;
	private static int[] id;
	private static int[] size;
	
	public static void main(String[] args) {
		InputReader in = new InputReader(System.in);
		PrintWriter w = new PrintWriter(System.out);
		n = in.nextInt();
		p = new int[n];
		
		id = new int[n];
		size = new int[n];
		for(int i=0; i<n; i++) id[i] = i;
		Arrays.fill(size, 1);
		
		boolean[] mark = new boolean[n];
		w.print(1+" ");
		for(int i=0; i<n; i++) {
			p[i] = in.nextInt()-1;
			mark[p[i]] = true;
			if (p[i]-1>=0 && mark[p[i]-1]) {
				union(p[i], p[i]-1);
			}
			if (p[i]+1<n && mark[p[i]+1]) {
				union(p[i], p[i]+1);
			}
			int sizeOfLast = (mark[n-1]?size[root(n-1)]:0);
			w.print((i+1-sizeOfLast+1)+" ");
		}
		w.flush();
		
		
	}
	
	private static void union(int u, int v) {
		int p = root(u);
		int q = root(v);
		id[p] = q;
		size[q]+=size[p];
		
	}
	
	private static int root(int u) {
		while(u!=id[u]) {
			id[u] = id[id[u]];
			u = id[u];
		}
		return u;
	}
	
	private static boolean connected(int u, int v) {
		return root(u)==root(v);
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
