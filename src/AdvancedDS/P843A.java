package AdvancedDS;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map.Entry;
import java.util.TreeSet;

public class P843A {

	static class Point implements Comparable<Point>{
		int i;
		int v;
		
		public Point(int a, int b) {
			i=a;
			v=b;
		}

		@Override
		public int compareTo(Point o) {
			return v-o.v;
		}
	}
	
	static int n;
	static int[] a;
	static Point[] b;
	static int[] id;
	static int[] size;
	static int components;
	
	
	public static void main(String[] args) {
		InputReader in = new InputReader(System.in);
		PrintWriter w = new PrintWriter(System.out);
		n = in.nextInt();
		a = new int[n];
		b = new Point[n];
		for(int i=0; i<n; i++) {
			a[i] = in.nextInt();
			b[i] = new Point(i, a[i]);
		}
		
		id = new int[n];
		size = new int[n];
		components = n;
		Arrays.fill(size, 1);
		for(int i=0; i<n; i++) id[i] = i;
		
		Arrays.sort(b);
		
		for(int i=0; i<n; i++) {
			if (a[i]==b[i].v) continue;
			if (!connected(i, b[i].i)) union(i, b[i].i);
		}

		System.out.println(components);
		
		HashMap<Integer, TreeSet<Integer>> comps = new HashMap<>();
		
		for(int i=0; i<n; i++) {
			if (!comps.containsKey(root(i))) {
				comps.put(root(i), new TreeSet<>());
			} 
			comps.get(root(i)).add(i);
		}
		
		for(Entry<Integer, TreeSet<Integer>> e: comps.entrySet()) {
			w.print(e.getValue().size()+" ");
			for(int x: e.getValue()) {
				w.print((x+1)+" ");
			}
			w.print("\n");
		}
		w.flush();
	}
	
	private static void union(int u, int v) {
		components--;
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
