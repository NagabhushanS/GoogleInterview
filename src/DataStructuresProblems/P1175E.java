package DataStructuresProblems;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;

public class P1175E {

	private static int n, q;
	private static int[] seg;
	private static int[][] a;
	private static int MAX = (int)5e5+100;
	private static int[] map;
	
	public static void main(String[] args) {
		InputReader in = new InputReader(System.in);
		PrintWriter w = new PrintWriter(System.out);
		n = in.nextInt();
		q = in.nextInt();
		a = new int[n][2];
		int size = 2*(int)Math.pow(2, Math.ceil(Math.log10(MAX)/Math.log10(2)))-1;
		seg = new int[size];
		for(int i=0; i<n; i++) {
			int x = in.nextInt();
			int y = in.nextInt();
			a[i] = new int[] {x, y};
			update(x, y, 0, MAX-1, 0);
		}
		
		int[] next = new int[MAX];
		Arrays.fill(next, -1);
		for(int i=0; i<MAX; i++) {
			int nextPointer = get(0, i, 0, MAX-1, 0);
			if (nextPointer>=i) {
				next[i]=nextPointer;
			}
		}
		
		int m = (int)Math.ceil(Math.log10(MAX)/Math.log10(2));
		int[][] P = new int[MAX][m];
		for(int j=0; j<m; j++) {
			for(int i=0; i<MAX; i++) {
				if (j==0) P[i][0]=next[i];
				else {
					if (P[i][j-1]!=-1)
						P[i][j]=P[P[i][j-1]][j-1];
				}
			}
		}
		
		nextQuery:
		for(int i=0; i<q; i++) {
			int l = in.nextInt();
			int r = in.nextInt();
			long ans=0;
			for(int j=m-1; j>=0; j--) {
				if (P[l][j]<r) {
					l=P[l][j];
					ans+=(1<<j);
				}
			}
			if (P[l][0]>=r)
				w.println(ans+1);
			else
				w.println(-1);
			
		}
		w.flush();

	}
	
	private static void update(int index, int value, int low, int high, int pos) {
		if (low==high) {
			seg[pos] = Math.max(seg[pos], value);
			return;
		}
		
		int mid = (low+high)/2;
		if (index<=mid) {
			update(index, value, low, mid, 2*pos+1);
		} else {
			update(index, value, mid+1, high, 2*pos+2);
		}
		seg[pos] = Math.max(seg[2*pos+1], seg[2*pos+2]);
	}
	
	private static int get(int qlow, int qhigh, int low, int high, int pos) {
		if (qhigh<low || high<qlow) {
			return 0;
		} else if (qhigh>=high && qlow<=low) {
			return seg[pos];
		} else {
			int mid = (low+high)/2;
			return Math.max(get(qlow, qhigh, low, mid, 2*pos+1), get(qlow, qhigh, mid+1, high, 2*pos+2));
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
