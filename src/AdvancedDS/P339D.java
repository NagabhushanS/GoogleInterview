package AdvancedDS;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;

//https://codeforces.com/contest/339/problem/D

public class P339D {

	private static int n, m;
	private static int[] a;
	private static int[] seg;
	private static int size;
	private static int count=0;
	
	public static void main(String[] args) {
		InputReader in = new InputReader(System.in);
		PrintWriter w = new PrintWriter(System.out);
		n = in.nextInt();
		m = in.nextInt();
		a = new int[1<<n];
		for(int i=0; i<a.length; i++) a[i] = in.nextInt();
		
		size = 2*(int)Math.pow(2, n)-1;
		seg = new int[size];
		preprocess(0, 0, a.length-1, 0);
		for(int i=0; i<m; i++) {
			int p = in.nextInt()-1;
			int b = in.nextInt();
			int temp = a[p];
			update(p, b);
			w.println(seg[0]);
		}
		w.flush();

	}
	
	private static void update(int i, int v) {
		seg[size-(1<<n)+i] = v;
		update((size-(1<<n)+i-1)/2, true);
	}
	
	private static void update(int i, boolean flag) {
		if (flag) {
			seg[i] = seg[2*i+1]|seg[2*i+2];
		} else {
			seg[i] = seg[2*i+1]^seg[2*i+2];
		}
		if (i!=0) {
			update((i-1)/2, !flag);
		}
	}
	
	private static void preprocess(int pos, int low, int high, int level) {
		if (low==high) {
			seg[pos] = a[low];
			return;
		}

		
		int mid = (low+high)/2;
		preprocess(2*pos+1, low, mid, level+1);
		preprocess(2*pos+2, mid+1, high, level+1);
		if (level%2==(n+1)%2) {
			//or
			seg[pos] = seg[2*pos+1]|seg[2*pos+2];
		} else {
			//xor
			seg[pos] = seg[2*pos+1]^seg[2*pos+2];
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
