package Maths.CombinatoricsAndProbability;

import java.util.Arrays;
import java.util.Scanner;

public class UVA932 {

	private static long[] dp;
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		dp = new long[21];
		Arrays.fill(dp, -1);
		int count=0;
		while(in.hasNext()) {
			int n = in.nextInt();
			System.out.println((count==0?"":"\n")+solve(2*n));
			count++;
		}
	}
	
	private static long solve(int n) {
		if (n%2!=0) return 0;
		if (n==0) return 1;
		
		if (dp[n]!=-1) return dp[n];
		
		long ans=0;
		
		for(int i=2; i<=n; i++) {
			ans += solve(i-2)*solve(n-i);
		}
		
		return dp[n]=ans;
	}

}
