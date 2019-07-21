package DP;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;

public class UVA11951 {

	private static int n, m;
	private static long k;
//	private static long[][] a;
	private static long[][] preSum;  //preSum[i][j] gives the sum from (0, 0) to (i, j)
	
	public static void main(String[] args) {
		InputReader in = new InputReader(System.in);
		PrintWriter w = new PrintWriter(System.out);
		int T = in.nextInt();
		preSum = new long[101][101];
		for(int z=1; z<=T; z++) {
			n = in.nextInt();
			m = in.nextInt();
			k = in.nextLong();
			for(int i=0; i<n; i++) {
				for(int j=0; j<m; j++) {
					preSum[i][j] = in.nextInt();
				}
			}
			
			for(int i=0; i<n; i++) {
				for(int j=0; j<m; j++) {
					preSum[i][j] = (i-1>=0?preSum[i-1][j]:0)+(j-1>=0?preSum[i][j-1]:0)-(i-1>=0 && j-1>=0?preSum[i-1][j-1]:0)+preSum[i][j];
					
				}
			}
			
			long maxArea=0;
			long leastCost=0;
			
			for(int i=0; i<Math.min(n, n-maxArea/m+1); i++) {
				for(int j=0; j<Math.min(m, m-maxArea/n+1); j++) {
					boolean continueFlag=false;
					int nmax=n;
					int mmax=m;
					next:
					for(int x=i; x<nmax; x++) {
						for(int y=j; y<mmax; y++) {
							//calculate area of rect from (i, j) to (x, y)
							long cost = preSum[x][y]-(i-1>=0?preSum[i-1][y]:0)-(j-1>=0?preSum[x][j-1]:0)+(i-1>=0 && j-1>=0?preSum[i-1][j-1]:0);
							if (cost<=k && (x-i+1)*1L*(y-j+1)>=maxArea) {
								if (maxArea==(x-i+1)*1L*(y-j+1)) {
									leastCost = Math.min(cost, leastCost);
									continue;
								}
								maxArea = (x-i+1)*1L*(y-j+1);
								leastCost = cost;
							}
							if (cost>k) {
								continue next;
							}
						}
					}
				}
			}
			
			w.println("Case #"+z+": "+maxArea+" "+leastCost);
			
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
