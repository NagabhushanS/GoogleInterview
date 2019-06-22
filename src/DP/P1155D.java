package DP;

import java.util.Scanner;

public class P1155D {

	static int n, x;
	static int[] a;
	static long[][][] dp = new long[3*(int)1e5+1][3][3];
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		x = in.nextInt();
		a = new int[n];
				
		for(int i=0; i<n; i++) {
			a[i] = in.nextInt();
		}
		
		
		for(int i=n-1; i>=0; i--) {
			for(int xFlag=0; xFlag<3; xFlag++) {
				for(int pickFlag=0; pickFlag<3; pickFlag++) {
					long ans=0;
					if (pickFlag==2) {
						continue;
					}
					if (pickFlag==1) {
						//either select or end pick
						if (xFlag==0) {
							ans = Math.max(ans, a[i]+dp[i+1][0][1]);
							ans = Math.max(ans, x*1L*a[i]+dp[i+1][1][1]);
						} else if (xFlag==1) {
							ans = Math.max(ans, a[i]+dp[i+1][2][1]);
							ans = Math.max(ans, x*1L*a[i]+dp[i+1][1][1]);
						} else {
							ans = Math.max(ans, a[i]+dp[i+1][2][1]);
						}
					} else {
						//start pick or move forward without picking
						if (xFlag==0) {
							ans = Math.max(ans, dp[i+1][0][0]);
							ans = Math.max(ans, a[i]+dp[i+1][0][1]);
							ans = Math.max(ans, x*1L*a[i]+dp[i+1][1][1]);
						} else if (xFlag==1) {
							ans = Math.max(ans, dp[i+1][1][0]);
							ans = Math.max(ans, x*1L*a[i]+dp[i+1][1][1]);
							ans = Math.max(ans, a[i]+dp[i+1][2][1]);
						} else {
							ans = Math.max(ans, dp[i+1][2][0]);
							ans = Math.max(ans, a[i]+dp[i+1][2][1]);
						}
					}
					dp[i][xFlag][pickFlag]=ans;
				}
			}
		}
		

		System.out.println(dp[0][0][0]);
	}
	
//	public static long solve(int i, int xFlag, int pickFlag) {
//		//xFlag - 0-->not begun, 1-->started, 2-->ended
//		if (i==n) {
//			return 0;
//		}
//		
//		if (dp[i][xFlag][pickFlag]!=-1) {
//			return dp[i][xFlag][pickFlag];
//		}
//		
//		long ans=0;
//		if (pickFlag==2) {
//			return 0;
//		}
//		if (pickFlag==1) {
//			//either select or end pick
//			if (xFlag==0) {
//				ans = Math.max(ans, a[i]+solve(i+1, 0, 1));
//				ans = Math.max(ans, x*1L*a[i]+solve(i+1, 1, 1));
//			} else if (xFlag==1) {
//				ans = Math.max(ans, a[i]+solve(i+1, 2, 1));
//				ans = Math.max(ans, x*1L*a[i]+solve(i+1, 1, 1));
//			} else {
//				ans = Math.max(ans, a[i]+solve(i+1, 2, 1));
//			}
//		} else {
//			//start pick or move forward without picking
//			if (xFlag==0) {
//				ans = Math.max(ans, solve(i+1, 0, 0));
//				ans = Math.max(ans, a[i]+solve(i+1, 0, 1));
//				ans = Math.max(ans, x*1L*a[i]+solve(i+1, 1, 1));
//			} else if (xFlag==1) {
//				ans = Math.max(ans, solve(i+1, 1, 0));
//				ans = Math.max(ans, x*1L*a[i]+solve(i+1, 1, 1));
//				ans = Math.max(ans, a[i]+solve(i+1, 2, 1));
//			} else {
//				ans = Math.max(ans, solve(i+1, 2, 0));
//				ans = Math.max(ans, a[i]+solve(i+1, 2, 1));
//			}
//		}
//		
//		return dp[i][xFlag][pickFlag]=ans;
//	}

}
