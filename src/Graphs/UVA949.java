package Graphs;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class UVA949 {
	
	private static class Node {
		private int x, y, t;
		
		public Node(int a, int b, int c) {
			x=a;
			y=b;
			t=c;
		}
		
		public int hashCode() {
			return 31+31*x+31*31*y+31*31*31*t;
		}
		
		public boolean equals(Object o) {
			Node node = (Node)o;
			return x==node.x && y==node.y && t==node.t;
		}
		
	}
	
	private static class Rest {
		private int x1, y1, x2, y2;
		
		public Rest(int a, int b, int c, int d) {
			x1=a;
			y1=b;
			x2=c;
			y2=d;
		}
		
		public int hashCode() {
			return 31+31*x1+31*31*y1+31*31*31*x2+31*31*31*31*y2;
		}
		
		@Override
		public boolean equals(Object o) {
			Rest rest = (Rest)o;
			return x1==rest.x1 && x2==rest.x2 && y1==rest.y1 && y2==rest.y2;
		}
		
	}
	
	private static int R, C;
	private static int rest;
	private static int mon;
	private static HashSet<Rest> rests;
	private static int[][] monitor;
	private static HashSet<Node> mark;	
	private static int[] dx = {1, -1, 0, 0};
	private static int[] dy = {0, 0, -1, 1};
	
	public static void main(String[] args) {
		InputReader in = new InputReader(System.in);
		while(true) {
			try {
				R = in.nextInt();
			} catch(Exception e) {
				break;
			}
			C = in.nextInt();
			rest = in.nextInt();
			rests = new HashSet<>();
			for(int i=0; i<rest; i++) {
				int x1 = in.nextInt();
				int y1 = in.nextInt();
				int x2 = in.nextInt();
				int y2 = in.nextInt();
				Rest rr = new Rest(x1, y1, x2, y2);
				rests.add(rr);
			}
			mon = in.nextInt();
			monitor = new int[501][2];
			for(int i=0; i<mon; i++) {
				int t = in.nextInt();
				int x = in.nextInt();
				int y = in.nextInt();
				monitor[t]=new int[] {x, y};
			}
			mark = new HashSet<>();
			Node start = new Node(0, 0, 0);
			mark.add(start);
			
			Queue<Node> queue = new LinkedList<>();
			queue.add(start);
			
			while(!queue.isEmpty()) {
				Node node = queue.poll();
				
				if (node.x==R-1 && node.y==C-1) {
					System.out.println(node.t);
					break;
				}
				
				for(int k=0; k<4; k++) {
					int xn = node.x+dx[k];
					int yn = node.y+dy[k];
					if (xn<0 || xn>=R || yn<0 || yn>=C) continue;
					if (node.t+1<501 && monitor[node.t+1][0]==xn && monitor[node.t+1][1]==yn) continue;
					Node newNode = new Node(xn, yn, node.t+1);
					Rest rr = new Rest(node.x, node.y, xn, yn);
					if (rests.contains(rr)) continue;
					if (mark.contains(newNode)) continue;
					mark.add(newNode);
					queue.add(newNode);
				}
				Node wait = new Node(node.x, node.y, node.t+1);
				if (!mark.contains(wait)) {
					mark.add(wait);
					queue.add(wait);
				}
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
