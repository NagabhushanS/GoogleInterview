package DP;

import java.util.Arrays;
import java.util.Scanner;

public class P1081C {

	static Scanner in = new Scanner(System.in);
	
	static int n;
	static int m;
	static int k;
	static final int MOD = 998244353;
	static long[][] dp = new long[2001][2001];
	
	public static void main(String[] args) {
		n = in.nextInt();
		m = in.nextInt();
		k = in.nextInt();
		
		for(int i=0; i<dp.length; i++) {
			Arrays.fill(dp[i], -1);
		}
		//k = n-k; //elements having same color as left element
		System.out.println((m*solve(1, 0))%MOD);
	}
	
	public static long solve(int i, int kDiffDone) {
		if (i==n) {
			if (kDiffDone==k) return 1;
			else return 0;
		}
		
		if (dp[i][kDiffDone]!=-1) return dp[i][kDiffDone];
		
		long ans = 0;
		
		ans = (ans+((m-1)*solve(i+1, kDiffDone+1))%MOD)%MOD;
		ans = (ans+solve(i+1, kDiffDone))%MOD;
		
		return dp[i][kDiffDone]=ans;
	}

}
