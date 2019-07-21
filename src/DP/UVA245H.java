package DP;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;

public class UVA245H {

	private static String s;
	private static int q;
	private static int[][] dp1 = new int[5001][5001];
	private static int[][] dp2 = new int[5001][5001];
	
	public static void main(String[] args) {
		InputReader in = new InputReader(System.in);
		PrintWriter w = new PrintWriter(System.out);
		s = in.readString();
		q = in.nextInt();
		int n = s.length();
		
//		dp1 = new int[5001][5001];;
//		dp2 = new int[5001][5001];;
		
		for(int i=0; i<n; i++) {
			Arrays.fill(dp1[i], -1);
			Arrays.fill(dp2[i], -1);
		}
		
		for(int i=0; i<n; i++) {
			for(int j=i; j<n; j++) {
				isPalindrome(i, j);
			}
		}
		
		for(int i=0; i<q; i++) {
			int l = in.nextInt()-1;
			int r = in.nextInt()-1;
			w.println(count(l, r));
		}
		
		w.flush();
	}
	
	private static int count(int i, int j) {
		if (i>j) return 0;
		if (i==j) {
			return 1;
		}
		if (dp2[i][j]!=-1) return dp2[i][j];
		
		//returns palindromes in substring (i, j)
		return dp2[i][j]=dp1[i][j]+count(i+1, j)+count(i, j-1)-count(i+1, j-1);
	}
	
	
	private static int isPalindrome(int i, int j) {
		if (i==j) {
			return dp1[i][j]=1;
		} else if (j==i+1) {
			return dp1[i][j]=s.charAt(i)==s.charAt(j)?1:0;
		}
		
		if (dp1[i][j]!=-1) return dp1[i][j];
		
		int ans=0;
		if (s.charAt(i)!=s.charAt(j)) {
			ans = 0;
		} else {
			ans = isPalindrome(i+1, j-1);
		}
		return dp1[i][j] = ans;
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
