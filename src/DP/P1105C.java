package DP;

import java.util.Scanner;

public class P1105C {

	static int n, l, r;
	static long zeroR, oneR, twoR;
	static int MOD = (int)1e9+7;
	static long[][] dp = new long[200001][3];
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		l = in.nextInt();
		r = in.nextInt();
		for(int i=0; i<200001; i++) {
			for(int j=0; j<3; j++) {
				dp[i][j]=-1;
			}
		}
		zeroR = findNumbersDivisibleByThree(l, r);
		oneR = findNumbersDivisibleByThree(l-1, r-1);
		twoR = findNumbersDivisibleByThree(l-2, r-2);
		
//		System.out.println(zeroR+" "+oneR+" "+twoR);
		
		System.out.println(solve(n-1, 0));
	}
	
	public static long solve(int i, int remainder) {
		if (i==-1) {
			if (remainder==0) return 1L;
			else return 0;
		}
		
		if (dp[i][remainder]!=-1) return dp[i][remainder];
		
		return dp[i][remainder]=((zeroR*1L*solve(i-1, remainder))%MOD+(oneR*1L*solve(i-1, (remainder+1)%3))%MOD+(twoR*1L*solve(i-1, (remainder+2)%3))%MOD)%MOD;
	}
	
	public static long findNumbersDivisibleByThree(int l, int r) {
		int last = 3*(int)Math.floor((double)r/3);
		int first = 3*(int)Math.ceil((double)l/3);
		return 1+(last-first)/3;
	}

}
