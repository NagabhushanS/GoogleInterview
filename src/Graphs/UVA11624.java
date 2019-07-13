package Graphs;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Queue;

public class UVA11624 {

	private static class Node {
		private int x, y;
		private int dist;
		
		public Node(int a, int b, int c) {
			x=a;
			y=b;
			dist=c;
		}
	}
	
	private static int R, C;
	private static char[][] g;
	private static int[][] fireTime;
	private static boolean[][] mark;
	private static int[] dx = {1, -1, 0, 0};
	private static int[] dy = {0, 0, 1, -1};
	
	
	public static void main(String[] args) {
		InputReader in = new InputReader(System.in);
		PrintWriter w = new PrintWriter(System.out);
		int t = in.nextInt();
		for(int z=1; z<=t; z++) {
			R = in.nextInt();
			C = in.nextInt();
			int jx=0, jy=0;
			int fx=0, fy=0;
			g = new char[R][C];
			for(int i=0; i<R; i++) {
				char[] row = in.readString().toCharArray();
				for(int j=0; j<C; j++) {
					g[i][j] = row[j];
					if (g[i][j]=='J') {
						jx=i;
						jy=j;
					}
					if (g[i][j]=='F') {
						fx=i;
						fy=j;
					}
				}
			}
			fireTime = new int[R][C];
			for(int i=0; i<R; i++) {
				for(int j=0; j<C; j++) {
					fireTime[i][j] = 100;
				}
			}
			
		
			mark = new boolean[R][C];
			
			fireTime[fx][fy] = 0;
			Node start = new Node(fx, fy, 0);
			Queue<Node> queue = new LinkedList<>();
			queue.add(start);
			
			mark[fx][fy] = true;
			
			while(!queue.isEmpty()) {
				Node node = queue.poll();
				
				for(int k=0; k<4; k++) {
					int xn = node.x+dx[k];
					int yn = node.y+dy[k];
					
					if (xn<0 || xn>=R || yn<0 || yn>=C) continue;
					if (mark[xn][yn] || g[xn][yn]=='#') continue;
					mark[xn][yn]=true;
					fireTime[xn][yn] = node.dist+1;
					queue.add(new Node(xn, yn, node.dist+1));
				}
			}
			
			
			
			start = new Node(jx, jy, 0);
			mark = new boolean[R][C];
			queue.add(start);
			
			mark[jx][jy] = true;
			boolean flag=false;
			while(!queue.isEmpty()) {
				Node node = queue.poll();
				
				if (node.x==0 || node.x==R-1 || node.y==0 || node.y==C-1) {
					w.println((node.dist+1)+"");
					flag=true;
					break;
				}
				
				for(int k=0; k<4; k++) {
					int xn = node.x+dx[k];
					int yn = node.y+dy[k];
					if (xn<0 || xn>=R || yn<0 || yn>=C) continue;
					if (mark[xn][yn] || g[xn][yn]=='#') continue;
					if (node.dist+1>=fireTime[xn][yn]) continue;
					mark[xn][yn] = true;
					queue.add(new Node(xn, yn, node.dist+1));
				}
			}
			if (!flag) {
				w.println("IMPOSSIBLE");
			}
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
