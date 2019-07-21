package DP;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class P864E {

	private class Triplet implements Comparable<Triplet> {
		private int t, d, p, i;
		
		public Triplet(int a, int b, int c, int e) {
			t=a;
			d=b;
			p=c;
			i=e;
		}
		
		public int compareTo(Triplet trip) {
			return d-trip.d;
//			if (p>trip.p) return -1;
//			else if (p<trip.p) return 1;
//			else {
////				if (t>trip.t) return 1;
////				else if (t<trip.t) return -1;
////				else {
////					return d-trip.d;
////				}
//				if (d>trip.d) return 1;
//				else if (d<trip.d) return -1;
//				else {
//					return t-trip.t;
//				}
//			}
		}
	}
	
	private final Triplet[] a;
	private long[][] dp;
	
	
	public P864E(int[] t, int[] d, int[] p) {
		int n = p.length;
		a = new Triplet[n];
		for(int i=0; i<n; i++) {
			a[i] = new Triplet(t[i], d[i], p[i], i);
		}
		Arrays.sort(a);
		
	}
	
	public void solve() {
		int n = a.length;
		dp = new long[n+1][n*20+1];
		for(int i=0; i<=n; i++) {
			for(int j=0; j<dp[0].length; j++) {
				dp[i][j] = -1;
			}
		}
		for(int i=0; i<=n; i++) {
			for(int j=0; j<dp[0].length; j++) {
				dp[i][j] = solve(i, j);
			}
		}
		long ans = solve(0, 0);
		System.out.println(ans);
		
		int i=0;
		int time=0;
		ArrayList<Integer> sol = new ArrayList<>();
		
		while(i<n) {
			if (time+a[i].t<a[i].d && dp[i][time]==a[i].p+dp[i+1][time+a[i].t]) {	
				time+=a[i].t;
				sol.add(a[i].i);
				i++;
			} else {
				i++;
			}
		}
		System.out.println(sol.size());
		for(int x: sol) {
			System.out.print((x+1)+" ");
		}
		System.out.println();
	}
	
	public long solve(int i, int time) {
		//solve i, t represent our subproblem for item i to n given t time is elapsed

		if (i==a.length) {
			return 0;
		}
		
		if (dp[i][time]!=-1) return dp[i][time];
		long ans = 0;
		if (time+a[i].t<a[i].d) {
			ans = Math.max(solve(i+1, time), a[i].p+solve(i+1, time+a[i].t));
		}
		else
			ans = solve(i+1, time);
		return dp[i][time]=ans;
	}
	
	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int[] t, d, p;
		t = new int[n];
		d = new int[n];
		p = new int[n];
		for(int i=0; i<n; i++) {
			t[i] = in.nextInt();
			d[i] = in.nextInt();
			p[i] = in.nextInt();
		}
		P864E sol = new P864E(t, d, p);
		sol.solve();
	}

}
