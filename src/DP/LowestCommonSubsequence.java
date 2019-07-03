package DP;

import java.util.Scanner;

public class LowestCommonSubsequence {

	static Scanner in = new Scanner(System.in);
	static String s, t;
	static int[][] dp;
	
	public static void main(String[] args) {
		s = in.next();
		t = in.next();
		int n = s.length();
		int m = t.length();
		
		dp = new int[s.length()+1][t.length()+1];
		solve();
	}
	
	public static void solve() {
		int[][] dp = new int[s.length()+1][t.length()+1];
		
		int n = s.length();
		int m = t.length();
		
		for(int i=n-1; i>=0; i--) {
			for(int j=m-1; j>=0; j--) {
				int ans=0;
				if (s.charAt(i)==t.charAt(j)) {
					ans = 1+dp[i+1][j+1];
				} else {
					ans = Math.max(dp[i+1][j], dp[i][j+1]);
				}
				dp[i][j] = ans;
			}
		}
		System.out.println(dp[0][0]);
		
		int i=0;
		int j=0;
		
		while(true) {
			if (i==s.length() || j==t.length()) break;
			if (dp[i][j]==1+dp[i+1][j+1]) {
				System.out.print(s.charAt(i));
				i++;
				j++;
			} else if (dp[i][j]==dp[i+1][j]) {
				i++;
			} else if (dp[i][j]==dp[i][j+1]) {
				j++;
			} else {
				//we dont reach here
			}
		}
		System.out.println();
		
	}


}
