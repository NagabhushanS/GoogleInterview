package AdvancedDS;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class P982D {

	static class Point implements Comparable<Point>{
		int i;
		int v;
		
		public Point(int a, int b) {
			i=a;
			v=b;
		}
		
		public int compareTo(Point p) {
			return v-p.v;
		}
	}
	
	static int n;
	static Point[] points;
	static int[] id;
	static int[] size;
	static boolean[] mark;
	static int components;
	static HashMap<Integer, Integer> frequency;
	
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		points = new Point[n];
		for(int i=0; i<n; i++) {
			points[i] = new Point(i, in.nextInt());
		}
		
		Arrays.sort(points);
		
		frequency = new HashMap<>();
		
		id = new int[n];
		for(int i=0; i<n; i++) id[i] = i;
		components = n;
		
		size = new int[n];
		Arrays.fill(size, 1);
		
		mark = new boolean[n];
		mark[points[0].i]=true;
		
		for(int i=1; i<n; i++) {
			int index = points[i].i;
			mark[index]=true;
			boolean left=false;
			if (mark[index-1]) {
				int size1 = size[root(index-1)];
				union(index, index-1);
				frequency.put(size1+1, frequency.getOrDefault(size1+1, 0)+1);
				frequency.put(size1, frequency.get(size1)-1);
				if (frequency.get(size1)==0) {
					frequency.remove(size1);
				}
				left=true;
			}
			if (mark[index+1]) {
				int sizeRight = size[root(index+1)];
				int sizeLeft = size[root(index)];
				union(index, index+1);
				frequency.put(sizeLeft+sizeRight, frequency.getOrDefault(sizeLeft+sizeRight, 0)+1);
				frequency.put(sizeRight, frequency.get(sizeRight)-1);
				frequency.put(sizeLeft, frequency.get(sizeLeft)-1);
				if (frequency.get(sizeRight)==0) {
					frequency.remove(sizeRight);
				}
				if (frequency.get(sizeLeft)==0) {
					frequency.remove(sizeLeft);
				}
			}
			
		}

	}
	
	private static void union(int u, int v) {
		if (id[u]!=id[v]) {
			components--;
		} else {
			return;
		}
		int p = root(u);
		int q = root(v);
		id[p] = q;
		size[p]+=size[q];
		
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

}
