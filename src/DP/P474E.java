package DP;

import java.util.Arrays;
import java.util.Scanner;
import java.util.TreeMap;

public class P474E {

	private static int n, d;
	private static int[] h;
	private static int[] seg;
	private static final int MAX = (int)1e6;
	private static int size;
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		d = in.nextInt();
		h = new int[n];
		for(int i=0; i<n; i++) {
			h[i] = in.nextInt();
		}
		
		size = 2*(int)Math.pow(2, (int)Math.ceil(Math.log10(MAX)/Math.log10(2)))-1;
		seg = new int[size];
		
		int[] dp = new int[n+1];
		dp[n] = 0;
		
		long ans=0;
		Compressor compressor = new Compressor(h);
		for(int i=n-1; i>=0; i--) {
			int r = h[i]+d;
			int l = h[i]-d;
			int left = (l>=0?query(0, l, 0, MAX-1, 0):0);
			int right = query(r, MAX-1, 0, MAX-1, 0);
			dp[i] = 1+Math.max(left, right);
			update(h[i], dp[i], 0, MAX-1, 0);
			ans = Math.max(ans, dp[i]);
		}
		System.out.println(ans);
		
	}
	
	private static void update(int index, int v, int low, int high, int pos) {
		if (low==high) {
			seg[pos] = Math.max(v, seg[pos]);
			return;
		}
		int mid = (low+high)/2;
		if (index<=mid) {
			update(index, v, low, mid, 2*pos+1);
		} else {
			update(index, v, mid+1, high, 2*pos+2);
		}
		seg[pos] = Math.max(seg[2*pos+1], seg[2*pos+2]);
	}
	
	private static int query(int qlow, int qhigh, int low, int high, int pos) {
		if (qhigh<low || high<qlow) {
			return -(int)1e9;
		} else if (qlow<=low && qhigh>=high) {
			return seg[pos];
		} else {
			int mid = (low+high)/2;
			int left = query(qlow, qhigh, low, mid, 2*pos+1);
			int right = query(qlow, qhigh, mid+1, high, 2*pos+2);
			return Math.max(left, right);
		}
	}
	
	private static class Compressor {
		
		private int[] a;
		private int count;
		private TreeMap<Integer, Integer> map;
		
		public Compressor(int[] b) {
			a = Arrays.copyOf(b, b.length);
			Arrays.sort(a);
			map = new TreeMap<>();
			count=0;
			for(int i=0; i<n; i++) {
				if (!map.containsKey(a[i])) {
					map.put(a[i], count++);
				}
			}
		}
		
		public int getLower(int x) {
			Integer key = map.floorKey(x);
			if (key==null) return -1;
			return map.get(key);
		}
		
		public int getHigher(int x) {
			Integer key = map.ceilingKey(x);
			if (key==null) return -1;
			return map.get(key);
		}
	}
}
