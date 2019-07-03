package DP;

import java.util.Arrays;
import java.util.Scanner;

public class P466D {

	static Scanner in = new Scanner(System.in);
	static int n, h;
	static int[] a;
	static final int MOD = (int)1e9+7;
	static long[][] dp;
	
	public static void main(String[] args) {
		n = in.nextInt();
		h = in.nextInt();
		a = new int[n];
		dp = new long[2001][2001];
		
		for(int i=0; i<dp.length; i++) {
			Arrays.fill(dp[i], -1);
		}
		for(int i=0; i<n; i++) {
			a[i] = in.nextInt();
		}
		
		if (a[0]==h) {
			System.out.println(solve(1, 0));
		} else if (a[0]==h-1){
			System.out.println((solve(1, 0)+solve(1, 1))%MOD);
		} else {
			System.out.println(0);
		}

	}
	
	public static long solve(int i, int g) {
//		assert(g>=0);
		//close a group after selecting one element
		if (i==n) {
			if (g==0) return 1;
			else return 0;
		}
		
		if (dp[i][g]!=-1) return dp[i][g];
		
		long ans=0;

		//end here
		if (h-a[i]==g && g-1>=0) {
			ans+=g*solve(i+1, g-1);
		}
		
		//end here and start and continue
		if (h-a[i]==g+1) {
			ans+=g*solve(i+1, g);
		}
		//continue
		if (h-a[i]==g) {
			ans+=solve(i+1, g);
		}
		//continue and start and continue
		if (h-a[i]==g+1) {
			ans+=solve(i+1, g+1);
		}
		//continue and start and end
		if (h-a[i]==g+1) {
			ans+=solve(i+1, g);
		}
		return dp[i][g]=ans%MOD;
	}

}
