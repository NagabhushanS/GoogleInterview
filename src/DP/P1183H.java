package DP;

import java.util.Arrays;
import java.util.Scanner;

public class P1183H {

	private static int n;
	private static long k;
	private static String s;
	private static long[][][] dp;
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		k = in.nextLong();
		s = in.next();
		dp = new long[101][101][27];
		for(int i=0; i<101; i++) {
			for(int j=0; j<101; j++) {
				Arrays.fill(dp[i][j], -1);
			}
		}
		
		
		long size = k;
		long ans = 0;
		for(int d=0; d<=n; d++) {
			//count number of unique subsequenes
			long c = count(0, d, 26);
//			System.out.println(c+" "+d);
			if (size>=c) {
				size-=c;
				ans += d*c;
			} else {
				ans += d*size;
				size = 0;
			}
			if (size==0) break;
		}
		System.out.println(size>0?-1:ans);
		

	}
	
	private static long count(int i, int toBeDeleted, int last) {
		
		if (toBeDeleted<0) return 0;
		if (i==n) {
			if (toBeDeleted==0) return 1;
			else return 0;
		}
		
		if (dp[i][toBeDeleted][last]!=-1) return dp[i][toBeDeleted][last];
		
		
		long ans = 0;
		
		//either delete or included in subsequence
		if (s.charAt(i)-'a'==last) {
			//include it
			ans = count(i+1, toBeDeleted, s.charAt(i)-'a');
		} else {
			//include or delete
			ans = count(i+1, toBeDeleted, s.charAt(i)-'a')
					+count(i+1, toBeDeleted-1, last);
		}
		return dp[i][toBeDeleted][last]=ans;
	}

}
