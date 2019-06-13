package Maths.NumberTheory;

import java.util.Arrays;

public class TCDivisorInc {

	static int n, m;
	static long[] dp;
	
	public static void main(String[] args) {
		n = 52704;
		m = 90705;
		
		dp = new long[m+1];
		
		dp[m]=0;
		
		for(int i=m-1; i>=n; i--) {
			long ans=(long)1e9;
			for(int j=2; j*j<=i; j++) {
				if (i%j==0) {
					if (i+j<=m)
						ans=Math.min(ans, 1+dp[i+j]);
					if (i+i/j<=m)
						ans=Math.min(ans, 1+dp[i+i/j]);
				}
			}
			dp[i]=ans;
		}
		
		System.out.println(dp[n]);

	}


}
