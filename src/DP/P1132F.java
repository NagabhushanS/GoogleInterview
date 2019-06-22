package DP;

import java.util.Arrays;
import java.util.Scanner;

public class P1132F {

	static int n;
	static int[] a;
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		String s = in.next();
		a = new int[n];
		for(int i=0; i<n; i++) {
			a[i] = s.charAt(i)-'a';
		}
		
		System.out.println(solve());
		
	}
	
	public static long solve() {
		
		long[][] dp = new long[501][501];
		
		for(int s=0; s<a.length; s++) {
			dp[s][s]=1;
		}
		
		
		for(int diff=1; diff<a.length; diff++) {
			for(int s=0; s<a.length; s++) {
				int e = s+diff;
				if (e>=a.length) continue;
				long ans=(long)1e15;
				for(int i=s+1; i<=e; i++) {
					if (a[i]==a[s]) {
						ans = Math.min(ans, dp[s+1][i-1]+dp[i][e]);
					}
				}
				ans = Math.min(ans, 1+dp[s+1][e]);
				dp[s][e] = ans;
			}
		}
		
		return dp[0][a.length-1];
	}
	
}
