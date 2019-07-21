package DP;

import java.util.Arrays;
import java.util.Scanner;

public class UVA11450 {

	private static int m;
	private static int c;
	private static int[][] costs;
	private static long[][] dp;
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int T = in.nextInt();
		dp = new long[21][201];
		while(T-->0) {
			for(int i=0; i<21; i++) {
				Arrays.fill(dp[i], -1);
			}
			m = in.nextInt();
			c = in.nextInt();
			costs = new int[c][];
			for(int i=0; i<c; i++) {
				int k = in.nextInt();
				costs[i] = new int[k];
				for(int j=0; j<k; j++) {
					costs[i][j] = in.nextInt();
				}
			}
			
			long ans = solve(0, m);
			System.out.println(ans<0?"no solution":ans);
			
		}
	}

	private static long solve(int i, int moneyLeft) {
		//ith garment
		if (i==c) {
			return 0;
		}
		
		if (dp[i][moneyLeft]!=-1) return dp[i][moneyLeft];
		
		long ans = -(long)1e15;
		
		for(int j=0; j<costs[i].length; j++) {
			//but brand j of item i
			if (moneyLeft>=costs[i][j])
				ans = Math.max(ans, costs[i][j]+solve(i+1, moneyLeft-costs[i][j]));
		}
		
		return dp[i][moneyLeft]=ans;
	}
}
