package Maths.CombinatoricsAndProbability;

import java.util.Scanner;

public class BinomialCoefficient {

	private static int n, k;
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		n = in.nextInt();
		k = in.nextInt();

		long[][] dp = new long[1001][1001];
		
		for(int i=1; i<dp.length; i++) {
			dp[i][i]=1;
			dp[i][0]=1;
		}
		
		for(int N=1; N<dp.length; N++) {
			for(int K=1; K<N; K++) {
				dp[N][K] = dp[N-1][K-1]+dp[N-1][K];
			}
		}
		
		
		System.out.println(dp[n][k]);
	}
	
	
	private static long nck(int n, int k) {
		if (k>n) return 0;
		if (k<0) return 0;
		
		if (n==k) return 1;
		
		long ans=0;
		
		ans += nck(n-1, k-1)+nck(n-1, k);
		
		return ans;
	}

}
