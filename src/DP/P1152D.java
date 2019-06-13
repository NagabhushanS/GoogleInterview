package DP;

import java.util.Scanner;

public class P1152D {

	static int n;
	static int MOD = (int)1e9+7;
	static long[][][] dp1 = new long[2001][2001][2];
	static int[][] dp2 = new int[2001][2001];
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		for(int i=0; i<2001; i++) {
			for(int j=0; j<2001; j++) {
				dp2[i][j]=-1;
				for(int k=0; k<2; k++) {
					dp1[i][j][k]=-1;
				}
			}
		}
		System.out.println(solve(0, 0, 0));
	}
	
	public static long solve(int i, int unMatched, int last) {
		if (i==2*n) {
			return 0;
		}
		if (dp1[i][unMatched][last]!=-1) return dp1[i][unMatched][last];
		long ans=0;
		long s1, s2, s3, s4;
		s1=s2=s3=s4=0;
		if (possible(i+1, unMatched+1)==1) {
			s1 = solve(i+1, unMatched+1, 0);
			s2 = solve(i+1, unMatched+1, 1)+1;
		} 
		if (possible(i+1, unMatched-1)==1){
			s3 = solve(i+1, unMatched-1, 0);
			s4 = solve(i+1, unMatched-1, 1)+1;
		}
		
		
		if (last==1) {
			ans = Math.max(ans, (s1+s3)%MOD);
		} else {
			ans = Math.max(ans, (s1+s3)%MOD);
			ans = Math.max(ans, (s1+s4)%MOD);
			ans = Math.max(ans, (s3+s2)%MOD);
		}
		
		return dp1[i][unMatched][last]=ans;
	}
	
	public static int possible(int i, int unMatched) {
		if (unMatched<0) {
			return 0;
		}
		if (2*n-i>=unMatched) {
			return 1;
		} else {
			return 0;
		}
//		if (unMatched<0) return 0;
//		if (i==2*n) {
//			if (unMatched==0) {
//				return 1;
//			} else {
//				return 0;
//			}
//		}
//		
//		if (dp2[i][unMatched]!=-1) return dp2[i][unMatched];
//		
//		int p = possible(i+1, unMatched+1)+possible(i+1, unMatched-1);
//		return dp2[i][unMatched]=(p>0?1:0);
	}

}
